package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

public class FoodNutrientDerivation {
    @Expose
    private final int id;
    @Expose
    private final String code;
    @Expose
    private final String description;
    @Expose
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
