package com.requests.backend.controllers;

import com.google.gson.GsonBuilder;
import com.requests.backend.models.*;
import com.requests.backend.models.responses.RecipeListResponse;
import com.requests.backend.models.responses.RecipeResponse;
import com.requests.backend.models.responses.ResultResponse;
import com.requests.backend.repositories.RecipeRepository;
import com.requests.backend.repositories.TokenRepository;
import com.util.security.Hasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import static com.util.Constants.RESULT_ERROR;
import static com.util.Constants.RESULT_ERROR_USER_HASH_MISMATCH;


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

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

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


    @PostMapping(path="/add/{token}")
    @ResponseBody
    public String addNewRecipe(@PathVariable String token, @RequestBody String json) {

        String hashedToken = Hasher.sha256(token);
        Token[] tokenQueryRes = tokenRepository.queryGetToken(token);

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

}
