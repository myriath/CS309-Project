package com.example.cs309android.models.api.request.recipes;

import static com.example.cs309android.util.Constants.Urls.Recipes.DELETE_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.models.Recipe;
import com.example.cs309android.models.api.request.abstraction.PutRequest;
import com.google.gson.annotations.Expose;

/**
 * Deletes a given recipe
 * TODO: test
 *
 * @author Mitch Hudson
 */
public class DeleteRecipeRequest extends PutRequest {
    /**
     * Recipe to delete
     */
    @Expose
    private final Recipe recipe;

    /**
     * Public constructor
     *
     * @param recipe Recipe to delete
     * @param token  token to authenticate
     */
    public DeleteRecipeRequest(Recipe recipe, String token) {
        super(new ParameterizedRequestURL(DELETE_URL)
                .addPathVar(token)
                .toString());
        this.recipe = recipe;
    }

    /**
     * Getter for the recipe
     *
     * @return recipe to delete
     */
    public Recipe getRecipe() {
        return recipe;
    }
}
