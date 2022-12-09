package com.example.cs309android.models.api.request.food;

import static com.example.cs309android.util.Constants.Urls.Food.DELETE_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.models.CustomFoodItem;
import com.example.cs309android.models.api.request.abstraction.PutRequest;
import com.google.gson.annotations.Expose;

/**
 * PUT request for removing a custom food item from the database
 * TODO: test
 *
 * @author Mitch Hudson
 */
public class DeleteCustomFoodRequest extends PutRequest {
    /**
     * Item to delete
     * dbId should be set so the server can delete the right item
     */
    @Expose
    private final CustomFoodItem item;

    /**
     * Public constructor
     *
     * @param item item to delete
     */
    public DeleteCustomFoodRequest(CustomFoodItem item, String token) {
        super(new ParameterizedRequestURL(DELETE_URL)
                .addPathVar(token)
                .toString());
        this.item = item;
    }

    /**
     * Getter for the item to delete
     *
     * @return item to delete
     */
    public CustomFoodItem getItem() {
        return item;
    }
}
