package com.example.cs309android.models.FDC;

import static com.example.cs309android.models.FDC.Constants.API_KEY;
import static com.example.cs309android.models.FDC.Constants.API_KEY_GET;
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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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
     * @param listener Code to run when the request succeeds
     */
    public void search(String query, FoodSearchCriteria criteria, Context context, com.android.volley.Response.Listener<JSONObject> listener) throws UnsupportedEncodingException {
        String url = API_URL_SEARCH_ENDPOINT + "?query=" + URLEncoder.encode(query, "utf-8") + "&" + criteria.toString() + API_KEY_GET;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, listener, Throwable::printStackTrace);
        RequestHandler.getInstance(context).add(request);
    }
}
