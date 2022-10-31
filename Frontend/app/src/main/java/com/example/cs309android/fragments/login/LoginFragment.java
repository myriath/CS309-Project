package com.example.cs309android.fragments.login;

import static com.example.cs309android.util.Constants.RESULT_ERROR_USER_HASH_MISMATCH;
import static com.example.cs309android.util.Constants.RESULT_LOGGED_IN;
import static com.example.cs309android.util.Constants.RESULT_OK;
import static com.example.cs309android.util.Constants.RESULT_REGEN_TOKEN;
import static com.example.cs309android.util.Util.hideKeyboard;
import static com.example.cs309android.util.Util.spin;
import static com.example.cs309android.util.Util.unSpin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.activities.MainActivity;
import com.example.cs309android.fragments.BaseFragment;
import com.example.cs309android.models.gson.request.users.LoginHashRequest;
import com.example.cs309android.models.gson.request.users.SaltRequest;
import com.example.cs309android.models.gson.response.users.LoginResponse;
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
                byte[] salt = Hasher.B64_URL_DECODER.decode(saltResponse.getSalt());
                String hash = Hasher.getEncoded(Hasher.hash(pwd.toCharArray(), salt));
                loginAttempt((GlobalClass) requireActivity().getApplicationContext(), unm, hash, 0);
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

    /**
     * Recursive method for login attempts
     * (recursive in case duplicates are generated)
     *
     * @param global GlobalClass for storing application variables
     * @param unm    Username for login attempt
     * @param hash   Hash for login attempt
     * @param depth  number of retries
     */
    public void loginAttempt(GlobalClass global, String unm, String hash, int depth) {
        String token = Hasher.genToken();

        new LoginHashRequest(unm, hash, token).request(response -> {
            LoginResponse loginResponse = Util.objFromJson(response, LoginResponse.class);
            int result = loginResponse.getResult();
            if (result == RESULT_ERROR_USER_HASH_MISMATCH) {
                passwordField.setError("Username / Password mismatch");
            } else if (result == RESULT_LOGGED_IN || result == RESULT_OK) {
                // No errors, so store credentials for future use
                // (HASH + USERNAME, NO PLAINTEXT PWD STORED!)
                Util.login(global, token, loginResponse, requireActivity());
                Util.unSpin(requireActivity());

                callbackFragment.callback(MainActivity.CALLBACK_MOVE_TO_HOME, null);
                callbackFragment.callback(MainActivity.CALLBACK_CLOSE_LOGIN, null);
            } else if (result == RESULT_REGEN_TOKEN && depth < MainActivity.TOKEN_MAX_DEPTH) {
                loginAttempt(global, unm, hash, depth + 1);
            } else {
                Toaster.toastShort("Error", getContext());
                Util.unSpin(requireActivity());
            }
        }, error -> {
            Toaster.toastShort("Unexpected error", getActivity());
            error.printStackTrace();
            Util.unSpin(requireActivity());
        }, getContext());
    }
}