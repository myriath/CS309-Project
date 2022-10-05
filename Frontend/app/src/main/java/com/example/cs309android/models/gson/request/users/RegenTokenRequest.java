package com.example.cs309android.models.gson.request.users;

import com.example.cs309android.models.gson.PutRequest;
import com.example.cs309android.util.Constants;
import com.google.gson.annotations.Expose;

/**
 * Gives the server a new token. This should happen weekly
 * to keep rotating passwords
 *
 * @author Mitch Hudson
 */
public class RegenTokenRequest extends PutRequest {
    /**
     * New token to replace the old token
     */
    @Expose
    private final String newToken;
    /**
     * Old token to authenticate the request
     */
    @Expose
    private final String oldToken;

    /**
     * Public constructor
     *
     * @param newToken New token to replace old token
     * @param newToken Old token for authenticating this request
     */
    public RegenTokenRequest(String newToken, String oldToken) {
        super(Constants.REGEN_TOKEN_URL);
        this.newToken = newToken;
        this.oldToken = oldToken;
    }

    /**
     * Getter for the new token
     *
     * @return Token to replace old token
     */
    public String getNewToken() {
        return newToken;
    }

    /**
     * Getter for the old token
     *
     * @return Old token used for authentication
     */
    public String getOldToken() {
        return oldToken;
    }
}
