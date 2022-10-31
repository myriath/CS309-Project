package com.example.cs309android.models.gson.request.recipes;

import com.example.cs309android.models.gson.request.abstraction.PostRequest;
import com.example.cs309android.util.Constants;
import com.google.gson.annotations.Expose;

/**
 * Add request to add a recipe with recipeName and instructions to db
 *
 * @author Travis Massner
 */
public class AddRecipe extends PostRequest {

    /**
     * Token as a string to authenticate the new recipe with
     */
    @Expose
    private final String token;
    /**
     * Recipe name as a string
     */
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
    public AddRecipe(String token, String recipeName, String instructions) {
        super(Constants.ADD_RECIPES_URL);
        this.token = token;
        this.recipeName = recipeName;
        this.instructions = instructions;
    }

    /**
     * Getter for the token
     *
     * @return authentication token
     */
    public String getToken() {
        return token;
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
