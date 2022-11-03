package com.example.cs309android.models.gson.request.recipes;

import com.example.cs309android.models.gson.models.SimpleRecipeItem;
import com.example.cs309android.models.gson.request.abstraction.PostRequest;
import com.example.cs309android.util.Constants;
import com.google.gson.annotations.Expose;

/**
 * Add request model for the /recipe/add endpoint
 *
 * @author Travis Massner
 */
public class AddRecipeRequest extends PostRequest {

    @Expose
    private final String recipeName;
    /**
     * Recipe instructions as a string
     */
    @Expose
    private final String instructions;

    /**
     * Constructor to be used by GSON
     *
     * @param token        token to authenticate
     * @param recipeName   Recipe name
     * @param instructions Recipe Instructions
     */
    public AddRecipeRequest(String token, String recipeName, String instructions) {
        super(Constants.ADD_RECIPES_URL + "/" + token);
        this.recipeName = recipeName;
        this.instructions = instructions;
    }

    /**
     * Getter for the recipe name
     *
     * @return recipeName
     */
    public String getRecipeName() {
        return recipeName;
    }

    /**
     * Getter for the recipe instructions
     *
     * @return instructions
     */
    public String getInstructions() {
        return instructions;
    }
}
