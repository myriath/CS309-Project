package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

/**
 * FDC Food Portion model
 *
 * @author Mitch Hudson
 */
public class FoodPortion {
    /**
     * Portion id
     */
    @Expose
    private final int id;
    /**
     * Portion amount
     */
    @Expose
    private final float amount;
    /**
     * Data points
     */
    @Expose
    private final int dataPoints;
    /**
     * Weight in grams
     */
    @Expose
    private final float gramWeight;
    /**
     * Minimum year acquired
     */
    @Expose
    private final int minYearAcquired;
    /**
     * Modifier
     */
    @Expose
    private final String modifier;
    /**
     * Portion description
     */
    @Expose
    private final String portionDescription;
    /**
     * Sequence number
     */
    @Expose
    private final int sequenceNumber;
    /**
     * Measure unit
     */
    @Expose
    private final MeasureUnit measureUnit;

    /**
     * Public constructor
     *
     * @param id                 portion id
     * @param amount             portion amount
     * @param dataPoints         data points
     * @param gramWeight         weight in grams
     * @param minYearAcquired    minimum year acquired
     * @param modifier           modifier
     * @param portionDescription portion description
     * @param sequenceNumber     sequence number
     * @param measureUnit        measure unit
     */
    public FoodPortion(int id, float amount, int dataPoints, float gramWeight, int minYearAcquired, String modifier, String portionDescription, int sequenceNumber, MeasureUnit measureUnit) {
        this.id = id;
        this.amount = amount;
        this.dataPoints = dataPoints;
        this.gramWeight = gramWeight;
        this.minYearAcquired = minYearAcquired;
        this.modifier = modifier;
        this.portionDescription = portionDescription;
        this.sequenceNumber = sequenceNumber;
        this.measureUnit = measureUnit;
    }

    /**
     * Getter for the portion id
     *
     * @return portion id
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for the portion amount
     *
     * @return portion amount
     */
    public float getAmount() {
        return amount;
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
     * @return gram weight
     */
    public float getGramWeight() {
        return gramWeight;
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
     * Getter for the modifier
     *
     * @return modifier
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * Getter for the portion description
     *
     * @return description
     */
    public String getPortionDescription() {
        return portionDescription;
    }

    /**
     * Getter for the sequence number
     *
     * @return sequence number
     */
    public int getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * Getter for the measure unit
     *
     * @return measure unit
     */
    public MeasureUnit getMeasureUnit() {
        return measureUnit;
    }
}
