package com.example.cs309android.models.gson;

import com.android.volley.Request;

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
        super(url, Request.Method.PATCH);
    }
}
