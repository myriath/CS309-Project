package com.requests.backend.models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@IdClass(Vote.VotePK.class)
@Table(name = "votes")
public class Vote {
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cid")
    private Comment comment;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username")
    private User user;

    /**
     * True = +1
     * False = -1
     */
    @Column(name = "rating")
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

    public static class VotePK {
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
