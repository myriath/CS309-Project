package com.example.cs309android.models.api.request.social;

import static com.example.cs309android.util.Constants.Urls.Social.COMMENT_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.PostRequest;
import com.google.gson.annotations.Expose;

/**
 * Adds a comment to a recipe
 *
 * @author Mitch Hudson
 */
public class CommentRequest extends PostRequest {
    /**
     * Comment text to post
     */
    @Expose
    private final String comment;

    /**
     * Public constructor
     * @param comment Comment text to post
     * @param rid     Recipe id for the recipe to comment on
     * @param token   Token for authentication
     */
    public CommentRequest(String comment, int rid, String token) {
        super(new ParameterizedRequestURL(COMMENT_URL)
                .addPathVar(token)
                .addPathVar(rid)
                .toString());
        this.comment = comment;
    }

    /**
     * Getter for the comment text
     *
     * @return Comment text to post
     */
    public String getComment() {
        return comment;
    }
}
