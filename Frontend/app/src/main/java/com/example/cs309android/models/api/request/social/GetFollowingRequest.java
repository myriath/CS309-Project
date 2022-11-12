package com.example.cs309android.models.api.request.social;

import static com.example.cs309android.util.Constants.GET_FOLLOWING_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.GetRequest;
import com.google.gson.annotations.Expose;

/**
 * Gets a list of users following this user
 *
 * @author Mitch Hudson
 */
public class GetFollowingRequest extends GetRequest {
    /**
     * Username to get following list of
     */
    @Expose
    private final String username;

    /**
     * Public constructor
     *
     * @param username Username to get list from
     */
    public GetFollowingRequest(String username) {
        this.username = username;
    }

    /**
     * Getter for the URL
     * @return URL for the request
     */
    @Override
    public String getURL() {
        return new ParameterizedRequestURL(GET_FOLLOWING_URL)
                .addPathVar(username)
                .toString();
    }
}
