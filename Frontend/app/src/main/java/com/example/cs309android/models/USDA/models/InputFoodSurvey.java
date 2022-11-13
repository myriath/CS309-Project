package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

/**
 * FDC Input Food Survey model
 *
 * @author Mitch Hudson
 */
public class InputFoodSurvey {
    /**
     * Survey id
     */
    @Expose
    private final int id;
    /**
     * Amount
     */
    @Expose
    private final float amount;
    /**
     * Food description
     */
    @Expose
    private final String foodDescription;
    /**
     * Ingredient code
     */
    @Expose
    private final int ingredientCode;
    /**
     * Ingredient description
     */
    @Expose
    private final String ingredientDescription;
    /**
     * Ingredient weight
     */
    @Expose
    private final float ingredientWeight;
    /**
     * Portion code
     */
    @Expose
    private final String portionCode;
    /**
     * Portion description
     */
    @Expose
    private final String portionDescription;
    /**
     * Sequence number
     */
    @Expose
    private final int sequenceNumber;
    /**
     * Survey flag
     */
    @Expose
    private final int surveyFlag;
    /**
     * Unit
     */
    @Expose
    private final String unit;
    /**
     * Input food
     */
    @Expose
    private final SurveyFoodItem inputFood;
    /**
     * Retention factor
     */
    @Expose
    private final RetentionFactor retentionFactor;

    /**
     * Public constructor
     *
     * @param id                    survey id
     * @param amount                amount
     * @param foodDescription       food description
     * @param ingredientCode        ingredient code
     * @param ingredientDescription ingredient description
     * @param ingredientWeight      ingredient weight
     * @param portionCode           portion code
     * @param portionDescription    portion description
     * @param sequenceNumber        sequence number
     * @param surveyFlag            survey flag
     * @param unit                  unit
     * @param inputFood             input food
     * @param retentionFactor       retention factor
     */
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

    /**
     * Getter for the survey id
     *
     * @return survey id
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for the amount
     *
     * @return amount
     */
    public float getAmount() {
        return amount;
    }

    /**
     * Getter for the food description
     *
     * @return food description
     */
    public String getFoodDescription() {
        return foodDescription;
    }

    /**
     * Getter for the ingredient code
     *
     * @return ingredient code
     */
    public int getIngredientCode() {
        return ingredientCode;
    }

    /**
     * Getter for the ingredient description
     *
     * @return ingredient description
     */
    public String getIngredientDescription() {
        return ingredientDescription;
    }

    /**
     * Getter for the ingredient weight
     *
     * @return ingredient weight
     */
    public float getIngredientWeight() {
        return ingredientWeight;
    }

    /**
     * Getter for the portion code
     *
     * @return portion code
     */
    public String getPortionCode() {
        return portionCode;
    }

    /**
     * Getter for the portion description
     *
     * @return portion description
     */
    public String getPortionDescription() {
        return portionDescription;
    }

    /**
     * Getter for the sequence number
     *
     * @return sequence number
     */
    public int getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * Getter for the survey flag
     *
     * @return survey flag
     */
    public int getSurveyFlag() {
        return surveyFlag;
    }

    /**
     * Getter for the unit
     *
     * @return unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Getter for the input food
     *
     * @return input food
     */
    public SurveyFoodItem getInputFood() {
        return inputFood;
    }

    /**
     * Getter for the retention factor
     *
     * @return retention factor
     */
    public RetentionFactor getRetentionFactor() {
        return retentionFactor;
    }
}
