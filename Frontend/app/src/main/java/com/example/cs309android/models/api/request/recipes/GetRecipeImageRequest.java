package com.example.cs309android.models.api.request.recipes;

import static com.example.cs309android.util.Constants.GET_RECIPE_IMAGE_URL;

import android.content.Context;
import android.widget.ImageView;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.ImageRequest;
import com.example.cs309android.util.PicassoSingleton;

/**
 * Get image request for recipes
 *
 * @author Travis Massner
 */
public class GetRecipeImageRequest extends ImageRequest {
    /**
     * Recipe id
     */
    private final String rid;

    /**
     * Constructor to be used by GSON
     *
     * @param rid rid for the recipe
     */
    public GetRecipeImageRequest(String rid) {
        this.rid = rid;
    }

    /**
     * Getter for the rid
     *
     * @return rid
     */
    public String getRid() {
        return rid;
    }

    /**
     * Getter for the request URL
     *
     * @return request URL
     */
    @Override
    public String getURL() {
        return new ParameterizedRequestURL(GET_RECIPE_IMAGE_URL)
                .addPathVar(rid)
                .toString();
    }

    public void request(ImageView view, Context context) {
        PicassoSingleton.getInstance(context).load(getURL()).into(view);
    }
}
