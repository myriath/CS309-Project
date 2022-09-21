package com.requests.backend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Blob;

@Entity // This tells Hibernate to make a table out of this class
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String username;

    private Blob p_hash;

    private Blob p_salt;

    private String user_type;

    private String email;

    private String full_name;

    private Integer age;

    public String getUsername() {
        return username;
    }
}