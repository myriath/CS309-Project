package com.example.cs309android.models.Nutritionix;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

/**
 * Simple FoodItem class that represents an item from Nutritionix's database.
 * Only stores the id to reference the database and the name for displaying.
 * Parcelable to allow it to be easily transferred between activities.
 *
 * @author Mitch Hudson
 */
public class FoodItem implements Parcelable {
    /**
     * Enum of valid food types
     */
    public enum FoodType {
        COMMON, BRANDED, SELF
    }

    /**
     * Name of the food item.
     */
    private final String name;

    /**
     * NixID from Nutritionix's database.
     */
    private final String id;

    /**
     * Type of food object
     * Used to figure out what the id is.
     */
    private final FoodType type;

    private boolean selected = false;

    /**
     * Public constructor for food item.
     *
     * @param name name of the item.
     * @param id   id from Nutritionix's database.
     * @param type type of food item (common, branded, self)
     */
    public FoodItem(String name, String id, FoodType type) {
        this.name = name;
        this.id = id;
        this.type = type;
    }

    /**
     * Public constructor from parcel
     *
     * @param parcel parcel to construct from.
     */
    public FoodItem(Parcel parcel) {
        name = parcel.readString();
        id = parcel.readString();
        type = FoodType.values()[parcel.readInt()];
    }

    /**
     * Getter for the nix id.
     *
     * @return id from Nutritionix's database
     */
    public String getId() {
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
     * Getter for the type.
     *
     * @return Food type depicting what type it is (common, branded, or self)
     */
    public FoodType getType() {
        return type;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * Used to test equivalency when performing searches.
     *
     * @param o Second FoodItem to check against this one.
     * @return True if the two FoodItems are equivalent
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodItem foodItem = (FoodItem) o;
        return Objects.equals(name, foodItem.name) && Objects.equals(id, foodItem.id) && type == foodItem.type;
    }

    /**
     * Generates a hash of this object
     *
     * @return Hash of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, id, type);
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
        parcel.writeString(id);
        parcel.writeInt(type.ordinal());
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
