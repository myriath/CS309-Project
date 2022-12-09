package com.example.cs309android.models.api.response.shopping;

import com.example.cs309android.models.api.models.ShoppingList;
import com.example.cs309android.models.api.response.GenericResponse;
import com.google.gson.annotations.Expose;

/**
 * Response object for the get shopping list request
 *
 * @author Mitch Hudson
 */
public class GetListResponse extends GenericResponse {
    /**
     * Shopping list array from JSON
     */
    @Expose
    private final ShoppingList[] shoppingList;

    /**
     * Public constructor
     *
     * @param result       Result code from the request
     * @param shoppingList Shopping list array
     */
    public GetListResponse(int result, ShoppingList[] shoppingList) {
        super(result);
        this.shoppingList = shoppingList;
    }

    /**
     * Getter for the shopping list array
     *
     * @return Array of simple food items.
     */
    public ShoppingList[] getShoppingList() {
        return shoppingList;
    }
}
