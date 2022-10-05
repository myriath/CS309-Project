package com.example.cs309android.models.gson;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cs309android.util.RequestHandler;
import com.example.cs309android.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Base class for post requests.
 * <p>
 * Post requests need a method to get the json body
 */
public abstract class PostRequest {
    /**
     * URL for the request
     */
    private final String url;

    /**
     * Public constructor
     *
     * @param url URL for the request
     */
    public PostRequest(String url) {
        this.url = url;
    }

    /**
     * Generates JSON of this object using GSON
     *
     * @return JSON string
     */
    public String getBody() {
        return Util.GSON.toJson(this);
    }

    /**
     * Getter for the url
     *
     * @return URL
     */
    public String getURL() {
        return url;
    }

    /**
     * Makes a request using Volley
     */
    public void request(Response.Listener<JSONObject> listener, Context context) {
        request(Request.Method.POST, listener, Throwable::printStackTrace, context);
    }

    /**
     * Makes a request using Volley (custom error listener)
     */
    public void request(Response.Listener<JSONObject> listener, Response.ErrorListener errorListener, Context context) {
        request(Request.Method.POST, listener, errorListener, context);
    }

    /**
     * Base request method
     */
    public void request(int method, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener, Context context) {
        try {
//            System.out.println(new JSONObject(getBody()).toString(4));
            new RequestHandler(context).add(
                    new JsonObjectRequest(method, url, new JSONObject(getBody()), listener, errorListener)
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
