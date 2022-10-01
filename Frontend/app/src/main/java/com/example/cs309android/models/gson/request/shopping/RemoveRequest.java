package com.example.cs309android.models.gson.request.shopping;

import com.example.cs309android.models.gson.DeleteRequest;

/**
 * Removes the given index from the shopping list on the DB
 *
 * @author Mitch Hudson
 */
public class RemoveRequest extends DeleteRequest {
    /**
     * Index of the item to remove
     */
    private final int index;
    /**
     * Verifying token to authenticate the request
     */
    private final String token;

    /**
     * Public constructor
     *
     * @param index Index of item to remove
     * @param token Authentication token
     */
    public RemoveRequest(int index, String token) {
        this.index = index;
        this.token = token;
    }

    /**
     * Getter for the index
     *
     * @return item index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Getter for the token
     *
     * @return Authentication token
     */
    public String getToken() {
        return token;
    }
}
