package com.example.cs309android.models.api.request.abstraction;

/**
 * Interface for get requests
 * <p>
 * Get requests need a method for getting a parameterized URL
 *
 * @author Mitch Hudson
 */
public abstract class GetRequest extends Request {
    /**
     * Generates a new get request
     */
    public GetRequest() {
        super(com.android.volley.Request.Method.GET);
    }

    /**
     * Generates the parameterized URL
     *
     * @return parameterized URL
     */
    public abstract String getURL();

    /**
     * Get requests have no body
     *
     * @return null
     */
    @Override
    public String getBody() {
        return null;
    }
}
