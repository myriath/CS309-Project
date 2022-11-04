package com.example.cs309android.models.gson.request.shopping;

import com.example.cs309android.models.gson.request.abstraction.PutRequest;
import com.example.cs309android.util.Constants;
import com.google.gson.annotations.Expose;

/**
 * Removes the given item from the shopping list on the DB
 *
 * @author Mitch Hudson
 */
public class ShoppingRemoveRequest extends PutRequest {
    /**
     * Id of the item to remove
     */
    @Expose
    private final int id;
    /**
     * True if the item is a custom item
     */
    @Expose
    private final boolean isCustom;

    /**
     * Public constructor
     *
     * @param id       Id of the item to remove
     * @param isCustom True if the item is custom
     * @param token    Token for authentication
     */
    public ShoppingRemoveRequest(int id, boolean isCustom, String token) {
        super(Constants.REMOVE_SHOPPING_URL + token);
        this.id = id;
        this.isCustom = isCustom;
    }

    /**
     * Getter for the fdcId
     *
     * @return item id
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for the custom bool
     *
     * @return True if the item to remove is custom
     */
    public boolean isCustom() {
        return isCustom;
    }
}
