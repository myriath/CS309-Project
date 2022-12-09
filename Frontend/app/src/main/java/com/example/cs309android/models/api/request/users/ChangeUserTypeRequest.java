package com.example.cs309android.models.api.request.users;

import static com.example.cs309android.util.Constants.Urls.Users.UPDATE_USER_TYPE_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.PatchRequest;

/**
 * Changes a user's type (for use by admins)
 * TODO: test
 *
 * @author Mitch Hudson
 */
public class ChangeUserTypeRequest extends PatchRequest {
    /**
     * Public constructor
     *
     * @param username  Username of the account to change
     * @param authToken Auth token to authorize this request (must be admin)
     * @param newType   New user type value for the user
     */
    public ChangeUserTypeRequest(String username, String authToken, int newType) {
        super(new ParameterizedRequestURL(UPDATE_USER_TYPE_URL)
                .addPathVar(authToken)
                .addParam("username", username)
                .addParam("type", newType)
                .toString()
        );
    }
}
