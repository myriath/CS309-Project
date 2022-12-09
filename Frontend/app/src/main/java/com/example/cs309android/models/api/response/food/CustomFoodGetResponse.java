package com.example.cs309android.models.api.response.food;

import com.example.cs309android.models.api.models.CustomFoodItem;
import com.example.cs309android.models.api.response.GenericResponse;
import com.google.gson.annotations.Expose;

/**
 * Response object for the custom food get request
 *
 * @author Mitch Hudson
 */
public class CustomFoodGetResponse extends GenericResponse {
    /**
     * Item corresponding to the dbId
     */
    @Expose
    private final CustomFoodItem item;

    /**
     * Public constructor
     *
     * @param result Result code from the request
     * @param item   Food item from the request
     */
    public CustomFoodGetResponse(int result, CustomFoodItem item) {
        super(result);
        this.item = item;
    }

    /**
     * Getter for the custom food item
     *
     * @return Item from the result
     */
    public CustomFoodItem getItem() {
        return item;
    }
}
