package com.example.cs309android.models.api.request.shopping;

import static com.example.cs309android.util.Constants.GET_SHOPPING_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.GetRequest;

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
        return new ParameterizedRequestURL(GET_SHOPPING_URL)
                .addPathVar(token)
                .toString();
    }
}
