package com.example.cs309android.models.api.request.shopping;

import com.example.cs309android.models.api.models.SimpleFoodItem;
import com.example.cs309android.models.api.request.abstraction.PostRequest;
import com.example.cs309android.util.Constants;
import com.google.gson.annotations.Expose;

/**
 * Add request model for the /shopping/add endpoint
 *
 * @author Mitch Hudson
 */
public class ShoppingAddRequest extends PostRequest {
    /**
     * Simple food item to add
     */
    @Expose
    private final SimpleFoodItem item;

    /**
     * Public constructor
     *
     * @param item Item to add to the list
     * @param token Authentication token
     */
    public ShoppingAddRequest(SimpleFoodItem item, String token) {
        super(Constants.ADD_SHOPPING_URL + token);
        this.item = item;
    }

    /**
     * Getter for the item
     *
     * @return item to add
     */
    public SimpleFoodItem getItem() {
        return item;
    }
}
