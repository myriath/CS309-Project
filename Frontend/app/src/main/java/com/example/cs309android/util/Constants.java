package com.example.cs309android.util;

import static com.example.cs309android.BuildConfig.BASE_API_URL;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;

import java.util.Arrays;

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
     * Picasso Singleton for the app
     */
    public static PicassoSingleton PICASSO;

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

    public static NotificationManager manager;

    public interface Notifications {
        String[] DESCRIPTIONS = new String[] {
                "high desc", "reg desc", "low desc"
        };

        String[] IDS = new String[] {
                "cs309.notification.high", "cs309.notification", "cs309.notification.low"
        };

        String[] NAMES = new String[] {
                "High Importance", "Regular Importance", "Low Importance"
        };

        NotificationChannel[] CHANNELS = new NotificationChannel[] {
                new NotificationChannel(IDS[0], NAMES[0], NotificationManager.IMPORTANCE_HIGH),
                new NotificationChannel(IDS[1], NAMES[1], NotificationManager.IMPORTANCE_DEFAULT),
                new NotificationChannel(IDS[2], NAMES[2], NotificationManager.IMPORTANCE_LOW)
        };

        static void createNotificationChannels(Context context) {
            for (int i = 0; i < CHANNELS.length; i++) {
                CHANNELS[i].setDescription(DESCRIPTIONS[i]);
            }
            manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannels(Arrays.asList(CHANNELS));
        }
    }

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

    /**
     * Callback interface
     * Stores all of the callback opcodes
     */
    public interface Callbacks {
        /**
         * Default callback for no particular use case
         */
        int CALLBACK_DEFAULT = -1;
        /**
         * Used to remove an item from a list
         */
        int CALLBACK_REMOVE = 0;
        /**
         * Tells MainActivity to open the login page
         */
        int CALLBACK_START_LOGIN = 1;
        /**
         * Tells MainActivity to move to the homepage
         */
        int CALLBACK_MOVE_TO_HOME = 2;
        /**
         * Opens the food details page
         */
        int CALLBACK_FOOD_DETAIL = 3;
        /**
         * Opens the food search activity
         */
        int CALLBACK_SEARCH_FOOD = 4;
        /**
         * Tells MainActivity to move to the settings page
         */
        int CALLBACK_MOVE_TO_SETTINGS = 5;
        /**
         * Used to return an image uri from the Image loader bottom sheet
         */
        int CALLBACK_IMAGE_URI = 6;
        /**
         * Used to open the account page
         */
        int CALLBACK_OPEN_ACCOUNT = 7;
    }

    /**
     * All of the intent codes for the application
     */
    public interface Intents {
        /**
         * Various intents tell the app what to do when certain things are done.
         */
        int INTENT_NONE = -1;
        /**
         * Intent code tells the SearchActivity it is from the shopping list
         */
        int INTENT_SHOPPING_LIST = 0;
        /**
         * Tells the SearchActivity it was opened from recipe add
         */
        int INTENT_RECIPE_ADD = 1;
    }

    /**
     * All of the parcel keys for the application
     */
    public interface Parcels {
        /**
         * Used to get an image uri from a parcel / intent
         */
        String PARCEL_IMAGE_URI = "image_uri";
        /**
         * This is used wherever a food item needs to be parceled.
         */
        String PARCEL_FOODITEM = "fooditem";
        /**
         * This is used whenever a list of food items needs to be parceled.
         */
        String PARCEL_FOODITEMS_LIST = "fooditems";
        /**
         * This is used to parcel the intent of opening an activity.
         */
        String PARCEL_INTENT_CODE = "intentCode";
        /**
         * Used to parcel the control variable
         */
        String PARCEL_BUTTON_CONTROL = "button-control";
        /**
         * Used to parcel an item's position in a list
         */
        String PARCEL_ITEM_POSITION = "item_pos";
        /**
         * Used to parcel a recipe
         */
        String PARCEL_RECIPE = "HomeFragment.recipe";
        /**
         * Used in the account page to tell if it is the owner or not
         */
        String PARCEL_OWNER = "owner";
        /**
         * Used to tell the account page if the account is being followed
         */
        String PARCEL_FOLLOWING = "following";
        /**
         * Used in the account page and login page
         */
        String PARCEL_USERNAME = "username";
        /**
         * Used in the login page to pass data to register
         */
        String PARCEL_PASSWORD = "password";
        /**
         * Used in AccountListPage for transferring an array of usernames
         */
        String PARCEL_ACCOUNT_LIST = "accounts";
        /**
         * Used to give the toolbar in AccountListView a title
         */
        String PARCEL_TITLE = "title";
        /**
         * Used to tell the AccountSwitchActivity whether or not to allow back button presses
         */
        String PARCEL_LOGGED_OUT = "logged_out";
        /**
         * Used to control the back button usability
         */
        String PARCEL_BACK_ENABLED = "enable_back";
    }

    /**
     * Result codes interface
     * Stores all possible result codes from the api
     */
    public interface Results {
        /**
         * Indicates the request resulted in an error (generic)
         */
        int RESULT_ERROR = -1;
        /**
         * Indicates a mismatch between the given username / password
         */
        int RESULT_ERROR_USER_HASH_MISMATCH = -2;
        /**
         * Indicates the username given has been taken
         */
        int RESULT_ERROR_USERNAME_TAKEN = -3;
        /**
         * Indicates the email given has been taken
         */
        int RESULT_ERROR_EMAIL_TAKEN = -4;
        /**
         * Indicates the request succeeded (generic)
         */
        int RESULT_OK = 0;
        /**
         * Indicates the user / hash matches
         */
        int RESULT_LOGGED_IN = 1;
        /**
         * Indicates the user has been successfully created
         */
        int RESULT_USER_CREATED = 2;
        /**
         * Login is valid, but needs to regen token
         */
        int RESULT_REGEN_TOKEN = 3;
        /**
         * Indicates the user has been successfully created
         */
        int RESULT_RECIPE_CREATED = 22;
        /**
         * Indicates the user has been successfully created
         */
        int RESULT_ERROR_RID_TAKEN = -23;

    }

    /**
     * Interface for storing all url constants
     */
    public interface Urls {
        /**
         * Base auth url for all authentication requests
         */
        String AUTH_URL = BASE_API_URL + "users/";
        /**
         * URL for the custom food database part of the api
         */
        String FOOD_URL = BASE_API_URL + "food/";
        /**
         * Base shopping url for all shopping list based requests
         */
        String SHOPPING_URL = BASE_API_URL + "shopping/";
        /**
         * Base recipes url for all recipes based requests
         */
        String RECIPES_URL = BASE_API_URL + "recipe/";
        /**
         * Base social url for all social based requests
         */
        String SOCIAL_URL = BASE_API_URL + "social/";
        /**
         * Base food log url for all food log based requests
         */
        String FOOD_LOG_URL = BASE_API_URL + "log/";
        /**
         * Base profile endpoints URL
         */
        String PROFILE_API_URL = BASE_API_URL + "profile/";
        /**
         * URL for getting images
         */
        String IMAGE_URL = BASE_API_URL + "images/";

        /**
         * URLs for the users endpoint
         */
        interface Users {
            /**
             * URL to register a new account to. (Params: email, username, salt, hash)
             */
            String REGISTER_URL = AUTH_URL + "register";
            /**
             * URL to get an accounts salt. (Params: username)
             */
            String SALT_URL = AUTH_URL + "getSalt/";
            /**
             * URL to check for login. (Params: username, hash)
             */
            String LOGIN_URL = AUTH_URL + "validateLogin/";
            /**
             * URL to check token login
             */
            String TOKEN_URL = AUTH_URL + "validateToken/";
            /**
             * URL to regen an expired token
             */
            String REGEN_TOKEN_URL = AUTH_URL + "regenToken/";
            /**
             * URL for deleting users (used by admin / moderator)
             */
            String DELETE_URL = AUTH_URL + "delete/";
            /**
             * URL for changing user types (used by admin)
             */
            String UPDATE_USER_TYPE_URL = AUTH_URL + "updateUserType/";
        }

        /**
         * Food endpoint urls
         */
        interface Food {
            /**
             * URL for adding a new custom food item
             */
            String ADD_FOOD_URL = FOOD_URL + "add/";
            /**
             * URL for getting querying the custom food items
             */
            String QUERY_FOOD_DB = FOOD_URL + "get";
            /**
             * URL for getting a custom food item
             */
            String GET_FOOD_URL = QUERY_FOOD_DB + "/";
            /**
             * URL for getting fdc id from a upc code
             */
            String UPC_URL = FOOD_URL + "foodByUpc/";
            /**
             * URL for deleting custom food items
             */
            String DELETE_URL = FOOD_URL + "delete/";
            /**
             * URL for updating custom food items
             */
            String UPDATE_URL = FOOD_URL + "update/";
        }

        /**
         * Shopping endpoint urls
         */
        interface Shopping {
            /**
             * Get url for getting shopping list
             */
            String GET_SHOPPING_URL = SHOPPING_URL + "get/";
            /**
             * Add url for adding an item to a shopping list
             */
            String ADD_SHOPPING_URL = SHOPPING_URL + "add/";
            /**
             * Remove url for removing an item from a shopping list
             */
            String REMOVE_SHOPPING_URL = SHOPPING_URL + "remove/";
            /**
             * Strikeout url for telling the server whether the item should be struck out or not.
             */
            String STRIKE_SHOPPING_URL = SHOPPING_URL + "strikeout/";
            /**
             * Strikeout url when the user interacts with custom food items
             */
            String STRIKE_SHOPPING_DB_URL = SHOPPING_URL + "strikeoutDb/";
        }

        /**
         * Recipe endpoint urls
         */
        interface Recipes {
            /**
             * Add url for adding an item to recipes
             */
            String ADD_RECIPES_URL = RECIPES_URL + "add/";
            /**
             * URL for updating recipes
             */
            String UPDATE_RECIPES_URL = RECIPES_URL + "update/";
            /**
             * Remove url for removing an item from recipes
             */
            String REMOVE_RECIPES_URL = RECIPES_URL + "remove";
            /**
             * Url for getting an item from recipes
             */
            String GET_RECIPES_URL = RECIPES_URL + "getRecipeByRid/";
            /**
             * Url for getting an item from recipes
             */
            String GET_RECIPES_LIST_URL = RECIPES_URL + "userRecipeList/";
            /**
             * URL for setting a recipe's image
             */
            String ADD_RECIPE_IMAGE_URL = RECIPES_URL + "addPicture/";
            /**
             * URL for getting a recipe's image
             */
            String GET_RECIPE_IMAGE_URL = RECIPES_URL + "getPicture/";
            /**
             * URL for deleting a recipe
             */
            String DELETE_URL = RECIPES_URL + "delete/";

        }

        /**
         * Social endpoint urls
         */
        interface Social {
            /**
             * URL used when creating a comment
             */
            String COMMENT_URL = SOCIAL_URL + "comment";
            /**
             * URL used when editing comments
             */
            String EDIT_COMMENT_URL = SOCIAL_URL + "editComment";
            /**
             * URL for deleting a comment
             */
            String DELETE_COMMENT_URL = SOCIAL_URL + "removeComment";
            /**
             * URL for following an account
             */
            String FOLLOW_URL = SOCIAL_URL + "follow";
            /**
             * URL for unfollowing an account
             */
            String UNFOLLOW_URL = SOCIAL_URL + "unfollow";
            /**
             * URL for checking follower status
             */
            String IS_FOLLOWING_URL = SOCIAL_URL + "isFollowing";
            /**
             * URL for getting the follower list
             */
            String GET_FOLLOWERS_URL = SOCIAL_URL + "getFollowers";
            /**
             * URL for getting the following list
             */
            String GET_FOLLOWING_URL = SOCIAL_URL + "getFollowing";
            /**
             * Add url for getting a users feed
             */
            String GET_FEED_URL = SOCIAL_URL + "getFeed";
        }

        /**
         * FoodLog endpoint urls
         */
        interface FoodLog {
            /**
             * Add url for adding an item to food log
             */
            String ADD_FOOD_LOG_URL = FOOD_LOG_URL + "add/";
            /**
             * Remove url for removing an item from food log
             */
            String REMOVE_FOOD_LOG_URL = FOOD_LOG_URL + "remove/";
            /**
             * Get url for getting an item from food log
             */
            String GET_FOOD_LOG_URL = FOOD_LOG_URL + "get/";
            /**
             * Get url for getting an item by day from food log
             */
            String GET_FOOD_LOG_BY_DAY_URL = FOOD_LOG_URL + "getDay/";
        }

        /**
         * Profile endpoint urls
         */
        interface Profile {
            /**
             * URL to get profile data
             */
            String GET_PROFILE_URL = PROFILE_API_URL + "getProfile/";
            /**
             * URL for getting a profile picture
             */
            String PROFILE_PICTURE_URL = PROFILE_API_URL + "profilePicture/";
            /**
             * URL for getting a profile banner
             */
            String BANNER_URL = PROFILE_API_URL + "profileBanner/";
            /**
             * URL for updating profile information
             */
            String UPDATE_PROFILE_URL = PROFILE_API_URL + "updateProfile/";
            /**
             * URL used to update a user's profile picture
             */
            String UPDATE_PFP_URL = PROFILE_API_URL + "updatePfp/";
            /**
             * URL used to update a user's banner image
             */
            String UPDATE_BANNER_URL = PROFILE_API_URL + "updateBanner/";
        }
    }

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
