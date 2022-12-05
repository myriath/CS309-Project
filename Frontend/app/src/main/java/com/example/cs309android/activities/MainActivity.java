package com.example.cs309android.activities;

import static com.example.cs309android.BuildConfig.SSL_OFF;
import static com.example.cs309android.util.Constants.BREAKFAST_LOG;
import static com.example.cs309android.util.Constants.Callbacks.CALLBACK_FOOD_DETAIL;
import static com.example.cs309android.util.Constants.Callbacks.CALLBACK_MOVE_TO_HOME;
import static com.example.cs309android.util.Constants.Callbacks.CALLBACK_MOVE_TO_SETTINGS;
import static com.example.cs309android.util.Constants.Callbacks.CALLBACK_SEARCH_FOOD;
import static com.example.cs309android.util.Constants.Callbacks.CALLBACK_START_LOGIN;
import static com.example.cs309android.util.Constants.DINNER_LOG;
import static com.example.cs309android.util.Constants.Intents.INTENT_SHOPPING_LIST;
import static com.example.cs309android.util.Constants.LUNCH_LOG;
import static com.example.cs309android.util.Constants.PICASSO;
import static com.example.cs309android.util.Constants.PREF_FIRST_TIME;
import static com.example.cs309android.util.Constants.PREF_LOGIN;
import static com.example.cs309android.util.Constants.PREF_NAME;
import static com.example.cs309android.util.Constants.Parcels.PARCEL_BACK_ENABLED;
import static com.example.cs309android.util.Constants.Parcels.PARCEL_BUTTON_CONTROL;
import static com.example.cs309android.util.Constants.Parcels.PARCEL_FOODITEM;
import static com.example.cs309android.util.Constants.Parcels.PARCEL_FOODITEMS_LIST;
import static com.example.cs309android.util.Constants.Parcels.PARCEL_INTENT_CODE;
import static com.example.cs309android.util.Constants.Parcels.PARCEL_LOGGED_OUT;
import static com.example.cs309android.util.Constants.USERS_LATEST;
import static com.example.cs309android.util.Util.spin;
import static com.example.cs309android.util.Util.unSpin;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;

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
import com.example.cs309android.models.api.models.FoodLogItem;
import com.example.cs309android.models.api.models.ShoppingList;
import com.example.cs309android.models.api.models.SimpleFoodItem;
import com.example.cs309android.services.NotificationService;
import com.example.cs309android.util.Constants;
import com.example.cs309android.util.PicassoSingleton;
import com.example.cs309android.util.RequestHandler;
import com.example.cs309android.util.Util;
import com.example.cs309android.util.security.NukeSSLCerts;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Main activity
 * Most pages should probably use fragments
 *
 * @author Mitch Hudson
 * @author Travis Massner
 */
public class MainActivity extends AppCompatActivity implements CallbackFragment {
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

    /**
     * Tracks whether the menu is open
     */
    private boolean openMenu = false;
    /**
     * Tracks whether the menu is hidden
     */
    private boolean menuHidden = false;
    /**
     * FAB Menu buttons
     */
    private FloatingActionButton mainButton, addShopping, addLog, addRecipe;

    /**
     * Shopping list items for the shopping list
     */
    private static ArrayList<ShoppingList> shoppingListItems;
    /**
     * Used to store the breakfast items
     */
    private static ArrayList<FoodLogItem> breakfast;
    /**
     * Used to store the lunch items
     */
    private static ArrayList<FoodLogItem> lunch;
    /**
     * Used to store the dinner items
     */
    private static ArrayList<FoodLogItem> dinner;

    /**
     * Public constructor
     * Initializes arraylists if they are null
     */
    public MainActivity() {
        if (shoppingListItems == null) {
            shoppingListItems = new ArrayList<>();
        }
        if (breakfast == null) {
            breakfast = new ArrayList<>();
        }
        if (lunch == null) {
            lunch = new ArrayList<>();
        }
        if (dinner == null) {
            dinner = new ArrayList<>();
        }
    }

    /**
     * Cancels all Volley requests when the application is closed or otherwise stopped.
     */
    @Override
    protected void onStop() {
        super.onStop();
        new RequestHandler(MainActivity.this).cancelAll();
    }

