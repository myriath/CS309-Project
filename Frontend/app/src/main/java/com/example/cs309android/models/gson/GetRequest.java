package com.example.cs309android.models.gson;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cs309android.util.RequestHandler;

import org.json.JSONObject;

/**
 * Interface for get requests
 *
 * Get requests need a method for getting a parameterized URL
 *
 * @author Mitch Hudson
 */
public abstract class GetRequest {
    /**
     * Generates the parameterized URL
     * @return parameterized URL
     */
    public abstract String getURL();

    /**
     * Makes a request using Volley
     */
    public void request(String url, Response.Listener<JSONObject> listener, Context context) {
        RequestHandler.getInstance(context).add(
                new JsonObjectRequest(url, listener, Throwable::printStackTrace)
        );
    }
}
