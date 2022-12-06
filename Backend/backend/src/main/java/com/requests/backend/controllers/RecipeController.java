package com.requests.backend.controllers;

import com.requests.backend.models.*;
import com.requests.backend.models.requests.RecipeAddRequest;
import com.requests.backend.models.responses.AddRecipeResponse;
import com.requests.backend.models.responses.RecipeListResponse;
import com.requests.backend.models.responses.RecipeResponse;
import com.requests.backend.models.responses.ResultResponse;
import com.requests.backend.repositories.*;
import com.util.security.Hasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static com.util.Constants.*;
import static com.util.Constants.UserType.USER_DEV;
import static com.util.Constants.UserType.USER_REG;

/**
 * This class is responsible for handling all requests related to recipes.
 * @author Joe
 * @author Mitch
 * @author Logan
 */
@RestController
@RequestMapping(path="/recipe")
public class RecipeController {

    public static final int RESULT_ERROR_RID_TAKEN = -23;

    public static final int RESULT_OK = 0;

    public static final int RESULT_RECIPE_CREATED = 22;


    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private NotificationsWebsocket notificationsWebsocket;

    @Autowired
    private InstructionRepository instructionRepository;
    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private SimpleFoodRepository simpleFoodRepository;

    /**
     * Get a list of all recipes from the database.
     * @return A list of all recipes.
     */
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    /**
     * Query a set of recipes based on recipe name.
     * @param rname The name of the recipe to query.
     * @return A list of recipes with the given name.
     */
    @GetMapping(path="/getRecipeByRname/{rname}")
    public @ResponseBody RecipeListResponse getRecipeByRname(@PathVariable String rname) {
        Recipe[] recipes = recipeRepository.queryGetRecipeByRname(rname);

        RecipeListResponse res = new RecipeListResponse();

        res.setRecipes(recipes);
        res.setResult(RESULT_OK);

        return res;
    }

    /**
     * Get a recipe by its recipe ID.
     * @param rid The recipe ID of the recipe to get.
     * @return A JSON response containing the recipe with the given recipe ID.
     */
    @GetMapping(path="/getRecipeByRid/{rid}")
    public @ResponseBody RecipeResponse getRecipeByRid(@PathVariable int rid) {
        Recipe[] recipe = recipeRepository.queryGetRecipeByRid(rid);

        RecipeResponse res = new RecipeResponse();

        if (recipe.length == 0) {
            res.setResult(RESULT_ERROR);
        }
        else {
            res.setRecipes(recipe);
            res.setResult(RESULT_OK);
        }

        return res;
    }

    /**
     * Add a recipe to the database given recipe information.
     * @param token The token of the user adding the recipe.
     * @param req Recipe Add Request containing the new recipe information
     * @return A JSON response containing the result of the operation.
     */
    @PostMapping(path="/add/{token}")
    @ResponseBody
    public AddRecipeResponse addNewRecipe(@PathVariable String token, @RequestBody RecipeAddRequest req) throws IOException {
        String hashedToken = Hasher.sha256(token);
        Token[] tokenQueryRes = tokenRepository.queryGetToken(hashedToken);

        AddRecipeResponse res = new AddRecipeResponse();

        if (tokenQueryRes.length == 0) {
            res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
        }
        else {
            User user = tokenQueryRes[0].getUser();

//            try {
                Recipe recipe = new Recipe();
                recipe.setUser(user);
                recipe.setRname(req.getRecipeName());
                Ingredient[] ingredients = req.getIngredients();
                Instruction[] instructions = req.getInstructions();
                recipe.setIngredients(List.of(ingredients));
                recipe.setInstructions(List.of(instructions));
                recipe.setDescription(req.getDescription());
                recipe = recipeRepository.save(recipe);
                res.setResult(RESULT_RECIPE_CREATED);
                res.setRid(recipe.getRid());

                Notification notification = new Notification();

                // Send out a notification to all followers that a new recipe has been added.
                notification.setFromUsername(user.getUsername());
                notification.setRid(recipe.getRid());
                notification.setType(Notification.NotificationType.RECIPE);

                notificationsWebsocket.sendPostNotifications(notification);

//            } catch (Exception e) {
//                res.setResult(RESULT_ERROR);
//            }
        }

        return res;
    }

