package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

/**
 * FDC WWEIA Food Category model
 *
 * @author Mitch Hudson
 */
public class WweiaFoodCategory {
    /**
     * Category code
     */
    @Expose
    private final int wweiaFoodCategoryCode;
    /**
     * Category description
     */
    @Expose
    private final String wweiaFoodCategoryDescription;

    /**
     * Public constructor
     *
     * @param wweiaFoodCategoryCode        category code
     * @param wweiaFoodCategoryDescription category description
     */
    public WweiaFoodCategory(int wweiaFoodCategoryCode, String wweiaFoodCategoryDescription) {
        this.wweiaFoodCategoryCode = wweiaFoodCategoryCode;
        this.wweiaFoodCategoryDescription = wweiaFoodCategoryDescription;
    }

    /**
     * Getter for the category code
     *
     * @return category code
     */
    public int getWweiaFoodCategoryCode() {
        return wweiaFoodCategoryCode;
    }

    /**
     * Getter for the category description
     *
     * @return category description
     */
    public String getWweiaFoodCategoryDescription() {
        return wweiaFoodCategoryDescription;
    }
}
