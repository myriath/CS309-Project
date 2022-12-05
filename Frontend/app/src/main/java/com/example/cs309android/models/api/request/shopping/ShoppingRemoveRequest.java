package com.example.cs309android.models.api.request.shopping;

import static com.example.cs309android.util.Constants.Urls.Shopping.REMOVE_SHOPPING_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.PutRequest;

/**
 * Removes the given item from the shopping list on the DB
 *
 * @author Mitch Hudson
 */
public class ShoppingRemoveRequest extends PutRequest {
    /**
     * Public constructor
     *
     * @param id    Id of the row to remove
     * @param token Token for authentication
     */
    public ShoppingRemoveRequest(int id, String token) {
        super(new ParameterizedRequestURL(REMOVE_SHOPPING_URL)
                .addPathVar(token)
                .addPathVar(id)
                .toString());
    }
}
