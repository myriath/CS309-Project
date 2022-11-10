package com.example.cs309android.models.api.response.recipes;

import com.example.cs309android.models.api.models.SimpleRecipeItem;
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
    private final SimpleRecipeItem[] items;

    /**
     * Constructor to be used by GSON
     *
     * @param result Result code from the request
     * @param items  Array of user posts
     */
    public GetRecipesResponse(int result, SimpleRecipeItem[] items) {
        super(result);
        this.items = items;
    }

    /**
     * Gets the response's items
     *
     * @return array of user posts
     */
    public SimpleRecipeItem[] getItems() {
        return items;
    }
}
