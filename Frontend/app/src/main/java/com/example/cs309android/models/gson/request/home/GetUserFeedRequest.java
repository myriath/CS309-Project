package com.example.cs309android.models.gson.request.home;

import com.example.cs309android.models.gson.GetRequest;
import com.example.cs309android.util.Constants;

public class GetUserFeedRequest extends GetRequest {
    /**
     * Authentication token of the user
     */
    private final String token;

    /**
     * Public constructor
     *
     * @param token Authentication token for the user
     */
    public GetUserFeedRequest(String token) {
        this.token = token;
    }

    /**
     * Getter for the authentication token
     *
     * @return authentication token
     */
    public String getToken() {
        return token;
    }

    /**
     * Getter for the URL with token
     *
     * @return URL with token
     */
    public String getURL() {
        return Constants.GET_FEED_URL + token;
    }
}
