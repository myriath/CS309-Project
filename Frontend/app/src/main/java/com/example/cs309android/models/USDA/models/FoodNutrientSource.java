package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

/**
 * FDC Food Nutrient Source model
 *
 * @author Mitch Hudson
 */
public class FoodNutrientSource {
    /**
     * Source id
     */
    @Expose
    private final int id;
    /**
     * Source code
     */
    @Expose
    private final String code;
    /**
     * Source description
     */
    @Expose
    private final String description;

    /**
     * Public constructor
     *
     * @param id          source id
     * @param code        source code
     * @param description description
     */
    public FoodNutrientSource(int id, String code, String description) {
        this.id = id;
        this.code = code;
        this.description = description;
    }

    /**
     * Getter for the source id
     *
     * @return source id
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for the source code
     *
     * @return source code
     */
    public String getCode() {
        return code;
    }

    /**
     * Getter for the description
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }
}
