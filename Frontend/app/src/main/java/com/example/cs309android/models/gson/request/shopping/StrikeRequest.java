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
    private final int index;
    /**
     * Authentication model for the user
     */
    @Expose
    private final AuthModel auth;

    /**
     * Public constructor
     *
     * @param index Index of the item to strikeout
     * @param auth  Authentication model for the user (almost always MainActivity.AUTH_MODEL)
     */
    public StrikeRequest(int index, AuthModel auth) {
        super(Constants.STRIKE_SHOPPING_URL);
        this.index = index;
        this.auth = auth;
    }

    /**
     * Getter for the index
     *
     * @return Index of the strikeout item.
     */
    public int getIndex() {
        return index;
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
