package com.example.cs309android.models.gson.request.social;

import static com.example.cs309android.util.Constants.GET_POSTS_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.gson.GetRequest;

/**
 * Gets a user's posts
 * <p>
 * TODO: STUB!
 *
 * @author Mitch Hudson
 */
public class GetUserPostsRequest extends GetRequest {
    /**
     * Username of the user to get posts of
     */
    private final String username;

    /**
     * Constructor
     *
     * @param username user to get posts of
     */
    public GetUserPostsRequest(String username) {
        this.username = username;
    }

    /**
     * Gets the username
     *
     * @return username of user to get posts of
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the URL for this request
     *
     * @return url for this request
     */
    @Override
    public String getURL() {
        return new ParameterizedRequestURL(GET_POSTS_URL + username)
                .toString();
    }
}
