package com.requests.backend.models.responses;

import com.requests.backend.models.Recipe;

public class RecipeResponse {
    private int result;

    private Recipe recipe;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getRecipeImage(){ return recipe.getImage();}


}
