package com.example.cs309android.fragments;

import androidx.preference.PreferenceFragmentCompat;

import com.example.cs309android.interfaces.CallbackFragment;

public abstract class BasePreferenceFragment extends PreferenceFragmentCompat implements CallbackFragment {
    /**
     * Callback fragment used for controlling fragment change activity.
     */
    protected CallbackFragment callbackFragment;

    /**
     * Sets the callback fragment for closing the window/opening the register page.
     *
     * @param fragment Callback fragment.
     */
    public void setCallbackFragment(CallbackFragment fragment) {
        callbackFragment = fragment;
    }

    /**
     * Overridden by children classes with callback code.
     *
     * @param op Tells the class what to do.
     */
    @Override
    public void callback(int op) {
    }
}
