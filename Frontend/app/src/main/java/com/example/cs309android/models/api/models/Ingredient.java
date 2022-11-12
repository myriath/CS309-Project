package com.example.cs309android.models.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

/**
 * Ingredient class contains a SimpleFoodItem and the quantity / units for it.
 * Used in recipes
 *
 * @author Mitch Hudson
 */
public class Ingredient implements Parcelable {
    /**
     * Food item that this ingredient is.
     */
    @Expose
    private final SimpleFoodItem food;
    /**
     * How many unit units is used in the recipe
     */
    @Expose
    private float quantity;
    /**
     * Unit for the quantity measurement.
     */
    @Expose
    private String unit;

    /**
     * Public constructor
     *
     * @param food     Food item for nutrition details
     * @param quantity Quantity of the food item
     * @param unit     Unit the quantity is measured in
     */
    public Ingredient(SimpleFoodItem food, float quantity, String unit) {
        this.food = food;
        this.quantity = quantity;
        this.unit = unit;
    }

    /**
     * Parcel constructor
     *
     * @param in parcel to unpack
     */
    protected Ingredient(Parcel in) {
        food = in.readParcelable(SimpleFoodItem.class.getClassLoader());
        quantity = in.readFloat();
        unit = in.readString();
    }

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

    /**
     * Getter for the food item
     *
     * @return food item
     */
    public SimpleFoodItem getFood() {
        return food;
    }

    /**
     * Getter for the quantity
     *
     * @return quantity
     */
    public float getQuantity() {
        return quantity;
    }

    /**
     * Getter for the unit
     *
     * @return units
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Setter for the quantity
     *
     * @param quantity new quantity
     */
    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    /**
     * Setter for the unit
     *
     * @param unit new unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * Unused for this class
     *
     * @return 0
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Writes this object to the given parcel
     *
     * @param parcel Parcel to write to
     * @param i      flags
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(food, i);
        parcel.writeFloat(quantity);
        parcel.writeString(unit);
    }
}
