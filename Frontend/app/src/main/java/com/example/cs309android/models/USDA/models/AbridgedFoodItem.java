package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

public class AbridgedFoodItem {
    @Expose
    private final String dataType;
    @Expose
    private final String description;
    @Expose
    private final int fdcId;
    @Expose
    private final AbridgedFoodNutrient[] foodNutrients;
    @Expose
    private final String publicationDate;
    @Expose
    private final String brandOwner;
    @Expose
    private final String gtinUpc;
    @Expose
    private final int ndbNumber;
    @Expose
    private final String foodCode;

    public AbridgedFoodItem(String dataType, String description, int fdcId, AbridgedFoodNutrient[] foodNutrients, String publicationDate, String brandOwner, String gtinUpc, int ndbNumber, String foodCode) {
        this.dataType = dataType;
        this.description = description;
        this.fdcId = fdcId;
        this.foodNutrients = foodNutrients;
        this.publicationDate = publicationDate;
        this.brandOwner = brandOwner;
        this.gtinUpc = gtinUpc;
        this.ndbNumber = ndbNumber;
        this.foodCode = foodCode;
    }

    public String getDataType() {
        return dataType;
    }

    public String getDescription() {
        return description;
    }

    public int getFdcId() {
        return fdcId;
    }

    public AbridgedFoodNutrient[] getFoodNutrients() {
        return foodNutrients;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getBrandOwner() {
        return brandOwner;
    }

    public String getGtinUpc() {
        return gtinUpc;
    }

    public int getNdbNumber() {
        return ndbNumber;
    }

    public String getFoodCode() {
        return foodCode;
    }
}
