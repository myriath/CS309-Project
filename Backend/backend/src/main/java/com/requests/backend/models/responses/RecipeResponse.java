package com.requests.backend.models.responses;

import com.google.gson.annotations.Expose;
import com.requests.backend.models.Recipe;

public class RecipeResponse extends ResultResponse {
    @Expose
    private Recipe[] recipes;

    public Recipe[] getRecipes() {
        return recipes;
    }

    public void setRecipes(Recipe[] recipes) {
        this.recipes = recipes;
    }
}
