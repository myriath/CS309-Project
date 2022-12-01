package com.requests.backend.models.responses;

import com.google.gson.annotations.Expose;
import com.requests.backend.models.Recipe;

import java.util.List;

public class RecipeListResponse {
    @Expose
    private int result;
    @Expose
    private Recipe[] recipes;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Recipe[] getRecipes() {
        return recipes;
    }

    public void setRecipes(Recipe[] recipes) {
        this.recipes = recipes;
    }
}
