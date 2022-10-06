package com.requests.backend.controllers;

import com.google.gson.GsonBuilder;
import com.requests.backend.models.*;
import com.requests.backend.models.responses.RecipeListResponse;
import com.requests.backend.models.responses.RecipeResponse;
import com.requests.backend.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import static com.requests.backend.ResultCodes.RESULT_ERROR;
import static com.requests.backend.ResultCodes.RESULT_OK;


@RestController // This means that this class is a Controller
@RequestMapping(path="/recipe")
public class RecipeController {

    public static final int RESULT_ERROR_RID_TAKEN = -23;

    public static final int RESULT_OK = 0;

    public static final int RESULT_RECIPE_CREATED = 22;


    @Autowired
    private RecipeRepository recipeRepository;

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


    @PostMapping(path="/newRecipe") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestBody String json) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        RecipeAddRequest req = new Gson().fromJson(json, RecipeAddRequest.class);

        String username = req.getUsername();
        String recipeName = req.getRecipeName();
        String instructions = req.getInstructions();


        //String rname = req.getEmail();

        ResultResponse res = new ResultResponse();

        try {
            recipeRepository.queryCreateRecipe2(username, recipeName, instructions);
            res.setResult(RESULT_RECIPE_CREATED);
        } catch (Exception e) {
            res.setResult(RESULT_ERROR_RID_TAKEN);
        }

        // Create a new GSON Builder and disable escaping (to allow for certain unicode characters like "="
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        return gson.toJson(res);
    }

}
