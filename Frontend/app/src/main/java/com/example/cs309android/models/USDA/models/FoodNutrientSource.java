package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

public class FoodNutrientSource {
    @Expose
    private final int id;
    @Expose
    private final String code;
    @Expose
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
