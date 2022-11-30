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
    private final String body;
    /**
     * Comment id from the database
     */
    @Expose
    private final int id;
    /**
     * Whether or not to show the full text
     */
    private boolean showFull = false;

    /**
     * Public constructor
     *
     * @param username username of the comment creator
     * @param comment  comment text
     * @param id       comment id from the database
     */
    public Comment(String username, String comment, int id) {
        this.username = username;
        this.body = comment;
        this.id = id;
    }

    /**
     * Parcel constructor
     *
     * @param in Parcel to unpack
     */
    protected Comment(Parcel in) {
        username = in.readString();
        body = in.readString();
        id = in.readInt();
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
        dest.writeString(body);
        dest.writeInt(id);
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
    public String getBody() {
        return body;
    }

    /**
     * Getter for the comment id
     *
     * @return comment id
     */
    public int getId() {
        return id;
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
