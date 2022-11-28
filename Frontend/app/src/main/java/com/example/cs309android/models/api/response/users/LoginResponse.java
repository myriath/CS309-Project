package com.example.cs309android.models.api.response.users;

import com.example.cs309android.models.api.response.GenericResponse;
import com.example.cs309android.util.Constants;
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
     * Type of the user
     * Value from {@link Constants.UserType}
     */
    @Expose
    private final int userType;

    /**
     * Public constructor
     *
     * @param result   Result code from the request
     * @param username Username of the account logged into
     * @param userType Type of the user from {@link Constants.UserType}
     */
    public LoginResponse(int result, String username, int userType) {
        super(result);
        this.username = username;
        this.userType = userType;
    }

    /**
     * Username for reference
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter for the user type
     * @return Type from {@link Constants.UserType}
     */
    public int getUserType() {
        return userType;
    }
}
