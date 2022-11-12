package com.example.cs309android.models.api.request.users;

import static com.example.cs309android.util.Constants.TOKEN_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.GetRequest;
import com.google.gson.annotations.Expose;

/**
 * GSON model for a login request
 *
 * @author Mitch Hudson
 */
public class LoginTokenRequest extends GetRequest {
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

    @Override
    public String getURL() {
        return new ParameterizedRequestURL(TOKEN_URL + token).toString();
    }
}
