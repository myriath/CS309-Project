package com.example.cs309android.fragments;

import static com.example.cs309android.util.Constants.REGISTER_URL;
import static com.example.cs309android.util.Constants.RESULT_ERROR_EMAIL_TAKEN;
import static com.example.cs309android.util.Constants.RESULT_ERROR_USERNAME_TAKEN;
import static com.example.cs309android.util.Constants.RESULT_ERROR_USER_HASH_MISMATCH;
import static com.example.cs309android.util.Constants.RESULT_OK;
import static com.example.cs309android.util.Constants.RESULT_USER_CREATED;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cs309android.R;
import com.example.cs309android.activities.MainActivity;
import com.example.cs309android.models.RequestHandler;
import com.example.cs309android.util.Hash;
import com.example.cs309android.util.Hasher;
import com.example.cs309android.util.Toaster;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private CallbackFragment callbackFragment;
    Button registerButton;
    EditText usernameField, emailField, passwordField, passwordField2;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        usernameField = view.findViewById(R.id.unameField);
        emailField = view.findViewById(R.id.emailField);
        passwordField = view.findViewById(R.id.passwordField);
        passwordField2 = view.findViewById(R.id.passwordField2);

        registerButton = view.findViewById(R.id.buttonRegister);
        registerButton.setOnClickListener(view1 -> {
            String unm = usernameField.getText().toString();
            String email = emailField.getText().toString();
            String pwd = passwordField.getText().toString();
            String pwd2 = passwordField2.getText().toString();

            if (unm.equals("")) {
                usernameField.setError("Username can't be empty");
                usernameField.requestFocus();
                return;
            } else if (email.equals("")) {
                emailField.setError("Email can't be empty");
                emailField.requestFocus();
                return;
            } else if (pwd.equals("")) {
                passwordField.setError("Password can't be empty");
                passwordField.requestFocus();
                return;
            } else if (!pwd.equals(pwd2)) {
                passwordField2.setError("Passwords don't match");
                passwordField2.requestFocus();
                return;
            }

            spin(view);

            Hash pwdHash = Hasher.generateNewHash(passwordField.getText().toString().toCharArray());
            JSONObject jsonBody = new JSONObject();
            try {
                jsonBody.put("username", unm);
                jsonBody.put("email", email);
                jsonBody.put("salt", Base64.encodeToString(pwdHash.getSalt(), Base64.DEFAULT).trim());
                jsonBody.put("hash", Base64.encodeToString(pwdHash.getHash(), Base64.DEFAULT).trim());
                JsonObjectRequest registerRequest = new JsonObjectRequest(Request.Method.POST, REGISTER_URL, jsonBody, (Response.Listener<JSONObject>) response -> {
                    try {
                        int result = response.getInt("result");
                        if (result == RESULT_ERROR_USERNAME_TAKEN) {
                            usernameField.setError("Username taken");
                            unSpin(view);
                            return;
                        } else if (result == RESULT_ERROR_EMAIL_TAKEN) {
                            emailField.setError("Account already exists");
                            unSpin(view);
                            return;
                        } else if (result != RESULT_USER_CREATED) {
                            Toaster.toastShort("Unexpected error", this.getActivity());
                            unSpin(view);
                            return;
                        }

                        SharedPreferences pref = this.requireActivity().getSharedPreferences(MainActivity.PREF_NAME, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("username", unm);
                        editor.putString("enc_hash", Base64.encodeToString(pwdHash.getHash(), Base64.DEFAULT));
                        editor.apply();

                        unSpin(view);

                        this.requireActivity()
                                .getSupportFragmentManager()
                                .beginTransaction()
                                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right)
                                .remove(RegisterFragment.this)
                                .commit();
                        callbackFragment.closeFragment();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
                    Toaster.toastShort("Unexpected error", this.getActivity());
                    unSpin(view);
                });
                RequestHandler.getInstance(this.getActivity()).add(registerRequest);
            } catch (JSONException e) {
                Toaster.toastShort("Unexpected Error", this.getActivity());
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