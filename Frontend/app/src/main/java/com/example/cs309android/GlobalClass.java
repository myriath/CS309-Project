package com.example.cs309android;

import android.app.Application;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

/**
 * Global class for holding onto the token for the application
 *
 * @author Mitch Hudson
 */
public class GlobalClass extends Application {
    /**
     * Token string for authentication
     */
    private String token;
    /**
     * Username used for the account page
     */
    private String username;
    /**
     * Profile picture used for the account page
     */
    private Bitmap pfp;
    /**
     * Banner image used for the account page
     */
    private Bitmap banner;

    /**
     * Shared preferences for the app
     */
    private SharedPreferences preferences;

    /**
     * Getter for the token string
     *
     * @return authentication token
     */
    public String getToken() {
        return token;
    }

    /**
     * Setter for the token string
     *
     * @param token authentication token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Getter for the username
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for the username
     *
     * @param username new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for the profile picture
     *
     * @return profile picture
     */
    public Bitmap getPfp() {
        return pfp;
    }

    /**
     * Setter for the profile picture
     *
     * @param pfp new profile picture
     */
    public void setPfp(Bitmap pfp) {
        this.pfp = pfp;
    }

    /**
     * Getter for the banner image
     *
     * @return banner image
     */
    public Bitmap getBanner() {
        return banner;
    }

    /**
     * Setter for the banner image
     *
     * @param banner new banner image
     */
    public void setBanner(Bitmap banner) {
        this.banner = banner;
    }

    /**
     * Getter for the shared preferences
     *
     * @return shared preferences
     */
    public SharedPreferences getPreferences() {
        return preferences;
    }

    /**
     * Setter for the shared preferences
     *
     * @param preferences new shared preferences
     */
    public void setPreferences(SharedPreferences preferences) {
        this.preferences = preferences;
    }
}
