package com.example.cs309android.models.api.request.profile;

import static com.example.cs309android.util.Constants.UPDATE_BANNER_URL;

import android.graphics.Bitmap;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.PatchImageRequest;

/**
 * Request to update a user's banner image
 *
 * @author Mitch Hudson
 */
public class UpdateBannerImageRequest extends PatchImageRequest {
    /**
     * Public constructor
     *
     * @param token  authentication token
     * @param bitmap Image to send
     */
    public UpdateBannerImageRequest(String token, Bitmap bitmap) {
        super(new ParameterizedRequestURL(UPDATE_BANNER_URL)
                        .addPathVar(token)
                        .toString(),
                bitmap);
    }
}
