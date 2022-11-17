package com.example.cs309android.models.api.response.users;

import com.example.cs309android.models.api.response.GenericResponse;
import com.google.gson.annotations.Expose;

/**
 * Response object for login requests
 *
 * @author Mitch Hudson
 */
public class LoginResponse extends GenericResponse {
    /**
     * Username of the logged in account
     */
    @Expose
    private final String username;

    /**
     * Public constructor
     *
     * @param result   Result code from the request
     * @param username Username of the account logged into
     */
    public LoginResponse(int result, String username) {
        super(result);
        this.username = username;
    }

    /**
     * Username for reference
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }
}
