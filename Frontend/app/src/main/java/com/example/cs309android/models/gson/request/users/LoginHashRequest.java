package com.example.cs309android.models.gson.request.users;

import static com.example.cs309android.util.Constants.LOGIN_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.gson.GetRequest;
import com.google.gson.annotations.Expose;

/**
 * GSON model for a login request
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
     * Public constructor
     *
     * @param username Username for login
     * @param hash     Hash for login
     */
    public LoginHashRequest(String username, String hash) {
        this.username = username;
        this.hash = hash;
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

    @Override
    public String getURL() {
        return new ParameterizedRequestURL(LOGIN_URL + "/" + username)
                .addParam("hash", hash)
                .toString();
    }
}
