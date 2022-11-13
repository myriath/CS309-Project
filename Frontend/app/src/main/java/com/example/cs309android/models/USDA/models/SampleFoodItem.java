package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

/**
 * FDC Sample Food Item model
 *
 * @author Mitch Hudson
 */
public class SampleFoodItem {
    /**
     * FDC id
     */
    @Expose
    private final int fdcId;
    /**
     * Data type
     */
    @Expose
    private final String datatype;
    /**
     * Description / name
     */
    @Expose
    private final String description;
    /**
     * Food class
     */
    @Expose
    private final String foodClass;
    /**
     * Publication date
     */
    @Expose
    private final String publicationDate;
    /**
     * Food attributes
     */
    @Expose
    private final FoodCategory[] foodAttributes;

    /**
     * Public constructor
     *
     * @param fdcId           fdc id
     * @param datatype        data type
     * @param description     description
     * @param foodClass       food class
     * @param publicationDate publication date
     * @param foodAttributes  food attributes
     */
    public SampleFoodItem(int fdcId, String datatype, String description, String foodClass, String publicationDate, FoodCategory[] foodAttributes) {
        this.fdcId = fdcId;
        this.datatype = datatype;
        this.description = description;
        this.foodClass = foodClass;
        this.publicationDate = publicationDate;
        this.foodAttributes = foodAttributes;
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
    public String getDatatype() {
        return datatype;
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
     * Getter for the food class
     *
     * @return food class
     */
    public String getFoodClass() {
        return foodClass;
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
     * Getter for the food attributes
     *
     * @return food attributes
     */
    public FoodCategory[] getFoodAttributes() {
        return foodAttributes;
    }
}
