package com.example.cs309android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.example.cs309android.R;
import com.example.cs309android.interfaces.CallbackFragment;
import com.example.cs309android.models.Nutritionix.FoodItem;
import com.example.cs309android.models.Nutritionix.queries.Search;
import com.example.cs309android.models.adapters.FoodSearchListAdapter;
import com.example.cs309android.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Activity used to search for food items in the nutritionix database.
 *
 * @author Mitch Hudson
 */
public class FoodSearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, CallbackFragment {
    /**
     * List of existing items from whatever called this.
     * Completing a search adds that item to this list.
     */
    private ArrayList<FoodItem> items;
    private ArrayList<FoodItem> searchResults;
    private ArrayList<FoodItem> searchResultsSelected;

    private static FoodSearchListAdapter adapter;

    public static final int CALLBACK_FOOD_DETAIL = 0;
    public static final int CALLBACK_SELECT = 1;
    public static final int CALLBACK_DESELECT = 2;

    public static final String PARCEL_INDEX = "fooditem_index";

    /**
     * Ran when the activity is created.
     *
     * @param savedInstanceState Saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_search);

        ListView listView = findViewById(R.id.search_results);
        listView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);

        items = getIntent().getParcelableArrayListExtra(MainActivity.PARCEL_FOODITEMS_LIST);
        if (items == null) {
            items = new ArrayList<>();
        }

        searchResults = new ArrayList<>();
        searchResultsSelected = new ArrayList<>();

        TextView empty = findViewById(R.id.empty_text);
        if (items.isEmpty()) {
            empty.setVisibility(View.VISIBLE);
        } else {
            empty.setVisibility(View.INVISIBLE);
        }

        adapter = new FoodSearchListAdapter(this, searchResults);
        listView.setAdapter(adapter);

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
        Intent intent = new Intent();
        items.addAll(searchResultsSelected);
        intent.putParcelableArrayListExtra(MainActivity.PARCEL_FOODITEMS_LIST, items);
        setResult(RESULT_OK, intent);
        finish();
        super.onBackPressed();
    }

    /**
     * Ran when the search is ran.
     * First, gets data based on the query from Nutritionix, then displays a list of the items.
     * Each item can be clicked on for more info which should open in a new fragment and then have an ok button.
     *
     * @param query Query text
     * @return true on successful query
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        findViewById(R.id.search_bar).clearFocus();

        Util.spin(this);
        Search search = new Search(query);
        try {
            search.search((Response.Listener<JSONObject>) response -> {
                try {
                    Search.Response parsed = new Search.Response(response);
                    searchResults.addAll(Arrays.asList(parsed.getBrandedFoods()));
                    searchResults.addAll(Arrays.asList(parsed.getCommonFoods()));

                    if (!searchResults.isEmpty()) {
                        ((TextView) findViewById(R.id.empty_text)).setVisibility(View.GONE);
                    } else {
                        ((TextView) findViewById(R.id.empty_text)).setVisibility(View.VISIBLE);
                    }
                    ((ListView) findViewById(R.id.search_results)).setAdapter(adapter);
                    Util.unSpin(this);
                } catch (JSONException e) {
                    Util.unSpin(this);
                    e.printStackTrace();
                }
            }, this);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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

    /**
     * Callback method used to control fragment activity
     * <p>
     * CALLBACK_FOOD_DETAIL (fooditem):
     * Opens the food detail window for the given food item
     *
     * @param op     Opcode to decide what to do
     * @param bundle Bundle with args
     */
    @Override
    public void callback(int op, Bundle bundle) {
        // TODO: Open details page
        // TODO: select and deselect methods
    }

    /**
     * Activity has no callbacks
     *
     * @param fragment Callback fragment.
     */
    @Override
    public void setCallbackFragment(CallbackFragment fragment) {
    }
}