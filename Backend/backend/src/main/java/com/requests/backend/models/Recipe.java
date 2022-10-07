package com.requests.backend.models;

import javax.persistence.*;

@Entity
@Table(name="user_recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int rid;
    private String username;
    private String rname;
    private int serving_size;
    private String unit_type;
    private int calories;
    private int fat;
    private int protein;
    private int carbs;
    private String steps;

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

    public int getServing_size() {
        return serving_size;
    }

    public void setServings(String Servings) {
        this.serving_size = Integer.parseInt(Servings);
    }

    public String getUnit_type() {
        return unit_type;
    }

    public void setUnit_type(String Unit_type){
        this.unit_type = Unit_type;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(String Calories) {
        this.calories = Integer.parseInt(Calories);
    }

    public int getFat() {
        return fat;
    }

    public void setFat(String Fat) {
        this.fat = Integer.parseInt(Fat);
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(String Protein) {
        this.protein = Integer.parseInt(Protein);
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(String Carbs) {
        this.carbs = Integer.parseInt(Carbs);
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String Steps) {
        this.steps = Steps;
    }







}
