package com.example.cs309android.activities;

import static com.example.cs309android.util.Constants.Callbacks.CALLBACK_FOOD_DETAIL;
import static com.example.cs309android.util.Constants.Callbacks.CALLBACK_IMAGE_URI;
import static com.example.cs309android.util.Constants.ITEM_ID_NULL;
import static com.example.cs309android.util.Constants.Intents.INTENT_NONE;
import static com.example.cs309android.util.Constants.Intents.INTENT_RECIPE_ADD;
import static com.example.cs309android.util.Constants.Intents.INTENT_SHOPPING_LIST;
import static com.example.cs309android.util.Constants.Parcels.PARCEL_BUTTON_CONTROL;
import static com.example.cs309android.util.Constants.Parcels.PARCEL_FOODITEM;
import static com.example.cs309android.util.Constants.Parcels.PARCEL_IMAGE_URI;
import static com.example.cs309android.util.Constants.Parcels.PARCEL_INTENT_CODE;

import android.content.Intent;
import android.databinding.tool.util.StringUtils;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
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

import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.activities.food.FoodDetailsActivity;
import com.example.cs309android.activities.food.NewFoodActivity;
import com.example.cs309android.fragments.ModalImageSelect;
import com.example.cs309android.interfaces.CallbackFragment;
import com.example.cs309android.models.USDA.Constants;
import com.example.cs309android.models.USDA.models.BrandedFoodItem;
import com.example.cs309android.models.USDA.queries.FoodSearchCriteria;
import com.example.cs309android.models.USDA.queries.FoodsCriteria;
import com.example.cs309android.models.USDA.queries.SearchResult;
import com.example.cs309android.models.USDA.queries.SearchResultFood;
import com.example.cs309android.models.adapters.FoodSearchListAdapter;
import com.example.cs309android.models.api.models.CustomFoodItem;
import com.example.cs309android.models.api.models.ShoppingList;
import com.example.cs309android.models.api.models.SimpleFoodItem;
import com.example.cs309android.models.api.request.food.FDCByUPCRequest;
import com.example.cs309android.models.api.request.food.GetCustomFoodsRequest;
import com.example.cs309android.models.api.request.shopping.ShoppingAddRequest;
import com.example.cs309android.models.api.response.food.FDCByUPCResponse;
import com.example.cs309android.models.api.response.food.GetCustomFoodsResponse;
import com.example.cs309android.models.api.response.shopping.ShoppingAddResponse;
import com.example.cs309android.util.BarcodeAnalyzer;
import com.example.cs309android.util.Toaster;
import com.example.cs309android.util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Activity used to search and display search results
 * Can be used for food items and recipes
 *
 * @author Mitch Hudson
 */
