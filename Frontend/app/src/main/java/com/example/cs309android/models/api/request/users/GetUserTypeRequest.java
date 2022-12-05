package com.example.cs309android.models.api.request.users;

import static com.example.cs309android.util.Constants.Urls.Users.GET_USER_TYPE_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.GetRequest;

/**
 * Get a user's type
 *
 * @author Mitch Hudson
 */
public class GetUserTypeRequest extends GetRequest {
    /**
     * Username of the account
     */
    private final String username;

    /**
     * Public constructor
     *
     * @param username username of the user to get the type of
     */
    public GetUserTypeRequest(String username) {
        this.username = username;
    }

    /**
     * Gets the url for the request
     * @return url for the request
     */
    @Override
    public String getURL() {
        return new ParameterizedRequestURL(GET_USER_TYPE_URL)
                .addPathVar(username)
                .toString();
    }
}
