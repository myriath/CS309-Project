package com.example.cs309android.util;

import static com.example.cs309android.BuildConfig.BASE_API_URL;

/**
 * Util constants class
 *
 * @author Mitch Hudson
 */
public class Constants {
    /**
     * Private constructor (Util class)
     */
    private Constants() {}

    public static final int RESULT_ERROR = -1;
    public static final int RESULT_ERROR_USER_HASH_MISMATCH = -2;
    public static final int RESULT_ERROR_USERNAME_TAKEN = -3;
    public static final int RESULT_ERROR_EMAIL_TAKEN = -4;

    public static final int RESULT_OK = 0;
    public static final int RESULT_LOGGED_IN = 1;
    public static final int RESULT_USER_CREATED = 2;

    /**
     * API key for Nutritionix API
     */
    public static final String API_KEY = "7914e1ef4c7afe4996c50d5b841d234d";
    public static final String APP_ID = "35b716f8";
    public static final String NIX_URL = "https://trackapi.nutritionix.com/";
    public static final String NIX_SEARCH_QUERY = NIX_URL + "v2/search/instant?query=";
    public static final String NIX_SEARCH_ITEM = NIX_URL + "v2/search/item?";

    /**
     * Base url for the server to connect to.
     * TODO: Replace with actual server url
     */
    @Deprecated
    public static final String SERVER_URL = "https://2411d393-4bd1-4d6a-b765-936105c8a49e.mock.pstmn.io";
    /**
     * Base auth url for all authentication requests
     */
    public static final String AUTH_URL = BASE_API_URL + "auth/";
    /**
     * URL to register a new account to. (Params: email, username, salt, hash)
     */
    public static final String REGISTER_URL = AUTH_URL + "register";
    /**
     * URL to get an accounts salt. (Params: username)
     */
    public static final String SALT_URL = AUTH_URL + "getSalt";
    /**
     * URL to check for login. (Params: username, hash)
     */
    public static final String LOGIN_URL = AUTH_URL + "login";

    /**
     * Volley tags used to control
     */
    public enum TAGS {
        TAG_GET,
        TAG_POST,
    }
}
