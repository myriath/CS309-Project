package com.example.cs309android.activities;

import static com.example.cs309android.util.Constants.RESULT_LOGGED_IN;
import static com.example.cs309android.util.Constants.TAGS.TAG_GET;
import static com.example.cs309android.util.Constants.TAGS.TAG_POST;
import static com.example.cs309android.util.Constants.LOGIN_URL;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
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

import java.util.Arrays;

/**
 * Main activity
 * EXPERIMENT 1, 2
 *
 * @author Mitch Hudson
 */
public class MainActivity extends AppCompatActivity implements CallbackFragment {
    // TODO: False for prod
    public static final boolean DEBUG = true;

    public static final String PREF_NAME = "COMS309";
    private SharedPreferences pref;

    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected void onStop() {
        super.onStop();
        RequestHandler.getInstance(this).cancelAll();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: Remove for production
        if (DEBUG) {
            NukeSSLCerts.nuke();
        }

        setContentView(R.layout.activity_main);

        pref = getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        String username = pref.getString("username", null);
        String encodedHash = pref.getString("enc_hash", null);
        if (username != null && encodedHash != null) {
            JSONObject jsonBody = new JSONObject();
            try {
                jsonBody.put("username", username);
                jsonBody.put("hash", encodedHash);

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, LOGIN_URL, jsonBody,
                        (Response.Listener<JSONObject>) response -> {
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

        findViewById(R.id.getButton).setOnClickListener(view1 -> {
            String url = "https://www.google.com";

            TextView textView = findViewById(R.id.volleyResponse);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    (Response.Listener<String>) response -> {
                        textView.setText("Response is: " + response.substring(0, 500));
                    }, (Response.ErrorListener) error -> {
                textView.setText(error.getMessage());
            }
            );

            stringRequest.setTag(TAG_GET);
            RequestHandler.getInstance(this).add(stringRequest);
        });

        findViewById(R.id.postButton).setOnClickListener(view1 -> {
            String url = "https://www.google.com";

            TextView textView = findViewById(R.id.volleyResponse);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    (Response.Listener<String>) response -> {
                        textView.setText("Response is: " + response.substring(0, 500));
                    }, (Response.ErrorListener) error -> {
                textView.setText(error.getMessage());
            }
            );

            stringRequest.setTag(TAG_POST);
            RequestHandler.getInstance(this).add(stringRequest);
        });

        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(view -> {
            SharedPreferences.Editor editor = pref.edit();
            editor.remove("username");
            editor.remove("enc_hash");
            editor.apply();
            startLoginFragment();
        });
    }

    public void closeFragment() {
        findViewById(R.id.mainLayout).setAlpha(1);
        findViewById(R.id.loginPopup).setClickable(false);
    }

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