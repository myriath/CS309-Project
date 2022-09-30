package com.example.cs309android.models.USDA.models;

public class FoodNutrientSource {
    private final int id;
    private final String code;
    private final String description;

    public FoodNutrientSource(int id, String code, String description) {
        this.id = id;
        this.code = code;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
