package com.example.cs309android.fragments;

import static com.example.cs309android.util.Constants.RESULT_ERROR_USER_HASH_MISMATCH;
import static com.example.cs309android.util.Constants.RESULT_LOGGED_IN;
import static com.example.cs309android.util.Constants.RESULT_OK;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.example.cs309android.util.Constants;
import com.example.cs309android.util.Hasher;
import com.example.cs309android.util.Toaster;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Login fragment that makes up the Login page.
 * Shown whenever a user needs to login (either the stored creds become invalid or first open).
 *
 * @author Mitch Hudson
 */
public class LoginFragment extends LoginWindowFragmentBase {
    Button loginButton, registerButton;
    EditText usernameField, passwordField;

    /**
     * Ran whenever the fragment is shown.
     * Login steps:
     * 1. Get the salt value for the account from the server
     * 2. Hash the given plaintext password + server salt into the hash
     * 3. Send the hash and username to the server for it to check against stored hashes.
     * 4. Evaluate response.
     *
     * @param inflater           Inflater to inflate the xml
     * @param container          This Fragment's container
     * @param savedInstanceState Any saved instance state data
     * @return The fragment's inflated view
     */
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

            // Checks for empty fields
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

            try {
                // Create request body for salt request
                JSONObject bodyJson = new JSONObject();
                bodyJson.put("username", unm);
                JsonObjectRequest saltRequest = new JsonObjectRequest(Request.Method.POST, Constants.SALT_URL, bodyJson,
                        (Response.Listener<JSONObject>) response -> {
                            try {
                                // Check for errors.
                                int result = response.getInt("result");
                                if (result == RESULT_ERROR_USER_HASH_MISMATCH) {
                                    passwordField.setError("Username / Password mismatch");
                                    unSpin(view);
                                    return;
                                } else if (result != RESULT_OK) {
                                    Toaster.toastShort("Unexpected error", this.getActivity());
                                    Log.e("Error", response.toString());
                                    Log.e("Error", String.valueOf(result));
                                    unSpin(view);
                                    return;
                                }

                                // Use salt with given password to generate test hash
                                byte[] salt = Base64.decode(response.getString("salt"), Base64.DEFAULT);
                                byte[] hash = Hasher.hash(pwd.toCharArray(), salt);

                                // Put the hash into the request body
                                bodyJson.put("hash", Base64.encodeToString(hash, Base64.DEFAULT));
                                JsonObjectRequest loginRequest = new JsonObjectRequest(Request.Method.POST, Constants.LOGIN_URL, bodyJson,
                                        (Response.Listener<JSONObject>) response1 -> {
                                            try {
                                                // Check for errors
                                                int result1 = response1.getInt("result");
                                                if (result1 == RESULT_ERROR_USER_HASH_MISMATCH) {
                                                    passwordField.setError("Username / Password mismatch");
                                                    unSpin(view);
                                                    return;
                                                } else if (result1 != RESULT_LOGGED_IN) {
                                                    Toaster.toastShort("Unexpected error", this.getActivity());
                                                    unSpin(view);
                                                    return;
                                                }

                                                // No errors, so store credentials for future use
                                                // (HASH + USERNAME, NO PLAINTEXT PWD STORED!)
                                                SharedPreferences pref = this.requireActivity().getSharedPreferences(MainActivity.PREF_NAME, Context.MODE_PRIVATE);
                                                SharedPreferences.Editor editor = pref.edit();
                                                editor.putString("username", unm);
                                                editor.putString("enc_hash", Base64.encodeToString(hash, Base64.DEFAULT));
                                                editor.apply();

                                                unSpin(view);

                                                // Close window
                                                callbackFragment.callback(MainActivity.CALLBACK_CLOSE_LOGIN);
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }, error -> {
                                    Toaster.toastShort("Unexpected error", this.getActivity());
                                    unSpin(view);
                                });
                                RequestHandler.getInstance(this.getActivity()).add(loginRequest);
                            } catch (JSONException ignored) {
                            }
                        }, error -> {
                    Toaster.toastShort("Unexpected error", this.getActivity());
                    unSpin(view);
                });
                RequestHandler.getInstance(this.getActivity()).add(saltRequest);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

        registerButton.setOnClickListener(view1 -> {
            // If the register button is pressed, switch screens
            if (callbackFragment == null) return;
            callbackFragment.callback(MainActivity.CALLBACK_SWITCH_TO_REGISTER);
        });

        // Inflate the layout for this fragment
        return view;
    }
}