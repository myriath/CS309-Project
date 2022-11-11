package com.example.cs309android.models.api.request.social;

import static com.example.cs309android.util.Constants.FOLLOW_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.PostRequest;

/**
 * Request used when a user follows another user
 *
 * @author Mitch Hudson
 */
public class FollowRequest extends PostRequest {
    /**
     * Public constructor
     * @param token Token for authentication
     * @param username  Username of the account to follow
     */
    public FollowRequest(String token, String username) {
        super(new ParameterizedRequestURL(FOLLOW_URL)
                .addPathVar(token)
                .addParam("following", username)
                .toString());
    }
}
