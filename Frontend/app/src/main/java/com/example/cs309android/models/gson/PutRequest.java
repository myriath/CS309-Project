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
public abstract class PutRequest extends PostRequest {
    /**
     * Public constructor
     *
     * @param url URL for the request
     */
    public PutRequest(String url) {
        super(url, Request.Method.PUT);
    }
}
