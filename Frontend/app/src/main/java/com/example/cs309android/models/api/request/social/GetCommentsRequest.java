package com.example.cs309android.models.api.request.social;

import static com.example.cs309android.util.Constants.Urls.Social.GET_COMMENTS_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.GetRequest;

/**
 * Adds a comment to a recipe
 *
 * @author Mitch Hudson
 */
public class GetCommentsRequest extends GetRequest {
    /**
     * Recipe id to get comments from
     */
    private final int rid;

    /**
     * Public constructor
     *
     * @param rid Recipe id for the recipe to get comment from
     */
    public GetCommentsRequest(int rid) {
        this.rid = rid;
    }

    /**
     * Getter for the recipe id
     *
     * @return recipe id to get comments from
     */
    public int getRid() {
        return rid;
    }

    @Override
    public String getURL() {
        return new ParameterizedRequestURL(GET_COMMENTS_URL)
                .addPathVar(rid)
                .toString();
    }
}
