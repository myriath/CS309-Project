package com.example.cs309android.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.activities.MainActivity;
import com.example.cs309android.models.gson.response.users.LoginResponse;
import com.example.cs309android.util.security.Hasher;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * Utility class containing many commonly used methods
 *
 * @author Mitch Hudson
 */
public class Util {
    /**
     * Gson object used by the entire application.
     */
    public static final GsonBuilder GSON_BUILDER = new GsonBuilder()
            .serializeNulls()
            .excludeFieldsWithoutExposeAnnotation();
    public static final Gson GSON = GSON_BUILDER.create();

    /**
     * Makes the spinner visible and locks interaction to the register page.
     * This is ran when the user starts a request to the server.
     *
     * @param view View to find the spinner from.
     */
    public static void spin(View view) {
        view.findViewById(R.id.loginSpinner).setVisibility(View.VISIBLE);
        view.findViewById(R.id.spinnerBlocker).setAlpha(0.5f);
        view.findViewById(R.id.spinnerBlocker).setClickable(true);
    }

    /**
     * Makes the spinner visible and locks interaction to the register page.
     * This is ran when the user starts a request to the server.
     *
     * @param view View to find the spinner from.
     */
    public static void spin(Activity view) {
        view.findViewById(R.id.loginSpinner).setVisibility(View.VISIBLE);
        view.findViewById(R.id.spinnerBlocker).setAlpha(0.5f);
        view.findViewById(R.id.spinnerBlocker).setClickable(true);
    }

    /**
     * Makes the spinner invisible and unlocks interaction with the register page.
     * This is ran when a response is received from the server.
     *
     * @param view View to find the spinner from.
     */
    public static void unSpin(View view) {
        view.findViewById(R.id.loginSpinner).setVisibility(View.GONE);
        view.findViewById(R.id.spinnerBlocker).setAlpha(0);
        view.findViewById(R.id.spinnerBlocker).setClickable(false);
    }

    /**
     * Makes the spinner invisible and unlocks interaction with the register page.
     * This is ran when a response is received from the server.
     *
     * @param view View to find the spinner from.
     */
    public static void unSpin(Activity view) {
        view.findViewById(R.id.loginSpinner).setVisibility(View.GONE);
        view.findViewById(R.id.spinnerBlocker).setAlpha(0);
        view.findViewById(R.id.spinnerBlocker).setClickable(false);
    }

    /**
     * Sets the title of the activity
     *
     * @param title   New title
     * @param toolbar Toolbar to change the title of
     */
    public static void setTitle(String title, MaterialToolbar toolbar) {
        if (title.length() > 20) {
            toolbar.setTitle(title.substring(0, 20) + "...");
        } else {
            toolbar.setTitle(title);
        }
    }

    /**
     * Sets the subtitle to whatever the brand of the item is
     *
     * @param subtitle Subtitle for the toolbar
     * @param toolbar  Toolbar to set subtitle of
     */
    public static void setSubtitle(String subtitle, MaterialToolbar toolbar) {
        if (subtitle != null && !subtitle.equals("")) {
            toolbar.setSubtitle(subtitle);
        }
    }

    /**
     * Hides the keyboard. This is usually ran when the user clicks a button.
     *
     * @param view View of the button/object being interacted with.
     */
    public static void hideKeyboard(View view, Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }

    /**
     * Sets variables necessary for app functions after a login
     * Also sets the preferences for a new token
     *
     * @param global GlobalClass for storing values
     * @param token  Token for authentication
     * @param login  LoginResponse for other values from the server
     */
    public static void login(GlobalClass global, String token, LoginResponse login) {
        String username = login.getUsername();
        global.setUsername(username);
        global.setToken(token);
        global.updateLoginPrefs();

        // TODO: uncomment when backend is in place
//        byte[] bannerBytes = Hasher.B64_URL_DECODER.decode(login.getBanner());
//        global.setBanner(BitmapFactory.decodeByteArray(bannerBytes, 0, bannerBytes.length));

//        byte[] pfpBytes = Hasher.B64_URL_DECODER.decode(login.getPfp());
//        global.setPfp(BitmapFactory.decodeByteArray(pfpBytes, 0, pfpBytes.length));
    }

    /**
     * Logs out of the given account
     *
     * @param global Global class for variables
     * @param username Username to log out of
     */
    public static void logout(GlobalClass global, String username) {
        global.setUsername(null);
        global.removeToken(username);
        global.updateLoginPrefs();
    }

    /**
     * Switches the active user to the new username
     *
     * @param global Global class for variables
     * @param username New username to switch to
     */
    public static void switchUser(GlobalClass global, String username) {
        global.getUsers().put(MainActivity.USERS_LATEST, username);
        global.updateLoginPrefs();
    }

    /**
     * Creates an object from JSON using GSON's fromJson() method
     *
     * @param json JSONObject (usually from a volley response)
     * @param type Type of the returned object
     * @param <T>  Type of json object
     * @return Object from json
     */
    public static <T> T objFromJson(JSONObject json, Type type) {
        return objFromJson(json.toString(), type);
    }

    /**
     * Creates an object from JSON using GSON's fromJson() method
     *
     * @param json JSON string
     * @param type Type of the returned object
     * @param <T>  Type of json object
     * @return Object from json
     */
    public static <T> T objFromJson(String json, Type type) {
        return GSON.fromJson(json, type);
    }

    /**
     * Creates an object from JSON using GSON and a custom type adapter
     *
     * @param json        JSON string
     * @param type        Type of the returned object
     * @param typeAdapter Adapter object to be used for generation
     * @param <T>         Type of the json object
     * @return Object from json
     */
    public static <T> T objFromJsonAdapted(String json, Type type, Object typeAdapter) {
        return GSON_BUILDER
                .registerTypeAdapter(type, typeAdapter)
                .create()
                .fromJson(json, type);
    }
}
