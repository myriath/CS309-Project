package com.example.cs309android.models.USDA.queries;

import com.example.cs309android.models.USDA.models.AbridgedFoodNutrient;
import com.google.gson.annotations.Expose;

public class SearchResultFood {
    @Expose
    private final Integer fdcId;
    @Expose
    private final String dataType;
    @Expose
    private final String description;
    @Expose
    private final String foodCode;
    @Expose
    private final AbridgedFoodNutrient[] foodNutrients;
    @Expose
    private final String publicationDate;
    @Expose
    private final String scientificName;
    @Expose
    private final String brandOwner;
    @Expose
    private final String gtinUpc;
    @Expose
    private final String ingredients;
    @Expose
    private final Integer ndbNumber;
    @Expose
    private final String additionalDescriptions;
    @Expose
    private final String allHighlightFields;
    @Expose
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
