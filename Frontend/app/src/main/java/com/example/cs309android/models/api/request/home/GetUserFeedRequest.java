package com.example.cs309android.models.api.request.home;

import com.example.cs309android.models.api.request.abstraction.GetRequest;
import com.google.gson.annotations.Expose;

/**
 * Request for getting a user's homepage feed
 *
 * @author Travis Massner
 */
public class GetUserFeedRequest extends GetRequest {
    /**
     * Authentication token of the user
     */
    @Expose
    private final String token;

    /**
     * Public constructor
     * @param token Authentication token for the user
     */
    public GetUserFeedRequest(String token) {
        this.token = token;
    }

    /**
     * Getter for the authentication token
     * @return authentication token
     */
    public String getToken() {
        return token;
    }

    /**
     * Getter for the URL with token
     * @return URL with token
     */
    public String getURL() {
        // TODO: temporary null because the server gives the wrong data
//        return new ParameterizedRequestURL(Constants.GET_FEED_URL)
//                .addPathVar(token)
//                .toString();
        return null;
    }
}
