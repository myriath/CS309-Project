package com.example.cs309android.models.USDA.queries;

import com.google.gson.annotations.Expose;

/**
 * FDC Search Result model
 * Returned by the FoodSearchCriteria request
 *
 * @author Mitch Hudson
 */
public class SearchResult {
    /**
     * Food search criteria
     * (The search query)
     */
    @Expose
    private final FoodSearchCriteria foodSearchCriteria;
    /**
     * Total number of matches
     */
    @Expose
    private final Integer totalHits;
    /**
     * Current page number
     */
    @Expose
    private final Integer currentPage;
    /**
     * Total number of pages
     */
    @Expose
    private final Integer totalPages;
    /**
     * Array of foods that the search found (on this page)
     */
    @Expose
    private final SearchResultFood[] foods;

    /**
     * Public constructor
     *
     * @param foodSearchCriteria search criterion
     * @param totalHits          total number of matches
     * @param currentPage        current page
     * @param totalPages         total pages
     * @param foods              food array on this page
     */
    public SearchResult(FoodSearchCriteria foodSearchCriteria, int totalHits, int currentPage, int totalPages, SearchResultFood[] foods) {
        this.foodSearchCriteria = foodSearchCriteria;
        this.totalHits = totalHits;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.foods = foods;
    }

    /**
     * Getter for the food search criteria
     *
     * @return food search criteria
     */
    public FoodSearchCriteria getFoodSearchCriteria() {
        return foodSearchCriteria;
    }

    /**
     * Getter for the total match count
     *
     * @return total match count
     */
    public int getTotalHits() {
        return totalHits;
    }

    /**
     * Getter for the current page
     *
     * @return current page #
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * Getter for the total page count
     *
     * @return total page count
     */
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * Getter for the results
     *
     * @return food array of results from the search
     */
    public SearchResultFood[] getFoods() {
        return foods;
    }
}
