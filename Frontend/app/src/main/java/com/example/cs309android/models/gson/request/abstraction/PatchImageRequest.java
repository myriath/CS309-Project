package com.example.cs309android.models.gson.request.abstraction;

import android.graphics.Bitmap;

import com.android.volley.Request;

/**
 * Base class for post requests.
 * <p>
 * Post requests need a method to get the json body
 */
public abstract class PatchImageRequest extends ImageSendRequest {
    /**
     * URL for the request
     */
    private final String url;

    /**
     * Public constructor
     *
     * @param url URL for the request
     */
    public PatchImageRequest(String url, Bitmap bitmap) {
        super(Request.Method.PATCH, bitmap);
        this.url = url;
    }

    /**
     * Getter for the url
     * @return url for the request
     */
    @Override
    public String getURL() {
        return url;
    }
}
