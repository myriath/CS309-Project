package com.requests.backend.models;

public class RecipeAddRequest {

    private String username;
    private String recipeName;
    private String instructions;

    public RecipeAddRequest(String username, String recipeName, String instructions) {
        this.username = username;
        this.recipeName = recipeName;
        this.instructions = instructions;
    }

    public String getUsername() {
        return username;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getInstructions() {
        return instructions;
    }

}