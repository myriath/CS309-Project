package com.example.cs309android.models.gson.response.social;

import com.example.cs309android.models.gson.models.SimpleRecipeItem;
import com.example.cs309android.models.gson.response.GenericResponse;

/**
 * Response for the GetUserPostsRequest
 *
 * @author Mitch Hudson
 */
public class GetUserPostsResponse extends GenericResponse {
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
    public GetUserPostsResponse(int result, SimpleRecipeItem[] items) {
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
