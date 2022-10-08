package com.example.cs309android.models.gson.request.recipes;

import com.example.cs309android.models.GetRequestURL;
import com.example.cs309android.models.gson.GetRequest;
import com.example.cs309android.models.gson.models.AuthModel;
import com.example.cs309android.util.Constants;
import com.google.gson.annotations.Expose;

/**
 * Gets info from specific recipe from the recipe id
 *
 * @author Travis Massner
 */
public class GetRecipeDetailsRequest extends GetRequest {
    /**
     * Recipe id to get info for
     */
    @Expose
    private final int rid;
    /**
     * Authentication model of the user
     */
    @Expose
    private final AuthModel auth;

    /**
     * Public constructor
     *
     * @param rid Recipe id to get info for
     * @param auth Authentication model for the user
     */
    public GetRecipeDetailsRequest(int rid, AuthModel auth) {
        this.rid = rid;
        this.auth = auth;
    }

    /**
     * Getter for the recipe id
     *
     * @return item to add
     */
    public int getRecipe() {
        return rid;
    }

    /**
     * Getter for the authentication model
     *
     * @return authentication model
     */
    public AuthModel getAuth() {
        return auth;
    }

    /**
     * Getter for the URL with rid
     *
     * @return URL with rid
     */
    @Override
    public String getURL() {
        return new GetRequestURL(Constants.GET_RECIPES_URL) + String.valueOf(rid);
    }
}
