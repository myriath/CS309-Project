package com.example.cs309android.util;

import static com.example.cs309android.util.Constants.PARCEL_FOLLOWING;
import static com.example.cs309android.util.Constants.PARCEL_OWNER;
import static com.example.cs309android.util.Constants.PARCEL_USERNAME;
import static com.example.cs309android.util.Constants.RESULT_LOGGED_IN;
import static com.example.cs309android.util.Constants.RESULT_OK;
import static com.example.cs309android.util.Constants.RESULT_REGEN_TOKEN;
import static com.example.cs309android.util.Constants.RESULT_USER_CREATED;
import static com.example.cs309android.util.Constants.TOKEN_MAX_DEPTH;
import static com.example.cs309android.util.Constants.USERS_LATEST;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.core.content.ContextCompat;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.activities.MainActivity;
import com.example.cs309android.activities.account.AccountActivity;
import com.example.cs309android.interfaces.ErrorListener;
import com.example.cs309android.interfaces.SuccessListener;
import com.example.cs309android.models.VolleyErrorHandler;
import com.example.cs309android.models.api.request.profile.GetBannerRequest;
import com.example.cs309android.models.api.request.profile.GetProfilePictureRequest;
import com.example.cs309android.models.api.request.shopping.GetListRequest;
import com.example.cs309android.models.api.request.social.IsFollowingRequest;
import com.example.cs309android.models.api.request.users.LoginHashRequest;
import com.example.cs309android.models.api.request.users.LoginTokenRequest;
import com.example.cs309android.models.api.request.users.RegenTokenRequest;
import com.example.cs309android.models.api.request.users.RegisterRequest;
import com.example.cs309android.models.api.request.users.SaltRequest;
import com.example.cs309android.models.api.response.GenericResponse;
import com.example.cs309android.models.api.response.shopping.GetListResponse;
import com.example.cs309android.models.api.response.social.FollowResponse;
import com.example.cs309android.models.api.response.users.LoginResponse;
import com.example.cs309android.models.api.response.users.SaltResponse;
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
     * GSON Builder used for the entire application
     */
    public static final GsonBuilder GSON_BUILDER = new GsonBuilder()
            .serializeNulls()
            .excludeFieldsWithoutExposeAnnotation();
    /**
     * GSON used for the entire application
     */
    public static final Gson GSON = GSON_BUILDER.create();

    /**
     * Bitmap drawable for the main button's closed state
     */
    public static BitmapDrawable mainButtonEdit;
    /**
     * Bitmap drawable for the main button's open state
     */
    public static BitmapDrawable mainButtonClose;

    /**
     * Scalar defined by MainActivity
     * Scales pixels to DIPs
     */
    public static float dpScalar;

    /**
     * Converts pixels to dip
     *
     * @param pixels pixel measurement
     * @return dip
     */
    public static float scalePixels(float pixels) {
        return pixels * dpScalar;
    }

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
     * Generates a BitmapDrawable from a vector drawable.
     * This allows the drawables to be used for cross fade transitions
     *
     * @param context Context to get resources from
     * @param id      R.drawable ID of the vector drawable
     * @return BitmapDrawable for cross fade animations
     */
    public static BitmapDrawable bitmapDrawableFromVector(Context context, int id) {
        Drawable drawable = ContextCompat.getDrawable(context, id);
        if (drawable == null) return null;
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return new BitmapDrawable(context.getResources(), bitmap);
    }

    /**
     * Sets variables necessary for app functions after a login
     * Also sets the preferences for a new token
     *
     * @param global   GlobalClass for context and storing login details
     * @param username Username for the account to log into
     * @param token    Token used for authentication
     */
    public static void login(GlobalClass global, String username, String token) {
        global.setUsername(username);
        global.setToken(token);
        global.updateLoginPrefs();

        new GetProfilePictureRequest(username).request(global::setPfp, global);
        new GetBannerRequest(username).request(global::setBanner, global);

        MainActivity.clearShoppingList();
        new GetListRequest(token).request(response -> {
            GetListResponse shoppingResponse = Util.objFromJson(response, GetListResponse.class);
            MainActivity.setShoppingList(shoppingResponse.getShoppingList());
        }, global);
    }

    /**
     * Gets the salt and attempts a login
     *
     * @param global        GlobalClass for context and storing login details
     * @param username      Username to attempt a login for
     * @param pwd           Password to hash
     * @param errorListener Runs when the request fails or the response code is incorrect
     */
    public static void loginAttempt(GlobalClass global, String username, String pwd, SuccessListener listener, ErrorListener errorListener, Response.ErrorListener errorListener2) {
        VolleyErrorHandler errorHandler = new VolleyErrorHandler(errorListener, errorListener2);
        new SaltRequest(username).request(response -> {
            SaltResponse saltResponse = Util.objFromJson(response, SaltResponse.class);

            int result = saltResponse.getResult();
            if (result == RESULT_OK) {
                byte[] salt = Hasher.B64_URL_DECODER.decode(saltResponse.getSalt());
                hashRequest(global, username, Hasher.getEncoded(Hasher.hash(pwd.toCharArray(), salt)), listener, errorListener, errorListener2, 0);
            } else {
                errorHandler.onErrorResponse(new VolleyError(String.valueOf(result)));
            }
        }, errorHandler, global);
    }

    /**
     * Makes a login hash request
     *
     * @param global        GlobalClass for context and storing login data
     * @param username      Username to login with
     * @param hash          Hash to login with
     * @param errorListener ErrorListener handles errors
     * @param depth         Number of retries
     */
    public static void hashRequest(GlobalClass global, String username, String hash, SuccessListener listener, ErrorListener errorListener, Response.ErrorListener errorListener2, int depth) {
        if (depth > TOKEN_MAX_DEPTH) {
            Toaster.toastShort("Unable to generate a token", global);
            return;
        }

        VolleyErrorHandler errorHandler = new VolleyErrorHandler(errorListener, errorListener2);
        String token = Hasher.genToken();
        new LoginHashRequest(username, hash, token).request(response -> {
            LoginResponse loginResponse = objFromJson(response, LoginResponse.class);

            int result = loginResponse.getResult();
            switch (loginResponse.getResult()) {
                case RESULT_REGEN_TOKEN: {
                    hashRequest(global, username, hash, listener, errorListener, errorListener2, depth + 1);
                    break;
                }
                case RESULT_LOGGED_IN: {
                    login(global, username, token);
                    listener.run();
                    break;
                }
                default: {
                    errorHandler.onErrorResponse(new VolleyError(String.valueOf(result)));
                }
            }
        }, errorHandler, global);
    }

    /**
     * Does a token based login request
     *
     * @param global        GlobalClass for context and storing login details
     * @param token         Token to log in with
     * @param listener      Listener to run on successful login
     * @param errorListener ErrorListener to handle request errors and error codes
     */
    public static void loginAttempt(GlobalClass global, String token, SuccessListener listener, ErrorListener errorListener, Response.ErrorListener errorListener2) {
        VolleyErrorHandler errorHandler = new VolleyErrorHandler(errorListener, errorListener2);
        new LoginTokenRequest(token).request(response -> {
            LoginResponse loginResponse = objFromJson(response, LoginResponse.class);

            int result = loginResponse.getResult();
            switch (result) {
                case RESULT_REGEN_TOKEN: {
                    regenToken(global, loginResponse.getUsername(), token, listener, errorListener, errorListener2, 0);
                    break;
                }
                case RESULT_LOGGED_IN: {
                    login(global, loginResponse.getUsername(), token);
                    listener.run();
                    break;
                }
                default: {
                    errorHandler.onErrorResponse(new VolleyError(String.valueOf(result)));
                }
            }
        }, errorHandler, global);
    }

    /**
     * Generates a new token for the account
     *
     * @param global        GlobalClass for context and storing login details
     * @param username      Username of the account to log into
     * @param oldToken      Old token for authentication
     * @param listener      Listener to run on successful login
     * @param errorListener ErrorListener to handle request errors and error codes
     * @param depth         Number of retries
     */
    public static void regenToken(GlobalClass global, String username, String oldToken, SuccessListener listener, ErrorListener errorListener, Response.ErrorListener errorListener2, int depth) {
        if (depth > TOKEN_MAX_DEPTH) {
            Toaster.toastShort("Unable to generate a token", global);
            return;
        }

        VolleyErrorHandler errorHandler = new VolleyErrorHandler(errorListener, errorListener2);
        String token = Hasher.genToken();
        new RegenTokenRequest(token, oldToken).request(response -> {
            GenericResponse genericResponse = objFromJson(response, GenericResponse.class);

            int result = genericResponse.getResult();
            switch (result) {
                case RESULT_REGEN_TOKEN: {
                    regenToken(global, username, oldToken, listener, errorListener, errorListener2, depth + 1);
                    break;
                }
                case RESULT_LOGGED_IN: {
                    login(global, username, token);
                    listener.run();
                    break;
                }
                default: {
                    errorHandler.onErrorResponse(new VolleyError(String.valueOf(result)));
                }
            }
        }, errorHandler, global);
    }

    /**
     * Registers a new account for the application
     *
     * @param global        GlobalClass for context and storing login details
     * @param email         Email for the new account
     * @param username      Username for the new account
     * @param hash          Salted hash of the password for the new account
     * @param salt          Salt used for the hash
     * @param listener      Listener to run when the login succeeds
     * @param errorListener ErrorListener to handle request errors and error codes
     * @param depth         Number of retries
     */
    public static void register(GlobalClass global, String email, String username, String hash, String salt, SuccessListener listener, ErrorListener errorListener, Response.ErrorListener errorListener2, int depth) {
        if (depth > TOKEN_MAX_DEPTH) {
            Toaster.toastShort("Unable to generate a token", global);
            return;
        }

        VolleyErrorHandler errorHandler = new VolleyErrorHandler(errorListener, errorListener2);
        String token = Hasher.genToken();
        new RegisterRequest(email, username, hash, salt, token).request(response -> {
            GenericResponse genericResponse = objFromJson(response, GenericResponse.class);

            int result = genericResponse.getResult();
            switch (result) {
                case RESULT_USER_CREATED: {
                    Util.login(global, username, token);
                    listener.run();
                    break;
                }
                case RESULT_REGEN_TOKEN: {
                    register(global, email, username, hash, salt, listener, errorListener, errorListener2, depth + 1);
                    break;
                }
                default: {
                    errorHandler.onErrorResponse(new VolleyError(String.valueOf(result)));
                }
            }
        }, errorHandler, global);
    }

    /**
     * Opens the account page based on the given parameters
     *
     * @param global   Used to get the current user's account details
     * @param username Username of the account to open
     * @param context  Context to start activity / volley with
     */
    public static void openAccountPage(GlobalClass global, String username, Context context) {
        new IsFollowingRequest(global.getUsername(), username).request(response -> {
            FollowResponse followResponse = Util.objFromJson(response, FollowResponse.class);

            Intent intent = new Intent(context, AccountActivity.class);
            intent.putExtra(PARCEL_FOLLOWING, followResponse.getUsers() != null && followResponse.getUsers().length != 0);
            intent.putExtra(PARCEL_USERNAME, username);
            intent.putExtra(PARCEL_OWNER, username.equals(global.getUsername()));
            context.startActivity(intent);
        }, error -> {
            Intent intent = new Intent(context, AccountActivity.class);
            intent.putExtra(PARCEL_USERNAME, username);
            intent.putExtra(PARCEL_OWNER, username.equals(global.getUsername()));
            context.startActivity(intent);
        }, context);
    }

    /**
     * Switches the active user to the new username
     *
     * @param global   Global class for variables
     * @param username New username to switch to
     */
    public static void switchUser(GlobalClass global, String username) {
        global.getUsers().put(USERS_LATEST, username);
        global.updateLoginPrefs();
    }

    /**
     * Logs out of the given account
     *
     * @param global   Global class for variables
     * @param username Username to log out of
     */
    public static void logout(GlobalClass global, String username) {
        global.setUsername(null);
        global.removeToken(username);
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
