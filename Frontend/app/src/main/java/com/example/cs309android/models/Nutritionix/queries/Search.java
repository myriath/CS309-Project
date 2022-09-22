package com.example.cs309android.models.Nutritionix.queries;

import static com.example.cs309android.models.Nutritionix.Constants.APP_ID;
import static com.example.cs309android.models.Nutritionix.Constants.APP_KEY;
import static com.example.cs309android.models.Nutritionix.Constants.NIX_URL;

import android.content.Context;

import androidx.annotation.NonNull;

import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cs309android.models.Nutritionix.BrandedFood;
import com.example.cs309android.models.Nutritionix.CommonFood;
import com.example.cs309android.util.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Performs a search using the Nutritionix API v2
 *
 * @author Mitch Hudson
 */
public class Search {
    /**
     * URL for the Search endpoint
     */
    private static final String URL = NIX_URL + "v2/search/instant?";

    // Required Parameters
    /**
     * Query to be executed against
     */
    private final String query;

    // Optional Parameters
    /**
     * Whether to include users food log results
     * Default false
     */
    private Boolean self = null;
    /**
     * Whether to include branded results
     * Default true
     */
    private Boolean branded = true;
    /**
     * A list of brand ids to filter by
     * Default null
     */
    private String[] brand_ids = null;
    /**
     * Brand type to filter results by
     * 1=Restaurant, 2=Grocery, null=No filtering
     * Default null
     */
    private Integer branded_type = null;
    /**
     * Region id to filter results by
     * 1=US, 2=UK, null=No filtering
     * Default null
     */
    private Integer branded_region = null;
    /**
     * Search branded foods by only item name, not by combo of name and brand
     * Default false
     */
    private Boolean branded_food_name_only = false;
    /**
     * Whether to include common food results
     * Default true
     */
    private Boolean common = true;
    /**
     * Include common foods without grocery or restaurant tags.
     * Applied only if common is set to true
     * Default true
     */
    private Boolean common_general = true;
    /**
     * Include common grocery foods.
     * Applied only if common is set to true
     * Default true
     */
    private Boolean common_grocery = true;
    /**
     * Include common restaurant foods.
     * Applied only if common is set to true
     * Default true
     */
    private Boolean common_restaurant = true;
    /**
     * Local value for common food phrases search.
     * Supported values: en_US, en_GB, de_DE, fr_FR, es_ES, it_IT, nl_NL, es_MX, pt_PT, ja_JP, ko_KR, pt_BR, ar_SA, tr_TR, pl_PL.
     * Only common foods will be included.
     * Default en_US
     */
    private String locale = "en_US";
    /**
     * Whether to include detailed nutrient fields like full_nutrients and serving_weight_grams
     * Default true
     */
    private Boolean detailed = true;
    /**
     * Whether to include food label claims array
     * Default true
     */
    private Boolean claims = true;
    /**
     * Array of food label claims that all must be true for each food in the results.
     * Default null
     */
    private String[] claims_query = null;
    /**
     * Whether to include taxonomy node id
     * Default true
     */
    private Boolean taxonomy = true;
    /**
     * Taxonomy node id to filter results by
     * Default null
     */
    private String taxonomy_node_id = null;

    /**
     * Public constructor with no limit or offset
     *
     * @param query A {String} as the search phrase in plain text
     */
    public Search(String query) {
        this.query = query;
    }

    /**
     * Setter for self
     *
     * @param self Whether to include users food log results
     */
    public void setSelf(Boolean self) {
        this.self = self;
    }

    /**
     * Setter for branded
     *
     * @param branded Whether to include branded results
     */
    public void setBranded(Boolean branded) {
        this.branded = branded;
    }

    /**
     * Setter for brand_ids
     *
     * @param brand_ids A list of brand ids to filter by
     */
    public void setBrandIds(String[] brand_ids) {
        this.brand_ids = brand_ids;
    }

    /**
     * Setter for branded_type
     *
     * @param branded_type Brand type to filter results by
     */
    public void setBrandedType(Integer branded_type) {
        this.branded_type = branded_type;
    }

    /**
     * Setter for branded_region
     *
     * @param branded_region Region id to filter results by
     */
    public void setBrandedRegion(Integer branded_region) {
        this.branded_region = branded_region;
    }

    /**
     * Setter for branded_food_name_only
     *
     * @param branded_food_name_only Search branded foods by only item name, not by combo of name and brand
     */
    public void setBrandedFoodNameOnly(Boolean branded_food_name_only) {
        this.branded_food_name_only = branded_food_name_only;
    }

    /**
     * Setter for common
     *
     * @param common Whether to include common food results
     */
    public void setCommon(Boolean common) {
        this.common = common;
    }

    /**
     * Setter for common_general
     *
     * @param common_general Include common grocery foods.
     */
    public void setCommonGeneral(Boolean common_general) {
        this.common_general = common_general;
    }

    /**
     * Setter for common_grocery
     *
     * @param common_grocery Include common grocery foods.
     */
    public void setCommonGrocery(Boolean common_grocery) {
        this.common_grocery = common_grocery;
    }

    /**
     * Setter for common_restaurant
     *
     * @param common_restaurant Include common restaurant foods.
     */
    public void setCommonRestaurant(Boolean common_restaurant) {
        this.common_restaurant = common_restaurant;
    }

    /**
     * Setter for locale
     *
     * @param locale Local value for common food phrases search.
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * Setter for detailed
     *
     * @param detailed Whether to include detailed nutrient fields like full_nutrients and serving_weight_grams
     */
    public void setDetailed(Boolean detailed) {
        this.detailed = detailed;
    }

