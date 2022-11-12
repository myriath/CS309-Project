package com.example.cs309android.models.api.request.recipes;

import static com.example.cs309android.util.Constants.GET_RECIPE_IMAGE_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.GetImageRequest;

/**
 * Get image request for recipes
 *
 * @author Mitch Hudson
 */
public class GetRecipeImageRequest extends GetImageRequest {
    /**
     * Recipe id
     */
    private final String rid;

    /**
     * Constructor to be used by GSON
     *
     * @param rid          rid for the recipe
     */
    public GetRecipeImageRequest(String rid) {
        this.rid = rid;
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
        return new ParameterizedRequestURL(GET_RECIPE_IMAGE_URL)
                .addPathVar(rid)
                .toString();
    }
}
