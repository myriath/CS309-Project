package com.example.cs309android.models.FDC.models;

public class FoodComponent {
    private final int id;
    private final String name;
    private final int dataPoints;
    private final float gramWeight;
    private final boolean isRefuse;
    private final int minYearAcquired;
    private final float percentWeight;

    public FoodComponent(int id, String name, int dataPoints, float gramWeight, boolean isRefuse, int minYearAcquired, float percentWeight) {
        this.id = id;
        this.name = name;
        this.dataPoints = dataPoints;
        this.gramWeight = gramWeight;
        this.isRefuse = isRefuse;
        this.minYearAcquired = minYearAcquired;
        this.percentWeight = percentWeight;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDataPoints() {
        return dataPoints;
    }

    public float getGramWeight() {
        return gramWeight;
    }

    public boolean isRefuse() {
        return isRefuse;
    }

    public int getMinYearAcquired() {
        return minYearAcquired;
    }

    public float getPercentWeight() {
        return percentWeight;
    }
}
