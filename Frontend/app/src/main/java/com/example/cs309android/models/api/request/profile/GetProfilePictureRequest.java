package com.example.cs309android.models.api.request.profile;

import static com.example.cs309android.util.Constants.PROFILE_PICTURE_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.GetImageRequest;

/**
 * Gets the profile
 */
public class GetProfilePictureRequest extends GetImageRequest {
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

    @Override
    public String getURL() {
        return new ParameterizedRequestURL(PROFILE_PICTURE_URL + username)
                .toString();
    }
}
