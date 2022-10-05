package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

public class NutrientConversionFactors {
    @Expose
    private final String type;
    @Expose
    private final float value;

    public NutrientConversionFactors(String type, float value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public float getValue() {
        return value;
    }
}
