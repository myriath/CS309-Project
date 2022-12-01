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
    private Date creationDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false)
    private User user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
