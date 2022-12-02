package com.requests.backend.models;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="user_recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private int rid;
    @Expose
    private String username;
    @Expose
    private String rname;
    @Expose
    private String description;
    @Expose
    @OneToMany
    @JoinColumn(name = "rid", nullable = false)
    private Collection<Instruction> instructions;
    @Expose
    @OneToMany
    @JoinColumn(name = "rid", nullable = false)
    private Collection<Ingredient> ingredients;
    @Expose
    @OneToMany
    @JoinColumn(name="rid", nullable = false)
    private Collection<Comment> comments;

    public Recipe() {}

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

    public Collection<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(Collection<Instruction> instructions) {
        this.instructions = instructions;
    }

    public Collection<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Collection<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }
}
