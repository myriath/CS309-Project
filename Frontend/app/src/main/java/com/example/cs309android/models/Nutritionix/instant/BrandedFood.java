package com.example.cs309android.models.Nutritionix.instant;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Represents a common type food item from the Nutritionix API
 *
 * @author Mitch Hudson
 */
public class BrandedFood extends FoodItem implements Parcelable {
    /**
     * Id for the brand
     */
    private final String nix_brand_id;
    /**
     * Name of the brand
     */
    private final String brand_name;
    /**
     * Item id used to find this item in API
     */
    private final String nix_item_id;
    /**
     * Item name given by the brand.
     */
    private final String brand_name_item_name;
    /**
     * Number of calories per serving
     */
    private final Double nf_calories;
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
     * Public constructor for use by GSON
     *
     * @param food_name            name of the food
     * @param nix_brand_id         brand id given by Nutritionix
     * @param photo                photo object
     * @param brand_name           name of the brand
     * @param full_nutrients       nutrients array
     * @param nix_item_id          item id given by Nutritionix
     * @param serving_unit         serving size unit
     * @param brand_name_item_name brand name's item name
     * @param serving_qty          serving size quantity per unit
     * @param nf_calories          number of calories per serving
     * @param claims               label claims
     * @param region               region code
     * @param brand_type           brand type code
     * @param taxonomy_node_id     taxonomy node id, if given
     * @param locale               locale code
     */
    public BrandedFood(String food_name, String nix_brand_id, Photo photo, String brand_name,
                       Nutrient[] full_nutrients, String nix_item_id,
                       String serving_unit, String brand_name_item_name, Integer serving_qty,
                       Double nf_calories, String[] claims, Integer region, Integer brand_type,
                       String taxonomy_node_id, String locale) {
        super(food_name, serving_unit, serving_qty, claims, full_nutrients, photo, locale);
        this.nix_brand_id = nix_brand_id;
        this.brand_name = brand_name;
        this.nix_item_id = nix_item_id;
        this.brand_name_item_name = brand_name_item_name;
        this.nf_calories = nf_calories;
        this.region = region;
        this.brand_type = brand_type;
        this.taxonomy_node_id = taxonomy_node_id;
    }

    /**
     * Public constructor.
     * Extracts a CommonFood from the given parcel.
     *
     * @param parcel Parcel containing a common food item
     */
    public BrandedFood(Parcel parcel) {
        super(parcel);
        nix_brand_id = parcel.readString();
        brand_name = parcel.readString();
        nix_item_id = parcel.readString();
        brand_name_item_name = parcel.readString();
        nf_calories = parcel.readDouble();
        region = parcel.readInt();
        brand_type = parcel.readInt();
        taxonomy_node_id = parcel.readString();
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
        parcel.writeString(nix_brand_id);
        parcel.writeString(brand_name);
        parcel.writeString(nix_item_id);
        parcel.writeString(brand_name_item_name);
        parcel.writeDouble(nf_calories);
        parcel.writeInt(region);
        parcel.writeInt(brand_type);
        parcel.writeString(taxonomy_node_id);
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
     * Getter for nix_brand_id
     *
     * @return Brand id used by Nutritionix API
     */
    public String getNixBrandId() {
        return nix_brand_id;
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
     * Getter for nix_item_id
     *
     * @return Item id used by Nutritionix API
     */
    public String getNixItemId() {
        return nix_item_id;
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
     * Getter for nf_calories
     *
     * @return Number of calories
     */
    public Double getNfCalories() {
        return nf_calories;
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
     * Parcelable method
     *
     * @return 0
     */
    @Override
    public int describeContents() {
        return 0;
    }
}
