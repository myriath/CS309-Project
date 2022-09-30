package com.requests.backend.models;

import javax.persistence.*;
import java.sql.Blob;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String username;
    private String p_hash;

    private String p_salt;

    private String user_type;

    private String email;

    private String fullName;

    private Integer age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPHash() {
        return p_hash;
    }

    public void setPHash(String p_hash) {
        this.p_hash = p_hash;
    }

    public String getPSalt() {
        return p_salt;
    }

    public void setPSalt(String p_salt) {
        this.p_salt = p_salt;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return fullName;
    }

    public void setName(String name) {
        this.fullName = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}