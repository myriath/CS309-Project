package com.example.cs309android.models.api.request.recipes;

import static com.example.cs309android.util.Constants.ADD_RECIPE_IMAGE_URL;

import android.graphics.Bitmap;

import com.android.volley.Request;
import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.ImageSendRequest;

/**
 * Add request model for the /recipe/add endpoint
 *
 * @author Travis Massner
 */
public class AddRecipeImageRequest extends ImageSendRequest {
    /**
     * Token for authentication
     */
    private final String token;
    /**
     * Recipe id
     */
    private final String rid;

    /**
     * Constructor to be used by GSON
     *
     * @param token        token to authenticate
     * @param rid          rid for the recipe
     */
    public AddRecipeImageRequest(Bitmap image, String token, String rid) {
        super(Request.Method.POST, image);
        this.token = token;
        this.rid = rid;
    }

    /**
     * Getter for the token
     * @return Authentication token
     */
    public String getToken() {
        return token;
    }

    /**
     * Getter for the rid
     * @return Rid
     */
    public String getRid() {
        return rid;
    }

    @Override
    public String getURL() {
        return new ParameterizedRequestURL(ADD_RECIPE_IMAGE_URL)
                .addPathVar(rid)
                .addPathVar(token)
                .toString();
    }
}