    /**
     * If on the homepage, close the app
     * Otherwise, move back to the homepage
     */
    @Override
    public void onBackPressed() {
        if (currentFragment == 2) {
            finish();
        } else {
            navbar.setSelectedItemId(R.id.home);
        }
    }

    /**
     * Resumes when the application is resumed.
     */
    @Override
    protected void onResume() {
        super.onResume();
        if (openMenu) toggleMenu();
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

        // Creates Picasso singleton
        PICASSO = new PicassoSingleton();

        // Creates main menu button icons
        Util.mainButtonEdit = Util.bitmapDrawableFromVector(this, R.drawable.ic_edit);
        Util.mainButtonClose = Util.bitmapDrawableFromVector(this, R.drawable.ic_close);

        // Sets dp scalars for the app
        Util.dpScalar = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, getResources().getDisplayMetrics());
        Constants.dp16 = Util.scalePixels(16);
        Constants.dp8 = Util.scalePixels(8);

        // Sets global and gets the preferences
        global = ((GlobalClass) getApplicationContext());
        global.setPreferences(getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE));

        // TODO: Remove for production
        // Removes SSL certificate checking until we can create a cert with a cert authority
        if (SSL_OFF) {
            NukeSSLCerts.nuke();
        }

        // Sets up main buttons and animations
        mainButton = findViewById(R.id.mainButton);
        TransitionDrawable drawable = (TransitionDrawable) mainButton.getDrawable();
        drawable.setDrawableByLayerId(R.id.closed, Util.mainButtonEdit);
        drawable.setDrawableByLayerId(R.id.open, Util.mainButtonClose);
        addShopping = findViewById(R.id.addShopping);
        addLog = findViewById(R.id.addLog);
        addRecipe = findViewById(R.id.addRecipe);

        // Hides/un-hides other buttons
        mainButton.setOnClickListener(view -> toggleMenu());

        // Recipe add button
        addRecipe.setOnClickListener(view -> {
            Intent myIntent = new Intent(this, AddRecipeActivity.class);
            startActivity(myIntent);
        });

        // Shopping list add button
        addShopping.setOnClickListener(view -> {
            Intent intent = new Intent(this, SearchActivity.class);
            intent.putExtra(PARCEL_INTENT_CODE, INTENT_SHOPPING_LIST);
            startActivity(intent);
        });

        // Log add button
        addLog.setOnClickListener(view -> {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        });

        // Hides the keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow((IBinder) getWindow().getCurrentFocus(), 0);

        // Runs the tutorial on first run
        if (!global.getPreferences().getBoolean(PREF_FIRST_TIME, false)) {
            // TODO: First time
            global.getPreferences().edit().putBoolean(PREF_FIRST_TIME, true).apply();
        }

        // Gets stored password hash, if it exists
        Map<String, String> users = Util.objFromJson(global.getPreferences().getString(PREF_LOGIN, "").trim(), Map.class);

        if (users == null || users.size() == 0) users = new HashMap<>();
        global.setUsers(users);
        global.updateLoginPrefs();

        String token = users.get(users.get(USERS_LATEST));

        // Attempts a login with stored creds. If they are invalid or don't exist, open login page
        spin(this);
        Log.d("TOKEN", token);
        if (token != null) {
            Util.loginAttempt(global, token, () -> {
                unSpin(this);
                // Creates notification channels
                Constants.Notifications.createNotificationChannels(this);
                // Starts the notification service
                startService(new Intent(this, NotificationService.class));
            }, result -> failedLogin(), error -> failedLogin());
        } else {
            unSpin(this);
            startLoginActivity(false);
        }

        navbar = findViewById(R.id.navbar);

        navbar.setOnItemSelectedListener(item -> {
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
            if (currentFragment == 4 && !menuHidden) toggleMenuHidden();
            else if (currentFragment != 4 && menuHidden) toggleMenuHidden();
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
     */
    public void toggleMenuHidden() {
        if (menuHidden) { // Shows the menu button
            mainButton.setVisibility(View.VISIBLE);
            mainButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_right_fade_in));
        } else {    // Hides the menu button
            if (openMenu) toggleMenu(); // Closes the menu if it is open
            mainButton.setVisibility(View.GONE);
            mainButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_out_right_fade_out));
        }

        menuHidden = !menuHidden;
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
                callback(CALLBACK_MOVE_TO_HOME, null);
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
                startActivity(intent);
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
        Intent intent;
        if (global.getUsers().size() > 1) {
            intent = new Intent(this, AccountSwitchActivity.class);
            intent.putExtra(PARCEL_LOGGED_OUT, !backEnabled);
        } else {
            intent = new Intent(this, LoginActivity.class);
            intent.putExtra(PARCEL_BACK_ENABLED, backEnabled);
        }
        startActivity(intent);
    }

    /**
     * Clears the shopping list
     */
    public static void clearShoppingList() {
        shoppingListItems.clear();
    }

    /**
     * Removes the item at the given index from the shopping list
     * @param i Index of the item to remove
     * @return True if the shopping list is now empty
     */
    public static boolean removeShoppingItem(int i) {
        shoppingListItems.remove(i);
        return shoppingListItems.isEmpty();
    }

    /**
     * Getter for the shopping list
     *
     * @return ArrayList of food items
     */
    public static ArrayList<ShoppingList> getShoppingList() {
        return shoppingListItems;
    }

    /**
     * Setter for the shopping list
     *
     * @param items Array of food items to add to the shopping list
     */
    public static void setShoppingList(ShoppingList[] items) {
        shoppingListItems.addAll(Arrays.asList(items));
    }

    /**
     * Clears the food log
     */
    public static void clearFoodLog() {
        breakfast.clear();
        lunch.clear();
        dinner.clear();
    }

    /**
     * Removes the given index from the given log
     * @param i     Index of the item to remove
     * @param logId Log id constant for the target of this remove
     * @return      True if the target log is empty
     */
    public static boolean removeLogItem(int i, int logId) {
        boolean ret = true;
        switch (logId) {
            case BREAKFAST_LOG: {
                breakfast.remove(i);
                ret = breakfast.isEmpty();
                break;
            }
            case LUNCH_LOG: {
                lunch.remove(i);
                ret = lunch.isEmpty();
                break;
            }
            case DINNER_LOG: {
                dinner.remove(i);
                ret = dinner.isEmpty();
                break;
            }
        }
        return ret;
    }

    /**
     * Adds the given item to the specified food log
     * @param item  Item to add
     * @param logId Log id constant for the log to target
     */
    public static void addLogItem(FoodLogItem item, int logId) {
        switch (logId) {
            case BREAKFAST_LOG: {
                breakfast.add(item);
                break;
            }
            case LUNCH_LOG: {
                lunch.add(item);
                break;
            }
            case DINNER_LOG: {
                dinner.add(item);
                break;
            }
        }
    }

    /**
     * Setter for the individual food logs
     * @param items Items to add to the food log
     * @param logId Log ID constant for the log to add to
     */
    public static void setLog(FoodLogItem[] items, int logId) {
        switch (logId) {
            case BREAKFAST_LOG: {
                breakfast.addAll(Arrays.asList(items));
                break;
            }
            case LUNCH_LOG: {
                lunch.addAll(Arrays.asList(items));
                break;
            }
            case DINNER_LOG: {
                dinner.addAll(Arrays.asList(items));
                break;
            }
        }
    }

    /**
     * Getter for the food logs' items
     * @param i     Index of the item to retrieve
     * @param logId Log id constant for the log to retrieve from
     * @return      Item from the log, null if the logId is invalid
     */
    public static FoodLogItem getLogItem(int i, int logId) {
        switch (logId) {
            case BREAKFAST_LOG: {
                return breakfast.get(i);
            }
            case LUNCH_LOG: {
                return lunch.get(i);
            }
            case DINNER_LOG: {
                return dinner.get(i);
            }
        }
        return null;
    }

    /**
     * Getter for the food logs
     * @param logId ID of the log to retrieve
     * @return      Food log list for an adapter
     */
    public static ArrayList<FoodLogItem> getLog(int logId) {
        switch (logId) {
            case BREAKFAST_LOG: {
                return breakfast;
            }
            case LUNCH_LOG: {
                return lunch;
            }
            case DINNER_LOG: {
                return dinner;
            }
        }
        return null;
    }
}
