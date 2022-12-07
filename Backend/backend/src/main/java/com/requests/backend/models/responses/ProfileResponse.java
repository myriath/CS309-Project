package com.requests.backend.models.responses;


import com.google.gson.annotations.Expose;

//Everything But Bio needs to be moved into socailController
public class ProfileResponse extends ResultResponse {
    @Expose
    private int followers;
    @Expose
    private int following;
    @Expose
    private String bio;

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
