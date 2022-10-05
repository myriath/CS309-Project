package com.example.cs309android.models.gson;

import android.content.Context;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cs309android.util.RequestHandler;
import com.example.cs309android.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Base class for post requests.
 * <p>
 * Post requests need a method to get the json body
 */
public abstract class PostRequest {
    /**
     * URL for the request
     */
    private final String url;
    /**
     * Method type for the request
     */
    private final int method;

    /**
     * Public constructor
     * Creates new post request for the given url
     *
     * @param url URL for the request
     */
    public PostRequest(String url) {
        this.url = url;
        this.method = Request.Method.POST;
    }

    /**
     * Public constructor
     * Creates new request with given type (used by subclasses)
     *
     * @param url URL for the request
     * @param method Method type for the request
     */
    protected PostRequest(String url, int method) {
        this.url = url;
        this.method = method;
    }

    /**
     * Generates JSON of this object using GSON
     *
     * @return JSON string
     */
    public String getBody() {
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

    /**
     * Automatically calls unSpin when the response completes
     *
     * @param listener What to do on response
     * @param context Application context for volley
     * @param view View to call unspin on
     */
    public void unspinOnComplete(Response.Listener<JSONObject> listener, Context context, View view) {
        unspinOnComplete(listener, Throwable::printStackTrace, context, view);
    }

    /**
     * Automatically calls unSpin when the response completes (Custom error listener)
     *
     * @param listener What to do on response
     * @param context Application context for volley
     * @param view View to call unspin on
     */
    public void unspinOnComplete(Response.Listener<JSONObject> listener, Response.ErrorListener errorListener, Context context, View view) {
        request(response -> {
            listener.onResponse(response);
            Util.unSpin(view);
        }, error -> {
            errorListener.onErrorResponse(error);
            Util.unSpin(view);
        }, context);
    }

    /**
     * Makes a request using Volley
     */
    public void request(Response.Listener<JSONObject> listener, Context context) {
        request(listener, Throwable::printStackTrace, context);
    }

    /**
     * Base request method
     */
    public void request(Response.Listener<JSONObject> listener, Response.ErrorListener errorListener, Context context) {
        try {
//            System.out.println(new JSONObject(getBody()).toString(4));
            new RequestHandler(context).add(
                    new JsonObjectRequest(method, url, new JSONObject(getBody()), listener, errorListener)
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
