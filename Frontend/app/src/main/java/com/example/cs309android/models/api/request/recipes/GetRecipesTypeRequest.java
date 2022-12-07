package com.example.cs309android.models.api.request.recipes;

import static com.example.cs309android.util.Constants.GET_RECIPES_LIST_TYPE_URL;
import static com.example.cs309android.util.Constants.GET_RECIPES_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.GetRequest;
import com.google.gson.annotations.Expose;

public class GetRecipesTypeRequest extends GetRequest {

    /**
     * Type of recipe list to get
     */
    private final String recipeType;

    /**
     * Search string to search for (Only applicable for explore recipe page)
     */
    @Expose
    private final String searchString;

    /**
     * Constructor
     *
     * @param recipeType Type of recipes to get
     */
    public GetRecipesTypeRequest(String recipeType) {
        this.recipeType = recipeType;
        this.searchString = "";
    }

    /**
     * Constructor
     *
     * @param recipeType Type of recipes to get
     * @param searchString Search string to search for
     */
    public GetRecipesTypeRequest(String recipeType, String searchString) {
        this.recipeType = recipeType;
        this.searchString = searchString;
    }

    /**
     * Getter for the recipe type
     *
     * @return recipe type
     */
    public String getRecipeType() {
        return recipeType;
    }

    /**
     * Getter for the search string
     *
     * @return search string
     */
    public String getSearchString() {
        return searchString;
    }

    /**
     * Getter for the request URL
     *
     * @return request URL
     */
    @Override
    public String getURL() {
        return new ParameterizedRequestURL(GET_RECIPES_LIST_TYPE_URL)
                .addParam("recipeType", recipeType)
                .toString();
    }
}

