package com.example.cs309android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cs309android.R;
import com.example.cs309android.interfaces.CallbackFragment;
import com.example.cs309android.models.USDA.Constants;
import com.example.cs309android.models.USDA.Queries;
import com.example.cs309android.models.USDA.custom.SimpleFoodItem;
import com.example.cs309android.models.USDA.queries.FoodSearchCriteria;
import com.example.cs309android.models.USDA.queries.SearchResult;
import com.example.cs309android.models.USDA.queries.SearchResultFood;
import com.example.cs309android.models.adapters.FoodSearchListAdapter;
import com.example.cs309android.util.Toaster;
import com.example.cs309android.util.Util;
import com.google.gson.GsonBuilder;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Objects;

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
    private ArrayList<SimpleFoodItem> items;
    private ArrayList<SimpleFoodItem> searchResults;

    private static FoodSearchListAdapter adapter;

    private ActivityResultLauncher<Intent> foodDetailsLauncher;

    public static final int CALLBACK_FOOD_DETAIL = 0;
    public static final int CALLBACK_CLOSE_DETAIL = 1;
    public static final int CALLBACK_SELECT = 2;

    /**
     * Ran when the activity is created.
     *
     * @param savedInstanceState Saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_search);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        ListView listView = findViewById(R.id.search_results);
        listView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);

        items = getIntent().getParcelableArrayListExtra(MainActivity.PARCEL_FOODITEMS_LIST);
        if (items == null) {
            items = new ArrayList<>();
        }

        searchResults = new ArrayList<>();

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
        ViewCompat.setOnApplyWindowInsetsListener(searchView, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            ((ViewGroup.MarginLayoutParams) v.getLayoutParams()).topMargin = insets.top;
            return WindowInsetsCompat.CONSUMED;
        });

        foodDetailsLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        items.add(Objects.requireNonNull(result.getData()).getParcelableExtra(MainActivity.PARCEL_FOODITEM));
                    }
                }
        );
    }

    /**
     * Runs when the system back button is pressed or the app's back button is pressed.
     * Sends all selected items back to the activity that called this activity.
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
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
        try {
            Queries.search(query, new FoodSearchCriteria(Constants.DataType.FOUNDATION), response -> {
                SearchResult result = new GsonBuilder().serializeNulls().create().fromJson(response.toString(), SearchResult.class);

                searchResults.clear();
                for (SearchResultFood food : result.getFoods()) {
                    searchResults.add(new SimpleFoodItem(food.getFdcId(), food.getDescription()));
                }

                try {
                    Queries.search(query, new FoodSearchCriteria(Constants.DataType.BRANDED), response1 -> {
                        SearchResult result1 = new GsonBuilder().serializeNulls().create().fromJson(response1.toString(), SearchResult.class);

                        for (SearchResultFood food : result1.getFoods()) {
                            searchResults.add(new SimpleFoodItem(food.getFdcId(), food.getDescription()));
                        }

                        if (!searchResults.isEmpty()) {
                            findViewById(R.id.empty_text).setVisibility(View.GONE);
                        } else {
                            findViewById(R.id.empty_text).setVisibility(View.VISIBLE);
                        }
                        ((ListView) findViewById(R.id.search_results)).setAdapter(adapter);
                        Util.unSpin(this);
                    }, this);
                } catch (UnsupportedEncodingException e) {
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
        switch (op) {
            case (CALLBACK_FOOD_DETAIL): {
                Intent intent = new Intent(this, FoodDetailsActivity.class);
                intent.putExtra(MainActivity.PARCEL_FOODITEM, (SimpleFoodItem) bundle.getParcelable(MainActivity.PARCEL_FOODITEM));
                foodDetailsLauncher.launch(intent);
                break;
            }
            case (CALLBACK_SELECT): {
                items.add(bundle.getParcelable(MainActivity.PARCEL_FOODITEM));
                Toaster.toastShort("Added", this);
            }
            case (CALLBACK_CLOSE_DETAIL): {
                getSupportFragmentManager().popBackStack();
                break;
            }
        }
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