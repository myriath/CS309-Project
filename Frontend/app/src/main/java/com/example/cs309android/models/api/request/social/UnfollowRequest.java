package com.example.cs309android.models.api.request.social;

import static com.example.cs309android.util.Constants.UNFOLLOW_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.PutRequest;

/**
 * Cause a user to unfollow another user
 *
 * @author Mitch Hudson
 */
public class UnfollowRequest extends PutRequest {
    /**
     * Public constructor
     *
     * @param token    Token for authentication
     * @param username Username of the account to unfollow
     */
    public UnfollowRequest(String token, String username) {
        super(new ParameterizedRequestURL(UNFOLLOW_URL)
                .addPathVar(token)
                .addParam("following", username)
                .toString());
    }
}
