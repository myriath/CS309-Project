package com.requests.backend.models.responses;

import com.requests.backend.models.Recipe;

public class RecipeResponse extends ResultResponse {
    private Recipe recipe;

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }



}
