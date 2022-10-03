package com.example.cs309android.models.gson.response.users;

import com.example.cs309android.models.gson.response.GenericResponse;

/**
 * Used by gson on response to the /getSalt endpoint
 *
 * @author Mitch Hudson
 */
public class SaltResponse extends GenericResponse {
    /**
     * Salt to be used for hashing the password
     * B64 encoded
     */
    private final String salt;

    /**
     * Constructor to be used by GSON
     *
     * @param result Result code from the request
     * @param salt   Salt value from the request
     */
    public SaltResponse(int result, String salt) {
        super(result);
        this.salt = salt;
    }

    /**
     * Getter for the salt value
     *
     * @return Salt value from the request
     */
    public String getSalt() {
        return salt;
    }
}
