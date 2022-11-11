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
     * Token for authentication
     */
    @Expose
    private final String token;

    /**
     * Public constructor
     * @param token Authentication token
     */
    public GetFollowingRequest(String token) {
        this.token = token;
    }

    /**
     * Getter for the URL
     * @return URL for the request
     */
    @Override
    public String getURL() {
        return new ParameterizedRequestURL(GET_FOLLOWING_URL)
                .addPathVar(token)
                .toString();
    }
}
