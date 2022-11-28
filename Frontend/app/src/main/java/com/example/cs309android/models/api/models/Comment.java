package com.example.cs309android.models.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

/**
 * Used to hold data for a comment.
 *
 * @author Mitch Hudson
 */
public class Comment implements Parcelable {
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
     * Parcel constructor
     *
     * @param in Parcel to unpack
     */
    protected Comment(Parcel in) {
        username = in.readString();
        comment = in.readString();
        showFull = in.readByte() != 0;
    }

    /**
     * Writes this comment to a parcel
     *
     * @param dest  Destination parcel
     * @param flags Flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(comment);
        dest.writeByte((byte) (showFull ? 1 : 0));
    }

    /**
     * Unused, returns 0
     *
     * @return 0
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Creator for the parcelable implementation
     */
    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel in) {
            return new Comment(in);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };

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
