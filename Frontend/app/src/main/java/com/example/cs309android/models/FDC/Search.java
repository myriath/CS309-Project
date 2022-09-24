package com.example.cs309android.models.FDC;

import static com.example.cs309android.models.FDC.Constants.API_KEY;
import static com.example.cs309android.models.FDC.Constants.API_URL_SEARCH_ENDPOINT;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cs309android.models.FDC.queries.FoodSearchCriteria;
import com.example.cs309android.util.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Makes a search request using the /foods/search endpoint on the FDC API
 * <p>
 * https://app.swaggerhub.com/apis/fdcnal/food-data_central_api/1.0.1#/FDC/getFoodsSearch
 *
 * @author Mitch Hudson
 */
public class Search {
    /**
     * Private constructor (Util class)
     */
    private Search() {
    }

    /**
     * Make a search with the given query
     *
     * @param query      Query to search for
     * @param criteria   criteria for search
     * @param context    Application context for request
     * @param onResponse Code to run when the request succeeds
     */
    public void search(String query, FoodSearchCriteria criteria, Context context, com.android.volley.Response.Listener<JSONObject> onResponse) throws JSONException {
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        Gson gson = builder.create();
        JSONObject json = new JSONObject(gson.toJson(criteria));
        json.put("query", query);
        json.put("api_key", API_KEY);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, API_URL_SEARCH_ENDPOINT, json,
                onResponse, Throwable::printStackTrace);

        RequestHandler.getInstance(context).add(request);
    }
}
