package com.example.cs309android.models.Nutritionix;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Represents a common type food item from the Nutritionix API
 *
 * @author Mitch Hudson
 */
public class CommonFood extends FoodItem implements Parcelable {
    /**
     * Food name
     */
    private final String food_name;
    /**
     * Serving size unit
     */
    private final String serving_unit;
    /**
     * Name of the common tag
     */
    private final String tag_name;
    /**
     * Number of serving units per unit
     */
    private final Integer serving_qty;
    /**
     * Type of common item
     * (restaurant, grocery)
     */
    private final String common_type;
    /**
     * Food label claims list.
     * (Atkins friendly, contains dairy, etc.)
     */
    private final String[] claims;
    /**
     * Id for the common tag
     */
    private final String tag_id;
    /**
     * Photo object for the item
     */
    private final Photo photo;
    /**
     * Nutrient array containing all nutrient information.
     */
    private final Nutrient[] full_nutrients;
    /**
     * Weight of the serving unit in grams
     */
    private final Double serving_weight_grams;
    /**
     * Localization value
     */
    private final String locale;

    /**
     * Public constructor.
     * Creates a new CommonFood object based on the json given.
     *
     * @param json JSON response from Nutritionix API
     */
    public CommonFood(JSONObject json) {
        super(json.optString("food_name"), null, FoodType.COMMON);
        food_name = json.optString("food_name");
        serving_unit = json.optString("serving_unit");
        tag_name = json.optString("tag_name");
        serving_qty = json.optInt("serving_qty");
        common_type = json.optString("common_type");
        JSONArray array = json.optJSONArray("claims");
        claims = array != null ? new String[array.length()] : new String[0];
        for (int i = 0; i < claims.length; i++) {
            claims[i] = array.optString(i);
        }
        tag_id = json.optString("tag_id");
        JSONObject photoJson = json.optJSONObject("photo");
        photo = photoJson != null ? new Photo(photoJson) : null;
        array = json.optJSONArray("full_nutrients");
        full_nutrients = array != null ? new Nutrient[array.length()] : new Nutrient[0];
        for (int i = 0; i < full_nutrients.length; i++) {
            JSONObject nutrientJson = array.optJSONObject(i);
            Nutrient nutrient = Nutrient.lookup(nutrientJson.optInt("attr_id"));
            if (nutrient == null) continue;
            full_nutrients[i] = new Nutrient(nutrient.getAttrId(), nutrientJson.optInt("value"));
        }
        serving_weight_grams = json.optDouble("serving_weight_grams");
        locale = json.optString("locale");
    }

    /**
     * Public constructor.
     * Extracts a CommonFood from the given parcel.
     *
     * @param parcel Parcel containing a common food item
     */
    public CommonFood(Parcel parcel) {
        super(parcel);
        food_name = parcel.readString();
        serving_unit = parcel.readString();
        tag_name = parcel.readString();
        serving_qty = parcel.readInt();
        common_type = parcel.readString();
        claims = parcel.createStringArray();
        tag_id = parcel.readString();
        photo = parcel.readParcelable(Photo.class.getClassLoader());
        full_nutrients = parcel.createTypedArray(Nutrient.CREATOR);
        serving_weight_grams = parcel.readDouble();
        locale = parcel.readString();
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
     * Getter for tag_name
     *
     * @return Name of the common tag
     */
    public String getTagName() {
        return tag_name;
    }

    /**
     * Getter for serving_qty
     *
     * @return Serving unit quantity per unit
     */
    public Integer getServingQty() {
        return serving_qty;
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
     * Getter for claims
     *
     * @return Food label claims (Atkins friendly, dairy free, etc.)
     */
    public String[] getClaims() {
        return claims;
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
     * Getter for photo
     *
     * @return photo object for the food item
     */
    public Photo getPhoto() {
        return photo;
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
     * Getter for serving_weight_grams
     *
     * @return Weight of the serving unit in grams
     */
    public Double getServingWeightGrams() {
        return serving_weight_grams;
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
        parcel.writeString(food_name);
        parcel.writeString(serving_unit);
        parcel.writeString(tag_name);
        parcel.writeInt(serving_qty);
        parcel.writeString(common_type);
        parcel.writeStringArray(claims);
        parcel.writeString(tag_id);
        parcel.writeParcelable(photo, i);
        parcel.writeTypedArray(full_nutrients, i);
        parcel.writeDouble(serving_weight_grams);
        parcel.writeString(locale);
    }
}
