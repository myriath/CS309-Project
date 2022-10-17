package com.example.cs309android.models.gson.response.users;

import com.example.cs309android.models.gson.response.GenericResponse;
import com.google.gson.annotations.Expose;

/**
 * Response class for the /users/validateLogin endpoint
 *
 * @author Mitch Hudson
 */
public class LoginHashResponse extends GenericResponse {
    /**
     * Rotating token to be used instead of hash
     */
    @Expose
    private final String token;

    /**
     * Constructor to be used by GSON
     *
     * @param result Result code from the request
     */
    public LoginHashResponse(int result, String token) {
        super(result);
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
