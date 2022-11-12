package com.example.cs309android.models.api.response.recipes;

import com.example.cs309android.models.api.models.Recipe;
import com.example.cs309android.models.api.response.GenericResponse;

/**
 * Response for the GetRecipesRequest
 *
 * @author Mitch Hudson
 */
public class GetRecipesResponse extends GenericResponse {
    /**
     * Recipe list for the user
     */
    private final Recipe[] items;

    /**
     * Constructor to be used by GSON
     *
     * @param result Result code from the request
     * @param items  Array of user posts
     */
    public GetRecipesResponse(int result, Recipe[] items) {
        super(result);
        this.items = items;
    }

    /**
     * Gets the response's items
     *
     * @return array of user posts
     */
    public Recipe[] getItems() {
        return items;
    }
}
