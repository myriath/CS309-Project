package com.example.cs309android.models.USDA.models;

public class InputFoodFoundation {
    private final int id;
    private final String foodDescription;
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
