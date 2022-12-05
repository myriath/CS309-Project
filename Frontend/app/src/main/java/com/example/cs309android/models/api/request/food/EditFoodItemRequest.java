package com.example.cs309android.models.api.request.food;

import static com.example.cs309android.util.Constants.Urls.Food.UPDATE_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.models.CustomFoodItem;
import com.example.cs309android.models.api.request.abstraction.PatchRequest;
import com.google.gson.annotations.Expose;

/**
 * PUT request for removing a custom food item from the database
 * TODO: test
 *
 * @author Mitch Hudson
 */
public class EditFoodItemRequest extends PatchRequest {
    /**
     * Item to edit
     * dbId should be set so the server can update the right item
     */
    @Expose
    private final CustomFoodItem item;

    /**
     * Public constructor
     *
     * @param item item to update
     */
    public EditFoodItemRequest(CustomFoodItem item, String token) {
        super(new ParameterizedRequestURL(UPDATE_URL)
                .addPathVar(token)
                .toString());
        this.item = item;
    }

    /**
     * Getter for the item to update
     *
     * @return item to update
     */
    public CustomFoodItem getItem() {
        return item;
    }
}
