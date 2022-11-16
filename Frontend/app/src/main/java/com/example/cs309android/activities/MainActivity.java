package com.example.cs309android.activities;

import static com.example.cs309android.BuildConfig.SSL_OFF;
import static com.example.cs309android.util.Constants.CALLBACK_FOOD_DETAIL;
import static com.example.cs309android.util.Constants.CALLBACK_MOVE_TO_HOME;
import static com.example.cs309android.util.Constants.CALLBACK_MOVE_TO_SETTINGS;
import static com.example.cs309android.util.Constants.CALLBACK_SEARCH_FOOD;
import static com.example.cs309android.util.Constants.CALLBACK_START_LOGIN;
import static com.example.cs309android.util.Constants.INTENT_SHOPPING_LIST;
import static com.example.cs309android.util.Constants.PARCEL_BACK_ENABLED;
import static com.example.cs309android.util.Constants.PARCEL_BUTTON_CONTROL;
import static com.example.cs309android.util.Constants.PARCEL_FOODITEM;
import static com.example.cs309android.util.Constants.PARCEL_FOODITEMS_LIST;
import static com.example.cs309android.util.Constants.PARCEL_INTENT_CODE;
import static com.example.cs309android.util.Constants.PARCEL_LOGGED_OUT;
import static com.example.cs309android.util.Constants.PREF_FIRST_TIME;
import static com.example.cs309android.util.Constants.PREF_LOGIN;
import static com.example.cs309android.util.Constants.PREF_NAME;
import static com.example.cs309android.util.Constants.USERS_LATEST;
import static com.example.cs309android.util.Util.spin;
import static com.example.cs309android.util.Util.unSpin;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.IBinder;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.activities.food.FoodDetailsActivity;
import com.example.cs309android.activities.login.AccountSwitchActivity;
import com.example.cs309android.activities.login.LoginActivity;
import com.example.cs309android.activities.recipe.AddRecipeActivity;
import com.example.cs309android.fragments.account.AccountFragment;
import com.example.cs309android.fragments.account.SettingsFragment;
import com.example.cs309android.fragments.home.HomeFragment;
import com.example.cs309android.fragments.nutrition.NutritionFragment;
import com.example.cs309android.fragments.recipes.RecipesFragment;
import com.example.cs309android.fragments.shopping.ShoppingFragment;
import com.example.cs309android.interfaces.CallbackFragment;
import com.example.cs309android.models.api.models.SimpleFoodItem;
import com.example.cs309android.util.RequestHandler;
import com.example.cs309android.util.Util;
import com.example.cs309android.util.security.NukeSSLCerts;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Main activity
 * Most pages should probably use fragments
 *
 * @author Mitch Hudson
 * @author Travis Massner
 */
public class MainActivity extends AppCompatActivity implements CallbackFragment {
    /**
     * Used to launch various activities.
     */
    ActivityResultLauncher<Intent> foodSearchLauncher;
    /**
     * Fragment containing the current login window.
     */
    private CallbackFragment loginWindowFragment;
    /**
     * Main window fragment
     */
    private CallbackFragment mainFragment;
    /**
     * Tracker for the current fragment
     */
    private static int currentFragment = 2;
    /**
     * GlobalClass for storing universal values
     */
    private GlobalClass global;
    /**
     * Navbar object at the bottom of the app.
     */
    private BottomNavigationView navbar;

    private boolean openMenu = false;
    private FloatingActionButton mainButton, addShopping, addLog, addRecipe;

    /**
     * Shopping list items for the shopping list
     */
    private static ArrayList<SimpleFoodItem> shoppingListItems;

    /**
     * Cancels all Volley requests when the application is closed or otherwise stopped.
     */
    @Override
    protected void onStop() {
        super.onStop();
        new RequestHandler(MainActivity.this).cancelAll();
    }

    /**
     * Resumes when the application is resumed.
     */
    @Override
    protected void onResume() {
        super.onResume();
//        navbar.setSelectedItemId(R.id.home);
//        mainFragment = new HomeFragment();
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.replace(R.id.mainLayout, (Fragment) mainFragment, null);
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
        setContentView(R.layout.activity_main);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        Util.mainButtonEdit = Util.bitmapDrawableFromVector(this, R.drawable.ic_edit);
        Util.mainButtonClose = Util.bitmapDrawableFromVector(this, R.drawable.ic_close);

        Util.dpScalar = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, getResources().getDisplayMetrics());

