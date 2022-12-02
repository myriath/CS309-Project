package com.requests.backend.models.requests;

import com.google.gson.annotations.Expose;

public class LoginRequest {
    @Expose
    private String username;

    @Expose
    private String pHash;

    public LoginRequest(String username, String pHash) {
        this.username = username;
        this.pHash = pHash;
    }

    public String getUsername() {
        return username;
    }

    public String getPHash() {
        return pHash;
    }
}
