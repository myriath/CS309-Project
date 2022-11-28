package com.example.cs309android.models.api.models;

import com.google.gson.annotations.Expose;

/**
 * Used to hold data for a comment.
 *
 * @author Mitch Hudson
 */
public class Comment {
    /**
     * Username of the account that made the comment
     */
    @Expose
    private final String username;
    /**
     * Comment text
     */
    @Expose
    private final String comment;
    /**
     * Whether or not to show the full text
     */
    private boolean showFull = false;

    /**
     * Public constructor
     *
     * @param username username of the comment creator
     * @param comment  comment text
     */
    public Comment(String username, String comment) {
        this.username = username;
        this.comment = comment;
    }

    /**
     * Getter for the comment's creator's username
     *
     * @return creator's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter for the comment text
     *
     * @return comment text
     */
    public String getComment() {
        return comment;
    }

    /**
     * Getter for the showFull boolean
     *
     * @return True if the comment should display the full text
     */
    public boolean showingFull() {
        return showFull;
    }

    /**
     * Setter for the showFull boolean
     *
     * @param showFull True if the comment should display the full text
     */
    public void setShowFull(boolean showFull) {
        this.showFull = showFull;
    }
}
