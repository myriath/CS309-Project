package com.example.cs309android.models.FDC.models;

public class SRLegacyFoodItem {
    private final int fdcId;
    private final String dataType;
    private final String description;
    private final String foodClass;
    private final boolean isHistoricalReference;
    private final int ndbNumber;
    private final String publicationDate;
    private final String scientificName;
    private final FoodCategory foodCategory;
    private final FoodNutrient[] foodNutrients;
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
