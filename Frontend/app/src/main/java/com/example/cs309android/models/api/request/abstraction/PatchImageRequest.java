package com.example.cs309android.models.api.request.abstraction;

import android.graphics.Bitmap;

import com.android.volley.Request;

/**
 * Base class to send an image in a patch request
 *
 * @author Mitch Hudson
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
     *
     * @return url for the request
     */
    @Override
    public String getURL() {
        return url;
    }
}
