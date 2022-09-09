package com.example.cs309android.fragments;

import android.view.View;

import androidx.fragment.app.Fragment;

import com.example.cs309android.R;

/**
 * Abstract helper class containing methods used by {@link LoginFragment} and {@link RegisterFragment}
 *
 * @author Mitch Hudson
 */
public abstract class LoginWindowFragmentBase extends Fragment {
    /**
     * Callback fragment used for controlling fragment change activity.
     */
    protected CallbackFragment callbackFragment;

    /**
     * Makes the spinner visible and locks interaction to the register page.
     * This is ran when the user starts a request to the server.
     *
     * @param view View to find the spinner from.
     */
    public void spin(View view) {
        view.findViewById(R.id.loginSpinner).setVisibility(View.VISIBLE);
        view.findViewById(R.id.loginCard).setAlpha(0.8f);
        view.findViewById(R.id.spinnerBlocker).setClickable(true);
    }

    /**
     * Makes the spinner invisible and unlocks interaction with the register page.
     * This is ran when a response is received from the server.
     *
     * @param view View to find the spinner from.
     */
    public void unSpin(View view) {
        view.findViewById(R.id.loginSpinner).setVisibility(View.GONE);
        view.findViewById(R.id.loginCard).setAlpha(1);
        view.findViewById(R.id.spinnerBlocker).setClickable(false);
    }

    /**
     * Sets the callback fragment for closing the window/opening the register page.
     *
     * @param fragment Callback fragment.
     */
    public void setCallbackFragment(CallbackFragment fragment) {
        callbackFragment = fragment;
    }
}
