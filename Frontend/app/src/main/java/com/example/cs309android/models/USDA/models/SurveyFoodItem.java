package com.example.cs309android.models.USDA.models;

public class SurveyFoodItem {
    private final int fdcId;
    private final String datatype;
    private final String endDate;
    private final String foodClass;
    private final String foodCode;
    private final String publicationDate;
    private final String startDate;
    private final FoodAttribute[] foodAttributes;
    private final FoodPortion[] foodPortions;
    private final InputFoodSurvey[] inputFoods;
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
