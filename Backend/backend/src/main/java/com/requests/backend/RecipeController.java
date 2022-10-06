package com.requests.backend;

import com.google.gson.GsonBuilder;
import com.requests.backend.models.*;
import com.requests.backend.ResultCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;
import java.util.List;

import static com.requests.backend.ResultCodes.RESULT_ERROR;
import static com.requests.backend.ResultCodes.RESULT_OK;


@RestController // This means that this class is a Controller
@RequestMapping(path="/recipe")
public class RecipeController {

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

}