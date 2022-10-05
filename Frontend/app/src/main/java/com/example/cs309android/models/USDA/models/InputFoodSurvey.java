package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

public class InputFoodSurvey {
    @Expose
    private final int id;
    @Expose
    private final float amount;
    @Expose
    private final String foodDescription;
    @Expose
    private final int ingredientCode;
    @Expose
    private final String ingredientDescription;
    @Expose
    private final float ingredientWeight;
    @Expose
    private final String portionCode;
    @Expose
    private final String portionDescription;
    @Expose
    private final int sequenceNumber;
    @Expose
    private final int surveyFlag;
    @Expose
    private final String unit;
    @Expose
    private final SurveyFoodItem inputFood;
    @Expose
    private final RetentionFactor retentionFactor;

    public InputFoodSurvey(int id, float amount, String foodDescription, int ingredientCode, String ingredientDescription, float ingredientWeight, String portionCode, String portionDescription, int sequenceNumber, int surveyFlag, String unit, SurveyFoodItem inputFood, RetentionFactor retentionFactor) {
        this.id = id;
        this.amount = amount;
        this.foodDescription = foodDescription;
        this.ingredientCode = ingredientCode;
        this.ingredientDescription = ingredientDescription;
        this.ingredientWeight = ingredientWeight;
        this.portionCode = portionCode;
        this.portionDescription = portionDescription;
        this.sequenceNumber = sequenceNumber;
        this.surveyFlag = surveyFlag;
        this.unit = unit;
        this.inputFood = inputFood;
        this.retentionFactor = retentionFactor;
    }

    public int getId() {
        return id;
    }

    public float getAmount() {
        return amount;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public int getIngredientCode() {
        return ingredientCode;
    }

    public String getIngredientDescription() {
        return ingredientDescription;
    }

    public float getIngredientWeight() {
        return ingredientWeight;
    }

    public String getPortionCode() {
        return portionCode;
    }

    public String getPortionDescription() {
        return portionDescription;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public int getSurveyFlag() {
        return surveyFlag;
    }

    public String getUnit() {
        return unit;
    }

    public SurveyFoodItem getInputFood() {
        return inputFood;
    }

    public RetentionFactor getRetentionFactor() {
        return retentionFactor;
    }
}
