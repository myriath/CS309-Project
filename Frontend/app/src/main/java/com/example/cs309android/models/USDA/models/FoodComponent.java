package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

public class FoodComponent {
    @Expose
    private final int id;
    @Expose
    private final String name;
    @Expose
    private final int dataPoints;
    @Expose
    private final float gramWeight;
    @Expose
    private final boolean isRefuse;
    @Expose
    private final int minYearAcquired;
    @Expose
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
