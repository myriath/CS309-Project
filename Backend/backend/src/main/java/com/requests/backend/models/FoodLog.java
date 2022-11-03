package com.requests.backend.models;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="food_log")
public class FoodLog {


    /**
     * Automatically generated ID Primary Key for the given logged food
     */
    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int logId;

    /**
     * The username of the user the food was logged by
     */
    @Expose
    private String username;


    /**
     * The fdcId of the food item
     */
    @Expose
    private int fdcId;

    /**
     * The name of the food item
     */
    @Expose
    private String foodName;

    /**
     * Amount of servings consumed by the user
     */
    @Expose
    private double servingAmt;

    /**
     * Amount of servings consumed by the user
     */
    @Expose
    private String servingUnit;

    // Label nutrient variables //

    /**
     * The amount of fat per serving in grams
     */
    @Expose
    private double fat;

    /**
     * The amount of saturated fat per serving, in grams
     */
    @Expose
    private double sat_fat;

    /**
     * The amount of sodium per serving, in milligrams
     */
    @Expose
    private double sodium;

    /**
     * The amount of carbohydrates per serving, in grams
     */
    @Expose
    private double carbohydrates;

    /**
     * The amount of fiber per serving, in grams
     */
    @Expose
    private double fiber;

    /**
     * The amount of sugars per serving, in grams
     */
    @Expose
    private double sugars;

    /**
     * The amount of protein per serving, in grams
     */
    @Expose
    private double protein;

    /**
     * Date the food was added to the log
     */
    @Expose
    private Date date;

    /**
     * Which meal the food is associated with. Can be Breakfast, Lunch, Dinner, or Snack.
     */
    @Expose
    private String meal;

}
