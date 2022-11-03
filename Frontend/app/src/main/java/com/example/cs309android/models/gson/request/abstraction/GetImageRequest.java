package com.example.cs309android.models.gson.request.abstraction;

/**
 * Abstract image get request handler
 *
 * @author Mitch Hudson
 */
public abstract class GetImageRequest extends ImageRequest {
    /**
     * Getter for the url for this get request
     *
     * @return URL for the request
     */
    public abstract String getURL();
}
