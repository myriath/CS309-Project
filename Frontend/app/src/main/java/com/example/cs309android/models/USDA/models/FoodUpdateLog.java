package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

/**
 * FDC Food Update Log model
 *
 * @author Mitch Hudson
 */
public class FoodUpdateLog {
    /**
     * FDC id
     */
    @Expose
    private final int fdcId;
    /**
     * Available date
     */
    @Expose
    private final String availableDate;
    /**
     * Brand owner
     */
    @Expose
    private final String brandOwner;
    /**
     * Data source
     */
    @Expose
    private final String dataSource;
    /**
     * Data type
     */
    @Expose
    private final String dataType;
    /**
     * UPC code?
     */
    @Expose
    private final String gtinUpc;
    /**
     * Household serving full text
     */
    @Expose
    private final String householdServingFullText;
    /**
     * Ingredients
     */
    @Expose
    private final String ingredients;
    /**
     * Modified date
     */
    @Expose
    private final String modifiedDate;
    /**
     * Publication date
     */
    @Expose
    private final String publicationDate;
    /**
     * Serving size
     */
    @Expose
    private final double servingSize;
    /**
     * Unit for the serving size
     */
    @Expose
    private final String servingSizeUnit;
    /**
     * Food category
     */
    @Expose
    private final String brandedFoodCategory;
    /**
     * Changes
     */
    @Expose
    private final String changes;
    /**
     * Attributes
     */
    @Expose
    private final FoodAttribute[] foodAttributes;

    /**
     * Public constructor
     *
     * @param fdcId                    FDC id
     * @param availableDate            date made available
     * @param brandOwner               brand owner
     * @param dataSource               data source
     * @param dataType                 data type
     * @param gtinUpc                  gtin upc
     * @param householdServingFullText household serving full text
     * @param ingredients              ingredients list from label
     * @param modifiedDate             date last modified
     * @param publicationDate          publication date
     * @param servingSize              serving size
     * @param servingSizeUnit          unit for the serving size
     * @param brandedFoodCategory      food category
     * @param changes                  changes
     * @param foodAttributes           food attributes
     */
    public FoodUpdateLog(int fdcId, String availableDate, String brandOwner, String dataSource, String dataType, String gtinUpc, String householdServingFullText, String ingredients, String modifiedDate, String publicationDate, double servingSize, String servingSizeUnit, String brandedFoodCategory, String changes, FoodAttribute[] foodAttributes) {
        this.fdcId = fdcId;
        this.availableDate = availableDate;
        this.brandOwner = brandOwner;
        this.dataSource = dataSource;
        this.dataType = dataType;
        this.gtinUpc = gtinUpc;
        this.householdServingFullText = householdServingFullText;
        this.ingredients = ingredients;
        this.modifiedDate = modifiedDate;
        this.publicationDate = publicationDate;
        this.servingSize = servingSize;
        this.servingSizeUnit = servingSizeUnit;
        this.brandedFoodCategory = brandedFoodCategory;
        this.changes = changes;
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
     * Getter for the available date
     *
     * @return available date
     */
    public String getAvailableDate() {
        return availableDate;
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
     * Getter for the data source
     *
     * @return data source
     */
    public String getDataSource() {
        return dataSource;
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
     * Getter for the upc code?
     *
     * @return upc code?
     */
    public String getGtinUpc() {
        return gtinUpc;
    }

    /**
     * Getter for the household serving size text
     *
     * @return serving size text
     */
    public String getHouseholdServingFullText() {
        return householdServingFullText;
    }

    /**
     * Getter for the ingredients
     *
     * @return ingredients from the label
     */
    public String getIngredients() {
        return ingredients;
    }

    /**
     * Getter for the date last modified
     *
     * @return date last modified
     */
    public String getModifiedDate() {
        return modifiedDate;
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
     * Getter for the serving size
     *
     * @return serving size
     */
    public double getServingSize() {
        return servingSize;
    }

    /**
     * Getter for the serving size unit
     *
     * @return serving size unit
     */
    public String getServingSizeUnit() {
        return servingSizeUnit;
    }

    /**
     * Getter for the branded food category
     *
     * @return food category
     */
    public String getBrandedFoodCategory() {
        return brandedFoodCategory;
    }

    /**
     * Getter for the changes
     *
     * @return changes
     */
    public String getChanges() {
        return changes;
    }

    /**
     * Getter for the food attributes
     *
     * @return food attributes
     */
    public FoodAttribute[] getFoodAttributes() {
        return foodAttributes;
    }
}
