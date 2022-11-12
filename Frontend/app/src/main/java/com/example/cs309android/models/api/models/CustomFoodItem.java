package com.example.cs309android.models.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

/**
 * Simple food item used for displaying and moving data in the app
 *
 * @author Mitch Hudson
 */
public class CustomFoodItem implements Parcelable {
    /**
     * Database id for the custom foods table
     */
    @Expose
    private final int dbId;
    /**
     * Custom item name
     */
    @Expose
    private final String name;
    /**
     * Custom item ingredients
     */
    @Expose
    private final String ingredients;
    /**
     * Custom item calories
     */
    @Expose
    private final float calories;
    /**
     * Custom item fat
     */
    @Expose
    private final float fat;
    /**
     * Custom item carbohydrates
     */
    @Expose
    private final float carbs;
    /**
     * Custom item protein
     */
    @Expose
    private final float protein;
    /**
     * True if the item should appear with strikeout on the shopping list
     */
    @Expose
    private boolean stricken;

    /**
     * Public constructor
     *
     * @param dbId        ID for the custom item
     * @param name        Name of the item
     * @param ingredients Ingredients of the item
     * @param calories    Calories in the item
     * @param fat         Fat in the item (grams)
     * @param carbs       Carbohydrates in the item (grams)
     * @param protein     Protein in the item (grams)
     */
    public CustomFoodItem(int dbId, String name, String ingredients, float calories, float fat, float carbs, float protein) {
        this.dbId = dbId;
        this.name = name;
        this.ingredients = ingredients;
        this.calories = calories;
        this.fat = fat;
        this.carbs = carbs;
        this.protein = protein;
        stricken = false;
    }

    /**
     * Public constructor
     *
     * @param dbId        ID for the custom item
     * @param name        Name of the item
     * @param ingredients Ingredients of the item
     * @param calories    Calories in the item
     * @param fat         Fat in the item (grams)
     * @param carbs       Carbohydrates in the item (grams)
     * @param protein     Protein in the item (grams)
     * @param stricken    True if the item is stricken on the grocery list
     */
    public CustomFoodItem(int dbId, String name, String ingredients, float calories, float fat, float carbs, float protein, boolean stricken) {
        this.dbId = dbId;
        this.name = name;
        this.ingredients = ingredients;
        this.calories = calories;
        this.fat = fat;
        this.carbs = carbs;
        this.protein = protein;
        this.stricken = stricken;
    }

    /**
     * Constructor from parcel
     *
     * @param in Parcel to unpack
     */
    protected CustomFoodItem(Parcel in) {
        dbId = in.readInt();
        name = in.readString();
        ingredients = in.readString();
        calories = in.readFloat();
        fat = in.readFloat();
        carbs = in.readFloat();
        protein = in.readFloat();
        stricken = in.readBoolean();
    }

    /**
     * Parcelable required CREATOR object
     */
    public static final Creator<CustomFoodItem> CREATOR = new Creator<CustomFoodItem>() {
        @Override
        public CustomFoodItem createFromParcel(Parcel in) {
            return new CustomFoodItem(in);
        }

        @Override
        public CustomFoodItem[] newArray(int size) {
            return new CustomFoodItem[size];
        }
    };

    /**
     * Getter for the custom id
     *
     * @return item id
     */
    public int getDbId() {
        return dbId;
    }

    /**
     * Getter for the description
     *
     * @return item description / title
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the stricken boolean
     *
     * @return true if this item should be rendered with strikeout
     */
    public boolean isStricken() {
        return stricken;
    }

    /**
     * Setter for the stricken boolean
     *
     * @param stricken true if this item should be rendered with strikeout
     */
    public void setStricken(boolean stricken) {
        this.stricken = stricken;
    }

    /**
     * Getter for the ingredients
     * @return ingredients
     */
    public String getIngredients() {
        return ingredients;
    }

    /**
     * Getter for the calories
     * @return calories
     */
    public float getCalories() {
        return calories;
    }

    /**
     * Getter for the fat
     * @return fat
     */
    public float getFat() {
        return fat;
    }

    /**
     * Getter for the carbs
     * @return carbs
     */
    public float getCarbs() {
        return carbs;
    }

    /**
     * Getter for the protein
     * @return protein
     */
    public float getProtein() {
        return protein;
    }

    /**
     * Parcelable required method
     *
     * @return 0
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Adds this object to a parcel
     *
     * @param parcel parcel to add this object to
     * @param i      flags
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(dbId);
        parcel.writeString(name);
        parcel.writeString(ingredients);
        parcel.writeFloat(calories);
        parcel.writeFloat(fat);
        parcel.writeFloat(carbs);
        parcel.writeFloat(protein);
        parcel.writeBoolean(stricken);
    }
}
