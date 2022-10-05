package com.example.cs309android.models.gson.request.shopping;

import com.example.cs309android.models.gson.PatchRequest;
import com.example.cs309android.models.gson.models.AuthModel;
import com.example.cs309android.util.Constants;
import com.google.gson.annotations.Expose;

/**
 * Strikeout request for the /shopping/strikeout endpoint
 *
 * @author Mitch Hudson
 */
public class StrikeRequest extends PatchRequest {
    /**
     * Index of the item to strikeout
     */
    @Expose
    private final String itemName;
    /**
     * Authentication model for the user
     */
    @Expose
    private final AuthModel auth;

    /**
     * Public constructor
     *
     * @param itemName Name of the item
     * @param auth  Authentication model for the user (almost always MainActivity.AUTH_MODEL)
     */
    public StrikeRequest(String itemName, AuthModel auth) {
        super(Constants.STRIKE_SHOPPING_URL);
        this.itemName = itemName;
        this.auth = auth;
    }

    /**
     * Getter for the index
     *
     * @return Index of the strikeout item.
     */
    public String getItemName() {
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
