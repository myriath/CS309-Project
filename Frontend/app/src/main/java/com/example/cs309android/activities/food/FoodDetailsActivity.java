package com.example.cs309android.activities.food;

import static com.example.cs309android.models.USDA.Constants.Format;
import static com.example.cs309android.util.Util.setSubtitle;

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
import com.example.cs309android.activities.MainActivity;
import com.example.cs309android.models.USDA.models.BrandedFoodItem;
import com.example.cs309android.models.USDA.queries.FoodsCriteria;
import com.example.cs309android.models.gson.models.CustomFoodItem;
import com.example.cs309android.models.gson.models.SimpleFoodItem;
import com.example.cs309android.models.gson.request.food.CustomFoodGetRequest;
import com.example.cs309android.models.gson.response.food.CustomFoodGetResponse;
import com.example.cs309android.util.Constants;
import com.example.cs309android.util.Util;
import com.example.cs309android.views.NutritionItemView;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

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

    /**
     * Layout for displaying the food details
     */
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
        Util.setTitle(item.getDescription(), toolbar);

        Space spacer = new Space(this);

        if (!item.isCustom()) {
            new FoodsCriteria(item.getId(), Format.FULL, null).unspinOnComplete(response -> {
                BrandedFoodItem foodItem = Util.objFromJson(response, BrandedFoodItem.class);
                setSubtitle(foodItem.getBrandOwner(), toolbar);
                fillIngredients(foodItem.getIngredients());
                fillNutrition(foodItem.getLabelNutrients(), (float) foodItem.getServingSize(), foodItem.getServingSizeUnit());
                detailsLayout.addView(spacer);
            }, FoodDetailsActivity.this, getWindow().getDecorView());
        } else {
            new CustomFoodGetRequest(item.getId()).unspinOnComplete(response -> {
                System.out.println(response);
                CustomFoodGetResponse customFoodGetResponse = Util.objFromJson(response, CustomFoodGetResponse.class);
                CustomFoodItem foodItem = customFoodGetResponse.getItem();
                System.out.println(foodItem);

                if (customFoodGetResponse.getResult() == Constants.RESULT_OK) {
                    setSubtitle("User added", toolbar);
                    fillIngredients(foodItem.getIngredients());
                    fillNutrition(foodItem);
                    detailsLayout.addView(spacer);
                }
            }, FoodDetailsActivity.this, getWindow().getDecorView());
        }

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);

        ExtendedFloatingActionButton fab = findViewById(R.id.add_item);
        int control = intent.getIntExtra(FoodDetailsActivity.PARCEL_BUTTON_CONTROL, CONTROL_NONE);
        if (control == CONTROL_ADD) {
            fab.setVisibility(View.VISIBLE);
            spacer.setMinimumHeight((int) dp16 * 10);
        } else {
            fab.setVisibility(View.GONE);
            spacer.setMinimumHeight((int) dp16 * 4);
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
     * Fills the nutrition values for the nutrition table
     *
     * @param nutrients Nutrients of the item
     */
    private void fillNutrition(BrandedFoodItem.LabelNutrients nutrients, float servingSize, String servingUnit) {
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

        // Macro nutrients
        // Body CardView
        CardView cardView = new CardView(this);
        cardView.setRadius(dp16);
        Space space = new Space(this);
        space.setMinimumHeight((int) dp16);
        // Linear layout inside CardView with details
        LinearLayout layout = new LinearLayout(this);
        layout.setPadding((int) dp16, (int) dp16, (int) dp16, (int) dp16);
        layout.setOrientation(LinearLayout.VERTICAL);
        cardView.addView(layout);

        layout.addView(generateNutritionRow("Calories", nutrients.getCalories(), "kcal"));
        layout.addView(generateNutritionRow("Total carbohydrates", nutrients.getCarbohydrates(), "g"));
        layout.addView(generateNutritionRow("Total fat", nutrients.getFat(), "g"));
        layout.addView(generateNutritionRow("Total protein", nutrients.getProtein(), "g"));
        detailsLayout.addView(cardView);
        detailsLayout.addView(space);

        // Micro nutrients
        // Body CardView
        cardView = new CardView(this);
        cardView.setRadius(dp16);
        // Linear layout inside CardView with details
        layout = new LinearLayout(this);
        layout.setPadding((int) dp16, (int) dp16, (int) dp16, (int) dp16);
        layout.setOrientation(LinearLayout.VERTICAL);
        cardView.addView(layout);

        layout.addView(generateNutritionRow("Sodium", nutrients.getSodium(), "mg"));
        layout.addView(generateNutritionRow("Total sugars", nutrients.getSugars(), "g"));
        layout.addView(generateNutritionRow("Total fiber", nutrients.getFiber(), "g"));
        layout.addView(generateNutritionRow("Saturated fat", nutrients.getSaturatedFat(), "g"));
        layout.addView(generateNutritionRow("Trans fat", nutrients.getTransFat(), "g"));
        layout.addView(generateNutritionRow("Cholesterol", nutrients.getCholesterol(), "mg"));
        layout.addView(generateNutritionRow("Calcium", nutrients.getCalcium(), "mg"));
        layout.addView(generateNutritionRow("Iron", nutrients.getIron(), "mg"));
        layout.addView(generateNutritionRow("Potassium", nutrients.getPotassium(), "mg"));
        detailsLayout.addView(cardView);
    }

    /**
     * Fills the nutrition card for custom food items
     *
     * @param foodItem Item to display nutrition info of
     */
    private void fillNutrition(CustomFoodItem foodItem) {
        // Title TextView
        TextView title = new TextView(this);
        title.setText(getResources().getString(R.string.nutrition));
        title.setTextSize(30f);
        title.setPadding((int) dp16, (int) dp8, (int) dp16, (int) dp8);
        detailsLayout.addView(title);

        // Macro nutrients
        // Body CardView
        CardView cardView = new CardView(this);
        cardView.setRadius(dp16);
        Space space = new Space(this);
        space.setMinimumHeight((int) dp16);
        // Linear layout inside CardView with details
        LinearLayout layout = new LinearLayout(this);
        layout.setPadding((int) dp16, (int) dp16, (int) dp16, (int) dp16);
        layout.setOrientation(LinearLayout.VERTICAL);
        cardView.addView(layout);

        layout.addView(generateNutritionRow("Calories", foodItem.getCalories(), "kcal"));
        layout.addView(generateNutritionRow("Total carbohydrates", foodItem.getCarbs(), "g"));
        layout.addView(generateNutritionRow("Total fat", foodItem.getFat(), "g"));
        layout.addView(generateNutritionRow("Total protein", foodItem.getProtein(), "g"));
        detailsLayout.addView(cardView);
        detailsLayout.addView(space);
    }

    /**
     * Generates a NutritionItemView to be used for the nutrition table
     *
     * @param name   Name of the nutrient
     * @param nutrient Nutrient for the row
     * @param unit   Unit for the nutrient
     * @return Null if the amount is 0, or a NutritionItemView to display the nutrient information
     */
    private NutritionItemView generateNutritionRow(String name, BrandedFoodItem.LabelNutrients.Nutrient nutrient, String unit) {
        NutritionItemView itemView;
        if (nutrient == null || nutrient.getValue() == null) {
            itemView = new NutritionItemView(this);
            itemView.initView(name, String.format(Locale.getDefault(), "%.02f %s", 0f, unit));
        } else {
            itemView = new NutritionItemView(this);
            itemView.initView(name, String.format(Locale.getDefault(), "%.02f %s", nutrient.getValue(), unit));
        }
        return itemView;
    }

    /**
     * Generates a NutritionItemView to be used for the nutrition table
     *
     * @param name   Name of the nutrient
     * @param nutrient Nutrient for the row
     * @param unit   Unit for the nutrient
     * @return Null if the amount is 0, or a NutritionItemView to display the nutrient information
     */
    private NutritionItemView generateNutritionRow(String name, Float nutrient, String unit) {
        NutritionItemView itemView;
        if (nutrient == null) {
            itemView = new NutritionItemView(this);
            itemView.initView(name, String.format(Locale.getDefault(), "%.02f %s", 0f, unit));
        } else {
            itemView = new NutritionItemView(this);
            itemView.initView(name, String.format(Locale.getDefault(), "%.02f %s", nutrient, unit));
        }
        return itemView;
    }

    /**
     * Fills the ingredient values for the ingredients table
     *
     * @param ingredientsText Ingredients of the item
     */
    private void fillIngredients(String ingredientsText) {
        if (ingredientsText == null || ingredientsText.equals("")) return;
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