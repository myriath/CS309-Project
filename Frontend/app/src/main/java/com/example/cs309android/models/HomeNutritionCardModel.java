package com.example.cs309android.models;

import android.graphics.Bitmap;

/**
 * Models the data on the home nutrition card
 *
 * @author Mitch Hudson
 */
public class HomeNutritionCardModel {
    /**
     * Name of the nutrient
     */
    private final String title;
    /**
     * Image to display
     */
    private final Bitmap image;
    /**
     * Current amount of the nutrient tracked
     */
    private double amount;
    /**
     * Goal / limit to the day's nutrient
     */
    private double limit;

    public HomeNutritionCardModel(String title, Bitmap image) {
        this.title = title;
        this.image = image;
    }

    /**
     * Getter for the amount
     * @return Currently tracked amount for the nutrient
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Setter for the amount
     * @param amount New tracked amount for the nutrient
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Getter for the limit
     * @return Limit / target goal of this nutrient
     */
    public double getLimit() {
        return limit;
    }

    /**
     * Setter for the limit
     * @param limit New limit / target goal of this nutrient
     */
    public void setLimit(double limit) {
        this.limit = limit;
    }

    /**
     * Getter for the title
     * @return Name of the nutrient
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter for the image
     * @return Image to display for this nutrient card
     */
    public Bitmap getImage() {
        return image;
    }
}
