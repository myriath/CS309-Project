package com.example.cs309android.models.api.request.profile;

import static com.example.cs309android.util.Constants.Urls.Profile.PROFILE_PICTURE_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.ImageRequest;

/**
 * Gets the profile picture of an account
 *
 * @author Mitch Hudson
 */
public class GetProfilePictureRequest extends ImageRequest {
    /**
     * Username to get the profile picture of
     */
    private final String username;

    /**
     * Constructor
     *
     * @param username Username to get the profile picture of
     */
    public GetProfilePictureRequest(String username) {
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
        return new ParameterizedRequestURL(PROFILE_PICTURE_URL)
                .addPathVar(username)
                .toString();
    }
}
