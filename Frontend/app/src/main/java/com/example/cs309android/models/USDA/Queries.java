package com.example.cs309android.models.USDA;

import static com.example.cs309android.models.USDA.Constants.API_KEY_GET;
import static com.example.cs309android.models.USDA.Constants.API_URL_SEARCH_ENDPOINT;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cs309android.models.USDA.queries.FoodSearchCriteria;
import com.example.cs309android.models.USDA.queries.FoodsCriteria;
import com.example.cs309android.util.RequestHandler;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Util class containing the various methods that perform endpoint calls with the API
 *
 * @author Mitch Hudson
 */
public class Queries {
    /**
     * Get details of a specific food item
     *
     * @param criteria Search criteria
     * @param fdcId    id to get info of
     * @param listener Controls what happens with the result
     * @param context  Context for volley
     */
    public static void food(FoodsCriteria criteria, int fdcId, Response.Listener<JSONObject> listener, Context context) {
        String url = Constants.API_URL_FOOD_ENDPOINT + fdcId + "?" + criteria.toString() + API_KEY_GET;
        System.out.println(url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, listener, Throwable::printStackTrace);
        RequestHandler.getInstance(context).add(request);
    }

    /**
     * Make a search with the given query
     *
     * @param query    Query to search for
     * @param criteria criteria for search
     * @param listener Code to run when the request succeeds
     * @param context  Application context for request
     */
    public static void search(String query, FoodSearchCriteria criteria, com.android.volley.Response.Listener<JSONObject> listener, Context context) throws UnsupportedEncodingException {
        String url = API_URL_SEARCH_ENDPOINT + "?query=" + URLEncoder.encode(query, "utf-8") + "&" + criteria.toString() + API_KEY_GET;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, listener, Throwable::printStackTrace);
        RequestHandler.getInstance(context).add(request);
    }
}
