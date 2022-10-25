package com.example.cs309android.models.gson;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cs309android.util.RequestHandler;
import com.example.cs309android.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class Request {
    /**
     * Method type for the request
     */
    private final int method;

    /**
     * Generates a new request with the given type and method
     *
     * @param method request method
     */
    public Request(int method) {
        this.method = method;
    }

    /**
     * Getter for the url
     *
     * @return url for the request
     */
    public abstract String getURL();

    /**
     * Getter for the json body
     *
     * @return body of the request
     */
    public abstract String getBody();

    /**
     * Automatically calls unSpin when the response completes
     *
     * @param listener What to do on response
     * @param context  Application context for volley
     * @param view     View to call unspin on
     */
    public void unspinOnComplete(Response.Listener<JSONObject> listener, Context context, View view) {
        unspinOnComplete(listener, Throwable::printStackTrace, context, view);
    }

    /**
     * Automatically calls unSpin when the response completes (Custom error listener)
     *
     * @param listener What to do on response
     * @param context  Application context for volley
     * @param view     View to call unspin on
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
     *
     * @param listener Runs when completed successfully
     * @param context  Context for volley
     */
    public void request(Response.Listener<JSONObject> listener, Context context) {
        request(listener, Throwable::printStackTrace, context);
    }

    /**
     * Base request method
     *
     * @param listener      Runs when completed successfully
     * @param errorListener Runs when volley throws an error
     * @param context       Context for volley
     */
    public void request(Response.Listener<JSONObject> listener, Response.ErrorListener errorListener, Context context) {
        if (method == com.android.volley.Request.Method.GET) {
            new RequestHandler(context).add(
                    new JsonObjectRequest(getURL(), listener, errorListener)
            );
        } else {
            try {
//            System.out.println(new JSONObject(getBody()).toString(4));
                new RequestHandler(context).add(
                        new JsonObjectRequest(method, getURL(), new JSONObject(getBody()), listener, errorListener)
                );
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Makes a request for the given image url
     *
     * @param listener  Runs when the request is completed
     * @param maxWidth  Maximum width of the image in pixels
     * @param maxHeight Maximum height of the image in pixels
     * @param context   Context for volley
     */
    public void request(Response.Listener<Bitmap> listener, int maxWidth, int maxHeight, Context context) {
        request(listener, maxWidth, maxHeight, Throwable::printStackTrace, context);
    }

    /**
     * Makes a request for the given image url (custom error listener)
     *
     * @param listener      Runs when the request is completed
     * @param maxWidth      Maximum width of the image in pixels
     * @param maxHeight     Maximum height of the image in pixels
     * @param errorListener Runs when volley throws an error
     * @param context       Context for volley
     */
    public void request(Response.Listener<Bitmap> listener, int maxWidth, int maxHeight, Response.ErrorListener errorListener, Context context) {
        new RequestHandler(context).add(
                new ImageRequest(getURL(), listener, maxWidth, maxHeight, ImageView.ScaleType.CENTER_CROP, Bitmap.Config.ARGB_8888, errorListener)
        );
    }
}