public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, CallbackFragment {
    /**
     * Adapter for the search results
     */
    private static FoodSearchListAdapter adapter;
    /**
     * List of search results from the search to display
     */
    private ArrayList<SimpleFoodItem> searchResults;
    /**
     * Launches the food details activity for a food item clicked in the search results
     */
    private ActivityResultLauncher<Intent> foodDetailsLauncher;
    /**
     * Launches the add custom food activity for the custom food item button in the search results
     */
    private ActivityResultLauncher<Intent> customDetailsLauncher;

    /**
     * Various intents tell the app what to do when certain things are done.
     */
    private int intentCode;

    /**
     * Runs when the activity is created.
     *
     * @param savedInstanceState Saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        intentCode = getIntent().getIntExtra(PARCEL_INTENT_CODE, INTENT_NONE);

        ListView listView = findViewById(R.id.search_results);
        listView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);

        searchResults = new ArrayList<>();

        TextView empty = findViewById(R.id.empty_text);
        if (intentCode == INTENT_SHOPPING_LIST) {
            if (MainActivity.getShoppingList().isEmpty()) {
                empty.setVisibility(View.VISIBLE);
            } else {
                empty.setVisibility(View.INVISIBLE);
            }
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

//        findViewById(R.id.scanButton).setOnClickListener(view -> imageChooser());

        foodDetailsLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent intent = result.getData();
                        runWithItem(Objects.requireNonNull(intent).getParcelableExtra(PARCEL_FOODITEM));
                    }
                }
        );

        customDetailsLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent intent = result.getData();
                        SimpleFoodItem item = Objects.requireNonNull(intent).getParcelableExtra(PARCEL_FOODITEM);
                        Util.spin(getWindow().getDecorView());
                        runWithItem(item);
                    }
                }
        );
    }

    /**
     * Does the current intent with the given simple food item
     *
     * @param item Item to work with
     */
    public void runWithItem(SimpleFoodItem item) {
        switch (intentCode) {
            case INTENT_SHOPPING_LIST: {
                Util.spin(getWindow().getDecorView());
                new ShoppingAddRequest(item, ((GlobalClass) getApplicationContext()).getToken()).unspinOnComplete(response -> {
                    ShoppingAddResponse addResponse = Util.objFromJson(response, ShoppingAddResponse.class);
                    System.out.println(response);
                    if (addResponse.getResult() == com.example.cs309android.util.Constants.Results.RESULT_OK) {
                        MainActivity.getShoppingList().add(new ShoppingList(addResponse.getId(), item, false));
                        Toaster.toastShort("Added", this);
                    } else {
                        Toaster.toastShort("Error", this);
                    }
                }, SearchActivity.this, getWindow().getDecorView());
                break;
            }
            case INTENT_RECIPE_ADD: {
                Intent intent1 = new Intent();
                intent1.putExtra(PARCEL_FOODITEM, item);
                setResult(RESULT_OK, intent1);
                finish();
                break;
            }
        }
    }

    /**
     * Shows the image source selection modal bottom sheet
     */
    public void imageChooser() {
        ModalImageSelect select = new ModalImageSelect();
        select.setCallbackFragment(this);
        select.show(getSupportFragmentManager(), ModalImageSelect.TAG);
    }

    /**
     * Runs when the system back button is pressed or the app's back button is pressed.
     * Sends all selected items back to the activity that called this activity.
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        switch (intentCode) {
            case INTENT_SHOPPING_LIST: {
                setResult(RESULT_OK, intent);
                break;
            }
            case INTENT_RECIPE_ADD: {
                setResult(RESULT_CANCELED);
                break;
            }
        }
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
        searchResults.clear();
        searchResults.add(new SimpleFoodItem(StringUtils.capitalize(query.toLowerCase()), "Add custom item"));
        new GetCustomFoodsRequest(query).request(response -> {
            GetCustomFoodsResponse result = Util.objFromJson(response, GetCustomFoodsResponse.class);

            if (result.getItems() != null) {
                for (CustomFoodItem food : result.getItems()) {
                    searchResults.add(new SimpleFoodItem(food.getDbId(), StringUtils.capitalize(food.getName().toLowerCase()), "User added", true));
                }
            }

            new FoodSearchCriteria(query, Constants.DataType.BRANDED).unspinOnComplete(response1 -> {
                SearchResult result1 = Util.objFromJson(response1, SearchResult.class);

                if (result1.getFoods() != null) {
                    for (SearchResultFood food : result1.getFoods()) {
                        searchResults.add(new SimpleFoodItem(food.getFdcId(), StringUtils.capitalize(food.getDescription().toLowerCase()), food.getBrandOwner(), false));
                    }
                }

                if (!searchResults.isEmpty()) {
                    findViewById(R.id.empty_text).setVisibility(View.GONE);
                } else {
                    findViewById(R.id.empty_text).setVisibility(View.VISIBLE);
                }
                ((ListView) findViewById(R.id.search_results)).setAdapter(adapter);
            }, error -> {
                if (!searchResults.isEmpty()) {
                    findViewById(R.id.empty_text).setVisibility(View.GONE);
                } else {
                    findViewById(R.id.empty_text).setVisibility(View.VISIBLE);
                }
                ((ListView) findViewById(R.id.search_results)).setAdapter(adapter);
            }, SearchActivity.this, getWindow().getDecorView());
        }, SearchActivity.this);
        return true;
    }

    /**
     * Runs when the search query is changed. Shouldn't do anything to reduce api calls.
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
     * CALLBACK_FOOD_DETAIL:
     * Opens the food detail window for the given food item
     * <p>
     * CALLBACK_IMAGE_URI:
     * Loads the barcode image and analyzes it for the UPC code
     * If valid, it will open the food details window for the given barcode
     *
     * @param op     Opcode to decide what to do
     * @param bundle Bundle with args
     */
    @Override
    public void callback(int op, Bundle bundle) {
        switch (op) {
            case (CALLBACK_FOOD_DETAIL): {
                SimpleFoodItem item = bundle.getParcelable(PARCEL_FOODITEM);
                if (item.getId() == ITEM_ID_NULL) {
                    Intent intent = new Intent(this, NewFoodActivity.class);
                    intent.putExtra(PARCEL_FOODITEM, item);
                    customDetailsLauncher.launch(intent);
                } else {
                    Intent intent = new Intent(this, FoodDetailsActivity.class);
                    intent.putExtra(PARCEL_FOODITEM, item);
                    intent.putExtra(PARCEL_BUTTON_CONTROL, FoodDetailsActivity.CONTROL_ADD);
                    foodDetailsLauncher.launch(intent);
                }
                break;
            }
            case (CALLBACK_IMAGE_URI): {
                try {
                    Uri imageUri = bundle.getParcelable(PARCEL_IMAGE_URI);
                    ImageDecoder.Source source = ImageDecoder.createSource(getContentResolver(), imageUri);
                    Bitmap bitmap = ImageDecoder.decodeBitmap(source);
                    BarcodeAnalyzer analyzer = new BarcodeAnalyzer(barcodes -> {
                        if (barcodes == null || barcodes.length == 0) {
                            Toaster.toastShort("Error reading barcode", this);
                            return;
                        }
                        new FDCByUPCRequest(barcodes[0]).request(response -> {
                            FDCByUPCResponse fdcResponse = Util.objFromJson(response, FDCByUPCResponse.class);
                            if (fdcResponse.getResult() == com.example.cs309android.util.Constants.Results.RESULT_OK) {
                                new FoodsCriteria(fdcResponse.getFdcId(), Constants.Format.FULL, null).request(response1 -> {
                                    BrandedFoodItem item = Util.objFromJson(response, BrandedFoodItem.class);
                                    Intent intent = new Intent(this, FoodDetailsActivity.class);
                                    intent.putExtra(PARCEL_FOODITEM, new SimpleFoodItem(item.getFdcId(), item.getDescription(), item.getBrandOwner(), false));
                                    intent.putExtra(PARCEL_BUTTON_CONTROL, FoodDetailsActivity.CONTROL_ADD);
                                    foodDetailsLauncher.launch(intent);
                                }, SearchActivity.this);
                            } else {
                                Toaster.toastShort("Error reading barcode", this);
                            }
                        }, SearchActivity.this);
                    }, error -> {
                        Toaster.toastShort("Error reading barcode", this);
                        error.printStackTrace();
                    });
                    analyzer.analyze(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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