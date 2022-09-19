package com.example.cs309android.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Simple FoodItem class that represents an item from Nutritionix's database.
 * Only stores the id to reference the database and the name for displaying.
 * Parcelable to allow it to be easily transferred between activities.
 *
 * @author Mitch Hudson
 */
public class FoodItem implements Parcelable {
    /**
     * ID from Nutritionix's database.
     */
    private final int id;
    /**
     * Name of the food item.
     */
    private final String name;

    /**
     * Public constructor.
     *
     * @param id   id from Nutritionix's database.
     * @param name name of the item.
     */
    public FoodItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Public constructor from parcel
     *
     * @param parcel parcel to construct from.
     */
    public FoodItem(Parcel parcel) {
        id = parcel.readInt();
        name = parcel.readString();
    }

    /**
     * Getter for the id.
     *
     * @return id from Nutritionix's database
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for the name.
     *
     * @return name from Nutritionix's database (for display)
     */
    public String getName() {
        return name;
    }

    /**
     * Required method from {@link Parcelable}
     *
     * @return 0
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Required method from {@link Parcelable}
     *
     * @param parcel parcel to write to
     * @param i      flags
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
    }

    /**
     * Required from {@link Parcelable}
     * Creator for creating a parcel of this object
     */
    public static final Parcelable.Creator<FoodItem> CREATOR = new Parcelable.Creator<FoodItem>() {
        public FoodItem createFromParcel(Parcel parcel) {
            return new FoodItem(parcel);
        }

        public FoodItem[] newArray(int size) {
            return new FoodItem[size];
        }
    };
}
