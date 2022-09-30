package com.example.cs309android.models.USDA.models;

public class FoodPortion {
    private final int id;
    private final float amount;
    private final int dataPoints;
    private final float gramWeight;
    private final int minYearAcquired;
    private final String modifier;
    private final String portionDescription;
    private final int sequenceNumber;
    private final MeasureUnit measureUnit;

    public FoodPortion(int id, float amount, int dataPoints, float gramWeight, int minYearAcquired, String modifier, String portionDescription, int sequenceNumber, MeasureUnit measureUnit) {
        this.id = id;
        this.amount = amount;
        this.dataPoints = dataPoints;
        this.gramWeight = gramWeight;
        this.minYearAcquired = minYearAcquired;
        this.modifier = modifier;
        this.portionDescription = portionDescription;
        this.sequenceNumber = sequenceNumber;
        this.measureUnit = measureUnit;
    }

    public int getId() {
        return id;
    }

    public float getAmount() {
        return amount;
    }

    public int getDataPoints() {
        return dataPoints;
    }

    public float getGramWeight() {
        return gramWeight;
    }

    public int getMinYearAcquired() {
        return minYearAcquired;
    }

    public String getModifier() {
        return modifier;
    }

    public String getPortionDescription() {
        return portionDescription;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public MeasureUnit getMeasureUnit() {
        return measureUnit;
    }
}
