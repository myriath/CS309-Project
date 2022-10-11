package com.example.cs309android;

import android.app.Application;

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
}
