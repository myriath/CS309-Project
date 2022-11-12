package com.example.cs309android.fragments;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.cs309android.interfaces.CallbackFragment;

/**
 * Abstract helper class containing methods used by most fragments.
 * @author Mitch Hudson
 */
public abstract class BasePreferenceFragment extends PreferenceFragmentCompat implements CallbackFragment {
    /**
     * Callback fragment used for controlling fragment change activity.
     */
    protected CallbackFragment callbackFragment;

    /**
     * Sets the callback fragment for closing the window/opening the register page.
     * @param fragment Callback fragment.
     */
    @Override
    public void setCallbackFragment(CallbackFragment fragment) {
        callbackFragment = fragment;
    }

    /**
     * Overridden by children classes
     *
     * @param op     Tells the class what to do.
     * @param bundle Bundle of callback arguments
     */
    @Override
    public void callback(int op, Bundle bundle) {
    }
}
