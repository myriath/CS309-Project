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
     * DEBUG variable for testing Logs
     * TODO: False for prod
     */
    public static final boolean DEBUG = true;

    /**
     * Name of the app for all mentions in the app
     */
    public static final String APP_NAME = "Föd";

    /**
     * Preference name for this app's shared preferences.
     */
    public static final String PREF_NAME = "COMS309";
    public static final String PREF_FIRST_TIME = "FirstTime";
    public static final String PREF_LOGIN = "users";
    public static final String USERS_LATEST = "latest";

    /**
     * Max retries for token generation
     */
    public static final int TOKEN_MAX_DEPTH = 5;

    /**
     * Response codes for callback method. Used by Fragments for this class
     */
    public static final int CALLBACK_SWITCH_TO_REGISTER = 0;
    public static final int CALLBACK_CLOSE_LOGIN = 1;
    public static final int CALLBACK_START_LOGIN = 2;
    public static final int CALLBACK_MOVE_TO_HOME = 3;
    public static final int CALLBACK_FOOD_DETAIL = 4;
    public static final int CALLBACK_SEARCH_FOOD = 5;
    public static final int CALLBACK_MOVE_TO_SETTINGS = 6;
    public static final int CALLBACK_CLOSE_PROFILE = 7;
    public static final int CALLBACK_FOLLOW = 8;
    public static final int CALLBACK_IMAGE_URI = 9;
    public static final int CALLBACK_CLOSE_DETAIL = 10;

    /**
     * Various intents tell the app what to do when certain things are done.
     */
    public static final int INTENT_NONE = -1;
    public static final int INTENT_SHOPPING_LIST = 0;


    public static final String PARCEL_IMAGE_URI = "image_uri";
    /**
     * This is used wherever a food item needs to be parceled.
     */
    public static final String PARCEL_FOODITEM = "fooditem";
    /**
     * This is used whenever a list of food items needs to be parceled.
     */
    public static final String PARCEL_FOODITEMS_LIST = "fooditems";
    /**
     * This is used to parcel the intent of opening an activity.
     */
    public static final String PARCEL_INTENT_CODE = "intentCode";
    /**
     * Used to parcel the control variable
     */
    public static final String PARCEL_BUTTON_CONTROL = "button-control";

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
    /**
     * Indicates the user has been successfully created
     */
    public static final int RESULT_RECIPE_CREATED = 22;
    /**
     * Indicates the user has been successfully created
     */
    public static final int RESULT_ERROR_RID_TAKEN = -23;


    // USERS
    /**
     * Base auth url for all authentication requests
     */
    public static final String AUTH_URL = BASE_API_URL + "users/";
    /**
     * URL to register a new account to. (Params: email, username, salt, hash)
     */
    public static final String REGISTER_URL = AUTH_URL + "register";
    /**
     * URL to get an accounts salt. (Params: username)
     */
    public static final String SALT_URL = AUTH_URL + "getSalt/";
    /**
     * URL to check for login. (Params: username, hash)
     */
    public static final String LOGIN_URL = AUTH_URL + "validateLogin/";
    /**
     * URL to check token login
     */
    public static final String TOKEN_URL = AUTH_URL + "validateToken/";
    /**
     * URL to regen an expired token
     */
    public static final String REGEN_TOKEN_URL = AUTH_URL + "regenToken/";


    // FOOD
    /**
     * URL for the custom food database part of the api
     */
    public static final String FOOD_URL = BASE_API_URL + "food/";
    /**
     * URL for adding a new custom food item
     */
    public static final String ADD_FOOD_URL = FOOD_URL + "add/";
    /**
     * URL for getting querying the custom food items
     */
    public static final String QUERY_FOOD_DB = FOOD_URL + "get";
    /**
     * URL for getting a custom food item
     */
    public static final String GET_FOOD_URL = QUERY_FOOD_DB + "/";
    /**
     * URL for getting fdc id from a upc code
     */
    public static final String UPC_URL = FOOD_URL + "foodByUpc/";


    // SHOPPING
    /**
     * Base shopping url for all shopping list based requests
     */
    public static final String SHOPPING_URL = BASE_API_URL + "shopping/";
    /**
     * Get url for getting shopping list
     */
    public static final String GET_SHOPPING_URL = SHOPPING_URL + "get/";
    /**
     * Add url for adding an item to a shopping list
     */
    public static final String ADD_SHOPPING_URL = SHOPPING_URL + "add/";
    /**
     * Remove url for removing an item from a shopping list
     */
    public static final String REMOVE_SHOPPING_URL = SHOPPING_URL + "remove/";
    /**
     * Strikeout url for telling the server whether the item should be struck out or not.
     */
    public static final String STRIKE_SHOPPING_URL = SHOPPING_URL + "strikeout/";
    /**
     * Strikeout url when the user interacts with custom food items
     */
    public static final String STRIKE_SHOPPING_DB_URL = SHOPPING_URL + "strikeoutDb/";

    // RECIPES
    /**
     * Base recipes url for all recipes based requests
     */
    public static final String RECIPES_URL = BASE_API_URL + "recipe/";
    /**
     * Add url for adding an item to recipes
     */
    public static final String ADD_RECIPES_URL = RECIPES_URL + "add/";
    /**
     * Remove url for removing an item from recipes
     */
    public static final String REMOVE_RECIPES_URL = RECIPES_URL + "remove";
    /**
     * Url for getting an item from recipes
     */
    public static final String GET_RECIPES_URL = RECIPES_URL + "getRecipeByRid/";
    /**
     * Url for getting an item from recipes
     */
    public static final String GET_RECIPES_LIST_URL = RECIPES_URL + "userRecipeList/";

    // SOCIAL
    /**
     * Base social url for all social based requests
     */
    public static final String SOCIAL_URL = BASE_API_URL + "social/";
    /**
     * Add url for getting a users feed
     */
    public static final String GET_FEED_URL = SOCIAL_URL + "getFeed/";

    //FOOD LOG
    /**
     * Base food log url for all food log based requests
     */
    public static final String FOOD_LOG_URL = BASE_API_URL + "log/";
    /**
     * Add url for adding an item to food log
     */
    public static final String ADD_FOOD_LOG_URL = FOOD_LOG_URL + "add/";
    /**
     * Remove url for removing an item from food log
     */
    public static final String REMOVE_FOOD_LOG_URL = FOOD_LOG_URL + "remove/";
    /**
     * Get url for getting an item from food log
     */
    public static final String GET_FOOD_LOG_URL = FOOD_LOG_URL + "get/";
    /**
     * Get url for getting an item by day from food log
     */
    public static final String GET_FOOD_LOG_BY_DAY_URL = FOOD_LOG_URL + "getDay/";


