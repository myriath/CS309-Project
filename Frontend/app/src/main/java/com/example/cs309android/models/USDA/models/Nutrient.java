package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

/**
 * FDC Nutrient model
 *
 * @author Mitch Hudson
 */
public class Nutrient {
    /**
     * Nutrient id
     */
    @Expose
    private final int id;
    /**
     * Nutrient number
     */
    @Expose
    private final String number;
    /**
     * Nutrient name
     */
    @Expose
    private final String name;
    /**
     * Nutrient rank
     */
    @Expose
    private final int rank;
    /**
     * Unit name
     */
    @Expose
    private final String unitName;

    /**
     * Public constructor
     *
     * @param id       nutrient id
     * @param number   nutrient number
     * @param name     nutrient name
     * @param rank     nutrient rank
     * @param unitName unit name
     */
    public Nutrient(int id, String number, String name, int rank, String unitName) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.rank = rank;
        this.unitName = unitName;
    }

    /**
     * Getter for the nutrient id
     *
     * @return nutrient id
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for the nutrient number
     *
     * @return nutrient number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Getter for the nutrient name
     *
     * @return nutrient name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the nutrient rank
     *
     * @return nutrient rank
     */
    public int getRank() {
        return rank;
    }

    /**
     * Getter for the unit name
     *
     * @return unit name
     */
    public String getUnitName() {
        return unitName;
    }
}
