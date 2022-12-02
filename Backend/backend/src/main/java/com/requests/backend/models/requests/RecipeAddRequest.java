package com.requests.backend.models.requests;

import com.google.gson.annotations.Expose;
import com.requests.backend.models.Ingredient;
import com.requests.backend.models.Instruction;

import java.util.Arrays;

public class RecipeAddRequest {
    @Expose
    private String recipeName;
    @Expose
    private String description;
    @Expose
    private Instruction[] instructions;
    @Expose
    private Ingredient[] ingredients;

    public RecipeAddRequest(String recipeName, String description, Instruction[] instructions, Ingredient[] ingredients) {
        this.recipeName = recipeName;
        this.description = description;
        this.instructions = instructions;
        this.ingredients = ingredients;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instruction[] getInstructions() {
        return instructions;
    }

    public void setInstructions(Instruction[] instructions) {
        this.instructions = instructions;
    }

    public Ingredient[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredient[] ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "RecipeAddRequest{" +
                "recipeName='" + recipeName + '\'' +
                ",\ndescription='" + description + '\'' +
                ",\ninstructions=" + Arrays.toString(instructions) +
                ",\ningredients=" + Arrays.toString(ingredients) +
                '}';
    }
}