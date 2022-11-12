package com.example.cs309android.interfaces;

import android.os.Bundle;

/**
 * Interface to allow callback methods for controlling fragment activity
 * @author Mitch Hudson
 */
public interface CallbackFragment {
    /**
     * Does something based on the callback op.
     *
     * @param op     Tells the class what to do.
     * @param bundle Bundle of callback arguments
     */
    void callback(int op, Bundle bundle);

    /**
     * Sets the callback fragment for closing the window/opening the register page.
     * @param fragment Callback fragment.
     */
    void setCallbackFragment(CallbackFragment fragment);
}
