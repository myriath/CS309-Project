package com.requests.backend.models;

public class RegisterRequest {
    private String username;

    private String pHash;

    private String pSalt;

    private String user_type;

    private String email;

    private String fullName;

    private Integer age;

    public RegisterRequest(String username, String pHash, String email, String fullName, int age){
        this.username = username;
        this.pHash = pHash;
        this.user_type = "User";
        this.email = email;
        this.fullName = fullName;
        this.age = age;
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

    public String getUserType() {
        return user_type;
    }

    public void setUserType(String user_type) {
        this.user_type = user_type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
