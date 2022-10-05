package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

public class WweiaFoodCategory {
    @Expose
    private final int wweiaFoodCategoryCode;
    @Expose
    private final String wweiaFoodCategoryDescription;

    public WweiaFoodCategory(int wweiaFoodCategoryCode, String wweiaFoodCategoryDescription) {
        this.wweiaFoodCategoryCode = wweiaFoodCategoryCode;
        this.wweiaFoodCategoryDescription = wweiaFoodCategoryDescription;
    }

    public int getWweiaFoodCategoryCode() {
        return wweiaFoodCategoryCode;
    }

    public String getWweiaFoodCategoryDescription() {
        return wweiaFoodCategoryDescription;
    }
}
