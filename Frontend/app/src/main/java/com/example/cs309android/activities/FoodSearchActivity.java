package com.example.cs309android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs309android.R;
import com.example.cs309android.models.FoodItem;

import java.util.ArrayList;

/**
 * Activity used to search for food items in the nutritionix database.
 *
 * @author Mitch Hudson
 */
public class FoodSearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    /**
     * List of existing items from whatever called this.
     * Completing a search adds that item to this list.
     */
    ArrayList<FoodItem> items;

    /**
     * Ran when the activity is created.
     *
     * @param savedInstanceState Saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_search);

        items = getIntent().getParcelableArrayListExtra("fooditems");
        if (items == null) {
            items = new ArrayList<>();
        }

        SearchView searchView = findViewById(R.id.search_bar);
        searchView.requestFocus();
        searchView.setOnQueryTextListener(this);
    }

    /**
     * Runs when the system back button is pressed or the app's back button is pressed.
     * Sends all selected items back to the activity that called this activity.
     */
    @Override
    public void onBackPressed() {
        System.out.println("back");
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra("fooditems", items);
        setResult(RESULT_OK, intent);
        finish();
        super.onBackPressed();
    }

    /**
     * Ran when the search is ran.
     * First, gets data based on the query from Nutritionix, then displays a list of the items.
     * Each item can be clicked on for more info which should open in a new fragment and then have an ok button.
     *
     * @param s Query text
     * @return true on successful query
     */
    @Override
    public boolean onQueryTextSubmit(String s) {
        // TODO: Should display search results. On item click, open detail view, then on select ok, return food item.
        items.add(new FoodItem(0, s));
        System.out.println(s);
        findViewById(R.id.search_bar).clearFocus();
        return true;
    }

    /**
     * Ran when the search query is changed. Shouldn't do anything to reduce api calls.
     *
     * @param s Query text.
     * @return false
     */
    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}