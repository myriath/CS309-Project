package com.example.cs309android.models.gson.request.shopping;

import com.example.cs309android.models.gson.request.abstraction.PatchRequest;
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
    private final int fdcId;

    /**
     * Public constructor
     *
     * @param fdcId Name of the item
     * @param token Token for authentication
     */
    public StrikeRequest(int fdcId, String token) {
        super(Constants.STRIKE_SHOPPING_URL + token);
        this.fdcId = fdcId;
    }

    /**
     * Getter for the index
     *
     * @return Index of the strikeout item.
     */
    public int getFdcId() {
        return fdcId;
    }
}
