package com.example.cs309android.models.gson;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;

import org.json.JSONObject;

/**
 * Base class for post requests.
 * <p>
 * Post requests need a method to get the json body
 */
public abstract class PatchRequest extends PostRequest {
    /**
     * Public constructor
     *
     * @param url URL for the request
     */
    public PatchRequest(String url) {
        super(url);
    }

    /**
     * Makes a request using Volley
     */
    @Override
    public void request(Response.Listener<JSONObject> listener, Context context) {
        request(Request.Method.PATCH, listener, Throwable::printStackTrace, context);
    }

    /**
     * Makes a request using Volley (custom error listener)
     */
    @Override
    public void request(Response.Listener<JSONObject> listener, Response.ErrorListener errorListener, Context context) {
        request(Request.Method.PATCH, listener, errorListener, context);
    }
}