    /**
     * Setter for claims
     *
     * @param claims Whether to include food label claims array
     */
    public void setClaims(Boolean claims) {
        this.claims = claims;
    }

    /**
     * Setter for claims_query
     *
     * @param claims_query Array of food label claims that all must be true for each food in the results.
     */
    public void setClaimsQuery(String[] claims_query) {
        this.claims_query = claims_query;
    }

    /**
     * Setter for taxonomy
     *
     * @param taxonomy Whether to include taxonomy node id
     */
    public void setTaxonomy(Boolean taxonomy) {
        this.taxonomy = taxonomy;
    }

    /**
     * Setter for taxonomy_node_id
     *
     * @param taxonomy_node_id Taxonomy node id to filter results by
     */
    public void setTaxonomyNodeId(String taxonomy_node_id) {
        this.taxonomy_node_id = taxonomy_node_id;
    }

    /**
     * Generates a request using volley and the arguments given earlier.
     *
     * @param listener Tells Volley what to do with the response.
     * @param context Context of the app.
     * @throws UnsupportedEncodingException Thrown when the search parameters can't be encoded.
     */
    public void search(com.android.volley.Response.Listener<JSONObject> listener, Context context) throws UnsupportedEncodingException {
        StringBuilder url = new StringBuilder(URL + String.format("query=%s", URLEncoder.encode(query, "utf-8")));
        if (self != null) {
            url.append(String.format("&self=%s", URLEncoder.encode(self.toString(), "utf-8")));
        }
        if (branded != null) {
            url.append(String.format("&branded=%s", URLEncoder.encode(branded.toString(), "utf-8")));
        }
        // array
        if (brand_ids != null) {
            for (String id : brand_ids) {
                url.append(String.format("&brand_ids[]=%s", URLEncoder.encode(id, "utf-8")));
            }
        }
        if (branded_type != null) {
            url.append(String.format("&branded_type=%s", URLEncoder.encode(branded_type.toString(), "utf-8")));
        }
        if (branded_region != null) {
            url.append(String.format("&branded_region=%s", URLEncoder.encode(branded_region.toString(), "utf-8")));
        }
        if (branded_food_name_only != null) {
            url.append(String.format("&branded_food_name_only=%s", URLEncoder.encode(branded_food_name_only.toString(), "utf-8")));
        }
        if (common != null) {
            url.append(String.format("&common=%s", URLEncoder.encode(common.toString(), "utf-8")));
        }
        if (common_general != null) {
            url.append(String.format("&common_general=%s", URLEncoder.encode(common_general.toString(), "utf-8")));
        }
        if (common_grocery != null) {
            url.append(String.format("&common_grocery=%s", URLEncoder.encode(common_grocery.toString(), "utf-8")));
        }
        if (common_restaurant != null) {
            url.append(String.format("&common_restaurant=%s", URLEncoder.encode(common_restaurant.toString(), "utf-8")));
        }
        if (locale != null) {
            url.append(String.format("&locale=%s", URLEncoder.encode(locale, "utf-8")));
        }
        if (detailed != null) {
            url.append(String.format("&detailed=%s", URLEncoder.encode(detailed.toString(), "utf-8")));
        }
        if (claims != null) {
            url.append(String.format("&claims=%s", URLEncoder.encode(claims.toString(), "utf-8")));
        }
        // array
        if (claims_query != null) {
            for (String query : claims_query) {
                url.append(String.format("&claims_query[]=%s", URLEncoder.encode(query, "utf-8")));
            }
        }
        if (taxonomy != null) {
            url.append(String.format("&taxonomy=%s", URLEncoder.encode(taxonomy.toString(), "utf-8")));
        }
        if (taxonomy_node_id != null) {
            url.append(String.format("&taxonomy_node_id=%s", URLEncoder.encode(taxonomy_node_id, "utf-8")));
        }

        JsonObjectRequest request = new JsonObjectRequest(url.toString(), listener, Throwable::printStackTrace) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("x-app-id", APP_ID);
                params.put("x-app-key", APP_KEY);

                return params;
            }
        };
        RequestHandler.getInstance(context).add(request);
    }

    /**
     * Response object generated from response JSON.
     *
     * @author Mitch Hudson
     */
    public static class Response {
        /**
         * Array of branded foods returned from API call
         */
        private final BrandedFood[] brandedFoods;
        /**
         * Array of common foods returned from API call
         */
        private final CommonFood[] commonFoods;

        /**
         * Constructs the object from response JSON
         *
         * @param json JSONObject returned by Nutritionix API
         * @throws JSONException Thrown when the JSON is malformed.
         */
        public Response(JSONObject json) throws JSONException {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();

            brandedFoods = gson.fromJson(json.getJSONArray("branded").toString(), BrandedFood[].class);
            commonFoods = gson.fromJson(json.getJSONArray("common").toString(), CommonFood[].class);
        }

        /**
         * Gets the array of branded foods found from the search query.
         *
         * @return Array of branded foods from search query.
         */
        public BrandedFood[] getBrandedFoods() {
            return brandedFoods;
        }

        /**
         * Gets the array of common foods found from the search query.
         *
         * @return Array of common foods from search query.
         */
        public CommonFood[] getCommonFoods() {
            return commonFoods;
        }

        /**
         * Debug toString method used to display the contents of this object.
         *
         * @return String containing the contents of this object.
         */
        @NonNull
        @Override
        public String toString() {
            return "Response{" +
                    "\n brandedFoods=" + Arrays.toString(brandedFoods) +
                    ",\n commonFoods=" + Arrays.toString(commonFoods) +
                    '}';
        }
    }
}
