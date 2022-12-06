package com.example.cs309android.models.api.models;

import static com.example.cs309android.util.Constants.ITEM_ID_NULL;

import android.os.Parcel;

import com.google.gson.annotations.Expose;

/**
 * Simple food log item model
 *
 * @author Travis Massner
 */
public class FoodLogItem {
    /**
     * Food log id
     */
    @Expose
    private final int logId;
    /**
     * User username
     */
    @Expose
    private final String username;
    /**
     * Food item id
     */
    @Expose
    private final int fdcId;
    /**
     * Food item name
     */
    @Expose
    private final String foodName;
    /**
     * Serving amount
     */
    @Expose
    private final int servingAmt;
    /**
     * Serving unit
     */
    @Expose
    private final String servingUnit;
    /**
     * Fat amount
     */
    @Expose
    private final float fat;
    /**
     * Saturated fat amount
     */
    @Expose
    private final int sat_fat;
    /**
     * Sodium amount
     */
    @Expose
    private final int sodium;
    /**
     * Carbohydrate amount
     */
    @Expose
    private final float carbohydrates;
    /**
     * Fiber amount
     */
    @Expose
    private final float fiber;
    /**
     * Sugar amount
     */
    @Expose
    private final float sugars;
    /**
     * Protein amount
     */
    @Expose
    private final float protein;
    /**
     * Date of log
     */
    @Expose
    private final String date;
    /**
     * Meal type
     */
    @Expose
    private final String meal;
    /**
     * Calories
     */
    @Expose
    private float calories;

    /**
     * Constructor for add food log item
     * @param item SimpleFoodItem with food log item data
     */
    public FoodLogItem(SimpleFoodItem item) {
        this.logId = ITEM_ID_NULL;
        this.username = "papajohn";
        this.fdcId = item.getId();
        this.foodName = item.getDescription();
        this.servingAmt = 0;
        this.servingUnit = "";
        this.fat = item.getFat();
        this.sat_fat = 0;
        this.sodium = 0;
        this.carbohydrates = item.getCarbs();
        this.fiber = 0;
        this.sugars = 0;
        this.protein = item.getProtein();
        this.date = item.getDate();
        this.meal = item.getMeal();
        this.calories = item.getCalories();
    }

    /**
     * Constructor for gson
     *
     * @param logId         Log id
     * @param username      Username
     * @param fdcId         Food item id
     * @param foodName      Food item name
     * @param servingAmt    Serving amount
     * @param servingUnit   Serving unit
     * @param fat           Fat amount
     * @param sat_fat       Saturated fat amount
     * @param sodium        Sodium amount
     * @param carbohydrates Carbohydrate amount
     * @param fiber         Fiber amount
     * @param sugars        Sugar amount
     * @param protein       Protein amount
     * @param date          Date of log
     * @param meal          Meal type
     */
    public FoodLogItem(int logId, String username, int fdcId, String foodName, int servingAmt, String servingUnit, int fat, int sat_fat, int sodium, int carbohydrates, int fiber, int sugars, int protein, String date, String meal) {
        this.logId = logId;
        this.username = username;
        this.fdcId = fdcId;
        this.foodName = foodName;
        this.servingAmt = servingAmt;
        this.servingUnit = servingUnit;
        this.fat = fat;
        this.sat_fat = sat_fat;
        this.sodium = sodium;
        this.carbohydrates = carbohydrates;
        this.fiber = fiber;
        this.sugars = sugars;
        this.protein = protein;
        this.date = date;
        this.meal = meal;
    }

    /**
     * Constructor for creating a new food log item
     *
     * @param username Username
     * @param foodName Food item name
     */
    public FoodLogItem(String username, String foodName) {
        this.logId = ITEM_ID_NULL;
        this.username = username;
        this.fdcId = ITEM_ID_NULL;
        this.foodName = foodName;
        this.servingAmt = 0;
        this.servingUnit = "";
        this.fat = 0;
        this.sat_fat = 0;
        this.sodium = 0;
        this.carbohydrates = 0;
        this.fiber = 0;
        this.sugars = 0;
        this.protein = 0;
        this.date = "";
        this.meal = "";
    }

