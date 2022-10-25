package com.example.cs309android.models.gson.request.social;

import static com.example.cs309android.util.Constants.UPDATE_PROFILE_URL;

import com.example.cs309android.models.gson.request.abstraction.ImageSendRequest;
import com.example.cs309android.models.gson.request.abstraction.PatchImageRequest;
import com.example.cs309android.models.gson.request.abstraction.PatchRequest;
import com.google.gson.annotations.Expose;

/**
 * Request to get details for a profile's page
 *
 * @author Mitch Hudson
 */
public class UpdateProfileRequest extends PatchRequest {
    /**
     * New bio for the profile
     */
    @Expose
    private final String newBio;

    /**
     * Public constructor
     *
     * @param token authentication token
     */
    public UpdateProfileRequest(String token, String newBio) {
        super(UPDATE_PROFILE_URL + token);
        this.newBio = newBio;
    }

    /**
     * Getter for the new bio
     * @return new bio for the profile
     */
    public String getNewBio() {
        return newBio;
    }
}
