package com.example.cs309android.models.api.request.users;

import static com.example.cs309android.util.Constants.Urls.Users.DELETE_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.DeleteRequest;

/**
 * Deletes a user (for use by moderators / admins)
 * TODO: test
 *
 * @author Mitch Hudson
 */
public class DeleteUserRequest extends DeleteRequest {
    /**
     * Public constructor
     *
     * @param username  Username of the account to delete
     * @param authToken Auth token to authorize this request (must be admin / moderator)
     */
    public DeleteUserRequest(String username, String authToken) {
        super(new ParameterizedRequestURL(DELETE_URL)
                .addPathVar(authToken)
                .addParam("username", username)
                .toString()
        );
    }
}
