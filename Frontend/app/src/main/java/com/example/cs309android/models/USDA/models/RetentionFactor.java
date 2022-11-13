package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

/**
 * FDC Retention Factor model
 *
 * @author Mitch Hudson
 */
public class RetentionFactor {
    /**
     * Factor id
     */
    @Expose
    private final int id;
    /**
     * Factor code
     */
    @Expose
    private final int code;
    /**
     * Description
     */
    @Expose
    private final String description;

    /**
     * Public constructor
     *
     * @param id          factor id
     * @param code        factor code
     * @param description description
     */
    public RetentionFactor(int id, int code, String description) {
        this.id = id;
        this.code = code;
        this.description = description;
    }

    /**
     * Getter for the retention factor id
     *
     * @return factor id
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for the retention factor code
     *
     * @return factor code
     */
    public int getCode() {
        return code;
    }

    /**
     * Getter for the retention factor description
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }
}
