package com.example.cs309android.models.api.request.shopping;

import static com.example.cs309android.util.Constants.Urls.Shopping.STRIKE_SHOPPING_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.PatchRequest;

/**
 * Toggles the strikeout for a given item in the shopping list
 *
 * @author Mitch Hudson
 */
public class StrikeRequest extends PatchRequest {
    /**
     * Public constructor
     *
     * @param id    ID of the row
     * @param token Token for authentication
     */
    public StrikeRequest(int id, String token) {
        super(new ParameterizedRequestURL(STRIKE_SHOPPING_URL)
                .addPathVar(token)
                .addPathVar(id)
                .toString());
    }
}
