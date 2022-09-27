package com.example.cs309android.models.gson;

/**
 * Used by gson on response to the /getSalt endpoint
 *
 * @author Mitch Hudson
 */
public class SaltResponse {
    /**
     * Salt to be used for hashing the password
     * B64 encoded
     */
    private final String salt;
    /**
     * Result code (Should be a constant from util.Constants)
     */
    private final int result;

    /**
     * Constructor to be used by GSON
     *
     * @param salt   Salt value from the request
     * @param result Result code from the request
     */
    public SaltResponse(String salt, int result) {
        this.salt = salt;
        this.result = result;
    }

    /**
     * Getter for the salt value
     *
     * @return Salt value from the request
     */
    public String getSalt() {
        return salt;
    }

    /**
     * Getter for the result value
     *
     * @return Result value from the request
     */
    public int getResult() {
        return result;
    }
}
