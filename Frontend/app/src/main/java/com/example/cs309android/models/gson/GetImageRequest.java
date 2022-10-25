package com.example.cs309android.models.gson;

import com.android.volley.Request;

/**
 * Abstract image get request handler
 *
 * @author Mitch Hudson
 */
public abstract class GetImageRequest extends ImageRequest {
    /**
     * Constructor
     */
    public GetImageRequest() {
        super(Request.Method.GET);
    }

    /**
     * Body is unused for get request
     *
     * @return null
     */
    @Override
    public String getBody() {
        return null;
    }

    /**
     * Getter for the url for this get request
     *
     * @return URL for the request
     */
    public abstract String getURL();
}
