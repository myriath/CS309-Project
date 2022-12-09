package com.requests.backend.models.requests;

import com.google.gson.annotations.Expose;

public class SaltRequest {
    @Expose
    private String username;

    public SaltRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
