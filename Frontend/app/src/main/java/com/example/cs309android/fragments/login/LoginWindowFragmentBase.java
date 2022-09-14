package com.example.cs309android.fragments.login;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.fragment.app.Fragment;

import com.example.cs309android.R;
import com.example.cs309android.interfaces.CallbackFragment;

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
     * Sets the callback fragment for closing the window/opening the register page.
     *
     * @param fragment Callback fragment.
     */
    public void setCallbackFragment(CallbackFragment fragment) {
        callbackFragment = fragment;
    }
}
