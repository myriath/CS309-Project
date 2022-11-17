package com.example.cs309android.models.api.request.recipes;

import com.example.cs309android.models.api.request.abstraction.GetRequest;

/**
 * Request to get a list of a user's recipes
 * TODO: STUB!
 *       Should take username and return a list of recipes uploaded by that user
 *
 * @author Travis Massner
 */
public class GetRecipesRequest extends GetRequest {
    /**
     * Constructor
     *
     * @param username Username of the account to get recipes from
     */
    public GetRecipesRequest(String username) {
    }

    /**
     * Getter for the request URL
     *
     * @return request URL
     */
    @Override
    public String getURL() {
        return null;
    }
}
