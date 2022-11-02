package com.example.cs309android.models.gson.request.recipes;

import com.example.cs309android.models.gson.GetRequest;
import com.example.cs309android.util.Constants;

public class GetUserRecipesRequest extends GetRequest {
    /**
     * Authentication token of the user
     */
    private final String token;

    /**
     * Public constructor
     *
     * @param token Authentication token for the user
     */
    public GetUserRecipesRequest(String token) {
        this.token = token;
    }

    /**
     * Getter for the authentication token
     *
     * @return authentication token
     */
    public String getToken() {
        return token;
    }

    public String getURL() {
        return Constants.GET_RECIPES_LIST_URL + token;
    }
}
