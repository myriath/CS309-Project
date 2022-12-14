package com.example.cs309android.activities.food;

import static com.example.cs309android.models.USDA.Constants.Format;
import static com.example.cs309android.util.Constants.Parcels.PARCEL_BUTTON_CONTROL;
import static com.example.cs309android.util.Constants.Parcels.PARCEL_FOODITEM;
import static com.example.cs309android.util.Constants.UserType.USER_REG;
import static com.example.cs309android.util.Constants.dp16;
import static com.example.cs309android.util.Constants.dp8;
import static com.example.cs309android.util.Util.setSubtitle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.WindowCompat;

import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.models.USDA.models.BrandedFoodItem;
import com.example.cs309android.models.USDA.queries.FoodsCriteria;
import com.example.cs309android.models.api.models.CustomFoodItem;
import com.example.cs309android.models.api.models.SimpleFoodItem;
import com.example.cs309android.models.api.request.food.CustomFoodGetRequest;
import com.example.cs309android.models.api.request.food.DeleteCustomFoodRequest;
import com.example.cs309android.models.api.response.GenericResponse;
import com.example.cs309android.models.api.response.food.CustomFoodGetResponse;
import com.example.cs309android.util.Constants;
import com.example.cs309android.util.Toaster;
import com.example.cs309android.util.Util;
import com.example.cs309android.views.NutritionItemView;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.Locale;
import java.util.Objects;

/**
 * Food Details activity displays the information of a food item
 *
 * @author Mitch Hudson
 */
public class FoodDetailsActivity extends AppCompatActivity {
    /**
     * Used to tell the activity to display no floating button
     */
    public static final int CONTROL_NONE = 0;
    /**
     * Used to tell the activity to display the add button
     */
    public static final int CONTROL_ADD = 1;

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
     * @param savedInstanceState saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        Util.spin(this);
        GlobalClass global = (GlobalClass) getApplicationContext();

        Intent intent = getIntent();
        item = intent.getParcelableExtra(PARCEL_FOODITEM);
        detailsLayout = findViewById(R.id.details_layout);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        Util.setTitle(item.getDescription(), toolbar);

        if (item.isCustom() && (item.getBrand().equals(global.getUsername()) || global.getUserType() > USER_REG)) {
            toolbar.inflateMenu(R.menu.moderation_menu);
        }

        Space spacer = new Space(this);

        ActivityResultLauncher<Intent> editCustomLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        update(spacer,
                                Objects.requireNonNull(result.getData()).getParcelableExtra(PARCEL_FOODITEM),
                                toolbar);
                    }
                }
        );

        if (!item.isCustom()) {
            new FoodsCriteria(item.getId(), Format.FULL, null).unspinOnComplete(response -> {
                BrandedFoodItem foodItem = Util.objFromJson(response, BrandedFoodItem.class);
                item.setNutrients(foodItem.getLabelNutrients());
                setSubtitle(foodItem.getBrandOwner(), toolbar);
                fillIngredients(foodItem.getIngredients());
                fillNutrition(foodItem.getLabelNutrients(), (float) foodItem.getServingSize(), foodItem.getServingSizeUnit());
                detailsLayout.addView(spacer);
            }, FoodDetailsActivity.this, getWindow().getDecorView());
        } else {
            new CustomFoodGetRequest(item.getId()).unspinOnComplete(response -> {
                CustomFoodGetResponse customFoodGetResponse = Util.objFromJson(response, CustomFoodGetResponse.class);
                CustomFoodItem foodItem = customFoodGetResponse.getItem();

                toolbar.setOnMenuItemClickListener(item -> {
                    int id = item.getItemId();
                    if (id == R.id.edit) {
                        Intent intent1 = new Intent(this, NewFoodActivity.class);
                        intent1.putExtra(PARCEL_FOODITEM, foodItem);
                        editCustomLauncher.launch(intent1);
                    } else if (id == R.id.delete) {
                        new DeleteCustomFoodRequest(foodItem, global.getToken()).request(response1 -> {
                            GenericResponse genericResponse = Util.objFromJson(response1, GenericResponse.class);
                            if (genericResponse.getResult() != Constants.Results.RESULT_OK) {
                                Toaster.toastShort("Error", this);
                            }
                        }, error -> Toaster.toastShort("Error", this), FoodDetailsActivity.this);
                        finish();
                    }
                    return true;
                });

                if (customFoodGetResponse.getResult() == Constants.Results.RESULT_OK) {
                    update(spacer, foodItem, toolbar);
                }
            }, FoodDetailsActivity.this, getWindow().getDecorView());
        }

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);

        ExtendedFloatingActionButton fab = findViewById(R.id.add_item);
        int control = intent.getIntExtra(PARCEL_BUTTON_CONTROL, CONTROL_NONE);
        if (control == CONTROL_ADD) {
            fab.setVisibility(View.VISIBLE);
            spacer.setMinimumHeight((int) Util.scalePixels(160));
        } else {
            fab.setVisibility(View.GONE);
            spacer.setMinimumHeight((int) Util.scalePixels(64));
        }

        fab.setOnClickListener(view -> {
            Intent intent1 = new Intent();
            intent1.putExtra(PARCEL_FOODITEM, item);
            setResult(RESULT_OK, intent1);
            finish();
        });
    }

    public void update(Space spacer, CustomFoodItem foodItem, MaterialToolbar toolbar) {
        setSubtitle("User added", toolbar);
        fillIngredients(foodItem.getIngredients());
        fillNutrition(foodItem);
        detailsLayout.addView(spacer);
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
        cardView.setRadius((int) dp16);
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
     * @param name     Name of the nutrient
     * @param nutrient Nutrient for the row
     * @param unit     Unit for the nutrient
     * @return Null if the amount is 0, or a NutritionItemView to
     * display the nutrient information
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
     * @param name     Name of the nutrient
     * @param nutrient Nutrient for the row
     * @param unit     Unit for the nutrient
     * @return Null if the amount is 0, or a NutritionItemView
     * to display the nutrient information
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