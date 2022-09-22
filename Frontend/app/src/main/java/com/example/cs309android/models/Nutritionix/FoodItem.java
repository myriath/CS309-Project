package com.example.cs309android.models.Nutritionix;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Arrays;
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
     * Food name
     */
    private final String food_name;
    /**
     * Serving size unit
     */
    private final String serving_unit;
    /**
     * Number of serving units per unit
     */
    private final Double serving_qty;
    /**
     * Food label claims list.
     * (Atkins friendly, contains dairy, etc.)
     */
    private final String[] claims;
    /**
     * Nutrient array containing all nutrient information.
     */
    private final Nutrient[] full_nutrients;
    /**
     * Photo object for the item
     */
    private final Photo photo;
    /**
     * Localization value
     */
    private final String locale;

    /**
     * Public constructor for use with GSON
     *
     * @param food_name            name of the food
     * @param serving_unit         serving size unit of the food
     * @param serving_qty          number of servings per unit of the food
     * @param claims               label claims
     * @param full_nutrients       nutrients of the food
     * @param photo                photo of the food
     * @param locale               locale
     */
    public FoodItem(String food_name, String serving_unit, double serving_qty, String[] claims, Nutrient[] full_nutrients, Photo photo, String locale) {
        this.food_name = food_name;
        this.serving_unit = serving_unit;
        this.serving_qty = serving_qty;
        this.claims = claims;
        this.full_nutrients = full_nutrients;
        this.photo = photo;
        this.locale = locale;
    }

    /**
     * Public constructor from parcel
     *
     * @param parcel parcel to construct from.
     */
    public FoodItem(Parcel parcel) {
        food_name = parcel.readString();
        serving_unit = parcel.readString();
        serving_qty = parcel.readDouble();
        claims = parcel.createStringArray();
        full_nutrients = parcel.createTypedArray(Nutrient.CREATOR);
        photo = parcel.readParcelable(Photo.class.getClassLoader());
        locale = parcel.readString();
    }

    /**
     * Getter for food_name
     *
     * @return Food name
     */
    public String getFoodName() {
        return food_name;
    }

    /**
     * Getter for serving_unit
     *
     * @return Serving unit
     */
    public String getServingUnit() {
        return serving_unit;
    }

    /**
     * Getter for serving_qty
     *
     * @return Serving unit quantity per unit
     */
    public Double getServingQty() {
        return serving_qty;
    }

    /**
     * Getter for claims
     *
     * @return Food label claims (Atkins friendly, dairy free, etc.)
     */
    public String[] getClaims() {
        return claims;
    }

    /**
     * Getter for full_nutrients
     *
     * @return Nutrient array of nutrients in the food item
     */
    public Nutrient[] getFullNutrients() {
        return full_nutrients;
    }

    /**
     * Getter for photo
     *
     * @return photo object for the food item
     */
    public Photo getPhoto() {
        return photo;
    }

    /**
     * Getter for locale
     *
     * @return Localization value
     */
    public String getLocale() {
        return locale;
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
        return Objects.equals(food_name, foodItem.food_name) && Objects.equals(serving_qty, foodItem.serving_qty);
    }

    /**
     * Generates a hash of this object
     *
     * @return Hash of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(food_name, serving_qty);
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
        parcel.writeString(food_name);
        parcel.writeParcelable(photo, i);
        parcel.writeTypedArray(full_nutrients, i);
        parcel.writeString(serving_unit);
        parcel.writeDouble(serving_qty);
        parcel.writeStringArray(claims);
        parcel.writeString(locale);
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

    @NonNull
    @Override
    public String toString() {
        return "FoodItem{" +
                "food_name='" + food_name + '\'' +
                ", serving_unit='" + serving_unit + '\'' +
                ", serving_qty=" + serving_qty +
                ", claims=" + Arrays.toString(claims) +
                ", full_nutrients=" + Arrays.toString(full_nutrients) +
                ", photo=" + photo +
                ", locale='" + locale + '\'' +
                '}';
    }
}
