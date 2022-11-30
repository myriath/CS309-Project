package com.requests.backend.controllers;

import com.requests.backend.models.*;
import com.requests.backend.models.responses.AddRecipeResponse;
import com.requests.backend.models.responses.RecipeListResponse;
import com.requests.backend.models.responses.RecipeResponse;
import com.requests.backend.models.responses.ResultResponse;
import com.requests.backend.repositories.IngredientRepository;
import com.requests.backend.repositories.InstructionRepository;
import com.requests.backend.repositories.RecipeRepository;
import com.requests.backend.repositories.TokenRepository;
import com.util.security.Hasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

import static com.util.Constants.*;

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
    private InstructionRepository instructionRepository;
    @Autowired
    private IngredientRepository ingredientRepository;

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

        if (recipes.length == 0) {
            res.setResult(RESULT_ERROR);
        }
        else {
            res.setRecipes(recipes);
            res.setResult(RESULT_OK);
        }

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
            res.setRecipe(recipe);
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
    public AddRecipeResponse addNewRecipe(@PathVariable String token, @RequestBody RecipeAddRequest req) {
        String hashedToken = Hasher.sha256(token);
        Token[] tokenQueryRes = tokenRepository.queryGetToken(hashedToken);

        String recipeName = req.getRecipeName();
        Instruction[] instructions = req.getInstructions();

        AddRecipeResponse res = new AddRecipeResponse();

        LOGGER.info(req.toString());


        if (tokenQueryRes.length == 0) {
            res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
        }
        else {
            String username = tokenQueryRes[0].getUsername();

//            try {
                Recipe recipe = new Recipe();
                recipe.setUsername(username);
                recipe.setRname(req.getRecipeName());
                recipe.setDescription(req.getDescription());
//                recipe.setIngredients(List.of(req.getIngredients()));
//                recipe.setInstructions(List.of(req.getInstructions()));
                recipe = recipeRepository.save(recipe);
                for (Instruction instruction : instructions) {
                    instructionRepository.queryCreateInstruction(recipe.getRid(), instruction.getStepNum(), instruction.getStepText());
                }
                for (Ingredient ingredient : req.getIngredients()) {
                    ingredientRepository.queryCreateIngredient(recipe.getRid(), ingredient.getItemId(), ingredient.isCustom(), ingredient.getQuantity(), ingredient.getUnit());
                }
                res.setResult(RESULT_RECIPE_CREATED);
                res.setRid(recipe.getRid());
//            } catch (Exception e) {
//                res.setResult(RESULT_ERROR_RID_TAKEN);
//            }
        }

        return res;
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
        return UserController.getUsernameFromToken(token, (username, res) -> {
            Recipe[] recipes = recipeRepository.queryGetRecipeByRid(Integer.parseInt(rid));
            if (recipes.length == 0 || !recipes[0].getUsername().equals(username)) {
                res.setResult(RESULT_ERROR);
            } else {
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
    public ResultResponse removeRecipe(@PathVariable  int rid, @PathVariable  String token) {
        String hashedToken = Hasher.sha256(token);
        Token[] tokens = recipeRepository.queryRecipeDeleteCheck(hashedToken);
        ResultResponse res = new ResultResponse();
        Recipe[] recipe = recipeRepository.queryGetRecipeByRid(rid);

        if (tokens.length == 0) {
            res.setResult(RESULT_ERROR);
        } else if(tokens[0].getUsername().equals(recipe[0].getUsername())){
            recipeRepository.queryDeleteRecipe(rid);
            res.setResult(RESULT_OK);
        }

        return res;
    }

    /**
     * Get a list of recipes based on a given Username
     * @param Username The token of the user to get the recipes of.
     * @return A JSON response containing the result of the operation and the list of recipes.
     */
    @GetMapping(path="/userRecipeList/{Username}")
    @ResponseBody
    public Recipe[] userRecipeList(@PathVariable String Username) {
        Recipe[] recipe = recipeRepository.queryuserRecipeList(Username);
        RecipeResponse res = new RecipeResponse();

        if(recipe.length == 0) {
            res.setResult(RESULT_ERROR);
        } else {

            res.setResult(RESULT_OK);
        }

        return recipe;
    }
}
