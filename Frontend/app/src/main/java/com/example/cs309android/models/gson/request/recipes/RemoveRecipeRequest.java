package com.example.cs309android.models.gson.request.recipes;

import com.example.cs309android.models.gson.DeleteRequest;
import com.example.cs309android.models.gson.models.AuthModel;
import com.example.cs309android.util.Constants;
import com.google.gson.annotations.Expose;

/**
 * Removes the specific recipe index from the DB
 *
 * @author Travis Massner
 */
public class RemoveRecipeRequest extends DeleteRequest {
    /**
     * Index of recipe to remove
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
     * @param index Index of recipe to remove
     * @param auth Authentication model for the user
     */
    public RemoveRecipeRequest(int index, AuthModel auth) {
        super(Constants.REMOVE_RECIPES_URL);
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
}
