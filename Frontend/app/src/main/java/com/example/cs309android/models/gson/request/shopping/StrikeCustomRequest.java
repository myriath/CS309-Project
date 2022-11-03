package com.example.cs309android.models.gson.request.shopping;

import com.example.cs309android.models.gson.request.abstraction.PatchRequest;
import com.example.cs309android.util.Constants;
import com.google.gson.annotations.Expose;

/**
 * Strikeout request for the /shopping/strikeout endpoint
 * Uses db ids
 *
 * @author Mitch Hudson
 */
public class StrikeCustomRequest extends PatchRequest {
    /**
     * Index of the item to strikeout
     */
    @Expose
    private final int dbId;

    /**
     * Public constructor
     *
     * @param dbId ID of the item
     * @param token Token for authentication
     */
    public StrikeCustomRequest(int dbId, String token) {
        super(Constants.STRIKE_SHOPPING_DB_URL + token);
        this.dbId = dbId;
    }

    /**
     * Getter for the id
     *
     * @return id of the strikeout item.
     */
    public int getDbId() {
        return dbId;
    }
}
