package com.example.cs309android.models.api.response.recipes;

import com.example.cs309android.models.api.response.GenericResponse;
import com.google.gson.annotations.Expose;

/**
 * Response object for the add recipe request
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
     * @param rid recipe id
     */
    public AddRecipeResponse(int rid, int result) {
        super(result);
        this.rid = rid;
    }

    /**
     * Getter for the recipe id
     * @return recipe id
     */
    public int getRid() {
        return rid;
    }
}
