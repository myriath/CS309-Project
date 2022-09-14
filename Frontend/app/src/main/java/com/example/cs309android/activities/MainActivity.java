package com.example.cs309android.activities;

import static com.example.cs309android.util.Constants.LOGIN_URL;
import static com.example.cs309android.util.Constants.RESULT_LOGGED_IN;
import static com.example.cs309android.util.Util.*;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

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
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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
     * Name of the app for all mentions in the app
     * TODO: Name the app
     */
    public static final String APP_NAME = "TEST";

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
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        // TODO: Remove for production
        // Removes SSL certificate checking until we can create a cert with a cert authority
        if (DEBUG) {
            NukeSSLCerts.nuke();
        }

        setContentView(R.layout.activity_main);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow((IBinder) getWindow().getCurrentFocus(), 0);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        // Gets stored username and password hash, if they exist
        pref = getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String username = pref.getString("username", null);
        String encodedHash = pref.getString("enc_hash", null);
        // Attempts a login with stored creds. If they are invalid or don't exist, open login page
        if (username != null && encodedHash != null) {
            spin(this);
            try {
                // Creates a new request with username and hash as the body
                JSONObject jsonBody = new JSONObject();
                jsonBody.put("username", username);
                jsonBody.put("hash", encodedHash);

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, LOGIN_URL, jsonBody,
                        response -> {
                            unSpin(this);
                            // Checks if the result is valid or not. If not, opens the login page
                            try {
                                int result = response.getInt("result");
                                if (result != RESULT_LOGGED_IN) startLoginFragment();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }, error -> {
                    unSpin(this);
                    startLoginFragment();
                });
                RequestHandler.getInstance(this).add(request);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            startLoginFragment();
        }

        // Logout button removes stored creds and prompts login
        Button logout = findViewById(R.id.logoutButton);
        logout.setOnClickListener(view -> {
            hideKeyboard(view, this);

            SharedPreferences.Editor editor = pref.edit();
            editor.remove("username");
            editor.remove("enc_hash");
            editor.apply();
            startLoginFragment();
        });
        ViewCompat.setOnApplyWindowInsetsListener(logout, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            ((ViewGroup.MarginLayoutParams) v.getLayoutParams()).topMargin = insets.top;
            return WindowInsetsCompat.CONSUMED;
        });

        BottomAppBar navbar = findViewById(R.id.navbar);
        navbar.setOnClickListener(view -> {
            System.out.println("nav press");
        });
        navbar.setOnMenuItemClickListener(menuItem -> {
            System.out.println(menuItem.getItemId());
            return true;
        });

//        BottomNavigationView bottomNav = findViewById(R.id.navbar);
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
//        findViewById(R.id.mainLayout).setAlpha(0.5f);
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