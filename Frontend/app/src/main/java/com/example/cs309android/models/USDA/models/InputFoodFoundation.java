package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

public class InputFoodFoundation {
    @Expose
    private final int id;
    @Expose
    private final String foodDescription;
    @Expose
    private final SampleFoodItem inputFood;

    public InputFoodFoundation(int id, String foodDescription, SampleFoodItem inputFood) {
        this.id = id;
        this.foodDescription = foodDescription;
        this.inputFood = inputFood;
    }

    public int getId() {
        return id;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public SampleFoodItem getInputFood() {
        return inputFood;
    }
}
