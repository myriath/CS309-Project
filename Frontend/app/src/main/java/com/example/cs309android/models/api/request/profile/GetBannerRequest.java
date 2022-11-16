package com.example.cs309android.models.api.request.profile;

import static com.example.cs309android.util.Constants.BANNER_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.ImageRequest;

/**
 * Gets the banner image of a profile
 *
 * @author Mitch Hudson
 */
public class GetBannerRequest extends ImageRequest {
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

    /**
     * Getter for the request URL
     *
     * @return request URL
     */
    @Override
    public String getURL() {
        return new ParameterizedRequestURL(BANNER_URL)
                .addPathVar(username)
                .toString();
    }
}
