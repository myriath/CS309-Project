package com.example.cs309android.models.api.request.users;

import static com.example.cs309android.util.Constants.LOGIN_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.GetRequest;
import com.google.gson.annotations.Expose;

/**
 * Handles a password login attempt
 *
 * @author Mitch Hudson
 */
public class LoginHashRequest extends GetRequest {
    /**
     * Username to attempt a login
     */
    @Expose
    private final String username;
    /**
     * Hash to attempt a login with
     */
    @Expose
    private final String hash;
    /**
     * B64 encoded string of the new login token
     */
    @Expose
    private final String token;

    /**
     * Public constructor
     *
     * @param username Username for login
     * @param hash     Hash for login
     * @param token    New token for logging in
     */
    public LoginHashRequest(String username, String hash, String token) {
        this.username = username;
        this.hash = hash;
        this.token = token;
    }

    /**
     * Username getter
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Hash getter
     *
     * @return hash
     */
    public String getHash() {
        return hash;
    }

    /**
     * Getter for the request URL
     *
     * @return request URL
     */
    @Override
    public String getURL() {
        return new ParameterizedRequestURL(LOGIN_URL)
                .addPathVar(username)
                .addParam("hash", hash)
                .addParam("newToken", token)
                .toString();
    }
}
