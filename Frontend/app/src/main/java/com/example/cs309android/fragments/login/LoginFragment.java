package com.example.cs309android.fragments.login;

import static com.example.cs309android.util.Constants.RESULT_ERROR_USER_HASH_MISMATCH;
import static com.example.cs309android.util.Constants.RESULT_LOGGED_IN;
import static com.example.cs309android.util.Constants.RESULT_OK;
import static com.example.cs309android.util.Constants.RESULT_REGEN_TOKEN;
import static com.example.cs309android.util.Util.hideKeyboard;
import static com.example.cs309android.util.Util.spin;
import static com.example.cs309android.util.Util.unSpin;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cs309android.R;
import com.example.cs309android.activities.MainActivity;
import com.example.cs309android.fragments.BaseFragment;
import com.example.cs309android.models.gson.request.users.LoginHashRequest;
import com.example.cs309android.models.gson.request.users.RegenTokenRequest;
import com.example.cs309android.models.gson.request.users.SaltRequest;
import com.example.cs309android.models.gson.response.GenericResponse;
import com.example.cs309android.models.gson.response.users.LoginHashResponse;
import com.example.cs309android.models.gson.response.users.SaltResponse;
import com.example.cs309android.util.Toaster;
import com.example.cs309android.util.Util;
import com.example.cs309android.util.security.Hasher;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

/**
 * Login fragment that makes up the Login page.
 * Shown whenever a user needs to login (either the stored creds become invalid or first open).
 *
 * @author Mitch Hudson
 */
public class LoginFragment extends BaseFragment {
    Button loginButton, registerButton;
    TextInputLayout usernameField, passwordField;

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
            hideKeyboard(view1, requireActivity());

            String unm = Objects.requireNonNull(usernameField.getEditText()).getText().toString();
            String pwd = Objects.requireNonNull(passwordField.getEditText()).getText().toString();
            usernameField.setError(null);
            passwordField.setError(null);

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
            new SaltRequest(unm).request(response -> {
                SaltResponse saltResponse = Util.objFromJson(response, SaltResponse.class);
                int result = saltResponse.getResult();
                if (result == RESULT_ERROR_USER_HASH_MISMATCH) {
                    passwordField.setError("Username / Password mismatch");
                    unSpin(view);
                    return;
                } else if (result != RESULT_OK) {
                    Toaster.toastShort("Unexpected error", getActivity());
                    unSpin(view);
                    return;
                }

                // Use salt with given password to generate test hash
                byte[] salt = Base64.decode(saltResponse.getSalt(), Base64.DEFAULT);
                String hash = Hasher.getEncoded(Hasher.hash(pwd.toCharArray(), salt));

                new LoginHashRequest(unm, hash).unspinOnComplete(response1 -> {
                    // Check for errors
//                    int result1 = ((GenericResponse) Util.objFromJson(response1, GenericResponse.class)).getResult();
                    LoginHashResponse loginResponse = Util.objFromJson(response1, GenericResponse.class);
                    if (loginResponse.getResult() == RESULT_ERROR_USER_HASH_MISMATCH) {
                        passwordField.setError("Username / Password mismatch");
                        return;
                    } else if (loginResponse.getResult() != RESULT_LOGGED_IN && loginResponse.getResult() != RESULT_REGEN_TOKEN) {
                        Toaster.toastShort("Unexpected error", getActivity());
                        return;
                    }

                    if (loginResponse.getResult() == RESULT_LOGGED_IN) {
                        // No errors, so store credentials for future use
                        // (HASH + USERNAME, NO PLAINTEXT PWD STORED!)
                        SharedPreferences pref = requireActivity().getSharedPreferences(MainActivity.PREF_NAME, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString(MainActivity.PREF_TOKEN, loginResponse.getToken());
                        editor.apply();

                        MainActivity.TOKEN = loginResponse.getToken();
                    } else if (loginResponse.getResult() == RESULT_REGEN_TOKEN) {
                        String token = Hasher.genToken();

                        new RegenTokenRequest(token, loginResponse.getToken()).request(response2 -> {
                            GenericResponse genericResponse = Util.objFromJson(response2, GenericResponse.class);
                            if (genericResponse.getResult() == RESULT_OK) {
                                // No errors, so store credentials for future use
                                // (HASH + USERNAME, NO PLAINTEXT PWD STORED!)
                                SharedPreferences pref = requireActivity().getSharedPreferences(MainActivity.PREF_NAME, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString(MainActivity.PREF_TOKEN, token);
                                editor.apply();

                                MainActivity.TOKEN = token;
                            } else {
                                Toaster.toastShort("Error", getContext());
                            }
                        }, getContext());
                    }

                    // Close window
                    callbackFragment.callback(MainActivity.CALLBACK_MOVE_TO_HOME, null);
                    callbackFragment.callback(MainActivity.CALLBACK_CLOSE_LOGIN, null);
                }, error -> {
                    Toaster.toastShort("Unexpected error", getActivity());
                    error.printStackTrace();
                }, requireActivity(), view);
            }, error -> {
                Toaster.toastShort("Unexpected error", getActivity());
                error.printStackTrace();
                unSpin(view);
            }, requireActivity());
        });

        registerButton.setOnClickListener(view1 -> {
            hideKeyboard(view1, requireActivity());

            usernameField.setError(null);
            passwordField.setError(null);
            // If the register button is pressed, switch screens
            if (callbackFragment == null) return;
            callbackFragment.callback(MainActivity.CALLBACK_SWITCH_TO_REGISTER, null);
        });

        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.loginTextView), (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            ((ViewGroup.MarginLayoutParams) v.getLayoutParams()).topMargin = insets.top;
            return WindowInsetsCompat.CONSUMED;
        });

        // Inflate the layout for this fragment
        return view;
    }
}