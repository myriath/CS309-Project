package com.example.cs309android.models.api.response.social;

import com.example.cs309android.models.api.models.Comment;
import com.example.cs309android.models.api.response.GenericResponse;
import com.google.gson.annotations.Expose;

/**
 * Comments response class
 * Holds an array of comments
 *
 * @author Mitch Hudson
 */
public class CommentsResponse extends GenericResponse {
    /**
     * Comments from the request
     */
    @Expose
    private final Comment[] comments;

    /**
     * Public constructor
     *
     * @param comments comments from the request
     * @param result   result of the request
     */
    public CommentsResponse(Comment[] comments, int result) {
        super(result);
        this.comments = comments;
    }

    /**
     * Getter for the comments
     *
     * @return comments from the request
     */
    public Comment[] getComments() {
        return comments;
    }
}
