package com.example.cs309android.models.api.request.abstraction;

import com.android.volley.Request;

/**
 * Base class for put requests.
 *
 * @author Mitch Hudson
 */
public abstract class PutRequest extends PostRequest {
    /**
     * Public constructor
     * @param url URL for the request
     */
    public PutRequest(String url) {
        super(url, Request.Method.PUT);
    }
}
