package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

/**
 * FDC Survey Food Item model
 *
 * @author Mitch Hudson
 */
public class SurveyFoodItem {
    /**
     * FDC id
     */
    @Expose
    private final int fdcId;
    /**
     * Data type
     */
    @Expose
    private final String datatype;
    /**
     * Description
     */
    @Expose
    private final String description;
    /**
     * Survey end date
     */
    @Expose
    private final String endDate;
    /**
     * Food class
     */
    @Expose
    private final String foodClass;
    /**
     * Food code
     */
    @Expose
    private final String foodCode;
    /**
     * Publication date
     */
    @Expose
    private final String publicationDate;
    /**
     * Survey start date
     */
    @Expose
    private final String startDate;
    /**
     * Food attributes
     */
    @Expose
    private final FoodAttribute[] foodAttributes;
    /**
     * Food portions
     */
    @Expose
    private final FoodPortion[] foodPortions;
    /**
     * Input foods
     */
    @Expose
    private final InputFoodSurvey[] inputFoods;
    /**
     * WWEIA Food category
     */
    @Expose
    private final WweiaFoodCategory wweiaFoodCategory;

    /**
     * Public constructor
     *
     * @param fdcId             fdc id
     * @param datatype          data type
     * @param description       description
     * @param endDate           end date
     * @param foodClass         food class
     * @param foodCode          food code
     * @param publicationDate   publication date
     * @param startDate         start date
     * @param foodAttributes    food attributes
     * @param foodPortions      food portions
     * @param inputFoods        input foods
     * @param wweiaFoodCategory wweia food category
     */
    public SurveyFoodItem(int fdcId, String datatype, String description, String endDate, String foodClass, String foodCode, String publicationDate, String startDate, FoodAttribute[] foodAttributes, FoodPortion[] foodPortions, InputFoodSurvey[] inputFoods, WweiaFoodCategory wweiaFoodCategory) {
        this.fdcId = fdcId;
        this.datatype = datatype;
        this.description = description;
        this.endDate = endDate;
        this.foodClass = foodClass;
        this.foodCode = foodCode;
        this.publicationDate = publicationDate;
        this.startDate = startDate;
        this.foodAttributes = foodAttributes;
        this.foodPortions = foodPortions;
        this.inputFoods = inputFoods;
        this.wweiaFoodCategory = wweiaFoodCategory;
    }

    /**
     * Getter for the fdc id
     *
     * @return fdc id
     */
    public int getFdcId() {
        return fdcId;
    }

    /**
     * Getter for the data type
     *
     * @return data type
     */
    public String getDatatype() {
        return datatype;
    }

    /**
     * Getter for the description
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for the end date
     *
     * @return end date
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Getter for the food class
     *
     * @return food class
     */
    public String getFoodClass() {
        return foodClass;
    }

    /**
     * Getter for the food code
     *
     * @return food code
     */
    public String getFoodCode() {
        return foodCode;
    }

    /**
     * Getter for the publication date
     *
     * @return publication date
     */
    public String getPublicationDate() {
        return publicationDate;
    }

    /**
     * Getter for the start date
     *
     * @return start date
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Getter for the food attributes
     *
     * @return food attributes
     */
    public FoodAttribute[] getFoodAttributes() {
        return foodAttributes;
    }

    /**
     * Getter for the food portions
     *
     * @return food portions
     */
    public FoodPortion[] getFoodPortions() {
        return foodPortions;
    }

    /**
     * Getter for the input foods
     *
     * @return input foods
     */
    public InputFoodSurvey[] getInputFoods() {
        return inputFoods;
    }

    /**
     * Getter for the wweia category
     *
     * @return wweia category
     */
    public WweiaFoodCategory getWweiaFoodCategory() {
        return wweiaFoodCategory;
    }
}
