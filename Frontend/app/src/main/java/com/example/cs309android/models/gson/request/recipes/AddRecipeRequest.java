package com.example.cs309android.models.gson.request.recipes;

import com.example.cs309android.models.gson.PostRequest;
import com.example.cs309android.models.gson.models.AuthModel;
import com.example.cs309android.models.gson.models.SimpleRecipeItem;
import com.example.cs309android.util.Constants;
import com.google.gson.annotations.Expose;

/**
 * Add request model for the /recipe/add endpoint
 *
 * @author Travis Massner
 */
public class AddRecipeRequest extends PostRequest {
    /**
     * Simple recipe to add
     */
    @Expose
    private final SimpleRecipeItem recipe;
    /**
     * Authentication model of the user
     */
    @Expose
    private final AuthModel auth;

    /**
     * Public constructor
     *
     * @param recipe recipe to add to the db
     * @param auth Authentication model for the user
     */
    public AddRecipeRequest(SimpleRecipeItem recipe, AuthModel auth) {
        super(Constants.ADD_RECIPES_URL);
        this.recipe = recipe;
        this.auth = auth;
    }

    /**
     * Getter for the recipe
     *
     * @return item to add
     */
    public SimpleRecipeItem getItem() {
        return recipe;
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