    /**
     * Constructor for new food log item
     *
     * @param username      Username
     * @param fdcId         Food item id
     * @param foodName      Food item name
     * @param servingAmt    Serving amount
     * @param servingUnit   Serving unit
     * @param fat           Fat amount
     * @param sat_fat       Saturated fat amount
     * @param sodium        Sodium amount
     * @param carbohydrates Carbohydrate amount
     * @param fiber         Fiber amount
     * @param sugars        Sugar amount
     * @param protein       Protein amount
     * @param date          Date of log
     * @param meal          Meal type
     */
    public FoodLogItem(String username, int fdcId, String foodName, int servingAmt, String servingUnit, int fat, int sat_fat, int sodium, int carbohydrates, int fiber, int sugars, int protein, String date, String meal) {
        this.logId = ITEM_ID_NULL;
        this.username = username;
        this.fdcId = fdcId;
        this.foodName = foodName;
        this.servingAmt = servingAmt;
        this.servingUnit = servingUnit;
        this.fat = fat;
        this.sat_fat = sat_fat;
        this.sodium = sodium;
        this.carbohydrates = carbohydrates;
        this.fiber = fiber;
        this.sugars = sugars;
        this.protein = protein;
        this.date = date;
        this.meal = meal;
    }

    /**
     * Constructor for parcelable
     *
     * @param in Parcel
     */
    protected FoodLogItem(Parcel in) {
        logId = in.readInt();
        username = in.readString();
        fdcId = in.readInt();
        foodName = in.readString();
        servingAmt = in.readInt();
        servingUnit = in.readString();
        fat = in.readInt();
        sat_fat = in.readInt();
        sodium = in.readInt();
        carbohydrates = in.readInt();
        fiber = in.readInt();
        sugars = in.readInt();
        protein = in.readInt();
        date = in.readString();
        meal = in.readString();
    }

    /**
     * Getter for the log id
     *
     * @return log id
     */
    public int getLogId() {
        return logId;
    }

    /**
     * Getter for the username
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter for the fdc id
     *
     * @return fdc id
     */
    public int getFdcId() {
        return fdcId;
    }

    /**
     * Getter for the food's name
     *
     * @return food name
     */
    public String getFoodName() {
        return foodName;
    }

    /**
     * Getter for the serving amount
     *
     * @return serving amount
     */
    public int getServingAmt() {
        return servingAmt;
    }

    /**
     * Getter for the serving unit
     *
     * @return serving unit
     */
    public String getServingUnit() {
        return servingUnit;
    }

    /**
     * Getter for the fat
     *
     * @return fat
     */
    public float getFat() {
        return fat;
    }

    /**
     * Getter for the saturated fat
     *
     * @return saturated fat
     */
    public int getSat_fat() {
        return sat_fat;
    }

    /**
     * Getter for the sodium
     *
     * @return sodium
     */
    public int getSodium() {
        return sodium;
    }

    /**
     * Getter for the carbohydrates
     *
     * @return carbs
     */
    public float getCarbohydrates() {
        return carbohydrates;
    }

    /**
     * Getter for the fiber
     *
     * @return fiber
     */
    public float getFiber() {
        return fiber;
    }

    /**
     * Getter for the sugars
     *
     * @return sugars
     */
    public float getSugars() {
        return sugars;
    }

    /**
     * Getter for the protein
     *
     * @return protein
     */
    public float getProtein() {
        return protein;
    }

    /**
     * Getter for the date
     *
     * @return date of the logged item
     */
    public String getDate() {
        return date;
    }

    /**
     * Getter for the calories
     */
    public float getCalories() {
        return calories;
    }

    /**
     * Getter for the meal value
     * Ex: Lunch, Dinner
     *
     * @return meal value
     */
    public String getMeal() {
        return meal;
    }
}
