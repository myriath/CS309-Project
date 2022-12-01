package com.requests.backend.models;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

@Entity
@Table(name="recipe_ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private int ingredient_id;

//    /**
//     * Composite key columns for the SimpleFoodItem
//     */
//    private int food_id;
//    private boolean is_custom;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "food_id", referencedColumnName = "id", nullable = false),
            @JoinColumn(name = "is_custom", referencedColumnName = "isCustom", nullable = false)
    })
    @Expose
    private SimpleFoodItem food;
    @Expose
    private double quantity;
    @Expose
    private String unit;

    public Ingredient() {}

    public int getIngredient_id() {
        return ingredient_id;
    }

    public void setIngredient_id(int ingredient_id) {
        this.ingredient_id = ingredient_id;
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

//    public int getFood_id() {
//        return food_id;
//    }
//
//    public void setFood_id(int food_id) {
//        this.food_id = food_id;
//    }
//
//    public boolean isIs_custom() {
//        return is_custom;
//    }
//
//    public void setIs_custom(boolean is_custom) {
//        this.is_custom = is_custom;
//    }
}
