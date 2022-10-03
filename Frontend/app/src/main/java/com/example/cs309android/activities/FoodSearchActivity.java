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
import com.example.cs309android.models.USDA.queries.FoodSearchCriteria;
import com.example.cs309android.models.USDA.queries.SearchResult;
import com.example.cs309android.models.USDA.queries.SearchResultFood;
import com.example.cs309android.models.adapters.FoodSearchListAdapter;
import com.example.cs309android.models.gson.models.SimpleFoodItem;
import com.example.cs309android.models.gson.request.shopping.AddRequest;
import com.example.cs309android.models.gson.response.GenericResponse;
import com.example.cs309android.util.Toaster;
import com.example.cs309android.util.Util;

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

    /**
     * Adapter for the search results
     */
    private static FoodSearchListAdapter adapter;

    /**
     * Launches the food details activity for a food item clicked in the search results
     */
    private ActivityResultLauncher<Intent> foodDetailsLauncher;

    /**
     * Callback codes used by children to tell this fragment what to do
     */
    public static final int CALLBACK_FOOD_DETAIL = 0;
    public static final int CALLBACK_CLOSE_DETAIL = 1;
    public static final int CALLBACK_SELECT = 2;

    /**
     * Various intents tell the app what to do when certain things are done.
     */
    private int intentCode;
    public static final int INTENT_NONE = -1;
    public static final int INTENT_SHOPPING_LIST = 0;

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

        intentCode = getIntent().getIntExtra(MainActivity.PARCEL_INTENT_CODE, INTENT_NONE);

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
        new FoodSearchCriteria(query, Constants.DataType.FOUNDATION).request(response -> {
            SearchResult result = Util.objFromJson(response, SearchResult.class);

            searchResults.clear();
            for (SearchResultFood food : result.getFoods()) {
                searchResults.add(new SimpleFoodItem(food.getFdcId(), food.getDescription()));
            }

            new FoodSearchCriteria(query, Constants.DataType.BRANDED).request(response1 -> {
                SearchResult result1 = Util.objFromJson(response1, SearchResult.class);

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
            }, FoodSearchActivity.this);
        }, FoodSearchActivity.this);
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
                intent.putExtra(FoodDetailsActivity.PARCEL_BUTTON_CONTROL, FoodDetailsActivity.CONTROL_ADD);
                foodDetailsLauncher.launch(intent);
                break;
            }
            case (CALLBACK_SELECT): {
                SimpleFoodItem item = bundle.getParcelable(MainActivity.PARCEL_FOODITEM);
                switch (intentCode) {
                    case INTENT_SHOPPING_LIST: {
                        new AddRequest(item, MainActivity.AUTH_MODEL).request(response -> {
                            GenericResponse genericResponse = Util.objFromJson(response, GenericResponse.class);
                            if (genericResponse.getResult() == com.example.cs309android.util.Constants.RESULT_OK) {
                                items.add(bundle.getParcelable(MainActivity.PARCEL_FOODITEM));
                                Toaster.toastShort("Added", this);
                            } else {
                                Toaster.toastShort("Error", this);
                            }
                        }, FoodSearchActivity.this);
                        break;
                    }
                }
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