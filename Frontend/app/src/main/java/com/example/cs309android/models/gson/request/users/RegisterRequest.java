package com.example.cs309android.models.gson.request.users;

import com.example.cs309android.models.gson.PostRequest;
import com.example.cs309android.util.Constants;
import com.google.gson.annotations.Expose;

/**
 * GSON model for the register endpoint POST request body
 *
 * @author Mitch Hudson
 */
public class RegisterRequest extends PostRequest {
    /**
     * Email as a string to register.
     */
    @Expose
    private final String email;
    /**
     * Username as a string to register.
     */
    @Expose
    private final String username;
    /**
     * Base64 encoded string of the 256 bit hash
     */
    @Expose
    private final String pHash;
    /**
     * Base64 encoded string of the 64 bit salt
     */
    @Expose
    private final String pSalt;

    /**
     * Constructor to be used by GSON
     *
     * @param email    Email to register
     * @param username Username to register
     * @param pHash    Hashed password + salt for new account
     * @param pSalt    Salt used for hashing
     */
    public RegisterRequest(String email, String username, String pHash, String pSalt) {
        super(Constants.REGISTER_URL);
        this.email = email;
        this.username = username;
        this.pHash = pHash;
        this.pSalt = pSalt;
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
        return pHash;
    }

    /**
     * Getter for the salt
     *
     * @return salt used when hashing the password.
     */
    public String getSalt() {
        return pSalt;
    }
}
