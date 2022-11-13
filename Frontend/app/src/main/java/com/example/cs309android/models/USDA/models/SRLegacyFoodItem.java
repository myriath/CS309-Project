package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

/**
 * FDC SR Legacy Food Item model
 *
 * @author Mitch Hudson
 */
public class SRLegacyFoodItem {
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
     * publication date
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
     * Food nutrients
     */
    @Expose
    private final FoodNutrient[] foodNutrients;
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
     * @param description               description
     * @param foodClass                 food class
     * @param isHistoricalReference     historical reference boolean
     * @param ndbNumber                 ndb number
     * @param publicationDate           publication date
     * @param scientificName            scientific name
     * @param foodCategory              food category
     * @param foodNutrients             food nutrients
     * @param nutrientConversionFactors nutrient conversion factors
     */
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
     * @return description
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
     * Getter for the food nutrients
     *
     * @return food nutrients
     */
    public FoodNutrient[] getFoodNutrients() {
        return foodNutrients;
    }

    /**
     * Getter for the nutrient conversion factors
     *
     * @return conversion factors
     */
    public NutrientConversionFactors[] getNutrientConversionFactors() {
        return nutrientConversionFactors;
    }
}
