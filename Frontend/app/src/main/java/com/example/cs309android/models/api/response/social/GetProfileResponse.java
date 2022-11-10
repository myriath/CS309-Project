package com.example.cs309android.models.api.response.social;

import com.example.cs309android.models.api.response.GenericResponse;
import com.google.gson.annotations.Expose;

/**
 * Response class for the /users/validateLogin endpoint
 *
 * @author Mitch Hudson
 */
public class GetProfileResponse extends GenericResponse {
    /**
     * Follower count
     */
    @Expose
    private final int followers;
    /**
     * Number of account followers
     */
    @Expose
    private final int following;
    /**
     * Biography text
     */
    @Expose
    private final String bio;

    /**
     * Public constructor
     *
     * @param result    request result code (OK for success, ERROR for fail)
     * @param followers follower count
     * @param following number of users this user is following
     * @param bio       biography text
     */
    public GetProfileResponse(int result, int followers, int following, String bio) {
        super(result);
        this.followers = followers;
        this.following = following;
        this.bio = bio;
    }

    /**
     * Gets the follower count
     *
     * @return follower count
     */
    public int getFollowers() {
        return followers;
    }

    /**
     * Gets the number of accounts being followed
     *
     * @return following count
     */
    public int getFollowing() {
        return following;
    }

    /**
     * Gets the bio
     *
     * @return biography text
     */
    public String getBio() {
        return bio;
    }
}
