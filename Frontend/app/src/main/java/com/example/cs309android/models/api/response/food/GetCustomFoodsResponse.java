package com.example.cs309android.models.api.response.food;

import com.example.cs309android.models.api.models.CustomFoodItem;
import com.example.cs309android.models.api.response.GenericResponse;
import com.google.gson.annotations.Expose;

/**
 * Response for the GetCustomFoods request
 *
 * @author Mitch Hudson
 */
public class GetCustomFoodsResponse extends GenericResponse {
    /**
     * Array of food items matching the query
     */
    @Expose
    private final CustomFoodItem[] items;

    /**
     * Constructor to be used by GSON
     *
     * @param result Result code from the request
     * @param items Search result items
     */
    public GetCustomFoodsResponse(int result, CustomFoodItem[] items) {
        super(result);
        this.items = items;
    }

    /**
     * Gets the search results
     *
     * @return Search results
     */
    public CustomFoodItem[] getItems() {
        return items;
    }
}
