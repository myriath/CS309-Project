package com.example.cs309android.models.gson.response.food;

import com.example.cs309android.models.gson.response.GenericResponse;

/**
 * Response for the {@link com.example.cs309android.models.gson.request.food.CustomFoodAddRequest} request
 *
 * @author Mitch Hudson
 */
public class CustomFoodAddResponse extends GenericResponse {
    /**
     * New database id for the food item
     */
    private final int dbId;

    /**
     * Constructor to be used by GSON
     *
     * @param result Result code from the request
     */
    public CustomFoodAddResponse(int result, int dbId) {
        super(result);
        this.dbId = dbId;
    }

    /**
     * Getter for the database id
     *
     * @return Database id for the food item that was added
     */
    public int getDbId() {
        return dbId;
    }
}
