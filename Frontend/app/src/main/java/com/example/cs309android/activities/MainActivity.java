package com.example.cs309android.activities;

import static com.example.cs309android.util.Constants.LOGIN_URL;
import static com.example.cs309android.util.Constants.RESULT_LOGGED_IN;
import static com.example.cs309android.util.Util.spin;
import static com.example.cs309android.util.Util.unSpin;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cs309android.R;
import com.example.cs309android.fragments.home.HomeFragment;
import com.example.cs309android.fragments.login.LoginFragment;
import com.example.cs309android.fragments.login.RegisterFragment;
import com.example.cs309android.fragments.nutrition.NutritionFragment;
import com.example.cs309android.fragments.recipes.RecipesFragment;
import com.example.cs309android.fragments.settings.SettingsFragment;
import com.example.cs309android.fragments.shopping.ShoppingFragment;
import com.example.cs309android.interfaces.CallbackFragment;
import com.example.cs309android.util.RequestHandler;
import com.example.cs309android.util.security.NukeSSLCerts;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

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

    /**
     * Fragment containing the current login window.
     */
    private CallbackFragment loginWindowFragment;

    /**
     * Main window fragment
     */
    private CallbackFragment mainFragment;
    private int currentFragment = 2;

    /**
     * Response codes for callback method. Used by Fragments for this class
     */
    public static final int CALLBACK_SWITCH_TO_REGISTER = 0;
    public static final int CALLBACK_CLOSE_LOGIN = 1;
    public static final int CALLBACK_START_LOGIN = 2;
    public static final int CALLBACK_MOVE_TO_HOME = 3;
//    public static final int CALLBACK_ = 0;

    /**
     * Navbar object at the bottom of the app.
     */
    private BottomNavigationView navbar;

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

        mainFragment = (CallbackFragment) getSupportFragmentManager().findFragmentById(R.id.mainFragment);
        Objects.requireNonNull(mainFragment).setCallbackFragment(this);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow((IBinder) getWindow().getCurrentFocus(), 0);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        // Gets stored username and password hash, if they exist
        SharedPreferences pref = getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
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
                    error.printStackTrace();
                    startLoginFragment();
                });
                RequestHandler.getInstance(this).add(request);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            startLoginFragment();
        }

        navbar = findViewById(R.id.navbar);
        navbar.setSelectedItemId(R.id.home);
        navbar.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.shopping) {
                mainFragment = new ShoppingFragment();
                // Always slide left (furthest left)
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                        .addToBackStack(null)
                        .replace(R.id.mainFragment, (Fragment) mainFragment, null)
                        .commit();
                currentFragment = 0;
            } else if (item.getItemId() == R.id.nutrition) {
                mainFragment = new NutritionFragment();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                // if coming from shopping slide right, otherwise slide left
                if (currentFragment == 0) {
                    transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
                } else {
                    transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left);
                }
                transaction.replace(R.id.mainFragment, (Fragment) mainFragment, null);
                transaction.commit();
                currentFragment = 1;
            } else if (item.getItemId() == R.id.home) {
                mainFragment = new HomeFragment();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                // if coming from shopping/nutrition, slide right, otherwise slide left
                if (currentFragment < 2) {
                    transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
                } else {
                    transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left);
                }
                transaction.replace(R.id.mainFragment, (Fragment) mainFragment, null);
                transaction.commit();
                currentFragment = 2;
            } else if (item.getItemId() == R.id.recipes) {
                mainFragment = new RecipesFragment();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                // if coming from settings, slide left, otherwise slide right
                if (currentFragment == 4) {
                    transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left);
                } else {
                    transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
                }
                transaction.replace(R.id.mainFragment, (Fragment) mainFragment, null);
                transaction.commit();
                currentFragment = 3;
            } else if (item.getItemId() == R.id.settings) {
                mainFragment = new SettingsFragment();
                mainFragment.setCallbackFragment(this);
                // Always slide right
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
                        .addToBackStack(null)
                        .replace(R.id.mainFragment, (Fragment) mainFragment, null)
                        .commit();
                currentFragment = 4;
            } else {
                return false;
            }
            return true;
        });
        navbar.setOnItemReselectedListener(item -> {
            if (item.getItemId() == R.id.shopping) {
                System.out.println("shopping re");
            } else if (item.getItemId() == R.id.nutrition) {
                System.out.println("nutrition re");
            } else if (item.getItemId() == R.id.home) {
                System.out.println("home re");
            } else if (item.getItemId() == R.id.recipes) {
                System.out.println("recipes re");
            } else if (item.getItemId() == R.id.settings) {
                System.out.println("settings re");
            }
        });
//        navbar.setOnMenuItemClickListener(menuItem -> {
//            System.out.println(menuItem.getItemId());
//            return true;
//        });

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
                        .replace(R.id.loginPopup, (Fragment) loginWindowFragment, null)
                        .commit();
                break;
            }
            case (CALLBACK_CLOSE_LOGIN): {
                findViewById(R.id.mainLayout).setAlpha(1);
                findViewById(R.id.loginPopup).setClickable(false);

                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_right)
                        .remove((Fragment) loginWindowFragment)
                        .commit();
                break;
            }
            case (CALLBACK_START_LOGIN): {
                startLoginFragment();
                break;
            }
            case (CALLBACK_MOVE_TO_HOME): {
                mainFragment = new HomeFragment();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.mainFragment, (Fragment) mainFragment, null);
                transaction.commit();
                currentFragment = 2;
                navbar.setSelectedItemId(R.id.home);
                break;
            }
        }
    }

    /**
     * Main activity has no callback.
     *
     * @param fragment Callback fragment.
     */
    @Override
    public void setCallbackFragment(CallbackFragment fragment) {
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
                .add(R.id.loginPopup, (Fragment) loginWindowFragment)
                .commit();
    }
}