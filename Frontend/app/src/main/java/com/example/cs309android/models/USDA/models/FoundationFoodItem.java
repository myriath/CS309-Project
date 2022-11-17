package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

/**
 * FDC Foundation Food Item model
 *
 * @author Mitch Hudson
 */
public class FoundationFoodItem {
    /**
     * FDC id
     */
    @Expose
    private final int fdcId;
    /**
     * Data type
     */
    @Expose
    private final String dataType;
    /**
     * Description
     */
    @Expose
    private final String description;
    /**
     * Food class
     */
    @Expose
    private final String foodClass;
    /**
     * Footnote
     */
    @Expose
    private final String footNote;
    /**
     * True if this is a historical reference
     */
    @Expose
    private final boolean isHistoricalReference;
    /**
     * NDB number
     */
    @Expose
    private final int ndbNumber;
    /**
     * Publication date
     */
    @Expose
    private final String publicationDate;
    /**
     * Scientific name
     */
    @Expose
    private final String scientificName;
    /**
     * Food category
     */
    @Expose
    private final FoodCategory foodCategory;
    /**
     * Food components
     */
    @Expose
    private final FoodComponent[] foodComponents;
    /**
     * Food nutrients
     */
    @Expose
    private final FoodNutrient[] foodNutrients;
    /**
     * Food portions
     */
    @Expose
    private final FoodPortion[] foodPortions;
    /**
     * Input food foundations
     */
    @Expose
    private final InputFoodFoundation[] inputFoodFoundations;
    /**
     * Nutrient conversion factors
     */
    @Expose
    private final NutrientConversionFactors[] nutrientConversionFactors;

    /**
     * Public constructor
     *
     * @param fdcId                     fdc id
     * @param dataType                  data type
     * @param description               description / name
     * @param foodClass                 food class
     * @param footNote                  footnote
     * @param isHistoricalReference     true if this is a historical reference
     * @param ndbNumber                 ndb number
     * @param publicationDate           publication date
     * @param scientificName            scientific name
     * @param foodCategory              food category
     * @param foodComponents            food components
     * @param foodNutrients             food nutrients
     * @param foodPortions              food portions
     * @param inputFoodFoundations      input food foundations
     * @param nutrientConversionFactors conversion factors
     */
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

    /**
     * Getter for the fdc id
     *
     * @return fdc id
     */
    public int getFdcId() {
        return fdcId;
    }

    /**
     * Getter for the data type
     *
     * @return data type
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * Getter for the description
     *
     * @return description / name
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for the food class
     *
     * @return food class
     */
    public String getFoodClass() {
        return foodClass;
    }

    /**
     * Getter for the footnote
     *
     * @return footnote
     */
    public String getFootNote() {
        return footNote;
    }

    /**
     * Getter for the historical reference boolean
     *
     * @return true if this is a historical reference
     */
    public boolean isHistoricalReference() {
        return isHistoricalReference;
    }

    /**
     * Getter for the ndb number
     *
     * @return ndb number
     */
    public int getNdbNumber() {
        return ndbNumber;
    }

    /**
     * Getter for the publication date
     *
     * @return publication date
     */
    public String getPublicationDate() {
        return publicationDate;
    }

    /**
     * Getter for the scientific name
     *
     * @return scientific name
     */
    public String getScientificName() {
        return scientificName;
    }

    /**
     * Getter for the food category
     *
     * @return food category
     */
    public FoodCategory getFoodCategory() {
        return foodCategory;
    }

    /**
     * Getter for the food components
     *
     * @return food components
     */
    public FoodComponent[] getFoodComponents() {
        return foodComponents;
    }

    /**
     * Getter for the food nutrients
     *
     * @return food nutrients
     */
    public FoodNutrient[] getFoodNutrients() {
        return foodNutrients;
    }

    /**
     * Getter for the food portions
     *
     * @return food portions
     */
    public FoodPortion[] getFoodPortions() {
        return foodPortions;
    }

    /**
     * Getter for the input food foundations
     *
     * @return input food foundations
     */
    public InputFoodFoundation[] getInputFoodFoundations() {
        return inputFoodFoundations;
    }

    /**
     * Getter for the nutrient conversion factors
     *
     * @return nutrient conversion factors
     */
    public NutrientConversionFactors[] getNutrientConversionFactors() {
        return nutrientConversionFactors;
    }
}
