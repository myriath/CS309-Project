package com.example.cs309android.models.api.request.shopping;

import static com.example.cs309android.util.Constants.Urls.Shopping.STRIKE_SHOPPING_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.PatchRequest;
import com.google.gson.annotations.Expose;

/**
 * Toggles the strikeout for a given item in the shopping list
 *
 * @author Mitch Hudson
 */
public class StrikeRequest extends PatchRequest {
    /**
     * ID of the item to strikeout
     */
    @Expose
    private final int id;
    /**
     * True if the item to strike is custom
     */
    @Expose
    private final boolean isCustom;

    /**
     * Public constructor
     *
     * @param id       ID of the item
     * @param isCustom True if the item is custom
     * @param token    Token for authentication
     */
    public StrikeRequest(int id, boolean isCustom, String token) {
        super(new ParameterizedRequestURL(STRIKE_SHOPPING_URL)
                .addPathVar(token)
                .toString());
        this.id = id;
        this.isCustom = isCustom;
    }

    /**
     * Getter for the index
     *
     * @return Index of the strikeout item.
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for the custom bool
     *
     * @return True if the item to strike is custom, false if it is fdc
     */
    public boolean isCustom() {
        return isCustom;
    }
}
