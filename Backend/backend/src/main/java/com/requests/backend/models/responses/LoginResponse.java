package com.requests.backend.models.responses;

public class LoginResponse extends ResultResponse {
    private String username, pfp, banner;

    public LoginResponse() {
    }

    public LoginResponse(int responseCode) {
        super(responseCode);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPfp() {
        return pfp;
    }

    public void setPfp(String pfp) {
        this.pfp = pfp;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }
}
