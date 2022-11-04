package com.example.cs309android.models.gson.response.food;

import com.example.cs309android.models.gson.response.GenericResponse;
import com.google.gson.annotations.Expose;

/**
 * Response for the {@link com.example.cs309android.models.gson.request.food.CustomFoodAddRequest} request
 *
 * @author Mitch Hudson
 */
public class FDCByUPCResponse extends GenericResponse {
    /**
     * New database id for the food item
     */
    @Expose
    private final int fdcId;

    /**
     * Constructor to be used by GSON
     *
     * @param result Result code from the request
     */
    public FDCByUPCResponse(int result, int fdcId) {
        super(result);
        this.fdcId = fdcId;
    }

    /**
     * Getter for the database id
     *
     * @return Database id for the food item that was added
     */
    public int getFdcId() {
        return fdcId;
    }
}
