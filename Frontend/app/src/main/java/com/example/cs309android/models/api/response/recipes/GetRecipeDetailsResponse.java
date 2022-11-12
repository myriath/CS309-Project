package com.example.cs309android.models.api.response.recipes;

import com.example.cs309android.models.api.models.Recipe;
import com.example.cs309android.models.api.response.GenericResponse;
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
    private final Recipe recipe;

    /**
     * Public constructor
     *
     * @param recipe recipe item
     */
    public GetRecipeDetailsResponse(Recipe recipe, int result) {
        super(result);
        this.recipe = recipe;
    }

    /**
     * Getter for the recipe
     *
     * @return Simple recipe item
     */
    public Recipe getRecipe() {
        return recipe;
    }
}
