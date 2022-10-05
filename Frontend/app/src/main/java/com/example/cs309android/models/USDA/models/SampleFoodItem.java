package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

public class SampleFoodItem {
    @Expose
    private final int fdcId;
    @Expose
    private final String datatype;
    @Expose
    private final String description;
    @Expose
    private final String foodClass;
    @Expose
    private final String publicationDate;
    @Expose
    private final FoodCategory[] foodAttributes;

    public SampleFoodItem(int fdcId, String datatype, String description, String foodClass, String publicationDate, FoodCategory[] foodAttributes) {
        this.fdcId = fdcId;
        this.datatype = datatype;
        this.description = description;
        this.foodClass = foodClass;
        this.publicationDate = publicationDate;
        this.foodAttributes = foodAttributes;
    }

    public int getFdcId() {
        return fdcId;
    }

    public String getDatatype() {
        return datatype;
    }

    public String getDescription() {
        return description;
    }

    public String getFoodClass() {
        return foodClass;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public FoodCategory[] getFoodAttributes() {
        return foodAttributes;
    }
}
