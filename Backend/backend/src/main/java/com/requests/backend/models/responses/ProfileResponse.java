package com.requests.backend.models.responses;

import com.requests.backend.models.Profile;

public class ProfileResponse {
    private int result;

    private Profile profile;

    public int getResult() {return result;}

    public void setResult(int result) {this.result = result;}

    public Profile getProfile() {return profile;}

    public void setProfile(Profile profile) {this.profile = profile;}

}
