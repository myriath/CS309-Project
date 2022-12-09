package com.example.cs309android.models.api.response.recipes;

import com.example.cs309android.models.api.models.Recipe;
import com.example.cs309android.models.api.response.GenericResponse;
import com.google.gson.annotations.Expose;

/**
 * Response object for the get recipes request
 *
 * @author Mitch Hudson
 */
public class GetRecipesResponse extends GenericResponse {
    /**
     * Recipe list for the user
     */
    @Expose
    private final Recipe[] recipes;

    /**
     * Constructor to be used by GSON
     *
     * @param result  Result code from the request
     * @param recipes Array of user posts
     */
    public GetRecipesResponse(int result, Recipe[] recipes) {
        super(result);
        this.recipes = recipes;
    }

    /**
     * Gets the response's items
     *
     * @return array of user posts
     */
    public Recipe[] getRecipes() {
        return recipes;
    }
}
