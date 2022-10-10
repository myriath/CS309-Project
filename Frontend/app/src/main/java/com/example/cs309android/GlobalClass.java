package com.example.cs309android;

import android.app.Application;

import com.example.cs309android.models.gson.models.AuthModel;

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
     * Auth model for authentication
     */
    private AuthModel authModel;

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
     * Getter for the auth model
     *
     * @return authentication model
     */
    public AuthModel getAuthModel() {
        return authModel;
    }

    /**
     * Setter for the auth model
     *
     * @param authModel authentication model
     */
    public void setAuthModel(AuthModel authModel) {
        this.authModel = authModel;
    }
}
