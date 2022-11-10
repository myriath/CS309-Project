package com.example.cs309android.models.api.response.recipes;

import com.example.cs309android.models.api.models.SimpleRecipeItem;
import com.example.cs309android.models.api.response.GenericResponse;
import com.google.gson.annotations.Expose;

public class GetRecipeListResponse extends GenericResponse {

    /**
     * Recipe list array from JSON
     */
    @Expose
    private final SimpleRecipeItem[] recipes;

    /**
     * Public constructor
     *
     * @param recipes recipe item
     */
    public GetRecipeListResponse(SimpleRecipeItem[] recipes, int result) {
        super(result);
        this.recipes = recipes;
    }

    /**
     * Getter for the recipe array
     *
     * @return Array of simple recipe items.
     */
    public SimpleRecipeItem[] getRecipes() {
        return recipes;
    }
}
