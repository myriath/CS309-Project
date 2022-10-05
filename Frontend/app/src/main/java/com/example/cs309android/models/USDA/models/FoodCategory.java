package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

public class FoodCategory {
    @Expose
    private final int id;
    @Expose
    private final String code;
    @Expose
    private final String description;

    public FoodCategory(int id, String code, String description) {
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
