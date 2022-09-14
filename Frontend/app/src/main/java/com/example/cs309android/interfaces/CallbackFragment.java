package com.example.cs309android.interfaces;

/**
 * Interface to allow callback methods for controlling fragment activity
 *
 * @author Mitch Hudson
 */
public interface CallbackFragment {
    /**
     * Does something based on the callback op.
     *
     * @param op Tells the class what to do.
     */
    void callback(int op);
}
