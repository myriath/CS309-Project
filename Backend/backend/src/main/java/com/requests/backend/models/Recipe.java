package com.requests.backend.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="user_recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rid;
    private String username;
    private String rname;
    private String instructions;

    public String getUsername() {
        return username;
    }

    public void setUsername(String Username) {
        this.username = Username;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String Rname) {
        this.rname = Rname;
    }
    public String getInstructions() {
        return instructions;
    }

    public void setSteps(String instructions) {
        this.instructions = instructions;
    }

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="rid")
    private Set<Comment> comments;

}
