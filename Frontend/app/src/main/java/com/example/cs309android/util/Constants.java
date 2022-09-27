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
    private Constants() {
    }

    /**
     * Indicates the request resulted in an error (generic)
     */
    public static final int RESULT_ERROR = -1;
    /**
     * Indicates a mismatch between the given username / password
     */
    public static final int RESULT_ERROR_USER_HASH_MISMATCH = -2;
    /**
     * Indicates the username given has been taken
     */
    public static final int RESULT_ERROR_USERNAME_TAKEN = -3;
    /**
     * Indicates the email given has been taken
     */
    public static final int RESULT_ERROR_EMAIL_TAKEN = -4;

    /**
     * Indicates the request succeeded (generic)
     */
    public static final int RESULT_OK = 0;
    /**
     * Indicates the user / hash matches
     */
    public static final int RESULT_LOGGED_IN = 1;
    /**
     * Indicates the user has been successfully created
     */
    public static final int RESULT_USER_CREATED = 2;

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
