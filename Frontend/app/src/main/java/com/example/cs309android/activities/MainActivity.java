package com.example.cs309android.activities;

import static com.example.cs309android.BuildConfig.SSL_OFF;
import static com.example.cs309android.util.Constants.RESULT_LOGGED_IN;
import static com.example.cs309android.util.Util.spin;
import static com.example.cs309android.util.Util.unSpin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.view.inputmethod.InputMethodManager;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.cs309android.R;
import com.example.cs309android.fragments.home.HomeFragment;
import com.example.cs309android.fragments.login.LoginFragment;
import com.example.cs309android.fragments.login.RegisterFragment;
import com.example.cs309android.fragments.nutrition.NutritionFragment;
import com.example.cs309android.fragments.recipes.RecipesFragment;
import com.example.cs309android.fragments.settings.SettingsFragment;
import com.example.cs309android.fragments.shopping.ShoppingFragment;
import com.example.cs309android.interfaces.CallbackFragment;
import com.example.cs309android.models.gson.models.AuthModel;
import com.example.cs309android.models.gson.models.SimpleFoodItem;
import com.example.cs309android.models.gson.request.users.LoginRequest;
import com.example.cs309android.models.gson.response.GenericResponse;
import com.example.cs309android.util.RequestHandler;
import com.example.cs309android.util.Util;
import com.example.cs309android.util.security.NukeSSLCerts;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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
    public static final String APP_NAME = "Föd";

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
     * Auth model for the whole app
     */
    public static AuthModel AUTH_MODEL;

    /**
     * Response codes for callback method. Used by Fragments for this class
     */
    public static final int CALLBACK_SWITCH_TO_REGISTER = 0;
    public static final int CALLBACK_CLOSE_LOGIN = 1;
    public static final int CALLBACK_START_LOGIN = 2;
    public static final int CALLBACK_MOVE_TO_HOME = 3;
    public static final int CALLBACK_FOOD_DETAIL = 4;
    public static final int CALLBACK_SEARCH_FOOD = 5;
//    public static final int CALLBACK_ = 0;

    public static final String PARCEL_FOODITEM = "fooditem";
    public static final String PARCEL_FOODITEMS_LIST = "fooditems";

    public static final String PREF_USERNAME = "username";
    public static final String PREF_HASH = "enc_hash";

    /**
     * Used to launch various activities.
     */
    ActivityResultLauncher<Intent> foodSearchLauncher;

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

    @Override
    protected void onResume() {
        super.onResume();
//        navbar.setSelectedItemId(R.id.home);
//        mainFragment = new HomeFragment();
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.replace(R.id.coordinator, (Fragment) mainFragment, null);
//        transaction.commit();
//        currentFragment = 2;
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
        if (SSL_OFF) {
            NukeSSLCerts.nuke();
        }

        setContentView(R.layout.activity_main);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow((IBinder) getWindow().getCurrentFocus(), 0);

        foodSearchLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        mainFragment = ShoppingFragment.newInstance(Objects.requireNonNull(result.getData()).getParcelableArrayListExtra(PARCEL_FOODITEMS_LIST));
                    } else {
                        mainFragment = new ShoppingFragment();
                    }

                    mainFragment.setCallbackFragment(this);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.coordinator, (Fragment) mainFragment, null)
                            .commit();
                    currentFragment = 0;
                }
        );

        // Gets stored username and password hash, if they exist
        SharedPreferences pref = getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String username = pref.getString(PREF_USERNAME, "").trim();
        String hash = pref.getString(PREF_HASH, "").trim();

        // Attempts a login with stored creds. If they are invalid or don't exist, open login page
        spin(this);
        new LoginRequest(username, hash).request(response -> {
            unSpin(this);
            // Checks if the result is valid or not. If not, opens the login page
            int result = ((GenericResponse) Util.objFromJson(response, GenericResponse.class)).getResult();
            if (result != RESULT_LOGGED_IN) startLoginFragment();
            AUTH_MODEL = new AuthModel(username, hash);
        }, error -> {
            unSpin(this);
            error.printStackTrace();
            startLoginFragment();
        }, this);

        navbar = findViewById(R.id.navbar);
        navbar.setSelectedItemId(R.id.home);
        navbar.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.shopping) {
                mainFragment = new ShoppingFragment();
                mainFragment.setCallbackFragment(this);
                // Always slide left (furthest left)
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                        .replace(R.id.coordinator, (Fragment) mainFragment, null)
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
                transaction.replace(R.id.coordinator, (Fragment) mainFragment, null);
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
                transaction.replace(R.id.coordinator, (Fragment) mainFragment, null);
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
                transaction.replace(R.id.coordinator, (Fragment) mainFragment, null);
                transaction.commit();
                currentFragment = 3;
            } else if (item.getItemId() == R.id.settings) {
                mainFragment = new SettingsFragment();
                mainFragment.setCallbackFragment(this);
                // Always slide right
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
                        .replace(R.id.coordinator, (Fragment) mainFragment, null)
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
    }

    /**
     * Callback method used to control fragment activity
     * <p>
     * CALLBACK_SWITCH_TO_REGISTER:
     * Switches the login screen to the register screen with a nice animation
     * <p>
     * CALLBACK_CLOSE_LOGIN:
     * Closes the login page with a nice animation and permits interaction and removes transparency
     * filter over the main activity
     *
     * @param op     Opcode to decide what to do
     * @param bundle Bundle with args
     */
    @Override
    public void callback(int op, Bundle bundle) {
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
                transaction.replace(R.id.coordinator, (Fragment) mainFragment, null);
                transaction.commit();
                currentFragment = 2;
                navbar.setSelectedItemId(R.id.home);
                break;
            }
            case (CALLBACK_FOOD_DETAIL): {
                Intent intent = new Intent(this, FoodDetailsActivity.class);
                intent.putExtra(PARCEL_FOODITEM, (SimpleFoodItem) bundle.getParcelable(PARCEL_FOODITEM));
                startActivity(intent);
                break;
            }
            case (CALLBACK_SEARCH_FOOD): {
                Intent intent = new Intent(this, FoodSearchActivity.class);
                intent.putExtra(PARCEL_FOODITEMS_LIST, bundle.getParcelableArrayList(PARCEL_FOODITEMS_LIST));
                foodSearchLauncher.launch(intent);
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