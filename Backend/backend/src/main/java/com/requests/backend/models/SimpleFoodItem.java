package com.requests.backend.models;

import com.google.gson.annotations.Expose;

/**
 * Simple food item used for displaying and moving data in the app
 *
 * @author Mitch Hudson
 */
public class SimpleFoodItem {

    /**
     * FDC ID from the api
     */
    @Expose
    private int fdcId;

    /**
     * Description / Item name from api
     */
    @Expose
    private String description;
    /**
     * True if the item should appear with strikeout on the shopping list
     */
    @Expose
    private boolean stricken;

    /**
     * Constructor for gson
     *
     * @param fdcId       item id
     * @param description description / title
     */
    public SimpleFoodItem(int fdcId, String description) {
        this.fdcId = fdcId;
        this.description = description;
        this.stricken = false;
    }

    /**
     * Constructor for gson
     *
     * @param fdcId       item id
     * @param description description / title
     * @param stricken    true if the item should appear with strikeout on the shopping list
     */
    public SimpleFoodItem(int fdcId, String description, boolean stricken) {
        this.fdcId = fdcId;
        this.description = description;
        this.stricken = stricken;
    }

    /**
     * Getter for the id
     *
     * @return item id
     */
    public int getFdcId() {
        return fdcId;
    }

    /**
     * Getter for the description
     *
     * @return item description / title
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for the stricken boolean
     *
     * @return true if this item should be rendered with strikeout
     */
    public boolean isStricken() {
        return stricken;
    }

    /**
     * Setter for the stricken boolean
     *
     * @param stricken true if this item should be rendered with strikeout
     */
    public void setStricken(boolean stricken) {
        this.stricken = stricken;
    }

}
