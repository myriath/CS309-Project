package com.requests.backend.models.requests;

import com.google.gson.annotations.Expose;

public class UpdateProfileRequest {
    @Expose
    private String newBio;

    public String getNewBio() {
        return newBio;
    }

    public void setNewBio(String newBio) {
        this.newBio = newBio;
    }
}
