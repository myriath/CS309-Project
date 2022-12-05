package com.example.cs309android.models.api.request.social;

import static com.example.cs309android.util.Constants.Urls.Social.EDIT_COMMENT_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.models.Comment;
import com.example.cs309android.models.api.request.abstraction.PatchRequest;
import com.google.gson.annotations.Expose;

/**
 * Edits an existing comment
 * TODO: test
 *
 * @author Mitch Hudson
 */
public class EditCommentRequest extends PatchRequest {
    /**
     * Comment to update (contains updated text)
     */
    @Expose
    private final String comment;

    /**
     * Public constructor
     *
     * @param comment   Comment with updated text
     * @param commentId Id of the comment to edit
     * @param token     Token for authentication
     */
    public EditCommentRequest(String comment, int commentId, String token) {
        super(new ParameterizedRequestURL(EDIT_COMMENT_URL)
                .addPathVar(token)
                .addPathVar(commentId)
                .toString());
        this.comment = comment;
    }

    /**
     * Getter for the updated comment
     *
     * @return updated comment
     */
    public String getComment() {
        return comment;
    }
}
