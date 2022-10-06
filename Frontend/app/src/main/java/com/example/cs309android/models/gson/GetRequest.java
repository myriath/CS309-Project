package com.example.cs309android.models.gson;

import android.content.Context;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cs309android.util.RequestHandler;
import com.example.cs309android.util.Util;

import org.json.JSONObject;

/**
 * Interface for get requests
 *
 * Get requests need a method for getting a parameterized URL
 *
 * @author Mitch Hudson
 */
public abstract class GetRequest {
    /**
     * Generates the parameterized URL
     *
     * @return parameterized URL
     */
    public abstract String getURL();

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
     * Makes a request using Volley
     */
    public void request(Response.Listener<JSONObject> listener, Response.ErrorListener errorListener, Context context) {
        new RequestHandler(context).add(
                new JsonObjectRequest(getURL(), listener, errorListener)
        );
    }
}
