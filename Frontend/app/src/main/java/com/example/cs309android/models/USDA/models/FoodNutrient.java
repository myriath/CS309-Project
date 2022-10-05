package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

public class FoodNutrient {
    @Expose
    private final int id;
    @Expose
    private final float amount;
    @Expose
    private final int dataPoints;
    @Expose
    private final float min;
    @Expose
    private final float max;
    @Expose
    private final float median;
    @Expose
    private final String type;
    @Expose
    private final Nutrient nutrient;
    @Expose
    private final FoodNutrientDerivation foodNutrientDerivation;
    @Expose
    private final NutrientAnalysisDetails[] nutrientAnalysisDetails;

    public FoodNutrient(int id, float amount, int dataPoints, float min, float max, float median, String type, Nutrient nutrient, FoodNutrientDerivation foodNutrientDerivation, NutrientAnalysisDetails[] nutrientAnalysisDetails) {
        this.id = id;
        this.amount = amount;
        this.dataPoints = dataPoints;
        this.min = min;
        this.max = max;
        this.median = median;
        this.type = type;
        this.nutrient = nutrient;
        this.foodNutrientDerivation = foodNutrientDerivation;
        this.nutrientAnalysisDetails = nutrientAnalysisDetails;
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

    public float getMin() {
        return min;
    }

    public float getMax() {
        return max;
    }

    public float getMedian() {
        return median;
    }

    public String getType() {
        return type;
    }

    public Nutrient getNutrient() {
        return nutrient;
    }

    public FoodNutrientDerivation getFoodNutrientDerivation() {
        return foodNutrientDerivation;
    }

    public NutrientAnalysisDetails[] getNutrientAnalysisDetails() {
        return nutrientAnalysisDetails;
    }
}
