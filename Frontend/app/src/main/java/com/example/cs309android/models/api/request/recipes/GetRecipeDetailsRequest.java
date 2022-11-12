package com.example.cs309android.models.api.request.recipes;

import static com.example.cs309android.util.Constants.GET_RECIPES_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.GetRequest;
import com.google.gson.annotations.Expose;

/**
 * Gets info from specific recipe from the recipe id
 * @author Travis Massner
 */
public class GetRecipeDetailsRequest extends GetRequest {
    /**
     * Recipe id to get info for
     */
    @Expose
    private final int rid;
    /**
     * Authentication token of the user
     */
    @Expose
    private final String token;

    /**
     * Public constructor
     * @param rid   Recipe id to get info for
     * @param token Authentication token for the user
     */
    public GetRecipeDetailsRequest(int rid, String token) {
        this.rid = rid;
        this.token = token;
    }

    /**
     * Getter for the recipe id
     * @return item to add
     */
    public int getRecipe() {
        return rid;
    }

    /**
     * Getter for the authentication token
     * @return authentication token
     */
    public String getToken() {
        return token;
    }

    /**
     * Getter for the URL with rid
     * @return URL with rid
     */
    @Override
    public String getURL() {
        return new ParameterizedRequestURL(GET_RECIPES_URL)
                .addPathVar(rid)
                .toString();
    }
}
