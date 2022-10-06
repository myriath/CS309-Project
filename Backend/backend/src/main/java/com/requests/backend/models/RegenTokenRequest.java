package com.requests.backend.models;

/**
 * Used when a token is being replaced.
 * Old token is used to authenticate the request (should match up in the database)
 * New token is the token to replace. If good, return RESULT_OK.
 * TODO: Only replace when token has expired!!!!!!!!! (todo is just to highlight this line)
 */
public class RegenTokenRequest {
    private String newToken;

    private String oldToken;

    public RegenTokenRequest(String newToken, String oldToken) {
        this.newToken = newToken;
        this.oldToken = oldToken;
    }

    public String getNewToken() {
        return newToken;
    }

    public String getPHash() {
        return oldToken;
    }
}
