package com.example.cs309android.models.FDC.models;

public class WweiaFoodCategory {
    private final int wweiaFoodCategoryCode;
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
