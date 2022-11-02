package com.example.cs309android.models.gson.response.food;

import com.example.cs309android.models.gson.models.CustomFoodItem;
import com.example.cs309android.models.gson.response.GenericResponse;

/**
 * Response for the CustomFoodGet request
 *
 * @author Mitch Hudson
 */
public class CustomFoodGetResponse extends GenericResponse {
    /**
     * Item corresponding to the dbId
     */
    private final CustomFoodItem item;

    /**
     * Constructor to be used by GSON
     *
     * @param result Result code from the request
     * @param item Food item from the request
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
