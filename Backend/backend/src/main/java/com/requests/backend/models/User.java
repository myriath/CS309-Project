package com.requests.backend.models;

import javax.persistence.*;
import java.sql.Blob;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="users")
public class User {
    @Id
    private String username;
    private String email;
    private String pHash;

    private String pSalt;

    private String userType;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email;
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

    public void setPSalt(String pSalt) { this.pSalt = pSalt;}

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}