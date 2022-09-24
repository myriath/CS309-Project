package com.example.cs309android.models.FDC.models;

public class FoodNutrientDerivation {
    private final int id;
    private final String code;
    private final String description;
    private final FoodNutrientSource foodNutrientSource;

    public FoodNutrientDerivation(int id, String code, String description, FoodNutrientSource foodNutrientSource) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.foodNutrientSource = foodNutrientSource;
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

    public FoodNutrientSource getFoodNutrientSource() {
        return foodNutrientSource;
    }
}
