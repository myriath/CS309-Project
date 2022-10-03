package com.example.cs309android.models.USDA.models;

public class FoodNutrient {
    private final int id;
    private final float amount;
    private final int dataPoints;
    private final float min;
    private final float max;
    private final float median;
    private final String type;
    private final Nutrient nutrient;
    private final FoodNutrientDerivation foodNutrientDerivation;
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
