package com.example.cs309android.models.api.request.shopping;

import static com.example.cs309android.util.Constants.Urls.Shopping.REMOVE_SHOPPING_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.PutRequest;
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
        super(new ParameterizedRequestURL(REMOVE_SHOPPING_URL)
                .addPathVar(token)
                .toString());
        this.id = id;
        this.isCustom = isCustom;
    }

    /**
     * Getter for the id
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
