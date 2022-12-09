package com.example.cs309android.models.api.request.social;

import static com.example.cs309android.util.Constants.Urls.Social.DELETE_COMMENT_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.DeleteRequest;

/**
 * Removes a comment from a recipe
 * TODO: test
 *
 * @author Mitch Hudson
 */
public class DeleteCommentRequest extends DeleteRequest {
    /**
     * Public constructor
     *
     * @param commentId ID of the comment to remove
     * @param token     Token for authentication
     */
    public DeleteCommentRequest(int commentId, String token) {
        super(new ParameterizedRequestURL(DELETE_COMMENT_URL)
                .addPathVar(token)
                .addPathVar(commentId)
                .toString());
    }
}
