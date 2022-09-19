package com.example.cs309android.models.Nutritionix.queries;

import static com.example.cs309android.util.Constants.NIX_URL;

import android.content.Context;

import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cs309android.models.Nutritionix.Item;
import com.example.cs309android.util.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Performs a search using the Nutritionix API v2
 *
 * @author Mitch Hudson
 */
public class Search {
    /**
     * URL for the Search endpoint
     */
    private static final String URL = NIX_URL + "/v2/search?";

    /**
     * Enum of valid search_type values
     */
    public enum SearchType {
        /**
         * Valid search_type(s)
         */
        RECIPE("recipe"), GROCERY("grocery"), RESTAURANT("restaurant"), USDA("usda");

        /**
         * Value of the enum object.
         */
        private final String value;

        /**
         * Constructor
         * @param value String literal for the valid search_type
         */
        SearchType(String value) {
            this.value = value;
        }

        /**
         * Getter for value
         * @return The valid string associated with the search_type
         */
        public String getValue() {
            return value;
        }
    }

    /**
     * Enum of valid search_nutrient values
     */
    public enum SearchNutrient {
        /**
         * Valid search_nutrient(s)
         */
        CALORIES("calories"), FAT("fat"), PROTEIN("protein"), CARB("carb");

        /**
         * Value of the enum object.
         */
        private final String value;

        /**
         * Constructor
         * @param value String literal for the valid search_nutrient
         */
        SearchNutrient(String value) {
            this.value = value;
        }

        /**
         * Getter for value
         * @return The valid string associated with the search_nutrient
         */
        public String getValue() {
            return value;
        }
    }

    // Required Parameters
    /**
     * A {String} as the search phrase in plain text
     */
    private final String q;

    // Optional Parameters
    /**
     * An {Integer} as the maximum rendered results
     * Requires offset
     */
    private Integer limit;
    /**
     * An {Integer} as search offset for paging through results
     * Requires limit
     */
    private Integer offset;
    /**
     * A {String} representing the search mode.
     * Must be one of the following (recipe, grocery, restaurant, usda)
     */
    private String search_type;
    /**
     * A {String} representing the nutrient returned in search defaults to calories.
     * Can be(calories, fat, protein, carb)
     */
    private String search_nutrient;

    /**
     * Public constructor with no limit or offset
     *
     * @param q A {String} as the search phrase in plain text
     */
    public Search(String q) {
        this.q = q;
    }

    /**
     * Public constructor with limits
     *
     * @param q A {String} as the search phrase in plain text
     * @param limit An {Integer} as the maximum rendered results
     * @param offset An {Integer} as search offset for paging through results
     */
    public Search(String q, int limit, int offset) {
        this.q = q;
        setLimits(limit, offset);
    }

    /**
     * Sets the limits for the search
     *
     * @param limit An {Integer} as the maximum rendered results
     * @param offset An {Integer} as search offset for paging through results
     */
    public void setLimits(int limit, int offset) {
        this.limit = limit;
        this.offset = offset;
    }

    /**
     * Sets the search type
     *
     * @param search_type SearchType that represents a valid search_type
     */
    public void setSearchType(SearchType search_type) {
        this.search_type = search_type.getValue();
    }

    /**
     * Sets the search nutrient
     *
     * @param search_nutrient SearchNutrient that represents a valid search_nutrient
     */
    public void setSearchNutrient(SearchNutrient search_nutrient) {
        this.search_nutrient = search_nutrient.getValue();
    }

    /**
     * Generates a request using volley and the arguments given earlier.
     *
     * @param listener Tells Volley what to do with the response.
     * @param context Context of the app.
     * @throws UnsupportedEncodingException Thrown when the search parameters can't be encoded.
     */
    public void search(com.android.volley.Response.Listener<JSONObject> listener, Context context) throws UnsupportedEncodingException {
        String url = URL + String.format("q=%s", URLEncoder.encode(q, "utf-8"));
        if (limit != null && offset != null) {
            url += URL + String.format("&limit=%s&offset=%s",
                    URLEncoder.encode(String.valueOf(limit), "utf-8"),
                    URLEncoder.encode(String.valueOf(offset), "utf-8"));
        }
        if (search_type != null) {
            url += URL + String.format("&search_type=%s",
                    URLEncoder.encode(search_type, "utf-8"));
        }
        if (search_nutrient != null) {
            url += URL + String.format("&search_nutrient=%s",
                    URLEncoder.encode(search_nutrient, "utf-8"));
        }

        JsonObjectRequest request = new JsonObjectRequest(url, listener, Throwable::printStackTrace);
        RequestHandler.getInstance(context).add(request);
    }

    /**
     * Response object generated from response JSON.
     *
     * @author Mitch Hudson
     */
    public static class Response {
        /**
         * A {Boolean} attribute representing if we found an exact match for the search phrase.
         */
        private final Boolean exact;
        /**
         * An {Integer} as the total number of hits for the search query
         */
        private final Integer total;
        /**
         * An [Array] of Item{Objects} representing items that matched the search phrase passed in the requests q parameter
         */
        private final Item[] results;
        /**
         * An {Integer} as the http status code. We will supply status code mappings to help you look up common issues
         */
        private final Integer status;

        /**
         * Constructs the object from response JSON
         *
         * @param json JSONObject returned by Nutritionix API
         * @throws JSONException Thrown when the JSON is malformed.
         */
        public Response(JSONObject json) throws JSONException {
            exact = json.getBoolean("exact");
            total = json.getInt("total");
            JSONArray array = json.getJSONArray("results");
            results = new Item[array.length()];
            for (int i = 0; i < array.length(); i++) {
                results[i] = new Item(array.getJSONObject(i));
            }
            status = json.getInt("status");
        }

        /**
         * Getter for exact
         * @return A {Boolean} attribute representing if we found an exact match for the search phrase.
         */
        public Boolean getExact() {
            return exact;
        }

        /**
         * Getter for total
         * @return An {Integer} as the total number of hits for the search query
         */
        public Integer getTotal() {
            return total;
        }

        /**
         * Getter for results
         * @return An [Array] of Item{Objects} representing items that matched the search phrase passed in the requests q parameter
         */
        public Item[] getResults() {
            return results;
        }

        /**
         * Getter for status
         * @return An {Integer} as the http status code. We will supply status code mappings to help you look up common issues
         */
        public Integer getStatus() {
            return status;
        }
    }
}
