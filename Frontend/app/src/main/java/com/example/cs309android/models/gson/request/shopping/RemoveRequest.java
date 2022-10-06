package com.example.cs309android.models.gson.request.shopping;

import com.example.cs309android.models.gson.DeleteRequest;
import com.example.cs309android.models.gson.PutRequest;
import com.example.cs309android.models.gson.models.AuthModel;
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
     * Authentication model of the user
     */
    @Expose
    private final AuthModel auth;

    /**
     * Public constructor
     *
     * @param itemName Name of the item to remove
     * @param auth  Authentication model for the user
     */
    public RemoveRequest(String itemName, AuthModel auth) {
        super(Constants.REMOVE_SHOPPING_URL);
        this.itemName = itemName;
        this.auth = auth;
    }

    /**
     * Getter for the index
     * @return item index
     */
    public String getIndex() {
        return itemName;
    }

    /**
     * Getter for the authentication model
     *
     * @return Authentication model
     */
    public AuthModel getAuth() {
        return auth;
    }
}
