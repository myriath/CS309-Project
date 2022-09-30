package com.example.cs309android.models.gson.request;

/**
 * GSON model for a login request
 *
 * @author Mitch Hudson
 */
public class LoginRequest {
    /**
     * Username to attempt a login
     */
    private final String username;
    /**
     * Hash to attempt a login with
     */
    private final String pHash;

    /**
     * Public constructor
     *
     * @param username Username for login
     * @param hash     Hash for login
     */
    public LoginRequest(String username, String hash) {
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
