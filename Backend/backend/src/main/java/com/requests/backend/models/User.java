package com.requests.backend.models;

import com.google.gson.annotations.Expose;
import com.util.SkipSerialization;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="users")
public class User {
    @Id
    @Expose
    private String username;

    private String email;

    private String pHash;

    private String pSalt;
    @Expose
    private int userType;
    @Expose
    private String bio;

    @ManyToMany(targetEntity = User.class)
    @JoinTable(name = "follows",
            joinColumns = { @JoinColumn(name = "follower", referencedColumnName = "username") },
            inverseJoinColumns = { @JoinColumn(name = "following", referencedColumnName = "username") })
    private Set<User> followers;

    @OneToMany
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

    public Set<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
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