package com.example.cs309android.models.gson.request.recipes;

import com.example.cs309android.models.gson.PostRequest;
import com.example.cs309android.util.Constants;
import com.google.gson.annotations.Expose;

/**
 * Add request to add a recipe with recipeName and instructions to db
 *
 * @author Travis Massner
 */
public class AddRecipe extends PostRequest {

    /**
     * Username as a string to add recipe under.
     */
    @Expose
    private final String username;
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
     * @param username Username to register
     * @param recipeName    Recipe name
     * @param instructions    Recipe Instructions
     */
    public AddRecipe(String username, String recipeName, String instructions) {
        super(Constants.ADD_RECIPES_URL);
        this.username = username;
        this.recipeName = recipeName;
        this.instructions = instructions;
    }

    /**
     * Getter for the username
     *
     * @return username to register
     */
    public String getUsername() {
        return username;
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
