package com.requests.backend.controllers;

import com.google.gson.GsonBuilder;
import com.requests.backend.models.*;
import com.requests.backend.models.responses.RecipeListResponse;
import com.requests.backend.models.responses.RecipeResponse;
import com.requests.backend.models.responses.ResultResponse;
import com.requests.backend.repositories.RecipeRepository;
import com.requests.backend.repositories.TokenRepository;
import com.util.Constants;
import com.util.security.Hasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

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
    public @ResponseBody String getRecipeByRname(@PathVariable String rname) {
        Recipe[] recipes = recipeRepository.queryGetRecipeByRname(rname);

        RecipeListResponse res = new RecipeListResponse();

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        if (recipes.length == 0) {
            res.setResult(RESULT_ERROR);
        }
        else {
            res.setRecipes(recipes);
            res.setResult(RESULT_OK);
        }

        return gson.toJson(res);
    }

    /**
     * Get a recipe by its recipe ID.
     * @param rid The recipe ID of the recipe to get.
     * @return A JSON response containing the recipe with the given recipe ID.
     */
    @GetMapping(path="/getRecipeByRid/{rid}")
    public @ResponseBody String getRecipeByRid(@PathVariable int rid) {
        Recipe[] recipe = recipeRepository.queryGetRecipeByRid(rid);

        RecipeResponse res = new RecipeResponse();

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        if (recipe.length == 0) {
            res.setResult(RESULT_ERROR);
        }
        else {
            res.setRecipe(recipe[0]);
            res.setResult(RESULT_OK);
        }

        return gson.toJson(res);
    }

    /**
     * Add a recipe to the database given recipe information.
     * @param token The token of the user adding the recipe.
     * @param json The JSON string containing the recipe information.
     * @return A JSON response containing the result of the operation.
     */
    @PostMapping(path="/add/{token}")
    @ResponseBody
    public String addNewRecipe(@PathVariable String token, @RequestBody String json) {

        String hashedToken = Hasher.sha256(token);
        Token[] tokenQueryRes = tokenRepository.queryGetToken(hashedToken);

        RecipeAddRequest req = new Gson().fromJson(json, RecipeAddRequest.class);

        String recipeName = req.getRecipeName();
        String instructions = req.getInstructions();

        ResultResponse res = new ResultResponse();


        if (tokenQueryRes.length == 0) {
            res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
        }
        else {
            String username = tokenQueryRes[0].getUsername();

            try {
                recipeRepository.queryCreateRecipe(username, recipeName, instructions);
                res.setResult(RESULT_RECIPE_CREATED);
            } catch (Exception e) {
                res.setResult(RESULT_ERROR_RID_TAKEN);
            }
        }

        // Create a new GSON Builder and disable escaping (to allow for certain unicode characters like "="
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        return gson.toJson(res);
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
    public String addRecipePicture(@PathVariable String token, @PathVariable String rid, MultipartFile file) {
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
     * @throws IOException
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
     * @param username The username of the user removing the recipe.
     * @param token The token of the user removing the recipe.
     * @return A JSON response containing the result of the operation.
     */
    @DeleteMapping(path="/remove")
    @ResponseBody
    public int removeRecipe(@RequestParam int rid, @RequestParam String username, @RequestParam String token) {
        Recipe[] recipe = recipeRepository.queryRecipeDeleteCheck(token, username);
        RecipeResponse res = new RecipeResponse();

        if(recipe.length == 0) {
            res.setResult(RESULT_ERROR);
        } else {
            recipeRepository.queryDeleteRecipe(rid);
            res.setResult(RESULT_OK);
        }

        return res.getResult();
    }


    /*  to be modified after database changed
    @GetMapping(path="/getImage/{rid}")
    @ResponseBody
    public String GetImage(@PathVariable int rid) {
        Recipe[] recipe = recipeRepository.queryGetImageByrid(rid);

        RecipeResponse res = new RecipeResponse();

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        if (recipe.length == 0) {
            res.setResult(RESULT_ERROR);
        }
        else {
            res.setRecipe(recipe[0]);
            res.setResult(RESULT_OK);
        }

        return gson.toJson(recipe[0]);
        //return null;
    }


     */

    /**
     * Get a list of recipes based on a given token
     * @param token The token of the user to get the recipes of.
     * @return A JSON response containing the result of the operation and the list of recipes.
     */
    @GetMapping(path="/userRecipeList/{token}")
    @ResponseBody
    public String userRecipeList(@PathVariable String token) {
        Recipe[] recipe = recipeRepository.queryuserRecipeList(token);
        RecipeResponse res = new RecipeResponse();

        if(recipe.length == 0) {
            res.setResult(RESULT_ERROR);
        } else {

            res.setResult(RESULT_OK);
        }

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        return gson.toJson(recipe);
    }




}
