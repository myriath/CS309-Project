package com.example.cs309android.activities;

import static com.example.cs309android.models.USDA.Constants.Format;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.WindowCompat;

import com.example.cs309android.R;
import com.example.cs309android.models.USDA.models.AbridgedFoodItem;
import com.example.cs309android.models.USDA.models.AbridgedFoodNutrient;
import com.example.cs309android.models.USDA.models.BrandedFoodItem;
import com.example.cs309android.models.USDA.models.FoodNutrient;
import com.example.cs309android.models.USDA.models.FoundationFoodItem;
import com.example.cs309android.models.USDA.models.SRLegacyFoodItem;
import com.example.cs309android.models.USDA.models.SurveyFoodItem;
import com.example.cs309android.models.USDA.queries.FoodsCriteria;
import com.example.cs309android.models.gson.models.DataTypeModel;
import com.example.cs309android.models.gson.models.SimpleFoodItem;
import com.example.cs309android.util.Util;
import com.example.cs309android.views.NutritionItemView;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;
import java.util.Objects;

/**
 * Food Details activity displays the information of a food from the USDA API
 *
 * @author Mitch Hudson
 */
public class FoodDetailsActivity extends AppCompatActivity {
    /**
     * Used to parcel the control variable
     */
    public static final String PARCEL_BUTTON_CONTROL = "button-control";
    /**
     * Used to tell the activity to display no fab
     */
    public static final int CONTROL_NONE = 0;
    /**
     * Used to tell the activity to display the add button
     */
    public static final int CONTROL_ADD = 1;

    /**
     * DP measurements
     */
    public float dp16;
    public float dp8;

    private LinearLayout detailsLayout;

    /**
     * Food item to display details for
     */
    private SimpleFoodItem item;

    /**
     * Runs when the activity is started
     *
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dp16 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16f, getResources().getDisplayMetrics());
        dp8 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8f, getResources().getDisplayMetrics());

        setContentView(R.layout.activity_food_details);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        Util.spin(this);

        Intent intent = getIntent();
        item = intent.getParcelableExtra(MainActivity.PARCEL_FOODITEM);
        detailsLayout = findViewById(R.id.details_layout);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setTitle(item.getDescription(), toolbar);

        Space spacer = new Space(this);

        new FoodsCriteria(item.getFdcId(), Format.FULL, null).unspinOnComplete(response -> {
            DataTypeModel dataType = Util.objFromJsonAdapted(response.toString(), DataTypeModel.class, new DataTypeModel.DataTypeModelDeserializer());
            switch (dataType.getDataType()) {
                // TODO: Write all of these, currently only need Branded/Foundation because of search
                case BRANDED: {
                    BrandedFoodItem foodItem = Util.objFromJson(response, BrandedFoodItem.class);
                    setSubtitle(foodItem.getBrandOwner(), toolbar);
                    fillIngredients(foodItem.getIngredients());
                    fillNutrition(foodItem.getFoodNutrients(), (float) foodItem.getServingSize(), foodItem.getServingSizeUnit());
                    detailsLayout.addView(spacer);
                    break;
                }
                case FOUNDATION: {
                    FoundationFoodItem foodItem = Util.objFromJson(response, FoundationFoodItem.class);
                    fillNutrition(foodItem.getFoodNutrients(), 0, null);
                    detailsLayout.addView(spacer);
                    break;
                }
                case SURVEY: {
                    SurveyFoodItem foodItem = Util.objFromJson(response, SurveyFoodItem.class);
                    detailsLayout.addView(spacer);
                    break;
                }
                case LEGACY: {
                    SRLegacyFoodItem foodItem = Util.objFromJson(response, SRLegacyFoodItem.class);
                    fillNutrition(foodItem.getFoodNutrients(), 0, null);
                    detailsLayout.addView(spacer);
                    break;
                }
                default: {
                    AbridgedFoodItem foodItem = Util.objFromJson(response, AbridgedFoodItem.class);
                    setSubtitle(foodItem.getBrandOwner(), toolbar);
                    fillNutrition(foodItem.getFoodNutrients(), 0, null);
                    detailsLayout.addView(spacer);
                }
            }
        }, FoodDetailsActivity.this, getWindow().getDecorView());

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);

        FloatingActionButton fab = findViewById(R.id.add_item);
        int control = intent.getIntExtra(FoodDetailsActivity.PARCEL_BUTTON_CONTROL, CONTROL_NONE);
        if (control == CONTROL_ADD) {
            fab.setVisibility(View.VISIBLE);
            spacer.setMinimumHeight((int) dp16 * 10);
        } else {
            spacer.setMinimumHeight((int) dp16 * 3);
        }

        fab.setOnClickListener(view -> {
            Intent intent1 = new Intent();
            intent1.putExtra(MainActivity.PARCEL_FOODITEM, item);
            setResult(RESULT_OK, intent1);
            finish();
        });
//z
//        findViewById(R.id.add_item).setOnClickListener(view1 -> {
//            Intent intent = new Intent();
//            intent.putExtra(MainActivity.PARCEL_FOODITEM, item);
//            setResult(RESULT_OK, intent);
//            finish();
//        });
//
//        NetworkImageView imageView = findViewById(R.id.image_view);
//        try {
//            Photo photo = item.getPhoto();
//            System.out.println(photo);
//            if (photo.getHighres() != null) {
//                imageView.setImageUrl(photo.getHighres(), RequestHandler.getInstance(this).getImageLoader());
//            } else {
//                imageView.setImageUrl(photo.getThumb(), RequestHandler.getInstance(this).getImageLoader());
//            }
//        } catch (NullPointerException ignored) {}
    }

    /**
     * Handles the back button on the toolbar
     *
     * @return true
     */
    @Override
    public boolean onSupportNavigateUp() {
        setResult(RESULT_CANCELED);
        finish();
        return true;
    }

