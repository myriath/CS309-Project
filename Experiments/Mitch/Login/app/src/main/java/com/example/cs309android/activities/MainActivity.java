package com.example.cs309android.activities;

import static com.example.cs309android.util.Constants.LOGIN_URL;
import static com.example.cs309android.util.Constants.RESULT_LOGGED_IN;
import static com.example.cs309android.util.Constants.TAGS.TAG_GET;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.cs309android.R;
import com.example.cs309android.fragments.CallbackFragment;
import com.example.cs309android.fragments.LoginFragment;
import com.example.cs309android.fragments.RegisterFragment;
import com.example.cs309android.models.RequestHandler;
import com.example.cs309android.util.NukeSSLCerts;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Main activity
 * Most pages should probably use fragments
 * EXPERIMENTS 1, 2
 *
 * @author Mitch Hudson
 */
public class MainActivity extends AppCompatActivity implements CallbackFragment {
    /**
     * DEBUG variable for testing Logs
     */
    // TODO: False for prod
    public static final boolean DEBUG = true;

    /**
     * Preference name for this app's shared preferences.
     */
    public static final String PREF_NAME = "COMS309";
    private SharedPreferences pref;

    /**
     * Fragment manager for subwindows (login, register, etc.)
     */
    private FragmentManager manager;
    private FragmentTransaction transaction;

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

        // Volley test button: GET request for google.com
        findViewById(R.id.getButton).setOnClickListener(view1 -> {
            String url = "https://www.google.com";

            TextView textView = findViewById(R.id.volleyResponse);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    (Response.Listener<String>) response -> {
                        textView.setText("Response is: " + response.substring(0, 500));
                    }, (Response.ErrorListener) error -> textView.setText(error.getMessage())
            );

            stringRequest.setTag(TAG_GET);
            RequestHandler.getInstance(this).add(stringRequest);
        });

        // Unused volley test button
//        findViewById(R.id.postButton).setOnClickListener(view1 -> {
//            String url = "https://www.google.com";
//
//            TextView textView = findViewById(R.id.volleyResponse);
//            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                    (Response.Listener<String>) response -> {
//                        textView.setText("Response is: " + response.substring(0, 500));
//                    }, (Response.ErrorListener) error -> {
//                textView.setText(error.getMessage());
//            }
//            );
//
//            stringRequest.setTag(TAG_POST);
//            RequestHandler.getInstance(this).add(stringRequest);
//        });

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
     * Runs whenever the login/register pages are closed.
     * Removes transparency layer and allows interaction with main activity.
     */
    public void closeFragment() {
        findViewById(R.id.mainLayout).setAlpha(1);
        findViewById(R.id.loginPopup).setClickable(false);
    }

    /**
     * Starts the login fragment.
     * First, makes MainActivity transparent and non-interactable
     * Then creates a new fragment and sets up the opening animations.
     */
    public void startLoginFragment() {
        findViewById(R.id.mainLayout).setAlpha(0.5f);
        findViewById(R.id.loginPopup).setClickable(true);

        LoginFragment fragment = new LoginFragment();
        fragment.setCallbackFragment(this);
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        transaction.add(R.id.loginPopup, fragment);
        transaction.commit();
    }

    /**
     * Runs when the Register button is pressed on the Login page.
     * First, create a new RegisterFragment, then put it on screen with some animations
     */
    @Override
    public void changeFragment() {
        RegisterFragment fragment = new RegisterFragment();
        fragment.setCallbackFragment(this);
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
        transaction.addToBackStack(null);
        transaction.replace(R.id.loginPopup, fragment, null);
        transaction.commit();
    }
}