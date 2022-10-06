package com.example.cs309android.models.gson.response.recipes;

import com.example.cs309android.models.gson.models.SimpleRecipeItem;
import com.google.gson.annotations.Expose;

/**
 * GSON model for the /recipe/get endpoint
 *
 * @author Mitch Hudson
 */
public class GetRecipeDetailsResponse {
    /**
     * Recipe Item from JSON
     */
    @Expose
    private final SimpleRecipeItem recipe;

    /**
     * Public constructor
     *
     * @param recipe recipe item
     */
    public GetRecipeDetailsResponse(SimpleRecipeItem recipe) {
        this.recipe = recipe;
    }

    /**
     * Getter for the recipe
     *
     * @return Simple recipe item
     */
    public SimpleRecipeItem getRecipe() {
        return recipe;
    }
}
