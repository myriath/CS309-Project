package com.example.cs309android.models.gson;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cs309android.util.RequestHandler;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Base class for post requests.
 *
 * Post requests need a method to get the json body
 */
public abstract class DeleteRequest extends PostRequest {
    /**
     * Makes a request using Volley
     */
    public void request(String url, Response.Listener<JSONObject> listener, Context context) throws JSONException {
        RequestHandler.getInstance(context).add(
                new JsonObjectRequest(Request.Method.DELETE, url, new JSONObject(getBody()), listener, Throwable::printStackTrace)
        );
    }
}
