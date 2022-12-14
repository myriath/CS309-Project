package com.example.cs309android.models.api.response;

import com.google.gson.annotations.Expose;

/**
 * Generic response class used for communication between front and back-ends
 *
 * @author Mitch Hudson
 */
public class GenericResponse {
    /**
     * Result code from the request
     */
    @Expose
    private final int result;

    /**
     * Public constructor
     *
     * @param result Result code from the request
     */
    public GenericResponse(int result) {
        this.result = result;
    }

    /**
     * Getter for the result code
     *
     * @return Code from util.Constants
     */
    public int getResult() {
        return result;
    }
}
