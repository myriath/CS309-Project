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
    public static final String SALT_URL = AUTH_URL + "getSalt";
    /**
     * URL to check for login. (Params: username, hash)
     */
    public static final String LOGIN_URL = AUTH_URL + "validateLogin";
    /**
     * URL to check token login
     */
    public static final String TOKEN_URL = AUTH_URL + "validateToken";
    /**
     * URL to regen an expired token
     */
    public static final String REGEN_TOKEN_URL = AUTH_URL + "regenToken";


    // FOOD
    /**
     * URL for the custom food database part of the api
     */
    public static final String FOOD_URL = BASE_API_URL + "food/";
    /**
     * URL for adding a new custom food item
     */
    public static final String ADD_FOOD_URL = FOOD_URL + "add";
    /**
     * URL for getting a custom food item
     */
    public static final String GET_FOOD_URL = FOOD_URL + "get";


    // SHOPPING
    /**
     * Base shopping url for all shopping list based requests
     */
    public static final String SHOPPING_URL = BASE_API_URL + "shopping/";
    /**
     * Get url for getting shopping list
     */
    public static final String GET_SHOPPING_URL = SHOPPING_URL + "get";
    /**
     * Add url for adding an item to a shopping list
     */
    public static final String ADD_SHOPPING_URL = SHOPPING_URL + "add";
    /**
     * Remove url for removing an item from a shopping list
     */
    public static final String REMOVE_SHOPPING_URL = SHOPPING_URL + "remove";
    /**
     * Strikeout url for telling the server whether the item should be struck out or not.
     */
    public static final String STRIKE_SHOPPING_URL = SHOPPING_URL + "strikeout";

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


    // OTHER
    /**
     * No fdc id associated with this item, used for custom items
     */
    public static final int ITEM_ID_NULL = -1;

    // TAGS

    /**
     * Volley tags used to control
     */
    public enum TAGS {
        TAG_GET,
        TAG_POST,
    }
}
