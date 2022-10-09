package com.example.cs309android.models.gson.response.recipes;

import com.example.cs309android.models.gson.models.SimpleRecipeItem;
import com.example.cs309android.models.gson.response.GenericResponse;
import com.google.gson.annotations.Expose;

/**
 * GSON model for the /recipe/get endpoint
 *
 * @author Travis Massner
 */
public class GetRecipeDetailsResponse extends GenericResponse {
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
    public GetRecipeDetailsResponse(SimpleRecipeItem recipe, int result) {
        super(result);
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
