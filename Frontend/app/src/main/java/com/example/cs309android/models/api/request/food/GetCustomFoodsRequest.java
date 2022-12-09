package com.example.cs309android.models.api.request.food;

import static com.example.cs309android.util.Constants.Urls.Food.QUERY_FOOD_DB;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.GetRequest;
import com.google.gson.annotations.Expose;

/**
 * Request for searching the custom food db
 *
 * @author Mitch Hudson
 */
public class GetCustomFoodsRequest extends GetRequest {
    /**
     * Name of the item to search for
     */
    @Expose
    private final String query;

    /**
     * Public constructor
     *
     * @param query Query to find the items
     */
    public GetCustomFoodsRequest(String query) {
        this.query = query;
    }

    /**
     * Getter for the parameterized url
     *
     * @return Parameterized url
     */
    @Override
    public String getURL() {
        return new ParameterizedRequestURL(QUERY_FOOD_DB)
                .addParam("query", query)
                .toString();
    }
}
