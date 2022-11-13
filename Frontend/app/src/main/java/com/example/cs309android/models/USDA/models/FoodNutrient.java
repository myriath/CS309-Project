package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

/**
 * FDC Food Nutrient model
 *
 * @author Mitch Hudson
 */
public class FoodNutrient {
    /**
     * Nutrient id
     */
    @Expose
    private final int id;
    /**
     * Nutrient amount
     */
    @Expose
    private final float amount;
    /**
     * Data points
     */
    @Expose
    private final int dataPoints;
    /**
     * Minimum
     */
    @Expose
    private final float min;
    /**
     * Maximum
     */
    @Expose
    private final float max;
    /**
     * Median
     */
    @Expose
    private final float median;
    /**
     * Nutrient type
     */
    @Expose
    private final String type;
    /**
     * Nutrient
     */
    @Expose
    private final Nutrient nutrient;
    /**
     * Nutrient derivation
     */
    @Expose
    private final FoodNutrientDerivation foodNutrientDerivation;
    /**
     * Nutrient analysis details
     */
    @Expose
    private final NutrientAnalysisDetails[] nutrientAnalysisDetails;

    /**
     * Public constructor
     *
     * @param id                      nutrient id
     * @param amount                  nutrient amount
     * @param dataPoints              data points
     * @param min                     minimum
     * @param max                     maximum
     * @param median                  median
     * @param type                    nutrient type
     * @param nutrient                nutrient
     * @param foodNutrientDerivation  nutrient derivation
     * @param nutrientAnalysisDetails analysis details
     */
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

    /**
     * Getter for the nutrient id
     *
     * @return nutrient id
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for the nutrient amount
     *
     * @return nutrient amount
     */
    public float getAmount() {
        return amount;
    }

    /**
     * Getter for the data points
     *
     * @return data points
     */
    public int getDataPoints() {
        return dataPoints;
    }

    /**
     * Getter for the minimum
     *
     * @return minimum
     */
    public float getMin() {
        return min;
    }

    /**
     * Getter for the maximum
     *
     * @return maximum
     */
    public float getMax() {
        return max;
    }

    /**
     * Getter for the median
     *
     * @return median
     */
    public float getMedian() {
        return median;
    }

    /**
     * Getter for the type
     *
     * @return nutrient type
     */
    public String getType() {
        return type;
    }

    /**
     * Getter for the nutrient
     *
     * @return nutrient
     */
    public Nutrient getNutrient() {
        return nutrient;
    }

    /**
     * Getter for the nutrient derivation
     *
     * @return nutrient derivation
     */
    public FoodNutrientDerivation getFoodNutrientDerivation() {
        return foodNutrientDerivation;
    }

    /**
     * Getter for the analysis details
     *
     * @return analysis details
     */
    public NutrientAnalysisDetails[] getNutrientAnalysisDetails() {
        return nutrientAnalysisDetails;
    }
}
