package com.example.cs309android.models.gson.request.recipes;

import com.example.cs309android.models.gson.DeleteRequest;
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
     * Authentication token of the user
     */
    @Expose
    private final String token;

    /**
     * Public constructor
     *
     * @param index Index of recipe to remove
     * @param token Authentication token for the user
     */
    public RemoveRecipeRequest(int index, String token) {
        super(Constants.REMOVE_RECIPES_URL);
        this.index = index;
        this.token = token;
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
     * Getter for the authentication token
     *
     * @return authentication token
     */
    public String getToken() {
        return token;
    }
}
