package com.example.cs309android.util;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Web socket notification class for sending and receiving notifications
 *
 * @author Mitch Hudson
 */
public class WSNotification {
    /**
     * Account this notification is coming from
     */
    @Expose
    private final String fromUsername;
    /**
     * Account this notification is going to
     */
    @Expose
    private final String toUsername;
    /**
     * Recipe ID of the recipe to go to
     */
    @Expose
    private final int rid;
    /**
     * Type of the notification to display
     */
    @Expose
    private final NotificationType type;

    /**
     * Possible notification types
     */
    public enum NotificationType {
        /**
         * New comment notification
         */
        @SerializedName("0")
        COMMENT,
        /**
         * Liked recipe notification
         */
        @SerializedName("1")
        LIKE,
        /**
         * New follower notification
         */
        @SerializedName("2")
        FOLLOWER,
        /**
         * New recipe notification
         */
        @SerializedName("3")
        RECIPE
    }

    /**
     * Public constructor
     *
     * @param fromUsername Username of the account this is coming from
     * @param toUsername   Username of the account this is going to
     * @param rid          Recipe id of the recipe to go to (may be unused)
     * @param type         Type of the notification to display
     */
    public WSNotification(String fromUsername, String toUsername, int rid, NotificationType type) {
        this.fromUsername = fromUsername;
        this.toUsername = toUsername;
        this.rid = rid;
        this.type = type;
    }

    /**
     * Getter for the fromUsername
     *
     * @return Username this notification is coming from
     */
    public String getFromUsername() {
        return fromUsername;
    }

    /**
     * Getter for the toUsername
     *
     * @return Username this notification is going to
     */
    public String getToUsername() {
        return toUsername;
    }

    /**
     * Getter for the rid
     *
     * @return Recipe id of the recipe to go to
     */
    public int getRid() {
        return rid;
    }

    /**
     * Getter for the notification type
     *
     * @return Type of the notification to display
     */
    public NotificationType getType() {
        return type;
    }
}
