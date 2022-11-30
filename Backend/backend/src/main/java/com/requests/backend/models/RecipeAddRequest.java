package com.requests.backend.models;

public class RecipeAddRequest {
    private String recipeName;
    private String description;
    private Instruction[] instructions;
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
}