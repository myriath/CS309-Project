package com.requests.backend.models.requests;

import java.sql.Date;

public class FoodLogAddRequest {

    /**
     * The fdcId of the food item
     */
    private int fdcId;

    /**
     * The name of the food item
     */
    private String foodName;

    /**
     * Amount of servings consumed by the user
     */
    private double servingAmt;

    /**
     * What servings are measured in (i.e. g, oz, Ml, etc.)
     */
    private String servingUnit;

    // Label nutrient variables //

    /**
     * The amount of fat per serving in grams
     */
    private double fat;

    /**
     * The amount of saturated fat per serving, in grams
     */
    private double satFat;

    /**
     * The amount of sodium per serving, in milligrams
     */
    private double sodium;

    /**
     * The amount of carbohydrates per serving, in grams
     */
    private double carbohydrates;

    /**
     * The amount of fiber per serving, in grams
     */
    private double fiber;

    /**
     * The amount of sugars per serving, in grams
     */
    private double sugars;

    /**
     * The amount of protein per serving, in grams
     */
    private double protein;

    /**
     * Date the food was added to the log
     */
    private Date date;

    /**
     * Which meal the food is associated with. Can be Breakfast, Lunch, Dinner, or Snack.
     */
    private String meal;

    public int getFdcId() {
        return fdcId;
    }

    public void setFdcId(int fdcId) {
        this.fdcId = fdcId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getServingAmt() {
        return servingAmt;
    }

    public void setServingAmt(double servingAmt) {
        this.servingAmt = servingAmt;
    }

    public String getServingUnit() {
        return servingUnit;
    }

    public void setServingUnit(String servingUnit) {
        this.servingUnit = servingUnit;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getSatFat() {
        return satFat;
    }

    public void setSatFat(double satFat) {
        this.satFat = satFat;
    }

    public double getSodium() {
        return sodium;
    }

    public void setSodium(double sodium) {
        this.sodium = sodium;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public double getFiber() {
        return fiber;
    }

    public void setFiber(double fiber) {
        this.fiber = fiber;
    }

    public double getSugars() {
        return sugars;
    }

    public void setSugars(double sugars) {
        this.sugars = sugars;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }
}
