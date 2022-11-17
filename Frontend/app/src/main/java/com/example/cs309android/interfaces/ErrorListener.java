package com.example.cs309android.interfaces;

/**
 * ErrorListener runs code based on an error code
 *
 * @author Mitch Hudson
 */
public interface ErrorListener {
    /**
     * Code to run on error with an error code
     *
     * @param result Error code to handle
     */
    void run(int result);
}
