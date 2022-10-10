package com.example.cs309android.models.gson.request.shopping;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.gson.GetRequest;
import com.example.cs309android.util.Constants;

/**
 * Request for getting a user's shopping list
 *
 * @author Mitch Hudson
 */
public class GetListRequest extends GetRequest {
    /**
     * Authentication token
     */
    private final String token;

    /**
     * Public constructor
     *
     * @param token Authentication token
     */
    public GetListRequest(String token) {
        this.token = token;
    }

    /**
     * Getter for the parameterized URL
     *
     * @return parameterized URL
     */
    @Override
    public String getURL() {
        return new ParameterizedRequestURL(Constants.GET_SHOPPING_URL + "/" + token).toString();
    }
}
