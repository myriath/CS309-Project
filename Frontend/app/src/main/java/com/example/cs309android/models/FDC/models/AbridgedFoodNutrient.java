package com.example.cs309android.models.FDC.models;

public class AbridgedFoodNutrient {
    private final int number;
    private final String name;
    private final float amount;
    private final String unitName;
    private final String derivationCode;
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
