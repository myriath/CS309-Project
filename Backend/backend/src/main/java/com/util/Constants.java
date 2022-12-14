package com.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Util constants class
 *
 * @author Mitch Hudson
 */
public class Constants {
    /**
     * LOGGER for logging stuff
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(Constants.class);

    /**
     * Private constructor (Util class)
     */
    private Constants() {
    }

    // RESULT CODES
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
     * Login is valid, but needs to regen token
     */
    public static final int RESULT_REGEN_TOKEN = 3;


    // USERS
    /**
     * Base auth url for all authentication requests
     */
    public static final String AUTH_URL = "/users";
    /**
     * URL to register a new account to. (Params: email, username, salt, hash)
     */
    public static final String REGISTER_URL = AUTH_URL + "/register";
    /**
     * URL to get an accounts salt. (Params: username)
     */
    public static final String SALT_URL = AUTH_URL + "/getSalt";
    /**
     * URL to check for login. (Params: username, hash)
     */
    public static final String LOGIN_URL = AUTH_URL + "/validateLogin";
    /**
     * URL to regen an expired token
     */
    public static final String REGEN_TOKEN_URL = AUTH_URL + "/regenToken";


    // SHOPPING
    /**
     * Base shopping url for all shopping list based requests
     */
    public static final String SHOPPING_URL = "/shopping";
    /**
     * Get url for getting shopping list
     */
    public static final String GET_SHOPPING_URL = SHOPPING_URL + "/get";
    /**
     * Add url for adding an item to a shopping list
     */
    public static final String ADD_SHOPPING_URL = SHOPPING_URL + "/add";
    /**
     * Remove url for removing an item from a shopping list
     */
    public static final String REMOVE_SHOPPING_URL = SHOPPING_URL + "/remove";
    /**
     * Strikeout url for telling the server whether the item should be struck out or not.
     */
    public static final String STRIKE_SHOPPING_URL = SHOPPING_URL + "/strikeout";


    // IMAGE PATHS
    public static final String MEDIA_BASE = "/springMedia/";
    /**
     * Profile picture storage location
     */
    public static final String PFP_SOURCE = MEDIA_BASE + "pfps/";
    /**
     * Default pfp image path
     */
    public static final String DEFAULT_PFP = MEDIA_BASE + "defaultpfp.webp";
    /**
     * Banner image storage location
     */
    public static final String BANNER_SOURCE = MEDIA_BASE + "banner/";
    /**
     * Default banner image path
     */
    public static final String DEFAULT_BANNER = MEDIA_BASE + "defaultbanner.webp";
    /**
     * Recipe image storage location
     */
    public static final String RECIPE_SOURCE = MEDIA_BASE + "recipes/";
    /**
     * Regular image storage location
     */
    public static final String MEDIA_SOURCE = MEDIA_BASE + "images/";

    /**
     * User types interface
     */
    public interface UserType {
        /**
         * Regular user
         */
        int USER_REG = 0;
        /**
         * Moderator type
         */
        int USER_MOD = 1;
        /**
         * Administrator type
         */
        int USER_ADM = 2;
        /**
         * Developer type
         */
        int USER_DEV = 3;
    }

    // TAGS

    /**
     * Volley tags used to control
     */
    public enum TAGS {
        TAG_GET,
        TAG_POST,
    }
}