//    // SOCIAL
//    /**
//     * Base URL for social endpoints
//     */
//    public static final String SOCIAL_URL = BASE_API_URL + "social/";
//    /**
//     * URL for getting user posts
//     */
//    public static final String GET_POSTS_URL = SOCIAL_URL + "getUserPosts/";

    // PROFILE
    /**
     * Base profile endpoints URL
     */
    public static final String PROFILE_API_URL = BASE_API_URL + "profile/";
    /**
     * URL to get profile data
     */
    public static final String GET_PROFILE_URL = PROFILE_API_URL + "getProfile/";
    /**
     * URL for getting a profile picture
     */
    public static final String PROFILE_PICTURE_URL = PROFILE_API_URL + "profilePicture/";
    /**
     * URL for getting a profile banner
     */
    public static final String BANNER_URL = PROFILE_API_URL + "profileBanner/";
    /**
     * URL for updating profile information
     */
    public static final String UPDATE_PROFILE_URL = PROFILE_API_URL + "updateProfile/";
    /**
     * URL used to update a user's profile picture
     */
    public static final String UPDATE_PFP_URL = PROFILE_API_URL + "updatePfp/";
    /**
     * URL used to update a user's banner image
     */
    public static final String UPDATE_BANNER_URL = PROFILE_API_URL + "updateBanner/";

    // OTHER
    /**
     * URL for getting images
     */
    public static final String IMAGE_URL = BASE_API_URL + "images/";

    /**
     * No fdc id associated with this item, used for custom items
     */
    public static final int ITEM_ID_NULL = -1;
}
