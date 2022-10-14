package com.example.cs309android.fragments.login;

import static com.example.cs309android.util.Constants.RESULT_ERROR_EMAIL_TAKEN;
import static com.example.cs309android.util.Constants.RESULT_ERROR_USERNAME_TAKEN;
import static com.example.cs309android.util.Constants.RESULT_REGEN_TOKEN;
import static com.example.cs309android.util.Constants.RESULT_USER_CREATED;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.activities.MainActivity;
import com.example.cs309android.fragments.BaseFragment;
import com.example.cs309android.models.Hash;
import com.example.cs309android.models.gson.request.users.RegisterRequest;
import com.example.cs309android.models.gson.response.users.LoginResponse;
import com.example.cs309android.util.Toaster;
import com.example.cs309android.util.Util;
import com.example.cs309android.util.security.Hasher;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

/**
 * Register fragment that makes up the Register new user page.
 * Shown whenever a user needs to create a new account.
 *
 * @author Mitch Hudson
 */
public class RegisterFragment extends BaseFragment {
    Button registerButton;
    TextInputLayout usernameField, emailField, passwordField;

    /**
     * Ran whenever the fragment is shown.
     * Register steps:
     * 1. Generate salt
     * 2. Generate hash from given password and generated salt
     * 3. Send register request to server with username, email, salt, and generated hash
     * 4. React to server response
     *
     * @param inflater           Inflater to inflate the xml
     * @param container          This Fragment's container
     * @param savedInstanceState Any saved instance state data
     * @return The fragment's inflated view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflates the view and sets class variables for buttons/edit texts
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ((TextView) view.findViewById(R.id.welcomeMsgTextView)).setText(getResources().getString(R.string.welcome_msg, MainActivity.APP_NAME));

        usernameField = view.findViewById(R.id.unameField);
        emailField = view.findViewById(R.id.emailField);
        passwordField = view.findViewById(R.id.passwordField);

        registerButton = view.findViewById(R.id.buttonRegister);
        registerButton.setOnClickListener(view1 -> {
            Util.hideKeyboard(view1, requireActivity());

            emailField.setError(null);
            usernameField.setError(null);
            passwordField.setError(null);
            String unm = Objects.requireNonNull(usernameField.getEditText()).getText().toString();
            String email = Objects.requireNonNull(emailField.getEditText()).getText().toString();
            String pwd = Objects.requireNonNull(passwordField.getEditText()).getText().toString();

            // Basic checks for empty/non-matching fields.
            // More checks should be ran serverside for duplicate accounts.
            if (email.equals("")) {
                emailField.setError("Username can't be empty");
                emailField.requestFocus();
                return;
            } else if (unm.equals("")) {
                usernameField.setError("Email can't be empty");
                usernameField.requestFocus();
                return;
            } else if (pwd.equals("")) {
                passwordField.setError("Password can't be empty");
                passwordField.requestFocus();
                return;
            }

            Util.spin(view);

            // Generates a new hash with the given password.
            Hash pwdHash = Hasher.generateNewHash(pwd.toCharArray());
            String hash = Hasher.getEncoded(pwdHash.getHash());
            String salt = Hasher.getEncoded(pwdHash.getSalt());

            register((GlobalClass) requireActivity().getApplicationContext(), email, unm, hash, salt, view, 0);
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
     * Performs a register attempt
     * Stops after being told to regen the token 5 times, and throws an error
     *
     * @param email email for the new user
     * @param unm   username for the new user
     * @param hash  hash for the new user
     * @param salt  salt for the new user
     * @param view  view to find views by id with
     * @param depth number of failed token generations
     */
    public void register(GlobalClass global, String email, String unm, String hash, String salt, View view, int depth) {
        String token = Hasher.genToken();

        new RegisterRequest(email, unm, hash, salt, token).unspinOnComplete(response -> {
            LoginResponse loginResponse = Util.objFromJson(response, LoginResponse.class);
            // Check for errors.
            int result = loginResponse.getResult();
            if (result == RESULT_ERROR_USERNAME_TAKEN) {
                usernameField.setError("Username taken");
                return;
            } else if (result == RESULT_ERROR_EMAIL_TAKEN) {
                emailField.setError("Account already exists");
                return;
            } else if (result == RESULT_REGEN_TOKEN && depth < 5) {
                register(global, email, unm, hash, salt, view, depth + 1);
            } else if (result != RESULT_USER_CREATED) {
                Toaster.toastShort("Unexpected error", getActivity());
                return;
            }

            // If there was no error, store valid creds and close the page.
            Util.login(global, token, loginResponse);

            callbackFragment.callback(MainActivity.CALLBACK_MOVE_TO_HOME, null);
            callbackFragment.callback(MainActivity.CALLBACK_CLOSE_LOGIN, null);
        }, error -> {
            Toaster.toastShort("Unexpected error", getActivity());
            error.printStackTrace();
        }, requireActivity(), view);
    }
}