package com.requests.backend.models.responses;

import com.requests.backend.models.Recipe;

public class RecipeResponse extends ResultResponse {
    private Recipe[] recipes;

    public Recipe[] getRecipes() {
        return recipes;
    }

    public void setRecipe(Recipe[] recipes) {
        this.recipes = recipes;
    }
}
