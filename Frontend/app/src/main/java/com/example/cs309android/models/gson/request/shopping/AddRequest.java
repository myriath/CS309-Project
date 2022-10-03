package com.example.cs309android.models.gson.request.shopping;

import com.example.cs309android.models.gson.PostRequest;
import com.example.cs309android.models.gson.models.AuthModel;
import com.example.cs309android.models.gson.models.SimpleFoodItem;
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
     * Authentication model of the user
     */
    @Expose
    private final AuthModel auth;

    /**
     * Public constructor
     *
     * @param item Item to add to the list
     * @param auth Authentication model for the user
     */
    public AddRequest(SimpleFoodItem item, AuthModel auth) {
        super(Constants.ADD_SHOPPING_URL);
        this.item = item;
        this.auth = auth;
    }

    /**
     * Getter for the item
     *
     * @return item to add
     */
    public SimpleFoodItem getItem() {
        return item;
    }

    /**
     * Getter for the authentication model
     *
     * @return authentication model
     */
    public AuthModel getAuth() {
        return auth;
    }
}
