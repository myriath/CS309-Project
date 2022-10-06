package com.example.cs309android.models.gson.request.recipes;

import com.example.cs309android.models.GetRequestURL;
import com.example.cs309android.models.gson.GetRequest;
import com.example.cs309android.models.gson.models.AuthModel;
import com.example.cs309android.util.Constants;
import com.google.gson.annotations.Expose;

/**
 * Gets info from specific recipe
 *
 * @author Travis Massner
 */
public class GetRecipeDetailsRequest extends GetRequest {
    /**
     * Index of recipe to get info for
     */
    @Expose
    private final int index;
    /**
     * Authentication model of the user
     */
    @Expose
    private final AuthModel auth;

    /**
     * Public constructor
     *
     * @param index Index of recipe to get
     * @param auth Authentication model for the user
     */
    public GetRecipeDetailsRequest(int index, AuthModel auth) {
        this.index = index;
        this.auth = auth;
    }

    /**
     * Getter for the recipe index
     *
     * @return item to add
     */
    public int getRecipe() {
        return index;
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
     * Method to
     *
     * @return
     */
    @Override
    public String getURL() {
        return new GetRequestURL(Constants.GET_RECIPES_URL)

                + "1";
    }
}
