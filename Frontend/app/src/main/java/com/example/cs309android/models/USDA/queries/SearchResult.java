package com.example.cs309android.models.USDA.queries;

public class SearchResult {
    private final FoodSearchCriteria foodSearchCriteria;
    private final Integer totalHits;
    private final Integer currentPage;
    private final Integer totalPages;
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
