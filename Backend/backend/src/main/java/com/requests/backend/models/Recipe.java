package com.requests.backend.models;

import javax.persistence.*;

@Entity
@Table(name="user_recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rid;
    private String username;
    private String rname;
    private String instructions;

    private String image;

    public String getImage() {return image;}

    public void setImage(String image) {
        this.username = image;
    }

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







}
