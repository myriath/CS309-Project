package com.example.cs309android.activities;

import static com.example.cs309android.util.Constants.LOGIN_URL;
import static com.example.cs309android.util.Constants.RESULT_LOGGED_IN;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cs309android.R;
import com.example.cs309android.fragments.login.LoginFragment;
import com.example.cs309android.fragments.login.LoginWindowFragmentBase;
import com.example.cs309android.fragments.login.RegisterFragment;
import com.example.cs309android.interfaces.CallbackFragment;
import com.example.cs309android.util.RequestHandler;
import com.example.cs309android.util.security.NukeSSLCerts;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Main activity
 * Most pages should probably use fragments
 *
 * @author Mitch Hudson
 */
public class MainActivity extends AppCompatActivity implements CallbackFragment {
    /**
     * DEBUG variable for testing Logs
     * TODO: False for prod
     */
    public static final boolean DEBUG = true;

    /**
     * Preference name for this app's shared preferences.
     */
    public static final String PREF_NAME = "COMS309";
    private SharedPreferences pref;

    /**
     * Fragment containing the current login window.
     */
    private LoginWindowFragmentBase loginWindowFragment;

    /**
     * Response codes for callback method. Used by Fragments for this class
     */
    public static final int CALLBACK_SWITCH_TO_REGISTER = 0;
    public static final int CALLBACK_CLOSE_LOGIN = 1;
//    public static final int CALLBACK_ = 0;

    /**
     * Cancels all Volley requests when the application is closed or otherwise stopped.
     */
    @Override
    protected void onStop() {
        super.onStop();
        RequestHandler.getInstance(this).cancelAll();
    }

    /**
     * Starts up the app.
     *
     * @param savedInstanceState Saved instance state data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: Remove for production
        // Removes SSL certificate checking until we can create a cert with a cert authority
        if (DEBUG) {
            NukeSSLCerts.nuke();
        }

        setContentView(R.layout.activity_main);

        // Gets stored username and password hash, if they exist
        pref = getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String username = pref.getString("username", null);
        String encodedHash = pref.getString("enc_hash", null);
        // Attempts a login with stored creds. If they are invalid or don't exist, open login page
        if (username != null && encodedHash != null) {
            try {
                // Creates a new request with username and hash as the body
                JSONObject jsonBody = new JSONObject();
                jsonBody.put("username", username);
                jsonBody.put("hash", encodedHash);

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, LOGIN_URL, jsonBody,
                        (Response.Listener<JSONObject>) response -> {
                            // Checks if the result is valid or not. If not, opens the login page
                            try {
                                int result = response.getInt("result");
                                if (result != RESULT_LOGGED_IN) startLoginFragment();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }, error -> startLoginFragment());
                RequestHandler.getInstance(this).add(request);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            startLoginFragment();
        }

        // Logout button removes stored creds and prompts login
        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(view -> {
            SharedPreferences.Editor editor = pref.edit();
            editor.remove("username");
            editor.remove("enc_hash");
            editor.apply();
            startLoginFragment();
        });
    }

    /**
     * Callback method used to control fragment activity
     *
     * CALLBACK_SWITCH_TO_REGISTER:
     * Switches the login screen to the register screen with a nice animation
     *
     * CALLBACK_CLOSE_LOGIN:
     * Closes the login page with a nice animation and permits interaction and removes transparency
     * filter over the main activity
     */
    @Override
    public void callback(int op) {
        switch (op) {
            case (CALLBACK_SWITCH_TO_REGISTER): {
                loginWindowFragment = new RegisterFragment();
                loginWindowFragment.setCallbackFragment(this);
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
                        .addToBackStack(null)
                        .replace(R.id.loginPopup, loginWindowFragment, null)
                        .commit();
                break;
            }
            case (CALLBACK_CLOSE_LOGIN): {
                findViewById(R.id.mainLayout).setAlpha(1);
                findViewById(R.id.loginPopup).setClickable(false);

                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_right)
                        .remove(loginWindowFragment)
                        .commit();
                break;
            }
        }
    }

    /**
     * Starts the login fragment.
     * First, makes MainActivity transparent and non-interactable
     * Then creates a new fragment and sets up the opening animations.
     */
    public void startLoginFragment() {
        findViewById(R.id.mainLayout).setAlpha(0.5f);
        findViewById(R.id.loginPopup).setClickable(true);

        loginWindowFragment = new LoginFragment();
        loginWindowFragment.setCallbackFragment(this);
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                .add(R.id.loginPopup, loginWindowFragment)
                .commit();
    }
}