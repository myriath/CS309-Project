package com.requests.backend.models;

import javax.persistence.*;

@Entity
@Table(name="recipe_ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ingredient_id;

    private int food_id;
    private boolean is_custom;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "food_id", referencedColumnName = "id"),
            @JoinColumn(name = "is_custom", referencedColumnName = "is_custom")
    })
    private SimpleFoodItem food;

    private int rid;
    private double quantity;
    private String unit;

    public Ingredient() {}

    public int getIngredient_id() {
        return ingredient_id;
    }

    public void setIngredient_id(int ingredient_id) {
        this.ingredient_id = ingredient_id;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public SimpleFoodItem getFood() {
        return food;
    }

    public void setFood(SimpleFoodItem food) {
        this.food = food;
    }
}
