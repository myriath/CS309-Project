package com.requests.backend;

import com.google.gson.GsonBuilder;
import com.requests.backend.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;
import java.util.Base64;
import java.util.Collection;


@RestController // This means that this class is a Controller
@RequestMapping(path="/recipe")
public class RecipeController {

    public static final int RESULT_ERROR = -1;
    public static final int RESULT_ERROR_USER_HASH_MISMATCH = -2;
    public static final int RESULT_ERROR_USERNAME_TAKEN = -3;
    public static final int RESULT_ERROR_EMAIL_TAKEN = -4;

    public static final int RESULT_OK = 0;
    public static final int RESULT_LOGGED_IN = 1;
    public static final int RESULT_USER_CREATED = 2;

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @GetMapping(path="/getRecipeByRname/{rname}")
    public @ResponseBody String getRecipeByRname(@PathVariable String rname) {
        Collection<Recipe> recipes = recipeRepository.queryGetRecipeByRname(rname);
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.toJson(recipes);
    }

    @GetMapping(path="/getRecipeByRid/{rid}")
    public @ResponseBody String getRecipeByRname(@PathVariable int rid) {
        Collection<Recipe> recipes = recipeRepository.queryGetRecipeByRid(rid);
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.toJson(recipes);
    }


}
