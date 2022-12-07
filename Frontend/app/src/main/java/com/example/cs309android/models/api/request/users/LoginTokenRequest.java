package com.example.cs309android.models.api.request.users;

import static com.example.cs309android.util.Constants.Urls.Users.TOKEN_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.GetRequest;
import com.google.gson.annotations.Expose;

/**
 * Handles a token login attempt
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
     * Token getter
     *
     * @return token
     */
    public String getToken() {
        return token;
    }

    @Override
    public String getURL() {
        return new ParameterizedRequestURL(TOKEN_URL)
                .addPathVar(token)
                .toString();
    }
}
