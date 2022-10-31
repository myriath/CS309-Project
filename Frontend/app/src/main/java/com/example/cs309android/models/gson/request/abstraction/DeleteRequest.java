package com.example.cs309android.models.gson.request.abstraction;

import com.android.volley.Request;

/**
 * Base class for post requests.
 * <p>
 * Post requests need a method to get the json body
 */
public abstract class DeleteRequest extends PostRequest {
    /**
     * Public constructor
     *
     * @param url URL for the request
     */
    public DeleteRequest(String url) {
        super(url, Request.Method.DELETE);
    }
}
