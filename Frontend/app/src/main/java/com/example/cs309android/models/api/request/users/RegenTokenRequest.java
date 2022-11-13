package com.example.cs309android.models.api.request.users;

import static com.example.cs309android.util.Constants.REGEN_TOKEN_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.PutRequest;

/**
 * Gives the server a new token. This should happen daily
 * to keep rotating tokens
 *
 * @author Mitch Hudson
 */
public class RegenTokenRequest extends PutRequest {
    /**
     * Public constructor
     *
     * @param newToken New token to replace old token
     * @param oldToken Old token for authenticating this request
     */
    public RegenTokenRequest(String newToken, String oldToken) {
        super(new ParameterizedRequestURL(REGEN_TOKEN_URL)
                .addPathVar(oldToken)
                .addParam("newToken", newToken)
                .toString());
    }
}
