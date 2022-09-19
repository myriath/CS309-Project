package com.example.cs309android.models.Nutritionix;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Nutrient{Object} returned by Nutritionix API.
 */
public class Nutrient {
    /**
     * An {Integer} representing the usda attribute id of the nutrient
     */
    private final int attr_id;
    /**
     * A {Float} representing the nutritional value of a nutrient
     */
    private final double value;
    /**
     * A {String} as the nutrients unit of measurement
     */
    private final String unit;
    /**
     * A {String} as the nutrients proper name
     */
    private final String name;
    /**
     * A {String} as the nutrients usda given abbreviation
     */
    private final String usda_tag;

    /**
     * Public constructor from JSON object.
     *
     * @param json JSON object returned by Nutritionix API
     * @throws JSONException Thrown if JSON is malformed
     */
    public Nutrient(JSONObject json) throws JSONException {
        attr_id = json.getInt("attr_id");
        value = json.getDouble("value");
        unit = json.getString("unit");
        name = json.getString("name");
        usda_tag = json.getString("usda_tag");
    }

    /**
     * Getter for attr_id
     * @return An {Integer} representing the usda attribute id of the nutrient
     */
    public int getAttrId() {
        return attr_id;
    }

    /**
     * Getter for value
     * @return A {Float} representing the nutritional value of a nutrient
     */
    public double getValue() {
        return value;
    }

    /**
     * Getter for unit
     * @return A {String} as the nutrients unit of measurement
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Getter for name
     * @return A {String} as the nutrients proper name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for usda_tag
     * @return A {String} as the nutrients usda given abbreviation
     */
    public String getUsdaTag() {
        return usda_tag;
    }
}
