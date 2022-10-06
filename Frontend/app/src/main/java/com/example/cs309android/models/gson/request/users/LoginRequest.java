package com.example.cs309android.models.gson.request.users;

import com.example.cs309android.models.gson.PostRequest;
import com.example.cs309android.util.Constants;
import com.google.gson.annotations.Expose;

/**
 * GSON model for a login request
 *
 * @author Mitch Hudson
 */
public class LoginRequest extends PostRequest {
    /**
     * Username to attempt a login
     */
    @Expose
    private final String username;
    /**
     * Hash to attempt a login with
     */
    @Expose
    private final String pHash;

    /**
     * Public constructor
     *
     * @param username Username for login
     * @param hash     Hash for login
     */
    public LoginRequest(String username, String hash) {
        super(Constants.LOGIN_URL);
        this.username = username;
        this.pHash = hash;
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
    public String getPHash() {
        return pHash;
    }
}
