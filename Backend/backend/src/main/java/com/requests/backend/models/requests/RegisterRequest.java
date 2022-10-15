package com.requests.backend.models.requests;

public class RegisterRequest {
    private String username;

    private String email;

    private String pHash;

    private String pSalt;

    private String token;

    public RegisterRequest(String username, String email, String pHash, String pSalt, String token){
        this.username = username;
        this.email = email;
        this.pHash = pHash;
        this.pSalt = pSalt;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPHash() {
        return pHash;
    }

    public void setPHash(String pHash) {
        this.pHash = pHash;
    }

    public String getPSalt() {
        return pSalt;
    }

    public void setPSalt(String pSalt) {
        this.pSalt = pSalt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
