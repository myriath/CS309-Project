package com.example.cs309android.models.api.request.abstraction;


import android.content.Context;
import android.graphics.Bitmap;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.example.cs309android.util.RequestHandler;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Abstract image get request handler
 *
 * @author Mitch Hudson
 */
public abstract class ImageSendRequest {
    /**
     * Request method
     */
    private final int method;
    /**
     * Bitmap to send
     */
    private final Bitmap bitmap;

    /**
     * Constructor
     *
     * @param method Request method
     * @param bitmap Image to send
     */
    public ImageSendRequest(int method, Bitmap bitmap) {
        this.method = method;
        this.bitmap = bitmap;
    }

    /**
     * Getter for the url for this get request
     *
     * @return URL for the request
     */
    public abstract String getURL();

    /**
     * Base request method
     *
     * @param listener Runs when completed successfully
     * @param context  Context for volley
     */
    public void request(Response.Listener<NetworkResponse> listener, Context context) {
        request(listener, Throwable::printStackTrace, context);
    }

    /**
     * Base request method
     * (Custom error listener)
     *
     * @param listener      Runs when completed successfully
     * @param errorListener Runs when volley throws an error
     * @param context       Context for volley
     */
    public void request(Response.Listener<NetworkResponse> listener, Response.ErrorListener errorListener, Context context) {
//            System.out.println(new JSONObject(getBody()).toString(4));
        new RequestHandler(context).add(
                new VolleyMultipartRequest(method, getURL(), listener, errorListener) {
                    @Override
                    protected Map<String, DataPart> getByteData() {
                        Map<String, DataPart> params = new HashMap<>();
                        long name = System.currentTimeMillis();
                        params.put("image", new DataPart(name + ".webp", getFileDataFromDrawable(bitmap)));
                        return params;
                    }
                }
        );
    }

    /**
     * Turns an image into a byte array
     *
     * @param bitmap Bitmap to compress and convert
     * @return byte[] of the compressed image
     */
    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.WEBP, 80, bos);
        return bos.toByteArray();
    }
}