        global = ((GlobalClass) getApplicationContext());
        global.setPreferences(getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE));

        // TODO: Remove for production
        // Removes SSL certificate checking until we can create a cert with a cert authority
        if (SSL_OFF) {
            NukeSSLCerts.nuke();
        }

        mainButton = findViewById(R.id.mainButton);
        TransitionDrawable drawable = (TransitionDrawable) mainButton.getDrawable();
        drawable.setDrawableByLayerId(R.id.closed, Util.mainButtonEdit);
        drawable.setDrawableByLayerId(R.id.open, Util.mainButtonClose);
        addShopping = findViewById(R.id.addShopping);
        addLog = findViewById(R.id.addLog);
        addRecipe = findViewById(R.id.addRecipe);

        // Hides/un-hides other buttons
        mainButton.setOnClickListener(view -> {
            toggleMenu();
        });

        // Recipe add button
        addRecipe.setOnClickListener(view -> {
            Intent myIntent = new Intent(this, AddRecipeActivity.class);
            startActivity(myIntent);
        });

        // Shopping list add button
        addShopping.setOnClickListener(view -> {
            Intent intent = new Intent(this, SearchActivity.class);
            intent.putExtra(PARCEL_INTENT_CODE, INTENT_SHOPPING_LIST);
            intent.putExtra(PARCEL_FOODITEMS_LIST, shoppingListItems);
            foodSearchLauncher.launch(intent);
        });

        // Log add button
        addLog.setOnClickListener(view -> {
            Intent intent = new Intent(this, SearchActivity.class);
            foodSearchLauncher.launch(intent);
        });

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow((IBinder) getWindow().getCurrentFocus(), 0);

        foodSearchLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    shoppingListItems = Objects.requireNonNull(result.getData()).getParcelableArrayListExtra(PARCEL_FOODITEMS_LIST);
                    mainFragment = new ShoppingFragment();
                    mainFragment.setCallbackFragment(this);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.mainLayout, (Fragment) mainFragment, null)
                            .commit();
                    currentFragment = 0;
                }
        );

        if (!global.getPreferences().getBoolean(PREF_FIRST_TIME, false)) {
            // TODO: First time
            global.getPreferences().edit().putBoolean(PREF_FIRST_TIME, true).apply();
        }

        // Gets stored password hash, if it exists
        Map<String, String> users = Util.objFromJson(global.getPreferences().getString(PREF_LOGIN, "").trim(), Map.class);

        if (users == null) users = new HashMap<>();
        global.setUsers(users);
        global.updateLoginPrefs();

        String token = users.get(users.get(USERS_LATEST));

        // Attempts a login with stored creds. If they are invalid or don't exist, open login page
        spin(this);
        System.out.println(token);
        if (token != null) {
            Util.loginAttempt(global, token, () -> unSpin(this), result -> failedLogin(), error -> failedLogin());
        } else {
            unSpin(this);
            startLoginActivity(false);
        }

        navbar = findViewById(R.id.navbar);

        navbar.setOnItemSelectedListener(item -> {
            int previousFragment = currentFragment;
            if (item.getItemId() == R.id.shopping) {
                mainFragment = new ShoppingFragment();
                mainFragment.setCallbackFragment(this);
                // Always slide left (furthest left)
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                        .replace(R.id.mainLayout, (Fragment) mainFragment, null)
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
                transaction.replace(R.id.mainLayout, (Fragment) mainFragment, null);
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
                transaction.replace(R.id.mainLayout, (Fragment) mainFragment, null);
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
                transaction.replace(R.id.mainLayout, (Fragment) mainFragment, null);
                transaction.commit();
                currentFragment = 3;
            } else if (item.getItemId() == R.id.account) {
                mainFragment = new AccountFragment();
                mainFragment.setCallbackFragment(this);
                // Always slide right
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
                        .replace(R.id.mainLayout, (Fragment) mainFragment, null)
                        .commit();
                currentFragment = 4;
            } else {
                return false;
            }

            // Hides / shows the menu button
            if (previousFragment == 4) {
                menuButtonAnimation(false);
            } else if (currentFragment == 4) {
                menuButtonAnimation(true);
            }
            return true;
        });
        navbar.setSelectedItemId(R.id.home);
        navbar.setOnItemReselectedListener(item -> {
            if (item.getItemId() == R.id.shopping) {
                System.out.println("shopping re");
            } else if (item.getItemId() == R.id.nutrition) {
                System.out.println("nutrition re");
            } else if (item.getItemId() == R.id.home) {
                System.out.println("home re");
            } else if (item.getItemId() == R.id.recipes) {
                System.out.println("recipes re");
            } else if (item.getItemId() == R.id.account) {
                System.out.println("settings re");
            }
        });
    }

    /**
     * Hides and un-hides the menu button
     *
     * @param hide True if the animation should hide the menu button
     */
    public void menuButtonAnimation(boolean hide) {
        if (hide) { // Hides the menu button
            if (openMenu) toggleMenu(); // Closes the menu if it is open
            mainButton.setVisibility(View.GONE);
            mainButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_out_right_fade_out));
        } else {    // Shows the menu button
            mainButton.setVisibility(View.VISIBLE);
            mainButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_right_fade_in));
        }
    }

    /**
     * Toggles display of the fab menu with animations
     */
    public void toggleMenu() {
        Animation animation;
        int visibility;
        TransitionDrawable drawable = (TransitionDrawable) mainButton.getDrawable();
        drawable.setCrossFadeEnabled(true);
        if (openMenu) {
            animation = AnimationUtils.loadAnimation(this, R.anim.slide_out_right_fade_out);
            visibility = View.GONE;
            drawable.reverseTransition(getResources().getInteger(android.R.integer.config_shortAnimTime));
        } else {
            animation = AnimationUtils.loadAnimation(this, R.anim.slide_in_right_fade_in);
            visibility = View.VISIBLE;
            drawable.startTransition(getResources().getInteger(android.R.integer.config_shortAnimTime));
        }
        addShopping.setVisibility(visibility);
        addLog.setVisibility(visibility);
        addRecipe.setVisibility(visibility);
        addShopping.startAnimation(animation);
        addLog.startAnimation(animation);
        addRecipe.startAnimation(animation);

        openMenu = !openMenu;
    }

    /**
     * Runs when a login fails, opening the login activity or the switch activity
     */
    public void failedLogin() {
        unSpin(this);
        if (global.getAccounts().length > 0) {
            Intent intent = new Intent(this, AccountSwitchActivity.class);
            intent.putExtra(PARCEL_LOGGED_OUT, true);
            startActivity(intent);
        } else {
            startLoginActivity(false);
        }
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
     * <p>
     * CALLBACK_START_LOGIN:
     * Starts the login fragment
     * <p>
     * CALLBACK_MOVE_TO_HOME:
     * Moves the current fragment to the home fragment
     * <p>
     * CALLBACK_FOOD_DETAIL:
     * Starts the food details activity with the given fooditem
     * <p>
     * CALLBACK_SEARCH_FOOD:
     * Starts the search activity
     * <p>
     * CALLBACK_MOVE_TO_SETTINGS:
     * Moves the current fragment to the settings fragment
     *
     * @param op     Opcode to decide what to do
     * @param bundle Bundle with args
     */
    @Override
    public void callback(int op, Bundle bundle) {
        switch (op) {
            case (CALLBACK_START_LOGIN): {
                boolean backEnabled = false;
                if (bundle != null) {
                    backEnabled = bundle.getBoolean(PARCEL_BACK_ENABLED);
                }
                startLoginActivity(backEnabled);
                break;
            }
            case (CALLBACK_MOVE_TO_HOME): {
                mainFragment = new HomeFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.mainLayout, (Fragment) mainFragment, null)
                        .commit();
                currentFragment = 2;
                navbar.setSelectedItemId(R.id.home);
                break;
            }
            case (CALLBACK_FOOD_DETAIL): {
                Intent intent = new Intent(this, FoodDetailsActivity.class);
                intent.putExtra(PARCEL_FOODITEM, (SimpleFoodItem) bundle.getParcelable(PARCEL_FOODITEM));
                intent.putExtra(PARCEL_BUTTON_CONTROL, FoodDetailsActivity.CONTROL_NONE);
                startActivity(intent);
                break;
            }
            case (CALLBACK_SEARCH_FOOD): {
                Intent intent = new Intent(this, SearchActivity.class);
                intent.putExtra(PARCEL_INTENT_CODE, bundle.getInt(PARCEL_INTENT_CODE));
                intent.putExtra(PARCEL_FOODITEMS_LIST, bundle.getParcelableArrayList(PARCEL_FOODITEMS_LIST));
                foodSearchLauncher.launch(intent);
                break;
            }
            case (CALLBACK_MOVE_TO_SETTINGS): {
                mainFragment = new SettingsFragment();
                mainFragment.setCallbackFragment(this);
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
                        .addToBackStack(null)
                        .replace(R.id.mainLayout, (Fragment) mainFragment, null)
                        .commit();
                currentFragment = 4;
                break;
            }
        }
    }

    /**
     * Main activity has no callback.
     *
     * @param fragment ignored
     */
    @Override
    public void setCallbackFragment(CallbackFragment fragment) {
    }

    /**
     * Starts the login fragment.
     * First, makes MainActivity transparent and non-interactive
     * Then creates a new fragment and sets up the opening animations.
     */
    public void startLoginActivity(boolean backEnabled) {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(PARCEL_BACK_ENABLED, backEnabled);
        startActivity(intent);
    }

    public static void clearShoppingList() {
        shoppingListItems.clear();
    }

    public static void addShoppingItem(SimpleFoodItem item) {
        shoppingListItems.add(item);
    }

    public static boolean removeShoppingItem(int i) {
        shoppingListItems.remove(i);
        return shoppingListItems.isEmpty();
    }

    public static ArrayList<SimpleFoodItem> getShoppingList() {
        return shoppingListItems;
    }
}
