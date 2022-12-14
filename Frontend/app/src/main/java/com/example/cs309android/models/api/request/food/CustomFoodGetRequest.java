package com.example.cs309android.models.api.request.food;

import static com.example.cs309android.util.Constants.Urls.Food.GET_FOOD_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.GetRequest;

/**
 * Request to get a specific custom food item by ID
 *
 * @author Mitch Hudson
 */
public class CustomFoodGetRequest extends GetRequest {
    /**
     * Database id of the item to get
     */
    private final int dbId;

    /**
     * Public constructor
     *
     * @param dbId database ID to look up
     */
    public CustomFoodGetRequest(int dbId) {
        this.dbId = dbId;
    }

    /**
     * Getter for the database id.
     *
     * @return dbId
     */
    public int getDbId() {
        return dbId;
    }

    /**
     * Getter for the URL
     *
     * @return URL for the request
     */
    @Override
    public String getURL() {
        return new ParameterizedRequestURL(GET_FOOD_URL)
                .addPathVar(dbId)
                .toString();
    }
}
