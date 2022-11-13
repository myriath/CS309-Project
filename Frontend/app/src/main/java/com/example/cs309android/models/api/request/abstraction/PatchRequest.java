package com.example.cs309android.models.api.request.abstraction;

import com.android.volley.Request;

/**
 * Base class for patch requests.
 *
 * @author Mitch Hudson
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