    @PatchMapping(path = "/update/{token}/{rid}")
    public @ResponseBody ResultResponse updateRecipe(@PathVariable String token, @PathVariable int rid, @RequestBody RecipeAddRequest req) {
        return UserController.getUserFromToken(token, (user, res) -> {
            Recipe recipe = recipeRepository.queryGetRecipeByRid(rid)[0];
            User other = recipe.getUser();

            LOGGER.info(token + " " + user.getUsername() + " " + req);
            if (user.getUsername().equals(other.getUsername()) ||
                    user.getUserType() == USER_DEV ||
                    (user.getUserType() > USER_REG && user.getUserType() > other.getUserType())) {
                ingredientRepository.queryDeleteRecipe(recipe.getRid());
                for (Ingredient ingredient : req.getIngredients()) {
                    ingredientRepository.queryCreateIngredient(recipe.getRid(), ingredient.getFood().getId(),
                            ingredient.getFood().isCustom(), ingredient.getQuantity(), ingredient.getUnit());
                }
                instructionRepository.queryDeleteRecipe(recipe.getRid());
                for (Instruction instruction : req.getInstructions()) {
                    instructionRepository.queryCreateInstruction(recipe.getRid(), instruction.getStepNum(),
                            instruction.getStepText());
                }
                recipeRepository.queryUpdateRecipe(recipe.getRid(),
                        req.getDescription(), req.getRecipeName());
//                recipe.setRname(req.getRecipeName());
//                Ingredient[] ingredients = req.getIngredients();
//                Instruction[] instructions = req.getInstructions();
//                recipe.setIngredients(Arrays.asList(ingredients));
//                recipe.setInstructions(Arrays.asList(instructions));
//                recipe.setDescription(req.getDescription());
//                recipeRepository.save(recipe);
                res.setResult(RESULT_OK);
            } else {
                res.setResult(RESULT_ERROR);
            }
        }, tokenRepository);
    }

    /**
     * Add a picture associated with a recipe based on its recipe ID.
     * @param token The token of the user adding the picture.
     * @param rid The recipe ID of the recipe to add the picture to.
     * @param file The picture file to add.
     * @return A JSON response containing the result of the operation.
     */
    @PostMapping(path="/addPicture/{rid}/{token}")
    @ResponseBody
    public ResultResponse addRecipePicture(@PathVariable String token, @PathVariable String rid, MultipartFile file) {
        return UserController.getUserFromToken(token, (user, res) -> {
            Recipe[] recipes = recipeRepository.queryGetRecipeByRid(Integer.parseInt(rid));

            try {
                User other = recipes[0].getUser();
                if (other.getUsername().equals(user.getUsername()) ||
                        user.getUserType() == USER_DEV ||
                        (user.getUserType() > USER_REG && user.getUserType() > other.getUserType())) {
                    String filename = Hasher.sha256plaintext(rid) + ".webp";
                    File file1 = new File(RECIPE_SOURCE, filename);
                    try (FileOutputStream outputStream = new FileOutputStream(file1)) {
                        outputStream.write(file.getBytes());
                        res.setResult(RESULT_OK);
                    } catch (IOException e) {
                        e.printStackTrace();
                        res.setResult(RESULT_ERROR);
                    }
                }
            } catch (Exception e) {
                res.setResult(RESULT_ERROR);
            }
        }, tokenRepository);
    }

    /**
     * Get the picture associated with a recipe as a byte array based on its recipe ID.
     * @param rid The recipe ID of the recipe to get the picture of.
     * @return A JSON response containing the result of the operation and the picture as a byte array.
     * @throws IOException Thrown if there is an error reading the file
     */
    @GetMapping(path="/getPicture/{rid}", produces="image/webp")
    public @ResponseBody byte[] getRecipePicture(@PathVariable String rid) throws IOException {
        File img = new File(RECIPE_SOURCE, Hasher.sha256plaintext(rid) + ".webp");
        if (!img.exists()) {
            img = new File(DEFAULT_BANNER);
        }
        InputStream in = new DataInputStream(new FileInputStream(img));
        return in.readAllBytes();
    }

    /**
     * Remove a recipe from the database based on its recipe ID.
     * @param rid The recipe ID of the recipe to remove.
     * @param token The token of the user removing the recipe.
     * @return A JSON response containing the result of the operation.
     */
    @DeleteMapping(path="/remove/{token}/{rid}")
    @ResponseBody
    public ResultResponse removeRecipe(@PathVariable int rid, @PathVariable String token) {
        return UserController.getUserFromToken(token, (user, res) -> {
            Recipe[] recipe = recipeRepository.queryGetRecipeByRid(rid);
            User other = recipe[0].getUser();
            LOGGER.info(token + " " + user.getUsername() + " " + recipe[0]);

            if (user.getUsername().equals(other.getUsername()) ||
                    user.getUserType() == USER_DEV ||
                    (user.getUserType() > USER_REG && user.getUserType() > other.getUserType())) {
                recipeRepository.delete(recipe[0]);
                res.setResult(RESULT_OK);
            } else {
                res.setResult(RESULT_ERROR);
            }
        }, tokenRepository);
    }

    /**
     * Get a list of recipes based on a given Username
     * @param Username The token of the user to get the recipes of.
     * @return A JSON response containing the result of the operation and the list of recipes.
     */
    @GetMapping(path="/userRecipeList/{Username}")
    @ResponseBody
    public RecipeResponse userRecipeList(@PathVariable String Username) {
        Recipe[] recipe = recipeRepository.queryUserRecipeList(Username);
        RecipeResponse res = new RecipeResponse();

        if(recipe.length == 0) {
            res.setResult(RESULT_ERROR);
        } else {
            res.setRecipes(recipe);
            res.setResult(RESULT_OK);
        }

        return res;
    }
}
