package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

/**
 * FDC Nutrient Conversion Factors model
 *
 * @author Mitch Hudson
 */
public class NutrientConversionFactors {
    /**
     * Conversion type
     */
    @Expose
    private final String type;
    /**
     * Conversion value
     */
    @Expose
    private final float value;

    /**
     * Public constructor
     *
     * @param type  conversion type
     * @param value conversion value
     */
    public NutrientConversionFactors(String type, float value) {
        this.type = type;
        this.value = value;
    }

    /**
     * Getter for the conversion type
     *
     * @return conversion type
     */
    public String getType() {
        return type;
    }

    /**
     * Getter for the conversion value
     *
     * @return conversion value
     */
    public float getValue() {
        return value;
    }
}
