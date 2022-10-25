package com.example.cs309android.models.gson.request.social;

import static com.example.cs309android.util.Constants.BANNER_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.gson.GetImageRequest;

/**
 * Gets the profile
 *
 * @author Mitch Hudson
 */
public class GetBannerRequest extends GetImageRequest {
    /**
     * Username to get the profile picture of
     */
    private final String username;

    /**
     * Constructor
     *
     * @param username Username to get the profile picture of
     */
    public GetBannerRequest(String username) {
        this.username = username;
    }

    /**
     * Gets the image id
     *
     * @return image id
     */
    public String getUsername() {
        return username;
    }

    @Override
    public String getURL() {
        return new ParameterizedRequestURL(BANNER_URL + username)
                .toString();
    }
}
