package com.requests.backend.models;

public class RecipeAddRequest {

    private int rid;
    private String recipeName;
    private String instructions;

    public RecipeAddRequest(int rid, String recipeName, String instructions) {
        this.rid = rid;
        this.recipeName = recipeName;
        this.instructions = instructions;
    }
    public int getRid() {
        return rid;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getInstructions() {
        return instructions;
    }

}