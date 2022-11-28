package com.example.cs309android.models.api.request.recipes;

import static com.example.cs309android.util.Constants.Urls.Recipes.GET_RECIPES_LIST_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.GetRequest;

/**
 * Gets a list of a user's recipes
 *
 * @author Travis Massner
 */
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

    /**
     * Getter for the request URL
     *
     * @return request URL
     */
    @Override
    public String getURL() {
        return new ParameterizedRequestURL(GET_RECIPES_LIST_URL)
                .addPathVar(token)
                .toString();
    }
}
