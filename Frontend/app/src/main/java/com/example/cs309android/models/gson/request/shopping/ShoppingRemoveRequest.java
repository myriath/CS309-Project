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
     * fdcId of the item to remove
     */
    @Expose
    private final int fdcId;

    /**
     * Public constructor
     *
     * @param fdcId Id of the item to remove
     * @param token  Token for authentication
     */
    public ShoppingRemoveRequest(int fdcId, String token) {
        super(Constants.REMOVE_SHOPPING_URL + token);
        this.fdcId = fdcId;
    }

    /**
     * Getter for the fdcId
     * @return item id
     */
    public int getFdcId() {
        return fdcId;
    }
}
