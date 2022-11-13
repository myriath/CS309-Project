package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

/**
 * FDC Food Nutrient Derivation model
 *
 * @author Mitch Hudson
 */
public class FoodNutrientDerivation {
    /**
     * Derivation id
     */
    @Expose
    private final int id;
    /**
     * Derivation code
     */
    @Expose
    private final String code;
    /**
     * Derivation description
     */
    @Expose
    private final String description;
    /**
     * Nutrient source
     */
    @Expose
    private final FoodNutrientSource foodNutrientSource;

    /**
     * Public constructor
     *
     * @param id                 derivation id
     * @param code               derivation code
     * @param description        derivation description
     * @param foodNutrientSource nutrient source
     */
    public FoodNutrientDerivation(int id, String code, String description, FoodNutrientSource foodNutrientSource) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.foodNutrientSource = foodNutrientSource;
    }

    /**
     * Getter for the id
     *
     * @return derivation id
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for the code
     *
     * @return derivation code
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

    /**
     * Getter for the nutrient source
     *
     * @return nutrient source
     */
    public FoodNutrientSource getFoodNutrientSource() {
        return foodNutrientSource;
    }
}
