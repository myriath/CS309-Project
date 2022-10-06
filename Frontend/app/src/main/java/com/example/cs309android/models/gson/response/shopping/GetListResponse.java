package com.example.cs309android.models.gson.response.shopping;

import com.example.cs309android.models.gson.models.SimpleFoodItem;
import com.example.cs309android.models.gson.response.GenericResponse;
import com.google.gson.annotations.Expose;

/**
 * GSON model for the /shopping/get endpoint
 *
 * @author Mitch Hudson
 */
public class GetListResponse extends GenericResponse {
    /**
     * Shopping list array from JSON
     */
    @Expose
    private final SimpleFoodItem[] shoppingList;

    /**
     * Public constructor
     *
     * @param result        Result code from the request
     * @param shoppingList Shopping list array
     */
    public GetListResponse(int result, SimpleFoodItem[] shoppingList) {
        super(result);
        this.shoppingList = shoppingList;
    }

    /**
     * Getter for the shopping list array
     *
     * @return Array of simple food items.
     */
    public SimpleFoodItem[] getShoppingList() {
        return shoppingList;
    }
}
