package com.example.cs309android.util;

import static com.example.cs309android.BuildConfig.BASE_API_URL;

/**
 * Util constants class
 *
 * @author Mitch Hudson
 */
public class Constants {
    /**
     * DP16 in pixels
     */
    public static float dp16;
    /**
     * DP8 in pixels
     */
    public static float dp8;

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
    /**
     * Preference key for the first time variable
     * Used to see if the app should run the tutorial
     */
    public static final String PREF_FIRST_TIME = "FirstTime";
    /**
     * Preference key for the users hash map
     */
    public static final String PREF_LOGIN = "users";
    /**
     * Preference key for the latest user
     */
    public static final String USERS_LATEST = "latest";
    /**
     * Used to target the breakfast food log
     */
    public static final int BREAKFAST_LOG = 0;
    /**
     * Used to target the lunch food log
     */
    public static final int LUNCH_LOG = 1;
    /**
     * Used to target the dinner log
     */
    public static final int DINNER_LOG = 2;

    /**
     * Max retries for token generation
     */
    public static final int TOKEN_MAX_DEPTH = 5;
    /**
     * Default callback for no particular use case
     */
    public static final int CALLBACK_DEFAULT = -1;
    /**
     * Used to remove an item from a list
     */
    public static final int CALLBACK_REMOVE = 0;
    /**
     * Tells MainActivity to open the login page
     */
    public static final int CALLBACK_START_LOGIN = 1;
    /**
     * Tells MainActivity to move to the homepage
     */
    public static final int CALLBACK_MOVE_TO_HOME = 2;
    /**
     * Opens the food details page
     */
    public static final int CALLBACK_FOOD_DETAIL = 3;
    /**
     * Opens the food search activity
     */
    public static final int CALLBACK_SEARCH_FOOD = 4;
    /**
     * Tells MainActivity to move to the settings page
     */
    public static final int CALLBACK_MOVE_TO_SETTINGS = 5;
    /**
     * Used to return an image uri from the Image loader bottom sheet
     */
    public static final int CALLBACK_IMAGE_URI = 6;
    /**
     * Used to open the account page
     */
    public static final int CALLBACK_OPEN_ACCOUNT = 7;
    /**
     * Various intents tell the app what to do when certain things are done.
     */
    public static final int INTENT_NONE = -1;
    /**
     * Intent code tells the SearchActivity it is from the shopping list
     */
    public static final int INTENT_SHOPPING_LIST = 0;
    /**
     * Tells the SearchActivity it was opened from recipe add
     */
    public static final int INTENT_RECIPE_ADD = 1;
    /**
     * Used to get an image uri from a parcel / intent
     */
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
    /**
     * Used to parcel an item's position in a list
     */
    public static final String PARCEL_ITEM_POSITION = "item_pos";
    /**
     * Used to parcel a recipe
     */
    public static final String PARCEL_RECIPE = "HomeFragment.recipe";
    /**
     * Used in the account page to tell if it is the owner or not
     */
    public static final String PARCEL_OWNER = "owner";
    /**
     * Used to tell the account page if the account is being followed
     */
    public static final String PARCEL_FOLLOWING = "following";
    /**
     * Used in the account page and login page
     */
    public static final String PARCEL_USERNAME = "username";
    /**
     * Used in the login page to pass data to register
     */
    public static final String PARCEL_PASSWORD = "password";
    /**
     * Used in AccountListPage for transferring an array of usernames
     */
    public static final String PARCEL_ACCOUNT_LIST = "accounts";
    /**
     * Used to give the toolbar in AccountListView a title
     */
    public static final String PARCEL_TITLE = "title";
    /**
     * Used to tell the AccountSwitchActivity whether or not to allow back button presses
     */
    public static final String PARCEL_LOGGED_OUT = "logged_out";
    /**
     * Used to control the back button usability
     */
    public static final String PARCEL_BACK_ENABLED = "enable_back";

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
    /**
     * Base auth url for all authentication requests
     */
    public static final String AUTH_URL = BASE_API_URL + "users/";


    // USERS
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
    /**
     * URL for the custom food database part of the api
     */
    public static final String FOOD_URL = BASE_API_URL + "food/";


    // FOOD
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
    /**
     * Base shopping url for all shopping list based requests
     */
    public static final String SHOPPING_URL = BASE_API_URL + "shopping/";


    // SHOPPING
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
    /**
     * Base recipes url for all recipes based requests
     */
    public static final String RECIPES_URL = BASE_API_URL + "recipe/";

    // RECIPES
    /**
     * Add url for adding an item to recipes
     */
    public static final String ADD_RECIPES_URL = RECIPES_URL + "add/";
    /**
     * URL for updating recipes
     */
    public static final String UPDATE_RECIPES_URL = RECIPES_URL + "update/";
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
    /**
     * URL for setting a recipe's image
     */
    public static final String ADD_RECIPE_IMAGE_URL = RECIPES_URL + "addPicture/";
    /**
     * URL for getting a recipe's image
     */
    public static final String GET_RECIPE_IMAGE_URL = RECIPES_URL + "getPicture/";
    /**
     * Base social url for all social based requests
     */
    public static final String SOCIAL_URL = BASE_API_URL + "social/";

    // SOCIAL
    /**
     * URL used when creating a comment
     */
    public static final String COMMENT_URL = SOCIAL_URL + "comment";
    /**
     * URL for deleting a comment
     */
    public static final String REMOVE_COMMENT_URL = SOCIAL_URL + "removeComment";
    /**
     * URL for following an account
     */
    public static final String FOLLOW_URL = SOCIAL_URL + "follow";
    /**
     * URL for unfollowing an account
     */
    public static final String UNFOLLOW_URL = SOCIAL_URL + "unfollow";
    /**
     * URL for checking follower status
     */
    public static final String IS_FOLLOWING_URL = SOCIAL_URL + "isFollowing";
    /**
     * URL for getting the follower list
     */
    public static final String GET_FOLLOWERS_URL = SOCIAL_URL + "getFollowers";
    /**
     * URL for getting the following list
     */
    public static final String GET_FOLLOWING_URL = SOCIAL_URL + "getFollowing";
    /**
     * Add url for getting a users feed
     */
    public static final String GET_FEED_URL = SOCIAL_URL + "getFeed";
    /**
     * Base food log url for all food log based requests
     */
    public static final String FOOD_LOG_URL = BASE_API_URL + "log/";

    //FOOD LOG
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
    /**
     * Base profile endpoints URL
     */
    public static final String PROFILE_API_URL = BASE_API_URL + "profile/";

    // PROFILE
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
    /**
     * URL for getting images
     */
    public static final String IMAGE_URL = BASE_API_URL + "images/";

    // OTHER
    /**
     * No fdc id associated with this item, used for custom items
     */
    public static final int ITEM_ID_NULL = -1;

    /**
     * Private constructor (Util class)
     */
    private Constants() {
    }
}
