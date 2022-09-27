package com.example.cs309android.models.FDC.models;

public class FoodUpdateLog {
    private final int fdcId;
    private final String availableDate;
    private final String brandOwner;
    private final String dataSource;
    private final String dataType;
    private final String gtinUpc;
    private final String householdServingFullText;
    private final String ingredients;
    private final String modifiedDate;
    private final String publicationDate;
    private final int servingSize;
    private final String servingSizeUnit;
    private final String brandedFoodCategory;
    private final String changes;
    private final FoodAttribute[] foodAttributes;

    public FoodUpdateLog(int fdcId, String availableDate, String brandOwner, String dataSource, String dataType, String gtinUpc, String householdServingFullText, String ingredients, String modifiedDate, String publicationDate, int servingSize, String servingSizeUnit, String brandedFoodCategory, String changes, FoodAttribute[] foodAttributes) {
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

    public int getServingSize() {
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
