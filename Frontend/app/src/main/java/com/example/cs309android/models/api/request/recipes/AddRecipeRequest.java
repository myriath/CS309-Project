package com.example.cs309android.models.api.request.recipes;

import static com.example.cs309android.util.Constants.ADD_RECIPES_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.models.Ingredient;
import com.example.cs309android.models.api.models.Instruction;
import com.example.cs309android.models.api.request.abstraction.PostRequest;
import com.google.gson.annotations.Expose;

/**
 * Adds a recipe
 *
 * @author Travis Massner
 */
public class AddRecipeRequest extends PostRequest {
    /**
     * Recipe name
     */
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
        super(new ParameterizedRequestURL(ADD_RECIPES_URL)
                .addPathVar(token)
                .toString());
        this.recipeName = recipeName;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    /**
     * Getter for the recipe name
     * @return recipeName
     */
    public String getRecipeName() {
        return recipeName;
    }

    /**
     * Getter for the recipe instructions
     * @return instructions
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for the ingredients
     * @return ingredients array
     */
    public Ingredient[] getIngredients() {
        return ingredients;
    }

    /**
     * Getter for the instructions
     * @return instructions array (ORDERED)
     */
    public Instruction[] getInstructions() {
        return instructions;
    }
}
