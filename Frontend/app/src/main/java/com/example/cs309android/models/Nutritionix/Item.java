package com.example.cs309android.models.Nutritionix;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Item{Object} returned by Nutritionix API
 */
public class Item {
    /**
     * A String as the name of the product or food item.
     */
    private final String item_name;
    /**
     * A String as the name of the item's brand.
     */
    private final String brand_name;
    /**
     * A String as the name of the selected nutrient to be returned in search.
     */
    private final String nutrient_name;
    /**
     * An Integer as the numeric value of the selected nutrient.
     */
    private final int nutrient_value;
    /**
     * A String as the unit of measurement for the selected nutrient.
     */
    private final String nutrient_uom;
    /**
     * An Integer as the item's serving quantity.
     */
    private final int serving_qty;
    /**
     * A String as the unit of measurement for the serving_qty.
     */
    private final String serving_uom;
    /**
     * A String as the tracking id that links searches to individual items.
     */
    private final String resource_id;
    /**
     * A String as the url to an item's thumbnail image.
     */
    private final String thumbnail;
    /**
     * An Array of Nutrient{Object} which will be null unless response.exact is true.
     */
    private final Nutrient[] nutrients;

    /**
     * Public constructor from the JSON response.
     *
     * @param json JSON response from the Nutritionix API.
     * @throws JSONException Thrown when the JSON is malformed.
     */
    public Item (JSONObject json) throws JSONException {
        item_name = json.getString("item_name");
        brand_name = json.getString("brand_name");
        nutrient_name = json.getString("nutrient_name");
        nutrient_value = json.getInt("nutrient_value");
        nutrient_uom = json.getString("nutrient_uom");
        serving_qty = json.getInt("serving_qty");
        serving_uom = json.getString("serving_uom");
        resource_id = json.getString("resource_id");
        thumbnail = json.getString("thumbnail");
        JSONArray array = json.getJSONArray("nutrients");
        nutrients = new Nutrient[array.length()];
        for (int i = 0; i < array.length(); i++) {
            nutrients[i] = new Nutrient(array.getJSONObject(i));
        }
    }

    /**
     * Getter for item_name
     * @return A String as the name of the product or food item.
     */
    public String getItemName() {
        return item_name;
    }

    /**
     * Getter for brand_name
     * @return A String as the name of the item's brand.
     */
    public String getBrandName() {
        return brand_name;
    }

    /**
     * Getter for nutrient_name
     * @return A String as the name of the selected nutrient to be returned in search.
     */
    public String getNutrientName() {
        return nutrient_name;
    }

    /**
     * Getter for nutrient_value
     * @return An Integer as the numeric value of the selected nutrient.
     */
    public int getNutrientValue() {
        return nutrient_value;
    }

    /**
     * Getter for nutrient_uom
     * @return A String as the unit of measurement for the selected nutrient.
     */
    public String getNutrientUom() {
        return nutrient_uom;
    }

    /**
     * Getter for serving_qty
     * @return An Integer as the item's serving quantity.
     */
    public int getServingQty() {
        return serving_qty;
    }

    /**
     * Getter for serving_uom
     * @return A String as the unit of measurement for the serving_qty.
     */
    public String getServingUom() {
        return serving_uom;
    }

    /**
     * Getter for resource_id
     * @return A String as the tracking id that links searches to individual items.
     */
    public String getResourceId() {
        return resource_id;
    }

    /**
     * Getter for thumbnail
     * @return A String as the url to an item's thumbnail image.
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * Getter for nutrients
     * @return An Array of Nutrient{Object} which will be null unless response.exact is true.
     */
    public Nutrient[] getNutrients() {
        return nutrients;
    }
}
