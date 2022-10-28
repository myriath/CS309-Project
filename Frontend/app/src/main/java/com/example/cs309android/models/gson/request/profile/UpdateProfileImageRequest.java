package com.example.cs309android.models.gson.request.profile;

import static com.example.cs309android.util.Constants.UPDATE_PFP_URL;
import static com.example.cs309android.util.Constants.UPDATE_PROFILE_URL;

import android.graphics.Bitmap;

import com.example.cs309android.models.gson.request.abstraction.PatchImageRequest;
import com.example.cs309android.models.gson.request.abstraction.PatchRequest;
import com.google.gson.annotations.Expose;

/**
 * Request to get details for a profile's page
 *
 * @author Mitch Hudson
 */
public class UpdateProfileImageRequest extends PatchImageRequest {
    /**
     * Public constructor
     *
     * @param token authentication token
     * @param bitmap Image to send
     */
    public UpdateProfileImageRequest(String token, Bitmap bitmap) {
        super(UPDATE_PFP_URL + token, bitmap);
    }
}
