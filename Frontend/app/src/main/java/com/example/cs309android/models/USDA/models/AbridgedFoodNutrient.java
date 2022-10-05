package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

public class AbridgedFoodNutrient {
    @Expose
    private final int number;
    @Expose
    private final String name;
    @Expose
    private final float amount;
    @Expose
    private final String unitName;
    @Expose
    private final String derivationCode;
    @Expose
    private final String derivationDescription;

    public AbridgedFoodNutrient(int number, String name, float amount, String unitName, String derivationCode, String derivationDescription) {
        this.number = number;
        this.name = name;
        this.amount = amount;
        this.unitName = unitName;
        this.derivationCode = derivationCode;
        this.derivationDescription = derivationDescription;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public float getAmount() {
        return amount;
    }

    public String getUnitName() {
        return unitName;
    }

    public String getDerivationCode() {
        return derivationCode;
    }

    public String getDerivationDescription() {
        return derivationDescription;
    }
}
