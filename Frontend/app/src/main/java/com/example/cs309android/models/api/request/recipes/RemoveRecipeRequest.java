package com.example.cs309android.models.api.request.recipes;

import static com.example.cs309android.util.Constants.Urls.Recipes.REMOVE_RECIPES_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.DeleteRequest;
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
        super(new ParameterizedRequestURL(REMOVE_RECIPES_URL)
                .addPathVar(token)
                .addPathVar(index)
                .toString());
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
