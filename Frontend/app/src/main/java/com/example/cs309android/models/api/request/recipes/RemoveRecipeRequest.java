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
     * Recipe id of recipe to remove
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
     *
     * @param rid   Recipe id of recipe to remove
     * @param token Authentication token for the user
     */
    public RemoveRecipeRequest(int rid, String token) {
        super(new ParameterizedRequestURL(REMOVE_RECIPES_URL)
                .addPathVar(token)
                .addPathVar(rid)
                .toString());
        this.rid = rid;
        this.token = token;
    }

    /**
     * Getter for the recipe id
     *
     * @return Recipe id
     */
    public int getRid() {
        return rid;
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
