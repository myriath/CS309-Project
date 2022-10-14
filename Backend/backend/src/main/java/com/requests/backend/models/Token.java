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

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
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

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

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
