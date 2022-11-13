package com.example.cs309android.models.api.response.social;

import com.example.cs309android.models.api.response.GenericResponse;
import com.google.gson.annotations.Expose;

/**
 * A generic response class that has a response code and an
 * array of usernames
 * Used for various requests in the social endpoints
 *
 * @author Mitch Hudson
 */
public class FollowResponse extends GenericResponse {
    /**
     * Array of usernames, used for various things
     */
    @Expose
    private final String[] users;

    /**
     * Public constructor
     *
     * @param result Result code from the request
     */
    public FollowResponse(int result, String[] users) {
        super(result);
        this.users = users;
    }

    /**
     * Getter for the users
     *
     * @return array of usernames
     */
    public String[] getUsers() {
        return users;
    }
}
