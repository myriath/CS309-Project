package com.example.cs309android.models.gson.request.shopping;

import com.example.cs309android.models.gson.models.SimpleFoodItem;
import com.example.cs309android.models.gson.request.abstraction.PostRequest;
import com.example.cs309android.util.Constants;
import com.google.gson.annotations.Expose;

/**
 * Add request model for the /shopping/add endpoint
 *
 * @author Mitch Hudson
 */
public class AddRequest extends PostRequest {
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
    public AddRequest(SimpleFoodItem item, String token) {
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
