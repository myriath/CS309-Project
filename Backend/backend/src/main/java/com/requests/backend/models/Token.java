package com.requests.backend.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name="tokens")
public class Token {
    @Id
    private String token;

    private String username;

    private Date creationDate;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    @PrePersist
    public void setCreationDate() {  this.creationDate = new Date(System.currentTimeMillis()); }

    @PreUpdate
    public void setLastUpdate() {  this.creationDate = new Date(System.currentTimeMillis()); }

    public boolean isOutdated() {

        // The current date and time
        Date currentDate = new Date(System.currentTimeMillis());

        // Calculate the difference between the time of creation of the token and the
        // current date in days.
        long diffInMils = Math.abs(currentDate.getTime() - creationDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMils, TimeUnit.MILLISECONDS);

        return diff >= 1;
    }
}
