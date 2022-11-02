package com.requests.backend.models;

import javax.persistence.*;

@Entity
@Table(name = "custom_foods")
public class CustomFood {
    // CustomFoodItem(int customId, String name, int calories, int carbs, int protein, int fat)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int dbId;

    String name;

    double calories;

    double carbs;

    double protein;

    double fat;

    public CustomFood() {
    }

    public CustomFood(int dbId, String name, double calories, double carbs, double protein, double fat) {
        this.dbId = dbId;
        this.name = name;
        this.calories = calories;
        this.carbs = carbs;
        this.protein = protein;
        this.fat = fat;
    }

    public int getDbId() {
        return dbId;
    }

    public void setDbId(int dbId) {
        this.dbId = dbId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

}
