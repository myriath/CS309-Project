package com.example.cs309android.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.cs309android.R;
import com.example.cs309android.activities.MainActivity;

import java.nio.charset.StandardCharsets;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    Button loginButton, registerButton;
    EditText usernameField, passwordField;
    CallbackFragment callbackFragment;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(SharedPreferences pref) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
//        args.put(ARG_PREF, pref);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        usernameField = view.findViewById(R.id.unameField);
        passwordField = view.findViewById(R.id.passwordField);

        loginButton = view.findViewById(R.id.buttonLogin);
        registerButton = view.findViewById(R.id.buttonRegister);

        loginButton.setOnClickListener(view1 -> {
            String unm = usernameField.getText().toString();
            String pwd = passwordField.getText().toString();

            if (unm.equals("")) {
                usernameField.setError("Username can't be empty");
                usernameField.requestFocus();
                return;
            } else if (pwd.equals("")) {
                passwordField.setError("Password can't be empty");
                passwordField.requestFocus();
                return;
            }

            spin(view);

            // TODO: Check for valid unm/pwd on db
            // If invalid don't forget to remove spinner

            SharedPreferences pref = this.requireActivity().getSharedPreferences(MainActivity.PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("username", unm);
            editor.putString("enc_hash", Base64.encodeToString(
                            /* TODO: Replace this with the db hash */
                            pwd.getBytes(StandardCharsets.UTF_8),
                            Base64.DEFAULT));
            editor.apply();

            unSpin(view);

            this.requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_right)
                    .remove(LoginFragment.this)
                    .commit();
            callbackFragment.closeFragment();
        });

        registerButton.setOnClickListener(view1 -> {
            if (callbackFragment != null) {
                callbackFragment.changeFragment();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    public void spin(View view) {
        view.findViewById(R.id.loginSpinner).setVisibility(View.VISIBLE);
        view.findViewById(R.id.loginCard).setAlpha(0.8f);
        view.findViewById(R.id.spinnerBlocker).setClickable(true);
    }

    public void unSpin(View view) {
        view.findViewById(R.id.loginSpinner).setVisibility(View.GONE);
        view.findViewById(R.id.loginCard).setAlpha(1);
        view.findViewById(R.id.spinnerBlocker).setClickable(false);
    }

    public void setCallbackFragment(CallbackFragment fragment) {
        callbackFragment = fragment;
    }
}