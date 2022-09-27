package com.example.cs309android.models.FDC.queries;

import com.example.cs309android.models.FDC.models.AbridgedFoodNutrient;

public class SearchResultFood {
    private final Integer fdcId;
    private final String dataType;
    private final String description;
    private final String foodCode;
    private final AbridgedFoodNutrient[] foodNutrients;
    private final String publicationDate;
    private final String scientificName;
    private final String brandOwner;
    private final String gtinUpc;
    private final String ingredients;
    private final Integer ndbNumber;
    private final String additionalDescriptions;
    private final String allHighlightFields;
    private final Float score;

    public SearchResultFood(Integer fdcId, String dataType, String description, String foodCode, AbridgedFoodNutrient[] foodNutrients, String publicationDate, String scientificName, String brandOwner, String gtinUpc, String ingredients, Integer ndbNumber, String additionalDescriptions, String allHighlightFields, Float score) {
        this.fdcId = fdcId;
        this.dataType = dataType;
        this.description = description;
        this.foodCode = foodCode;
        this.foodNutrients = foodNutrients;
        this.publicationDate = publicationDate;
        this.scientificName = scientificName;
        this.brandOwner = brandOwner;
        this.gtinUpc = gtinUpc;
        this.ingredients = ingredients;
        this.ndbNumber = ndbNumber;
        this.additionalDescriptions = additionalDescriptions;
        this.allHighlightFields = allHighlightFields;
        this.score = score;
    }

    public Integer getFdcId() {
        return fdcId;
    }

    public String getDataType() {
        return dataType;
    }

    public String getDescription() {
        return description;
    }

    public String getFoodCode() {
        return foodCode;
    }

    public AbridgedFoodNutrient[] getFoodNutrients() {
        return foodNutrients;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getScientificName() {
        return scientificName;
    }

    public String getBrandOwner() {
        return brandOwner;
    }

    public String getGtinUpc() {
        return gtinUpc;
    }

    public String getIngredients() {
        return ingredients;
    }

    public Integer getNdbNumber() {
        return ndbNumber;
    }

    public String getAdditionalDescriptions() {
        return additionalDescriptions;
    }

    public String getAllHighlightFields() {
        return allHighlightFields;
    }

    public Float getScore() {
        return score;
    }
}
