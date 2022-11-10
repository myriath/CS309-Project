package com.example.cs309android.models.api.response.users;

import com.example.cs309android.models.api.response.GenericResponse;
import com.google.gson.annotations.Expose;

/**
 * Response class for the /users/validateLogin endpoint
 *
 * @author Mitch Hudson
 */
public class LoginResponse extends GenericResponse {
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
     * Constructor to be used by GSON
     *
     * @param result Result code from the request
     */
    public LoginResponse(int result, String token, String banner, String pfp) {
        super(result);
        this.username = token;
        this.banner = banner;
        this.pfp = pfp;
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
}
