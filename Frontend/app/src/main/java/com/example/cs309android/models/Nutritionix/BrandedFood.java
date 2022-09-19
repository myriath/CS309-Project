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
public class BrandedFood extends FoodItem implements Parcelable {
    /**
     * Food name
     */
    private final String food_name;
    /**
     * Id for the brand
     */
    private final String nix_brand_id;
    /**
     * Photo object for the item
     */
    private final Photo photo;
    /**
     * Name of the brand
     */
    private final String brand_name;
    /**
     * Weight of the serving unit in grams
     */
    private final Double serving_weight_grams;
    /**
     * Nutrient array containing all nutrient information.
     */
    private final Nutrient[] full_nutrients;
    /**
     * Item id used to find this item in API
     */
    private final String nix_item_id;
    /**
     * Serving size unit
     */
    private final String serving_unit;
    /**
     * Item name given by the brand.
     */
    private final String brand_name_item_name;
    /**
     * Number of serving units per unit
     */
    private final Integer serving_qty;
    /**
     * Number of calories per serving
     */
    private final Integer nf_calories;
    /**
     * Food label claims list.
     * (Atkins friendly, contains dairy, etc.)
     */
    private final String[] claims;
    /**
     * Region
     * 1=US, 2=UK
     */
    private final Integer region;
    /**
     * Type of brand
     */
    private final Integer brand_type;
    /**
     * Taxonomy node id
     */
    private final String taxonomy_node_id;
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
    public BrandedFood(JSONObject json) {
        super(json.optString("food_name"), json.optString("nix_item_id"), FoodType.BRANDED);
        food_name = json.optString("food_name");
        nix_brand_id = json.optString("nix_brand_id");
        JSONObject photoObj = json.optJSONObject("photo");
        photo = photoObj != null ? new Photo(photoObj) : null;
        brand_name = json.optString("brand_name");
        serving_weight_grams = json.optDouble("serving_weight_grams");
        JSONArray array = json.optJSONArray("full_nutrients");
        full_nutrients = array != null ? new Nutrient[array.length()] : new Nutrient[0];
        for (int i = 0; i < full_nutrients.length; i++) {
            JSONObject nutrientJson = array.optJSONObject(i);
            Nutrient nutrient = Nutrient.lookup(nutrientJson.optInt("attr_id"));
            if (nutrient == null) continue;
            full_nutrients[i] = new Nutrient(nutrient.getAttrId(), nutrientJson.optInt("value"));
        }
        nix_item_id = json.optString("nix_item_id");
        serving_unit = json.optString("serving_unit");
        brand_name_item_name = json.optString("brand_name_item_name");
        serving_qty = json.optInt("serving_qty");
        nf_calories = json.optInt("nf_calories");
        array = json.optJSONArray("claims");
        claims = array != null ? new String[array.length()] : new String[0];
        for (int i = 0; i < claims.length; i++) {
            claims[i] = array.optString(i);
        }
        region = json.optInt("region");
        brand_type = json.optInt("brand_type");
        taxonomy_node_id = json.optString("taxonomy_node_id");
        locale = json.optString("locale");
    }

    /**
     * Public constructor.
     * Extracts a CommonFood from the given parcel.
     *
     * @param parcel Parcel containing a common food item
     */
    public BrandedFood(Parcel parcel) {
        super(parcel);
        food_name = parcel.readString();
        nix_brand_id = parcel.readString();
        photo = parcel.readParcelable(Photo.class.getClassLoader());
        brand_name = parcel.readString();
        serving_weight_grams = parcel.readDouble();
        full_nutrients = parcel.createTypedArray(Nutrient.CREATOR);
        nix_item_id = parcel.readString();
        serving_unit = parcel.readString();
        brand_name_item_name = parcel.readString();
        serving_qty = parcel.readInt();
        nf_calories = parcel.readInt();
        claims = parcel.createStringArray();
        region = parcel.readInt();
        brand_type = parcel.readInt();
        taxonomy_node_id = parcel.readString();
        locale = parcel.readString();
    }

    /**
     * Parcelable CREATOR object.
     * Used to create common foods from parcels
     */
    public static final Creator<BrandedFood> CREATOR = new Creator<BrandedFood>() {
        @Override
        public BrandedFood createFromParcel(Parcel in) {
            return new BrandedFood(in);
        }

        @Override
        public BrandedFood[] newArray(int size) {
            return new BrandedFood[size];
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
     * Getter for nix_brand_id
     *
     * @return Brand id used by Nutritionix API
     */
    public String getNixBrandId() {
        return nix_brand_id;
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
     * Getter for brand_name
     *
     * @return Brand name
     */
    public String getBrandName() {
        return brand_name;
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
     * Getter for full_nutrients
     *
     * @return Nutrient array of nutrients in the food item
     */
    public Nutrient[] getFullNutrients() {
        return full_nutrients;
    }

    /**
     * Getter for nix_item_id
     *
     * @return Item id used by Nutritionix API
     */
    public String getNixItemId() {
        return nix_item_id;
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
     * Getter for brand_name_item_name
     *
     * @return Brand's name for the item
     */
    public String getBrandNameItemName() {
        return brand_name_item_name;
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
     * Getter for nf_calories
     *
     * @return Number of calories
     */
    public Integer getNfCalories() {
        return nf_calories;
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
     * Getter for region
     *
     * @return Region (1=US, 2=UK)
     */
    public Integer getRegion() {
        return region;
    }

    /**
     * Getter for brand_type
     *
     * @return Brand type
     */
    public Integer getBrandType() {
        return brand_type;
    }

    /**
     * Getter for taxonomy_node_id
     *
     * @return Taxonomy node id
     */
    public String getTaxonomyNodeId() {
        return taxonomy_node_id;
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
        parcel.writeString(nix_brand_id);
        parcel.writeParcelable(photo, i);
        parcel.writeString(brand_name);
        parcel.writeDouble(serving_weight_grams);
        parcel.writeTypedArray(full_nutrients, i);
        parcel.writeString(nix_item_id);
        parcel.writeString(serving_unit);
        parcel.writeString(brand_name_item_name);
        parcel.writeInt(serving_qty);
        parcel.writeInt(nf_calories);
        parcel.writeStringArray(claims);
        parcel.writeInt(region);
        parcel.writeInt(brand_type);
        parcel.writeString(taxonomy_node_id);
        parcel.writeString(locale);
    }
}
