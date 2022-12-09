package com.requests.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@IdClass(Vote.VotePK.class)
@Table(name = "votes")
public class Vote {
    @Id
    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "cid")
    private Comment comment;

    @Id
    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "username")
    private User user;

    /**
     * True = +1
     * False = -1
     */
    @Column(name = "rating")
    @Expose
    private boolean rating;

    public Vote() {
    }

    public Vote(Comment comment, User user, boolean rating) {
        this.comment = comment;
        this.user = user;
        this.rating = rating;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean getRating() {
        return rating;
    }

    public void setRating(boolean rating) {
        this.rating = rating;
    }

    public static class VotePK implements Serializable {
        protected User user;
        protected Comment comment;

        public VotePK(User user, Comment comment) {
            this.user = user;
            this.comment = comment;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            VotePK votePK = (VotePK) o;
            return Objects.equals(user, votePK.user) && Objects.equals(comment, votePK.comment);
        }

        @Override
        public int hashCode() {
            return Objects.hash(user, comment);
        }
    }
}
