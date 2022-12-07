package com.requests.backend.models.responses;

import com.google.gson.annotations.Expose;
import com.requests.backend.models.Comment;

public class GetCommentsResponse extends ResultResponse{
    @Expose
    private Comment[] comments;

    public Comment[] getComments() {
        return comments;
    }

    public void setComments(Comment[] comments) {
        this.comments = comments;
    }
}
