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
     * Name of the food item.
     */
    private final String name;

    /**
     * NixID from Nutritionix's database.
     */
    private final String nixId;

    /**
     * TagID from Nutritionix's database.
     */
    private final String tagId;

    /**
     * Brand name from Nutritionix's database.
     */
    private final String brandName;

    /**
     * Public constructor for branded item.
     *
     * @param name  name of the item.
     * @param nixId id from Nutritionix's database.
     */
    public FoodItem(String name, String nixId, String brandName) {
        this.name = name;
        this.nixId = nixId;
        this.tagId = null;
        this.brandName = brandName;
    }

    /**
     * Public constructor for common item.
     *
     * @param name  name of the item.
     * @param tagId tagId from Nutritionix's database.
     */
    public FoodItem(String name, String tagId) {
        this.name = name;
        this.nixId = null;
        this.tagId = tagId;
        this.brandName = null;
    }

    /**
     * Public constructor from parcel
     *
     * @param parcel parcel to construct from.
     */
    public FoodItem(Parcel parcel) {
        name = parcel.readString();
        nixId = parcel.readString();
        tagId = parcel.readString();
        brandName = parcel.readString();
    }

    /**
     * Getter for the nix id.
     *
     * @return id from Nutritionix's database
     */
    public String getNixId() {
        return nixId;
    }

    /**
     * Getter for the tagId.
     *
     * @return tag id from Nutritionix's database
     */
    public String getTagId() {
        return tagId;
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
     * Getter for the brand name
     *
     * @return brand name from Nutritionix's database
     */
    public String getBrandName() {
        return brandName;
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
        parcel.writeString(name);
        parcel.writeString(nixId);
        parcel.writeString(tagId);
        parcel.writeString(brandName);
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
