package com.example.cs309android.activities.login;

import static com.example.cs309android.util.Constants.PARCEL_BACK_ENABLED;
import static com.example.cs309android.util.Constants.RESULT_ERROR_USER_HASH_MISMATCH;
import static com.example.cs309android.util.Util.hideKeyboard;
import static com.example.cs309android.util.Util.spin;
import static com.example.cs309android.util.Util.unSpin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.util.Toaster;
import com.example.cs309android.util.Util;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

/**
 * Login Activity handles logging into a new account
 *
 * @author Mitch Hudson
 */
public class LoginActivity extends AppCompatActivity {
    /**
     * Password input field
     */
    private TextInputLayout passwordField;
    /**
     * Launcher for the register page
     */
    private ActivityResultLauncher<Intent> registerLauncher;

    /**
     * True if the back button should allow the user to return to the previous page
     */
    private boolean backEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        backEnabled = getIntent().getBooleanExtra(PARCEL_BACK_ENABLED, true);

        registerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        setResult(RESULT_OK);
                        finish();
                    }
                }
        );

        TextInputLayout usernameField = findViewById(R.id.unameField);
        passwordField = findViewById(R.id.passwordField);

        Button loginButton = findViewById(R.id.buttonLogin);
        Button registerButton = findViewById(R.id.buttonRegister);

        GlobalClass global = (GlobalClass) getApplicationContext();

        loginButton.setOnClickListener(view1 -> {
            hideKeyboard(view1, this);

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

            spin(this);
            Util.loginAttempt(global, unm, pwd, () -> {
                unSpin(this);
                System.out.println(unm + pwd);
                setResult(RESULT_OK);
                finish();
            }, result -> {
                unSpin(this);
                if (result == RESULT_ERROR_USER_HASH_MISMATCH) {
                    passwordField.setError("Username / Password mismatch");
                } else {
                    Toaster.toastShort("Unexpected error", this);
                }
            }, error -> {
                unSpin(this);
            });
        });

        registerButton.setOnClickListener(view1 -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            registerLauncher.launch(intent);
        });
    }

    @Override
    public void onBackPressed() {
        if (backEnabled) {
            super.onBackPressed();
        }
    }
}
