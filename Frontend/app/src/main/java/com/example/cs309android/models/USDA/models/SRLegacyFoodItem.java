package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

public class SRLegacyFoodItem {
    @Expose
    private final int fdcId;
    @Expose
    private final String dataType;
    @Expose
    private final String description;
    @Expose
    private final String foodClass;
    @Expose
    private final boolean isHistoricalReference;
    @Expose
    private final int ndbNumber;
    @Expose
    private final String publicationDate;
    @Expose
    private final String scientificName;
    @Expose
    private final FoodCategory foodCategory;
    @Expose
    private final FoodNutrient[] foodNutrients;
    @Expose
    private final NutrientConversionFactors[] nutrientConversionFactors;

    public SRLegacyFoodItem(int fdcId, String dataType, String description, String foodClass, boolean isHistoricalReference, int ndbNumber, String publicationDate, String scientificName, FoodCategory foodCategory, FoodNutrient[] foodNutrients, NutrientConversionFactors[] nutrientConversionFactors) {
        this.fdcId = fdcId;
        this.dataType = dataType;
        this.description = description;
        this.foodClass = foodClass;
        this.isHistoricalReference = isHistoricalReference;
        this.ndbNumber = ndbNumber;
        this.publicationDate = publicationDate;
        this.scientificName = scientificName;
        this.foodCategory = foodCategory;
        this.foodNutrients = foodNutrients;
        this.nutrientConversionFactors = nutrientConversionFactors;
    }

    public int getFdcId() {
        return fdcId;
    }

    public String getDataType() {
        return dataType;
    }

    public String getDescription() {
        return description;
    }

    public String getFoodClass() {
        return foodClass;
    }

    public boolean isHistoricalReference() {
        return isHistoricalReference;
    }

    public int getNdbNumber() {
        return ndbNumber;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getScientificName() {
        return scientificName;
    }

    public FoodCategory getFoodCategory() {
        return foodCategory;
    }

    public FoodNutrient[] getFoodNutrients() {
        return foodNutrients;
    }

    public NutrientConversionFactors[] getNutrientConversionFactors() {
        return nutrientConversionFactors;
    }
}
