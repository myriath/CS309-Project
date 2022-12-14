package com.example.cs309android.models.api.response.food;

import com.example.cs309android.models.api.response.GenericResponse;
import com.google.gson.annotations.Expose;

/**
 * Response object for the FDC by UPC request
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
