package com.example.cs309android.fragments;

import androidx.fragment.app.Fragment;

import com.example.cs309android.fragments.login.LoginFragment;
import com.example.cs309android.fragments.login.RegisterFragment;
import com.example.cs309android.interfaces.CallbackFragment;

/**
 * Abstract helper class containing methods used by {@link LoginFragment} and {@link RegisterFragment}
 *
 * @author Mitch Hudson
 */
public abstract class BaseFragment extends Fragment implements CallbackFragment {
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
