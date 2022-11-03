package com.requests.backend.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;

    private String username;

    private String body;

    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private int upvotes;

    @ManyToMany(targetEntity = User.class, cascade = { CascadeType.ALL })
    @JoinTable(name = "comment_votes",
            joinColumns = { @JoinColumn(name = "cid", referencedColumnName = "cid") },
            inverseJoinColumns = { @JoinColumn(name = "username", referencedColumnName = "username") })
    private Set<User> voters;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }
}
