package com.example.cs309android.models.gson.request.users;

import com.example.cs309android.models.gson.PostRequest;
import com.example.cs309android.util.Constants;
import com.google.gson.annotations.Expose;

/**
 * GSON model for a login request
 *
 * @author Mitch Hudson
 */
public class LoginTokenRequest extends PostRequest {
    /**
     * Token to log in with
     */
    @Expose
    private final String token;

    /**
     * Public constructor
     *
     * @param token Token for login
     */
    public LoginTokenRequest(String token) {
        super(Constants.LOGIN_URL);
        this.token = token;
    }

    /**
     * Username getter
     *
     * @return username
     */
    public String getToken() {
        return token;
    }
}
