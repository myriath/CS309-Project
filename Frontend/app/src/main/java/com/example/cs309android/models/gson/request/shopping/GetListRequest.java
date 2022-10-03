package com.example.cs309android.models.gson.request.shopping;

import com.example.cs309android.models.GetRequestURL;
import com.example.cs309android.models.gson.GetRequest;
import com.example.cs309android.models.gson.models.AuthModel;
import com.example.cs309android.util.Constants;

/**
 * Request for getting a user's shopping list
 *
 * @author Mitch Hudson
 */
public class GetListRequest extends GetRequest {
    /**
     * Authentication object for the user
     */
    private final AuthModel auth;

    /**
     * Public constructor
     *
     * @param auth Authentication object for the user (should almost always be MainActivity.AUTH_MODEL)
     */
    public GetListRequest(AuthModel auth) {
        this.auth = auth;
    }

    /**
     * Getter for the parameterized URL
     *
     * @return parameterized URL
     */
    @Override
    public String getURL() {
        return new GetRequestURL(Constants.GET_SHOPPING_URL)
                .addParam("username", auth.getUsername())
                .addParam("hash", auth.getHash())
                .toString();
    }
}
