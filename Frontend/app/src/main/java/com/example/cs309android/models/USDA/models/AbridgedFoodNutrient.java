package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

/**
 * FDC Abridged Food Nutrient model
 */
public class AbridgedFoodNutrient {
    /**
     * Nutrient number
     */
    @Expose
    private final int number;
    /**
     * Nutrient name
     */
    @Expose
    private final String name;
    /**
     * Nutrient amount
     */
    @Expose
    private final float amount;
    /**
     * Nutrient unit
     */
    @Expose
    private final String unitName;
    /**
     * Derivation code
     */
    @Expose
    private final String derivationCode;
    /**
     * Derivation description
     */
    @Expose
    private final String derivationDescription;

    /**
     * Public constructor
     *
     * @param number                nutrient number
     * @param name                  nutrient name
     * @param amount                nutrient amount
     * @param unitName              nutrient unit
     * @param derivationCode        derivation code
     * @param derivationDescription derivation description
     */
    public AbridgedFoodNutrient(int number, String name, float amount, String unitName, String derivationCode, String derivationDescription) {
        this.number = number;
        this.name = name;
        this.amount = amount;
        this.unitName = unitName;
        this.derivationCode = derivationCode;
        this.derivationDescription = derivationDescription;
    }

    /**
     * Getter for the nutrient number
     *
     * @return nutrient number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Getter for the name
     *
     * @return nutrient name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the amount
     *
     * @return nutrient amount
     */
    public float getAmount() {
        return amount;
    }

    /**
     * Getter for the unit
     *
     * @return nutrient unit
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * Getter for the derivation code
     *
     * @return derivation code
     */
    public String getDerivationCode() {
        return derivationCode;
    }

    /**
     * Getter for the derivation description
     *
     * @return derivation description
     */
    public String getDerivationDescription() {
        return derivationDescription;
    }
}
