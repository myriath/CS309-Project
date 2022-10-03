package com.example.cs309android.models.gson.models;

import com.google.gson.annotations.Expose;

/**
 * GSON model for authentication
 *
 * @author Mitch Hudson
 */
public class AuthModel {
    /**
     * Username of the authentication
     */
    @Expose
    private final String username;
    /**
     * Hashed password of the authentication
     */
    @Expose
    private final String hash;

    /**
     * Public constructor
     *
     * @param username Username
     * @param hash     Password hash
     */
    public AuthModel(String username, String hash) {
        this.username = username;
        this.hash = hash;
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
     * Getter for the hash
     *
     * @return password hash
     */
    public String getHash() {
        return hash;
    }
}
