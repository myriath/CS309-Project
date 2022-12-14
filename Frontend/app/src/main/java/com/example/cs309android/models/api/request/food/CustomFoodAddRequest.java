package com.example.cs309android.models.api.request.food;

import static com.example.cs309android.util.Constants.Urls.Food.ADD_FOOD_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.models.CustomFoodItem;
import com.example.cs309android.models.api.request.abstraction.PostRequest;
import com.google.gson.annotations.Expose;

/**
 * POST request for adding a custom food item to the database
 *
 * @author Mitch Hudson
 */
public class CustomFoodAddRequest extends PostRequest {
    /**
     * Item to add
     * dbId should be ITEM_ID_NULL to allow the server to allocate the id
     */
    @Expose
    private final CustomFoodItem item;

    /**
     * Public constructor
     *
     * @param item item to add
     */
    public CustomFoodAddRequest(CustomFoodItem item, String token) {
        super(new ParameterizedRequestURL(ADD_FOOD_URL)
                .addPathVar(token)
                .toString());
        this.item = item;
    }

    /**
     * Getter for the item to add
     *
     * @return item to add
     */
    public CustomFoodItem getItem() {
        return item;
    }
}
