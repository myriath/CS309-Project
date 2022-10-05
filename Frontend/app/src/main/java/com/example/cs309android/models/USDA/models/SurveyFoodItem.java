package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

public class SurveyFoodItem {
    @Expose
    private final int fdcId;
    @Expose
    private final String datatype;
    @Expose
    private final String endDate;
    @Expose
    private final String foodClass;
    @Expose
    private final String foodCode;
    @Expose
    private final String publicationDate;
    @Expose
    private final String startDate;
    @Expose
    private final FoodAttribute[] foodAttributes;
    @Expose
    private final FoodPortion[] foodPortions;
    @Expose
    private final InputFoodSurvey[] inputFoods;
    @Expose
    private final WweiaFoodCategory wweiaFoodCategory;

    public SurveyFoodItem(int fdcId, String datatype, String endDate, String foodClass, String foodCode, String publicationDate, String startDate, FoodAttribute[] foodAttributes, FoodPortion[] foodPortions, InputFoodSurvey[] inputFoods, WweiaFoodCategory wweiaFoodCategory) {
        this.fdcId = fdcId;
        this.datatype = datatype;
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

    public int getFdcId() {
        return fdcId;
    }

    public String getDatatype() {
        return datatype;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getFoodClass() {
        return foodClass;
    }

    public String getFoodCode() {
        return foodCode;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public FoodAttribute[] getFoodAttributes() {
        return foodAttributes;
    }

    public FoodPortion[] getFoodPortions() {
        return foodPortions;
    }

    public InputFoodSurvey[] getInputFoods() {
        return inputFoods;
    }

    public WweiaFoodCategory getWweiaFoodCategory() {
        return wweiaFoodCategory;
    }
}
