package com.example.cs309android.models.USDA.models;

public class FoundationFoodItem {
    private final int fdcId;
    private final String dataType;
    private final String description;
    private final String foodClass;
    private final String footNote;
    private final boolean isHistoricalReference;
    private final int ndbNumber;
    private final String publicationDate;
    private final String scientificName;
    private final FoodCategory foodCategory;
    private final FoodComponent[] foodComponents;
    private final FoodNutrient[] foodNutrients;
    private final FoodPortion[] foodPortions;
    private final InputFoodFoundation[] inputFoodFoundations;
    private final NutrientConversionFactors[] nutrientConversionFactors;

    public FoundationFoodItem(int fdcId, String dataType, String description, String foodClass, String footNote, boolean isHistoricalReference, int ndbNumber, String publicationDate, String scientificName, FoodCategory foodCategory, FoodComponent[] foodComponents, FoodNutrient[] foodNutrients, FoodPortion[] foodPortions, InputFoodFoundation[] inputFoodFoundations, NutrientConversionFactors[] nutrientConversionFactors) {
        this.fdcId = fdcId;
        this.dataType = dataType;
        this.description = description;
        this.foodClass = foodClass;
        this.footNote = footNote;
        this.isHistoricalReference = isHistoricalReference;
        this.ndbNumber = ndbNumber;
        this.publicationDate = publicationDate;
        this.scientificName = scientificName;
        this.foodCategory = foodCategory;
        this.foodComponents = foodComponents;
        this.foodNutrients = foodNutrients;
        this.foodPortions = foodPortions;
        this.inputFoodFoundations = inputFoodFoundations;
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

    public String getFootNote() {
        return footNote;
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

    public FoodComponent[] getFoodComponents() {
        return foodComponents;
    }

    public FoodNutrient[] getFoodNutrients() {
        return foodNutrients;
    }

    public FoodPortion[] getFoodPortions() {
        return foodPortions;
    }

    public InputFoodFoundation[] getInputFoodFoundations() {
        return inputFoodFoundations;
    }

    public NutrientConversionFactors[] getNutrientConversionFactors() {
        return nutrientConversionFactors;
    }
}
