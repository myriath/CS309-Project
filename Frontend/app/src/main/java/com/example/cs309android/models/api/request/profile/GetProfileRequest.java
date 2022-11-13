package com.example.cs309android.models.api.request.profile;

import static com.example.cs309android.util.Constants.GET_PROFILE_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.GetRequest;

/**
 * Request to get details for a user's profile
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

    /**
     * Getter for the request URL
     *
     * @return request URL
     */
    @Override
    public String getURL() {
        return new ParameterizedRequestURL(GET_PROFILE_URL)
                .addPathVar(username)
                .toString();
    }
}
