package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

public class FoodUpdateLog {
    @Expose
    private final int fdcId;
    @Expose
    private final String availableDate;
    @Expose
    private final String brandOwner;
    @Expose
    private final String dataSource;
    @Expose
    private final String dataType;
    @Expose
    private final String gtinUpc;
    @Expose
    private final String householdServingFullText;
    @Expose
    private final String ingredients;
    @Expose
    private final String modifiedDate;
    @Expose
    private final String publicationDate;
    @Expose
    private final double servingSize;
    @Expose
    private final String servingSizeUnit;
    @Expose
    private final String brandedFoodCategory;
    @Expose
    private final String changes;
    @Expose
    private final FoodAttribute[] foodAttributes;

    public FoodUpdateLog(int fdcId, String availableDate, String brandOwner, String dataSource, String dataType, String gtinUpc, String householdServingFullText, String ingredients, String modifiedDate, String publicationDate, double servingSize, String servingSizeUnit, String brandedFoodCategory, String changes, FoodAttribute[] foodAttributes) {
        this.fdcId = fdcId;
        this.availableDate = availableDate;
        this.brandOwner = brandOwner;
        this.dataSource = dataSource;
        this.dataType = dataType;
        this.gtinUpc = gtinUpc;
        this.householdServingFullText = householdServingFullText;
        this.ingredients = ingredients;
        this.modifiedDate = modifiedDate;
        this.publicationDate = publicationDate;
        this.servingSize = servingSize;
        this.servingSizeUnit = servingSizeUnit;
        this.brandedFoodCategory = brandedFoodCategory;
        this.changes = changes;
        this.foodAttributes = foodAttributes;
    }

    public int getFdcId() {
        return fdcId;
    }

    public String getAvailableDate() {
        return availableDate;
    }

    public String getBrandOwner() {
        return brandOwner;
    }

    public String getDataSource() {
        return dataSource;
    }

    public String getDataType() {
        return dataType;
    }

    public String getGtinUpc() {
        return gtinUpc;
    }

    public String getHouseholdServingFullText() {
        return householdServingFullText;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public double getServingSize() {
        return servingSize;
    }

    public String getServingSizeUnit() {
        return servingSizeUnit;
    }

    public String getBrandedFoodCategory() {
        return brandedFoodCategory;
    }

    public String getChanges() {
        return changes;
    }

    public FoodAttribute[] getFoodAttributes() {
        return foodAttributes;
    }
}
