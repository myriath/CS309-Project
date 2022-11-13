package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

/**
 * FDC Food Component model
 *
 * @author Mitch Hudson
 */
public class FoodComponent {
    /**
     * Component id
     */
    @Expose
    private final int id;
    /**
     * Component name
     */
    @Expose
    private final String name;
    /**
     * Component data points
     */
    @Expose
    private final int dataPoints;
    /**
     * Component weight (grams)
     */
    @Expose
    private final float gramWeight;
    /**
     * Is refuse
     */
    @Expose
    private final boolean isRefuse;
    /**
     * Minimum year acquired
     */
    @Expose
    private final int minYearAcquired;
    /**
     * Percent weight
     */
    @Expose
    private final float percentWeight;

    /**
     * Public constructor
     *
     * @param id              component id
     * @param name            component name
     * @param dataPoints      data points
     * @param gramWeight      gram weight
     * @param isRefuse        is refuse
     * @param minYearAcquired minimum year acquired
     * @param percentWeight   percent weight
     */
    public FoodComponent(int id, String name, int dataPoints, float gramWeight, boolean isRefuse, int minYearAcquired, float percentWeight) {
        this.id = id;
        this.name = name;
        this.dataPoints = dataPoints;
        this.gramWeight = gramWeight;
        this.isRefuse = isRefuse;
        this.minYearAcquired = minYearAcquired;
        this.percentWeight = percentWeight;
    }

    /**
     * Getter for the component id
     *
     * @return component id
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for the component name
     *
     * @return component name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the data points
     *
     * @return data points
     */
    public int getDataPoints() {
        return dataPoints;
    }

    /**
     * Getter for the gram weight
     *
     * @return weight in grams
     */
    public float getGramWeight() {
        return gramWeight;
    }

    /**
     * Getter for the isRefuse boolean
     *
     * @return isRefuse boolean
     */
    public boolean isRefuse() {
        return isRefuse;
    }

    /**
     * Getter for the minimum year acquired
     *
     * @return minimum year acquired
     */
    public int getMinYearAcquired() {
        return minYearAcquired;
    }

    /**
     * Getter for the percent weight
     *
     * @return percent weight
     */
    public float getPercentWeight() {
        return percentWeight;
    }
}
