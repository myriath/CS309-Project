package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

/**
 * FDC Input Food Foundation model
 *
 * @author Mitch Hudson
 */
public class InputFoodFoundation {
    /**
     * Foundation id
     */
    @Expose
    private final int id;
    /**
     * Food description
     */
    @Expose
    private final String foodDescription;
    /**
     * Input food
     */
    @Expose
    private final SampleFoodItem inputFood;

    /**
     * Public constructor
     *
     * @param id              foundation id
     * @param foodDescription food description
     * @param inputFood       input food
     */
    public InputFoodFoundation(int id, String foodDescription, SampleFoodItem inputFood) {
        this.id = id;
        this.foodDescription = foodDescription;
        this.inputFood = inputFood;
    }

    /**
     * Getter for the foundation id
     *
     * @return foundation id
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for the food description
     *
     * @return food description
     */
    public String getFoodDescription() {
        return foodDescription;
    }

    /**
     * Getter for the input food
     *
     * @return input food
     */
    public SampleFoodItem getInputFood() {
        return inputFood;
    }
}
