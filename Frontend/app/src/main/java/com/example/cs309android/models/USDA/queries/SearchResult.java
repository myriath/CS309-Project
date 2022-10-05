package com.example.cs309android.models.USDA.queries;

import com.google.gson.annotations.Expose;

public class SearchResult {
    @Expose
    private final FoodSearchCriteria foodSearchCriteria;
    @Expose
    private final Integer totalHits;
    @Expose
    private final Integer currentPage;
    @Expose
    private final Integer totalPages;
    @Expose
    private final SearchResultFood[] foods;

    public SearchResult(FoodSearchCriteria foodSearchCriteria, int totalHits, int currentPage, int totalPages, SearchResultFood[] foods) {
        this.foodSearchCriteria = foodSearchCriteria;
        this.totalHits = totalHits;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.foods = foods;
    }

    public FoodSearchCriteria getFoodSearchCriteria() {
        return foodSearchCriteria;
    }

    public int getTotalHits() {
        return totalHits;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public SearchResultFood[] getFoods() {
        return foods;
    }
}
