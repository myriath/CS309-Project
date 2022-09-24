package com.example.cs309android.models.FDC.models;

public class NutrientConversionFactors {
    private final String type;
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
