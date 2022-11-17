package com.example.cs309android.models.api.response.users;

import com.example.cs309android.models.api.response.GenericResponse;
import com.google.gson.annotations.Expose;

/**
 * Response object for the get salt request
 *
 * @author Mitch Hudson
 */
public class SaltResponse extends GenericResponse {
    /**
     * Salt to be used for hashing the password
     * B64 encoded
     */
    @Expose
    private final String salt;

    /**
     * Public constructor
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
