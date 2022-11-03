package com.requests.backend.models;

import javax.persistence.*;

@Entity
@Table(name="Profile")
public class Profile {

    @Id
    private String username;

    private String Bio;


    public Profile() {
    }

    public Profile(String username, String Bio) {
        this.username = username;
        this.Bio = Bio;
    }

    public String getUserName() {return username;}

    public String getBio() {return Bio;}

}
