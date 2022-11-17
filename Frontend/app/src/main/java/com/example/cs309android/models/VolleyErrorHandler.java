package com.example.cs309android.models;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.cs309android.interfaces.ErrorListener;

import java.util.Objects;

/**
 * This Response.ErrorListener has custom code that takes the message of the input error
 * as a result code to be used with an ErrorListener
 *
 * @author Mitch Hudson
 */
public class VolleyErrorHandler implements Response.ErrorListener {
    /**
     * Runs code with an error code
     */
    private final ErrorListener listener;
    /**
     * Runs when the request fails
     */
    private final Response.ErrorListener errorListener;

    /**
     * Public constructor
     *
     * @param listener Runs code with an error code
     */
    public VolleyErrorHandler(ErrorListener listener) {
        this.listener = listener;
        errorListener = null;
    }

    /**
     * Public constructor
     *
     * @param listener      Runs code with an error code
     * @param errorListener Runs when the request fails
     */
    public VolleyErrorHandler(ErrorListener listener, Response.ErrorListener errorListener) {
        this.listener = listener;
        this.errorListener = errorListener;
    }

    /**
     * Uses the error message as a result code to run error code with
     *
     * @param error Error to use as result code
     */
    @Override
    public void onErrorResponse(VolleyError error) {
        try {
            listener.run(Integer.parseInt(Objects.requireNonNull(error.getMessage())));
        } catch (Exception e) {
            if (errorListener != null) {
                errorListener.onErrorResponse(error);
            }
            error.printStackTrace();
        }
    }
}
