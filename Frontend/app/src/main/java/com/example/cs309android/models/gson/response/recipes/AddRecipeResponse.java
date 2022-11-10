package com.example.cs309android.models.gson.response.recipes;

import com.example.cs309android.models.gson.response.GenericResponse;
import com.google.gson.annotations.Expose;

/**
 * Response for the AddRecipeRequest
 *
 * @author Mitch Hudson
 */
public class AddRecipeResponse extends GenericResponse {
    /**
     * Recipe Item from JSON
     */
    @Expose
    private final int rid;

    /**
     * Public constructor
     *
     * @param rid recipe id
     */
    public AddRecipeResponse(int rid, int result) {
        super(result);
        this.rid = rid;
    }

    /**
     * Getter for the recipe id
     *
     * @return recipe id
     */
    public int getRid() {
        return rid;
    }
}
