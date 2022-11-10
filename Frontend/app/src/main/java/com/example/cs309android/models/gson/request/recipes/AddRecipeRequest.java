package com.example.cs309android.models.gson.request.recipes;

import com.example.cs309android.models.gson.models.Ingredient;
import com.example.cs309android.models.gson.models.Instruction;
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
    private final String description;
    /**
     * Ingredient array for the recipe
     */
    @Expose
    private final Ingredient[] ingredients;
    /**
     * Instruction array for the recipe
     */
    @Expose
    private final Instruction[] instructions;

    /**
     * Constructor to be used by GSON
     *
     * @param token        token to authenticate
     * @param recipeName   Recipe name
     * @param description  Recipe description
     * @param ingredients  Recipe ingredients
     * @param instructions Recipe Instructions
     */
    public AddRecipeRequest(String token, String recipeName, String description, Ingredient[] ingredients, Instruction[] instructions) {
        super(Constants.ADD_RECIPES_URL + "/" + token);
        this.recipeName = recipeName;
        this.description = description;
        this.ingredients = ingredients;
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
    public String getDescription() {
        return description;
    }

    /**
     * Getter for the ingredients
     *
     * @return ingredients array
     */
    public Ingredient[] getIngredients() {
        return ingredients;
    }

    /**
     * Getter for the instructions
     *
     * @return instructions array (ORDERED)
     */
    public Instruction[] getInstructions() {
        return instructions;
    }
}
