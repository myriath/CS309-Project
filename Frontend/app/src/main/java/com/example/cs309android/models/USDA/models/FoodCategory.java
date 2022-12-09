package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

/**
 * FDC Food Category model
 *
 * @author Mitch Hudson
 */
public class FoodCategory {
    /**
     * Category id
     */
    @Expose
    private final int id;
    /**
     * Category code
     */
    @Expose
    private final String code;
    /**
     * Category description
     */
    @Expose
    private final String description;

    /**
     * Public constructor
     *
     * @param id          category id
     * @param code        category code
     * @param description category description
     */
    public FoodCategory(int id, String code, String description) {
        this.id = id;
        this.code = code;
        this.description = description;
    }

    /**
     * Getter for the id
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for the code
     *
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * Getter for the description
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }
}
