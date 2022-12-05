package com.example.cs309android.models.api.request.recipes;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.GetRequest;
import com.example.cs309android.util.Constants;

/**
 * Request to get a list of a user's recipes
 *
 * @author Travis Massner
 */
public class GetRecipesRequest extends GetRequest {
    /**
     * Username of the account to get recipes from
     */
    private final String username;

    /**
     * Constructor
     *
     * @param username Username of the account to get recipes from
     */
    public GetRecipesRequest(String username) {
        this.username = username;
    }

    /**
     * Getter for the request URL
     *
     * @return request URL
     */
    @Override
    public String getURL() {
        return new ParameterizedRequestURL(Constants.Urls.Recipes.GET_RECIPES_LIST_URL)
                .addPathVar(username)
                .toString();
    }
}
