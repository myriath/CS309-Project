package com.example.cs309android.models.gson.request.social;

import static com.example.cs309android.util.Constants.GET_PROFILE_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.gson.GetRequest;

/**
 * Request to get details for a profile's page
 *
 * @author Mitch Hudson
 */
public class GetProfileRequest extends GetRequest {
    /**
     * Username of the profile to get
     */
    private final String username;

    /**
     * Public constructor
     *
     * @param username username to get the profile of
     */
    public GetProfileRequest(String username) {
        this.username = username;
    }

    @Override
    public String getURL() {
        return new ParameterizedRequestURL(GET_PROFILE_URL + username)
                .toString();
    }
}
