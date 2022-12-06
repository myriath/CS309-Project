package com.example.cs309android.models.api.request.profile;

import static com.example.cs309android.util.Constants.Urls.Profile.UPDATE_PROFILE_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.PatchRequest;
import com.google.gson.annotations.Expose;

/**
 * Request to update a user's profile info
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
        super(new ParameterizedRequestURL(UPDATE_PROFILE_URL)
                .addPathVar(token)
                .toString());
        this.newBio = newBio;
    }

    /**
     * Getter for the new bio
     *
     * @return new bio for the profile
     */
    public String getNewBio() {
        return newBio;
    }
}
