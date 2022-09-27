package com.example.cs309android.models.gson.request;

/**
 * GSON model for the register endpoint POST request body
 *
 * @author Mitch Hudson
 */
public class RegisterRequest {
    /**
     * Email as a string to register.
     */
    private final String email;
    /**
     * Username as a string to register.
     */
    private final String username;
    /**
     * Base64 encoded string of the 256 bit hash
     */
    private final String hash;
    /**
     * Base64 encoded string of the 64 bit salt
     */
    private final String salt;

    /**
     * Constructor to be used by GSON
     *
     * @param email    Email to register
     * @param username Username to register
     * @param hash     Hashed password + salt for new account
     * @param salt     Salt used for hashing
     */
    public RegisterRequest(String email, String username, String hash, String salt) {
        this.email = email;
        this.username = username;
        this.hash = hash;
        this.salt = salt;
    }

    /**
     * Getter for the email
     *
     * @return email to register
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter for the username
     *
     * @return username to register
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter for the hash
     *
     * @return hashed password + salt for new account
     */
    public String getHash() {
        return hash;
    }

    /**
     * Getter for the salt
     *
     * @return salt used when hashing the password.
     */
    public String getSalt() {
        return salt;
    }
}
