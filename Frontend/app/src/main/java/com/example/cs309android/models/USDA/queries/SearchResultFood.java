package com.example.cs309android.models.USDA.queries;

import com.example.cs309android.models.USDA.models.AbridgedFoodNutrient;
import com.google.gson.annotations.Expose;

/**
 * FDC Search Result Food model
 * Models a food item returned from a search
 *
 * @author Mitch Hudson
 */
public class SearchResultFood {
    /**
     * FDC id
     */
    @Expose
    private final Integer fdcId;
    /**
     * Data type (5 values possible)
     */
    @Expose
    private final String dataType;
    /**
     * Description / name
     */
    @Expose
    private final String description;
    /**
     * Food code
     */
    @Expose
    private final String foodCode;
    /**
     * Food nutrients
     */
    @Expose
    private final AbridgedFoodNutrient[] foodNutrients;
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
     * Brand owner
     */
    @Expose
    private final String brandOwner;
    /**
     * UPC code?
     */
    @Expose
    private final String gtinUpc;
    /**
     * Ingredients list from the label
     */
    @Expose
    private final String ingredients;
    /**
     * NDB number
     */
    @Expose
    private final Integer ndbNumber;
    /**
     * Any additional descriptions
     */
    @Expose
    private final String additionalDescriptions;
    /**
     * All highlighted fields from the search
     */
    @Expose
    private final String allHighlightFields;
    /**
     * Score?
     */
    @Expose
    private final Float score;

    /**
     * Public constructor
     *
     * @param fdcId                  fdci d
     * @param dataType               data type
     * @param description            description / name
     * @param foodCode               food code
     * @param foodNutrients          food nutrients
     * @param publicationDate        publication date
     * @param scientificName         scientific name
     * @param brandOwner             brand owner
     * @param gtinUpc                upc?
     * @param ingredients            ingredients from the label
     * @param ndbNumber              ndb number
     * @param additionalDescriptions additional descriptions
     * @param allHighlightFields     highlighted fields
     * @param score                  score?
     */
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

    /**
     * Getter for the fdc id
     *
     * @return fdc id
     */
    public Integer getFdcId() {
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
     * Getter for the description / name
     *
     * @return description / name
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for the food code
     *
     * @return food code
     */
    public String getFoodCode() {
        return foodCode;
    }

    /**
     * Getter for the food nutrients
     *
     * @return food nutrients
     */
    public AbridgedFoodNutrient[] getFoodNutrients() {
        return foodNutrients;
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
     * Getter for the brand owner
     *
     * @return brand owner
     */
    public String getBrandOwner() {
        return brandOwner;
    }

    /**
     * Getter for the upc?
     *
     * @return upc?
     */
    public String getGtinUpc() {
        return gtinUpc;
    }

    /**
     * Getter for the ingredients
     *
     * @return ingredients list from the label
     */
    public String getIngredients() {
        return ingredients;
    }

    /**
     * Getter for the ndb number
     *
     * @return ndb number
     */
    public Integer getNdbNumber() {
        return ndbNumber;
    }

    /**
     * Getter for the additional descriptions
     *
     * @return additional descriptions
     */
    public String getAdditionalDescriptions() {
        return additionalDescriptions;
    }

    /**
     * Getter for the highlighted fields
     *
     * @return highlighted fields
     */
    public String getAllHighlightFields() {
        return allHighlightFields;
    }

    /**
     * Getter for the score?
     *
     * @return score?
     */
    public Float getScore() {
        return score;
    }
}
