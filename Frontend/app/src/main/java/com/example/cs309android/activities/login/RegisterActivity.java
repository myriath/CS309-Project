package com.example.cs309android.activities.login;

import static com.example.cs309android.util.Constants.PARCEL_PASSWORD;
import static com.example.cs309android.util.Constants.PARCEL_USERNAME;
import static com.example.cs309android.util.Constants.RESULT_ERROR_EMAIL_TAKEN;
import static com.example.cs309android.util.Constants.RESULT_ERROR_USERNAME_TAKEN;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.models.Hash;
import com.example.cs309android.util.Util;
import com.example.cs309android.util.security.Hasher;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

/**
 * Register activity handles registering a new account with the server
 *
 * @author Mitch Hudson
 */
public class RegisterActivity extends AppCompatActivity {
    /**
     * Runs when the activity starts
     *
     * @param savedInstanceState Saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        GlobalClass global = (GlobalClass) getApplicationContext();

        TextInputLayout usernameField = findViewById(R.id.unameField);
        TextInputLayout emailField = findViewById(R.id.emailField);
        TextInputLayout passwordField = findViewById(R.id.passwordField);

        Intent input = getIntent();
        Objects.requireNonNull(usernameField.getEditText()).setText(input.getStringExtra(PARCEL_USERNAME));
        Objects.requireNonNull(passwordField.getEditText()).setText(input.getStringExtra(PARCEL_PASSWORD));

        Button registerButton = findViewById(R.id.buttonRegister);
        registerButton.setOnClickListener(view1 -> {
            Util.hideKeyboard(view1, this);

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

            Util.spin(this);

            // Generates a new hash with the given password.
            Hash pwdHash = Hasher.generateNewHash(pwd.toCharArray());
            String hash = Hasher.getEncoded(pwdHash.getHash());
            String salt = Hasher.getEncoded(pwdHash.getSalt());

            Util.register(global, email, unm, hash, salt, () -> {
                Util.unSpin(this);
                setResult(RESULT_OK);
                finish();
            }, result -> {
                switch (result) {
                    case RESULT_ERROR_USERNAME_TAKEN: {
                        usernameField.setError("Username taken");
                        break;
                    }
                    case RESULT_ERROR_EMAIL_TAKEN: {
                        emailField.setError("Account for this email already exists");
                        break;
                    }
                    default: {
                        System.out.println(result);
                    }
                }
            }, error -> {
                Util.unSpin(this);
            }, 0);
        });
    }
}
