package com.requests.backend.models;

import java.util.List;

public class RecipeListResponse {

    private int result;
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
