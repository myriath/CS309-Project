package com.example.cs309android.models.gson.request.abstraction;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.Response;
import com.example.cs309android.util.RequestHandler;

/**
 * Used for making requests with Bitmaps
 *
 * @author Mitch Hudson
 */
public abstract class ImageRequest {
    /**
     * Default max width of the image in pixels
     */
    public static final int MAX_WIDTH = 1000;
    /**
     * Default max height of the image in pixels
     */
    public static final int MAX_HEIGHT = 1000;

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
    public String getBody() {
        return null;
    }

    /**
     * Makes a request for the given image url, uses base max width/height
     *
     * @param listener Runs when the request is completed
     * @param context  Context for volley
     */
    public void request(Response.Listener<Bitmap> listener, Context context) {
        request(listener, Throwable::printStackTrace, context);
    }

    /**
     * Makes a request for the given image url, uses base max width/height
     * (Custom errorListener)
     *
     * @param listener      Runs when the request is completed
     * @param errorListener Runs when volley throws an error
     * @param context       Context for volley
     */
    public void request(Response.Listener<Bitmap> listener, Response.ErrorListener errorListener, Context context) {
        request(listener, MAX_WIDTH, MAX_HEIGHT, errorListener, context);
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
                new com.android.volley.toolbox.ImageRequest(getURL(), listener, maxWidth, maxHeight, ImageView.ScaleType.CENTER_CROP, Bitmap.Config.ARGB_8888, errorListener)
        );
    }
}
