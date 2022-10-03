package com.example.cs309android.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.cs309android.R;
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
    public static final Gson GSON = new GsonBuilder().serializeNulls().create();

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
     * Hides the keyboard. This is usually ran when the user clicks a button.
     *
     * @param view View of the button/object being interacted with.
     */
    public static void hideKeyboard(View view, Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
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
        return new GsonBuilder()
                .serializeNulls()
                .registerTypeAdapter(type, typeAdapter)
                .create()
                .fromJson(json, type);
    }
}
