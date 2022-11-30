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
    private String description;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "rid")
    @OrderColumn(name = "rid")
    private Instruction[] instructions;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "rid")
    @OrderColumn(name = "rid")
    private Ingredient[] ingredients;
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="rid")
    @OrderColumn(name = "rid")
    private Comment[] comments;

    public String getUsername() {
        return username;
    }

    public void setUsername(String Username) {
        this.username = Username;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public Instruction[] getInstructions() {
        return instructions;
    }

    public void setInstructions(Instruction[] instructions) {
        this.instructions = instructions;
    }

    public Ingredient[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredient[] ingredients) {
        this.ingredients = ingredients;
    }

    public Comment[] getComments() {
        return comments;
    }

    public void setComments(Comment[] comments) {
        this.comments = comments;
    }

}
