package com.example.cs309android.models.Nutritionix;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Represents a common type food item from the Nutritionix API
 *
 * @author Mitch Hudson
 */
public class CommonFood extends FoodItem implements Parcelable {
    /**
     * Name of the common tag
     */
    private final String tag_name;
    /**
     * Type of common item
     * (restaurant, grocery)
     */
    private final String common_type;
    /**
     * Id for the common tag
     */
    private final String tag_id;

    /**
     * Public constructor for use with GSON
     *
     * @param food_name            name of the food
     * @param serving_unit         unit for the serving size
     * @param tag_name             name of the tag
     * @param serving_qty          number of servings per unit
     * @param common_type          type of common item
     * @param claims               label claims
     * @param tag_id               tag id
     * @param photo                photo object
     * @param full_nutrients       nutrients array
     * @param serving_weight_grams weight of serving in grams
     * @param locale               locale
     */
    public CommonFood(String food_name, String serving_unit, String tag_name, Integer serving_qty,
                      String common_type, String[] claims, String tag_id, Photo photo,
                      Nutrient[] full_nutrients, Double serving_weight_grams, String locale) {
        super(food_name, serving_unit, serving_weight_grams, serving_qty, claims, full_nutrients, photo, locale);
        this.tag_name = tag_name;
        this.common_type = common_type;
        this.tag_id = tag_id;
    }

    /**
     * Public constructor.
     * Extracts a CommonFood from the given parcel.
     *
     * @param parcel Parcel containing a common food item
     */
    public CommonFood(Parcel parcel) {
        super(parcel);
        tag_name = parcel.readString();
        common_type = parcel.readString();
        tag_id = parcel.readString();
    }

    /**
     * Parcelable CREATOR object.
     * Used to create common foods from parcels
     */
    public static final Creator<CommonFood> CREATOR = new Creator<CommonFood>() {
        @Override
        public CommonFood createFromParcel(Parcel in) {
            return new CommonFood(in);
        }

        @Override
        public CommonFood[] newArray(int size) {
            return new CommonFood[size];
        }
    };

    /**
     * Getter for tag_name
     *
     * @return Name of the common tag
     */
    public String getTagName() {
        return tag_name;
    }

    /**
     * Getter for common_type
     *
     * @return Common type (restaurant, grocery, none)
     */
    public String getCommonType() {
        return common_type;
    }

    /**
     * Getter for tag_id
     *
     * @return ID for the common tag
     */
    public String getTagId() {
        return tag_id;
    }

    /**
     * Parcelable method
     *
     * @return 0
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Writes this object to a parcel
     *
     * @param parcel parcel to write to
     * @param i      flags
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(tag_name);
        parcel.writeString(common_type);
        parcel.writeString(tag_id);
    }
}
