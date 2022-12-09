package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

/**
 * FDC Abridged Food Item model
 *
 * @author Mitch Hudson
 */
public class AbridgedFoodItem {
    /**
     * Data type
     */
    @Expose
    private final String dataType;
    /**
     * Description / Name of the item
     */
    @Expose
    private final String description;
    /**
     * FDC id
     */
    @Expose
    private final int fdcId;
    /**
     * Array of nutrients for this item
     */
    @Expose
    private final AbridgedFoodNutrient[] foodNutrients;
    /**
     * Publication date
     */
    @Expose
    private final String publicationDate;
    /**
     * Brand of the item
     */
    @Expose
    private final String brandOwner;
    /**
     * Unsure
     */
    @Expose
    private final String gtinUpc;
    /**
     * NDB number
     */
    @Expose
    private final int ndbNumber;
    /**
     * Food code
     */
    @Expose
    private final String foodCode;

    /**
     * Public constructor
     *
     * @param dataType        data type
     * @param description     description / name
     * @param fdcId           fdc id
     * @param foodNutrients   nutrients
     * @param publicationDate publication date
     * @param brandOwner      brand name
     * @param gtinUpc         unsure
     * @param ndbNumber       ndb number
     * @param foodCode        food code
     */
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
     * Getter for the fdc id
     *
     * @return fdc id
     */
    public int getFdcId() {
        return fdcId;
    }

    /**
     * Getter for the food nutrients
     *
     * @return array of nutrients
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
     * Getter for the brand
     *
     * @return brand name
     */
    public String getBrandOwner() {
        return brandOwner;
    }

    /**
     * Getter for gtinUpc
     *
     * @return upc?
     */
    public String getGtinUpc() {
        return gtinUpc;
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
     * Getter for the food code
     *
     * @return food code
     */
    public String getFoodCode() {
        return foodCode;
    }
}
