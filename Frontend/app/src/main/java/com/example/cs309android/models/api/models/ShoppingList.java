package com.example.cs309android.models.api.models;

import com.google.gson.annotations.Expose;

/**
 * Shopping list class represents a stricken / not stricken food item
 *
 * @author Mitch Hudson
 */
public class ShoppingList {
    /**
     * ID for the list row in the database
     */
    @Expose
    private final int shoppingId;
    /**
     * Simple food item for the list
     */
    @Expose
    private final SimpleFoodItem foodItem;
    /**
     * True if the item is stricken out
     */
    @Expose
    private boolean stricken;

    /**
     * Public constructor
     *
     * @param shoppingId ID for the list row
     * @param foodItem   food item for the list
     * @param stricken   true if the item is stricken
     */
    public ShoppingList(int shoppingId, SimpleFoodItem foodItem, boolean stricken) {
        this.shoppingId = shoppingId;
        this.foodItem = foodItem;
        this.stricken = stricken;
    }

    /**
     * Getter for the shopping list id
     *
     * @return ID for the shopping list row
     */
    public int getShoppingId() {
        return shoppingId;
    }

    /**
     * Getter for the food item
     *
     * @return food item this list represents
     */
    public SimpleFoodItem getFoodItem() {
        return foodItem;
    }

    /**
     * Getter for the stricken boolean
     *
     * @return true if the item is stricken
     */
    public boolean isStricken() {
        return stricken;
    }

    /**
     * Setter for the stricken boolean
     *
     * @param stricken New stricken boolean
     */
    public void setStricken(boolean stricken) {
        this.stricken = stricken;
    }
}