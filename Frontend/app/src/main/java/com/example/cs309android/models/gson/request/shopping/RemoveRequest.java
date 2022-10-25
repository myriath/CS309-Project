package com.example.cs309android.models.gson.request.shopping;

import com.example.cs309android.models.gson.PutRequest;
import com.example.cs309android.util.Constants;
import com.google.gson.annotations.Expose;

/**
 * Removes the given index from the shopping list on the DB
 *
 * @author Mitch Hudson
 */
public class RemoveRequest extends PutRequest {
    /**
     * Index of the item to remove
     */
    @Expose
    private final String itemName;

    /**
     * Public constructor
     *
     * @param itemName Name of the item to remove
     * @param token  Token for authentication
     */
    public RemoveRequest(String itemName, String token) {
        super(Constants.REMOVE_SHOPPING_URL + token);
        this.itemName = itemName;
    }

    /**
     * Getter for the index
     * @return item index
     */
    public String getIndex() {
        return itemName;
    }
}
