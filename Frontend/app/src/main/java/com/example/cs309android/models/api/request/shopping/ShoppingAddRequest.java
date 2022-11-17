package com.example.cs309android.models.api.request.shopping;

import static com.example.cs309android.util.Constants.ADD_SHOPPING_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.models.SimpleFoodItem;
import com.example.cs309android.models.api.request.abstraction.PostRequest;
import com.google.gson.annotations.Expose;

/**
 * Adds the given item to the shopping list
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
     * @param item  Item to add to the list
     * @param token Authentication token
     */
    public ShoppingAddRequest(SimpleFoodItem item, String token) {
        super(new ParameterizedRequestURL(ADD_SHOPPING_URL)
                .addPathVar(token)
                .toString());
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
