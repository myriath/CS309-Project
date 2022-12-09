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
    @ManyToOne
    @JoinColumn(name = "username")
    @Expose
    private User user;
    @Expose
    private String rname;
    @Expose
    private String description;
    @Expose
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "rid", nullable = false)
    private Collection<Instruction> instructions;
    @Expose
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "rid", nullable = false)
    private Collection<Ingredient> ingredients;
    @Expose
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="rid", nullable = false)
    private Collection<Comment> comments;

    public Recipe() {}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    @Override
    public String toString() {
        return "Recipe{" +
                "rid=" + rid +
                ", user=" + user +
                ", rname='" + rname + '\'' +
                ", description='" + description + '\'' +
                ", instructions=" + instructions +
                ", ingredients=" + ingredients +
                ", comments=" + comments +
                '}';
    }
}
