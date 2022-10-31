package com.example.cs309android.models.gson.request.profile;

import static com.example.cs309android.util.Constants.UPDATE_BANNER_URL;
import static com.example.cs309android.util.Constants.UPDATE_PROFILE_URL;

import android.graphics.Bitmap;

import com.example.cs309android.models.gson.request.abstraction.PatchImageRequest;

/**
 * Request to get details for a profile's page
 *
 * @author Mitch Hudson
 */
public class UpdateBannerImageRequest extends PatchImageRequest {
    /**
     * Public constructor
     *
     * @param token authentication token
     * @param bitmap Image to send
     */
    public UpdateBannerImageRequest(String token, Bitmap bitmap) {
        super(UPDATE_BANNER_URL + token, bitmap);
    }
}
