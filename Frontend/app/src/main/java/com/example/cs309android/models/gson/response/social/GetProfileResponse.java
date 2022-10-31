package com.example.cs309android.models.gson.response.social;

import com.example.cs309android.models.gson.response.GenericResponse;
import com.google.gson.annotations.Expose;

/**
 * Response class for the /users/validateLogin endpoint
 *
 * @author Mitch Hudson
 */
public class GetProfileResponse extends GenericResponse {
    /**
     * Rotating token to be used instead of hash
     */
    @Expose
    private final String username;
    /**
     * Rotating token to be used instead of hash
     */
    @Expose
    private final String banner;
    /**
     * Rotating token to be used instead of hash
     */
    @Expose
    private final String pfp;
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
     * @param username  username of the profile
     * @param banner    banner image of the profile
     * @param pfp       profile picture
     * @param followers follower count
     * @param following number of users this user is following
     * @param bio       biography text
     * @param recipes   recipe array (most recent first)
     */
    public GetProfileResponse(int result, String username, String banner, String pfp, int followers, int following, String bio) {
        super(result);
        this.username = username;
        this.banner = banner;
        this.pfp = pfp;
        this.followers = followers;
        this.following = following;
        this.bio = bio;
    }

    /**
     * Username for reference
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Profile banner image as base64 encoded string
     *
     * @return banner image b64
     */
    public String getBanner() {
        return banner;
    }

    /**
     * Profile picture as base64 encoded string
     *
     * @return profile picture b64
     */
    public String getPfp() {
        return pfp;
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
