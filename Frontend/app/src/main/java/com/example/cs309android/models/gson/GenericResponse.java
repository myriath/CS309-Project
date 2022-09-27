package com.example.cs309android.models.gson;

/**
 * Generic response class used for communication between front and back-ends
 *
 * @author Mitch Hudson
 */
public class GenericResponse {
    /**
     * Result code from the request
     */
    private final int result;

    /**
     * Constructor to be used by GSON
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
