package com.example.cs309android.models.api.request.users;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.GetRequest;
import com.example.cs309android.util.Constants;
import com.google.gson.annotations.Expose;

/**
 * Request for getting the salt associated with a username
 * @author Mitch Hudson
 */
public class SaltRequest extends GetRequest {
    /**
     * Username to get the salt of
     */
    @Expose
    private final String username;

    /**
     * Public constructor to generate a request.
     * @param username Username to get the salt of
     */
    public SaltRequest(String username) {
        this.username = username;
    }

    /**
     * Getter for the username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the parameterized URL
     * @return Parameterized GET URL
     */
    @Override
    public String getURL() {
        return new ParameterizedRequestURL(Constants.SALT_URL)
                .addPathVar(username)
                .toString();
    }
}
