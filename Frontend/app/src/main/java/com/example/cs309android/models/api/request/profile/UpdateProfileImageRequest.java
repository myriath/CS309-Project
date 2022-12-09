package com.example.cs309android.models.api.request.profile;

import static com.example.cs309android.util.Constants.Urls.Profile.UPDATE_PFP_URL;

import android.graphics.Bitmap;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.PatchImageRequest;

/**
 * Request to update a user's profile picture
 *
 * @author Mitch Hudson
 */
public class UpdateProfileImageRequest extends PatchImageRequest {
    /**
     * Public constructor
     *
     * @param token  authentication token
     * @param bitmap Image to send
     */
    public UpdateProfileImageRequest(String token, String username, Bitmap bitmap) {
        super(new ParameterizedRequestURL(UPDATE_PFP_URL)
                        .addPathVar(token)
                        .addPathVar(username)
                        .toString(),
                bitmap);
    }
}
