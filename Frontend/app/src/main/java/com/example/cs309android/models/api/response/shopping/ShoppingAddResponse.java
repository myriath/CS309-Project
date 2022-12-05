package com.example.cs309android.models.api.response.shopping;

import com.example.cs309android.models.api.response.GenericResponse;

/**
 * Response for when an item is added to the shopping list
 *
 * @author Mitch Hudson
 */
public class ShoppingAddResponse extends GenericResponse {
    /**
     * Shopping list row
     */
    private final int id;

    /**
     * Public constructor
     *
     * @param result Result for the request
     * @param id     ID for the new shopping list row
     */
    public ShoppingAddResponse(int result, int id) {
        super(result);
        this.id = id;
    }

    /**
     * Getter for the id
     *
     * @return Shopping list row ID
     */
    public int getId() {
        return id;
    }
}
