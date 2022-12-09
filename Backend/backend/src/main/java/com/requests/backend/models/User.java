package com.requests.backend.models;

import com.google.gson.annotations.Expose;
import com.util.SkipSerialization;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="users")
public class User {
    @Id
    @Expose
    private String username;

    @JsonIgnore
    private String email;

    @JsonIgnore
    private String pHash;
    @JsonIgnore
    private String pSalt;
    @Expose
    private int userType;
    @Expose
    private String bio;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<Token> token;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "follows",
            joinColumns = @JoinColumn(name = "follower"),
            inverseJoinColumns = @JoinColumn(name = "following"))
    private Set<User> followers;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "follows",
            joinColumns = @JoinColumn(name = "following"),
            inverseJoinColumns = @JoinColumn(name = "follower"))
    private Set<User> following;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Recipe> recipes;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private Set<ShoppingList> shoppingLists;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @JsonIgnore
    public String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email;
    }
    @JsonIgnore
    public String getPHash() {
        return pHash;
    }

    public void setPHash(String pHash) {
        this.pHash = pHash;
    }
    @JsonIgnore
    public String getPSalt() {
        return pSalt;
    }

    public void setPSalt(String pSalt) { this.pSalt = pSalt;}

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
    @JsonIgnore
    public Set<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }
    @JsonIgnore
    public Set<User> getFollowing() {
        return following;
    }

    public void setFollowing(Set<User> following) {
        this.following = following;
    }

    public Set<ShoppingList> getShoppingLists() {
        return shoppingLists;
    }

    public void setShoppingLists(Set<ShoppingList> shoppingLists) {
        this.shoppingLists = shoppingLists;
    }

    public void addShoppingList(ShoppingList list) {
        this.shoppingLists.add(list);
    }

    public Collection<Token> getToken() {
        return token;
    }

    public void setToken(Collection<Token> token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", pHash='" + pHash + '\'' +
                ", pSalt='" + pSalt + '\'' +
                ", userType=" + userType +
                ", bio='" + bio + '\'' +
                ", followers=" + followers +
                '}';
    }
}