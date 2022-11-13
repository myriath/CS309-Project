package com.requests.backend.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="food_log")
public class FoodLog {


    /**
     * Automatically generated ID Primary Key for the given logged food
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int logId;

    /**
     * The username of the user the food was logged by
     */
    private String username;


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
     * Amount of servings consumed by the user
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
    private double sat_fat;

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

}
