package com.example.cs309android.models.api.request.social;

import static com.example.cs309android.util.Constants.Urls.Social.IS_FOLLOWING_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.GetRequest;

/**
 * Checks if the given user is following the `following` user
 *
 * @author Mitch Hudson
 */
public class IsFollowingRequest extends GetRequest {
    /**
     * Checks if this user is following the `following` user
     */
    private final String username;
    /**
     * Checks if the `username` user is following this user
     */
    private final String following;

    /**
     * Public constructor
     *
     * @param username  Checks if this user is following the `following` user
     * @param following Checks if the `username` user is following this user
     */
    public IsFollowingRequest(String username, String following) {
        this.username = username;
        this.following = following;
    }

    /**
     * Getter for the request URL
     *
     * @return request URL
     */
    @Override
    public String getURL() {
        return new ParameterizedRequestURL(IS_FOLLOWING_URL)
                .addPathVar(username)
                .addPathVar(following)
                .toString();
    }
}
