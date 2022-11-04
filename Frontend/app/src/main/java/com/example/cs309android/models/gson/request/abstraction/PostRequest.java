package com.example.cs309android.models.gson.request.abstraction;

import com.android.volley.Request;
import com.example.cs309android.util.Util;

/**
 * Base class for post requests.
 * <p>
 * Post requests need a method to get the json body
 */
public abstract class PostRequest extends com.example.cs309android.models.gson.request.abstraction.Request {
    /**
     * URL for the request
     */
    private final String url;

    /**
     * Public constructor
     * Creates new post request for the given url
     *
     * @param url URL for the request
     */
    public PostRequest(String url) {
        super(Request.Method.POST);
        this.url = url;
    }

    /**
     * Public constructor
     * Creates new request with given type (used by subclasses)
     *
     * @param url    URL for the request
     * @param method Method type for the request
     */
    protected PostRequest(String url, int method) {
        super(method);
        this.url = url;
    }

    /**
     * Generates JSON of this object using GSON
     *
     * @return JSON string
     */
    public String getBody() {
//        System.out.println(Util.GSON.toJson(this));
        return Util.GSON.toJson(this);
    }

    /**
     * Getter for the url
     *
     * @return URL
     */
    public String getURL() {
        return url;
    }
}
