package com.example.cs309android.models.gson.response.shopping;

import com.example.cs309android.models.gson.models.SimpleFoodItem;
import com.google.gson.annotations.Expose;

/**
 * GSON model for the /shopping/get endpoint
 *
 * @author Mitch Hudson
 */
public class GetListResponse {
    /**
     * Shopping list array from JSON
     */
    @Expose
    private final SimpleFoodItem[] shopping_list;

    /**
     * Public constructor
     *
     * @param shopping_list Shopping list array
     */
    public GetListResponse(SimpleFoodItem[] shopping_list) {
        this.shopping_list = shopping_list;
    }

    /**
     * Getter for the shopping list array
     *
     * @return Array of simple food items.
     */
    public SimpleFoodItem[] getShoppingList() {
        return shopping_list;
    }
}
