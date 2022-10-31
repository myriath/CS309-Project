package com.example.cs309android;

import android.app.Application;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

import com.example.cs309android.activities.MainActivity;
import com.example.cs309android.util.Util;

import java.util.Map;

/**
 * Global class for holding onto the token for the application
 *
 * @author Mitch Hudson
 */
public class GlobalClass extends Application {
    /**
     * Profile picture used for the account page
     */
    private Bitmap pfp;
    /**
     * Banner image used for the account page
     */
    private Bitmap banner;
    /**
     * Biography text for the account page
     */
    private String bio;
    /**
     * Follower count
     */
    private int followers;
    /**
     * Following count;
     */
    private int following;
    /**
     * User map for storing login data
     */
    private Map<String, String> users;

    /**
     * Shared preferences for the app
     */
    private SharedPreferences preferences;

    /**
     * Updates the login prefs with a json string of
     * the users map
     */
    public void updateLoginPrefs() {
        preferences.edit()
                .putString(
                        MainActivity.PREF_LOGIN,
                        Util.GSON.toJson(users)
                ).apply();
    }

    /**
     * Getter for the token string
     *
     * @return authentication token
     */
    public String getToken() {
        return users.get(getUsername());
    }

    /**
     * Setter for the token string
     *
     * @param token authentication token
     */
    public void setToken(String token) {
        users.put(getUsername(), token);
    }

    /**
     * Removes the login details of the username given
     *
     * @param username username to remove from the table
     */
    public void removeToken(String username) {
        if (MainActivity.USERS_LATEST.equals(username)) return;
        users.remove(username);
    }

    /**
     * Getter for the username
     *
     * @return username
     */
    public String getUsername() {
        return users.get(MainActivity.USERS_LATEST);
    }

    /**
     * Setter for the username
     *
     * @param username new username
     */
    public void setUsername(String username) {
        if (MainActivity.USERS_LATEST.equals(username)) return;
        users.put(MainActivity.USERS_LATEST, username);
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
     * Gets the bio text
     *
     * @return bio text
     */
    public String getBio() {
        return bio;
    }

    /**
     * Sets the bio text
     *
     * @param bio new bio text
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * Gets the follower count
     *
     * @return follower count
     */
    public int getFollowers() {
        return followers;
    }

    /**
     * Sets the follower count
     *
     * @param followers new follower count
     */
    public void setFollowers(int followers) {
        this.followers = followers;
    }

    /**
     * Gets the following count
     *
     * @return following count
     */
    public int getFollowing() {
        return following;
    }

    /**
     * Sets the following count
     *
     * @param following new following count
     */
    public void setFollowing(int following) {
        this.following = following;
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

    /**
     * Getter for the users map
     *
     * @return Map of users
     */
    public Map<String, String> getUsers() {
        return users;
    }

    /**
     * Setter for the users map
     *
     * @param users Map of users
     */
    public void setUsers(Map<String, String> users) {
        this.users = users;
    }
}
