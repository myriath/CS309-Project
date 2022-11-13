package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

/**
 * FDC Measure Unit model
 *
 * @author Mitch Hudson
 */
public class MeasureUnit {
    /**
     * Unit id
     */
    @Expose
    private final int id;
    /**
     * Unit abbreviation
     */
    @Expose
    private final String abbreviation;
    /**
     * Unit full name
     */
    @Expose
    private final String name;

    /**
     * Public constructor
     *
     * @param id           unit id
     * @param abbreviation unit abbreviation
     * @param name         unit full name
     */
    public MeasureUnit(int id, String abbreviation, String name) {
        this.id = id;
        this.abbreviation = abbreviation;
        this.name = name;
    }

    /**
     * Getter for the unit id
     *
     * @return unit id
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for the unit abbreviation
     *
     * @return unit abbreviation
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * Getter for the full name
     *
     * @return full name
     */
    public String getName() {
        return name;
    }
}
