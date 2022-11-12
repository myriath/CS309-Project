package com.example.cs309android.models.api.response.recipes;

import com.example.cs309android.models.api.models.Recipe;
import com.example.cs309android.models.api.response.GenericResponse;
import com.google.gson.annotations.Expose;

/**
 * Response object for the get recipe list request
 *
 * @author Travis Massner
 */
public class GetRecipeListResponse extends GenericResponse {
    /**
     * Recipe list array from JSON
     */
    @Expose
    private final Recipe[] recipes;

    /**
     * Public constructor
     * @param recipes recipe item
     */
    public GetRecipeListResponse(Recipe[] recipes, int result) {
        super(result);
        this.recipes = recipes;
    }

    /**
     * Getter for the recipe array
     * @return Array of simple recipe items.
     */
    public Recipe[] getRecipes() {
        return recipes;
    }
}