    /**
     * Sets the title of the activity
     *
     * @param title   New title
     * @param toolbar Toolbar to change the title of
     */
    private void setTitle(String title, MaterialToolbar toolbar) {
        if (title.length() > 20) {
            toolbar.setTitle(title.substring(0, 20) + "...");
        } else {
            toolbar.setTitle(title);
        }
    }

    private void setSubtitle(String brand, MaterialToolbar toolbar) {
        if (brand != null && !brand.equals("")) {
            toolbar.setSubtitle(brand);
        }
    }

    /**
     * Fills the nutrition values for the nutrition table
     *
     * @param nutrients Nutrients of the item
     */
    private void fillNutrition(Object[] nutrients, float servingSize, String servingUnit) {
        if (nutrients.getClass() != FoodNutrient[].class && nutrients.getClass() != AbridgedFoodNutrient[].class)
            return;
        // Title TextView
        TextView title = new TextView(this);
        title.setText(getResources().getString(R.string.nutrition));
        title.setTextSize(30f);
        title.setPadding((int) dp16, (int) dp8, (int) dp16, (int) dp8);
        detailsLayout.addView(title);

        if (servingUnit != null) {
            TextView subtitle = new TextView(this);
            subtitle.setText(String.format(Locale.getDefault(), "per %.02f %s serving", servingSize, servingUnit));
            subtitle.setTextSize(24f);
            subtitle.setEnabled(false);
            subtitle.setPadding((int) dp16, (int) dp8, (int) dp16, (int) dp8);
            detailsLayout.addView(subtitle);
        }

        // Body CardView
        CardView cardView = new CardView(this);
        cardView.setRadius(dp16);
        // Linear layout inside CardView with details
        LinearLayout layout = new LinearLayout(this);
        layout.setPadding((int) dp16, (int) dp16, (int) dp16, (int) dp16);
        layout.setOrientation(LinearLayout.VERTICAL);
        cardView.addView(layout);

        if (nutrients.getClass() == FoodNutrient[].class) {
            fillNutritionTable((FoodNutrient[]) nutrients, layout);
        } else {
            fillNutritionTable((AbridgedFoodNutrient[]) nutrients, layout);
        }

        detailsLayout.addView(cardView);
    }

    /**
     * Handles the nutrition table for a FoodNutrient[]
     *
     * @param nutrients FoodNutrient[] of nutrients
     * @param table     Linear Layout to be used for the table
     */
    private void fillNutritionTable(FoodNutrient[] nutrients, LinearLayout table) {
        for (FoodNutrient nutrient : nutrients) {
            NutritionItemView view = generateNutritionRow(nutrient.getNutrient().getName(), nutrient.getAmount(), nutrient.getNutrient().getUnitName());
            if (view != null) {
                table.addView(view);
            }
        }
    }

    /**
     * Handles the nutrition table for an AbridgedFoodNutrient[]
     *
     * @param nutrients AbridgedFoodNutrient[] of nutrients
     * @param table     Linear Layout to be used for the table
     */
    private void fillNutritionTable(AbridgedFoodNutrient[] nutrients, LinearLayout table) {
        for (AbridgedFoodNutrient nutrient : nutrients) {
            NutritionItemView view = generateNutritionRow(nutrient.getName(), nutrient.getAmount(), nutrient.getUnitName());
            if (view != null) {
                table.addView(view);
            }
        }
    }

    /**
     * Generates a NutritionItemView to be used for the nutrition table
     *
     * @param name   Name of the nutrient
     * @param amount Amount of the nutrient in units
     * @param unit   Unit for the nutrient
     * @return Null if the amount is 0, or a NutritionItemView to display the nutrient information
     */
    private NutritionItemView generateNutritionRow(String name, float amount, String unit) {
        System.out.println(name);
        if (amount == 0) return null;
        NutritionItemView itemView = new NutritionItemView(this);
        itemView.initView(name, String.format(Locale.getDefault(), "%.02f %s", amount, unit));
        return itemView;
    }

    /**
     * Fills the ingredient values for the ingredients table
     *
     * @param ingredientsText Ingredients of the item
     */
    private void fillIngredients(String ingredientsText) {
        // Title TextView
        TextView title = new TextView(this);
        title.setText(getResources().getString(R.string.ingredients));
        title.setTextSize(30f);
        title.setPadding((int) dp16, (int) dp8, (int) dp16, (int) dp8);
        detailsLayout.addView(title);

        // Body CardView
        CardView cardView = new CardView(this);
        cardView.setRadius(dp16);
        TextView ingredients = new TextView(this);
        ingredients.setTextSize(20f);
        ingredients.setPadding((int) dp16, (int) dp16, (int) dp16, (int) dp16);
        ingredients.setText(ingredientsText);
        cardView.addView(ingredients);
        detailsLayout.addView(cardView);
    }
}