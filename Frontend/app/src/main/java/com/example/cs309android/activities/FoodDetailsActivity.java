package com.example.cs309android.activities;

import static com.example.cs309android.models.USDA.Constants.Format;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import com.example.cs309android.R;
import com.example.cs309android.models.USDA.models.AbridgedFoodItem;
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
        setContentView(R.layout.activity_food_details);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        Util.spin(this);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);

        Intent i = getIntent();
        int fdcId = ((SimpleFoodItem) i.getParcelableExtra(MainActivity.PARCEL_FOODITEM)).getFdcId();
        new FoodsCriteria(fdcId, Format.FULL, null).unspinOnComplete(response -> {
            DataTypeModel dataType = Util.objFromJsonAdapted(response.toString(), DataTypeModel.class, new DataTypeModel.DataTypeModelDeserializer());
            switch (dataType.getDataType()) {
                // TODO: Write all of these, currently only need Branded/Foundation because of search
                case BRANDED: {
                    BrandedFoodItem foodItem = Util.objFromJson(response, BrandedFoodItem.class);
                    item = new SimpleFoodItem(foodItem.getFdcId(), foodItem.getDescription());
                    setTitle(foodItem.getDescription(), toolbar);
                    fillNutrition(foodItem.getFoodNutrients());
                    fillIngredients(foodItem.getIngredients());
                    break;
                }
                case FOUNDATION: {
                    FoundationFoodItem foodItem = Util.objFromJson(response, FoundationFoodItem.class);
                    item = new SimpleFoodItem(foodItem.getFdcId(), foodItem.getDescription());
                    setTitle(foodItem.getDescription(), toolbar);
                    fillNutrition(foodItem.getFoodNutrients());
                    break;
                }
                case SURVEY: {
                    SurveyFoodItem foodItem = Util.objFromJson(response, SurveyFoodItem.class);
//                    item = new SimpleFoodItem(foodItem.getFdcId(), foodItem.getDescription());
//                    setTitle(foodItem.getDescription(), toolbar);
                    break;
                }
                case LEGACY: {
                    SRLegacyFoodItem foodItem = Util.objFromJson(response, SRLegacyFoodItem.class);
                    item = new SimpleFoodItem(foodItem.getFdcId(), foodItem.getDescription());
                    setTitle(foodItem.getDescription(), toolbar);
                    break;
                }
                default: {
                    AbridgedFoodItem foodItem = Util.objFromJson(response, AbridgedFoodItem.class);
                    item = new SimpleFoodItem(foodItem.getFdcId(), foodItem.getDescription());
                    setTitle(foodItem.getDescription(), toolbar);
                }
            }
        }, FoodDetailsActivity.this, getWindow().getDecorView());

        FloatingActionButton fab = findViewById(R.id.add_item);
        int control = i.getIntExtra(FoodDetailsActivity.PARCEL_BUTTON_CONTROL, CONTROL_NONE);
        if (control == CONTROL_ADD) fab.setVisibility(View.VISIBLE);

        fab.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.putExtra(MainActivity.PARCEL_FOODITEM, item);
            setResult(RESULT_OK, intent);
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
        toolbar.setTitle(title.substring(0, Math.min(title.length(), 18)).trim() + (title.length() > 18 ? "..." : ""));
        setSupportActionBar(toolbar);
    }

    /**
     * Fills the nutrition values for the nutrition table
     * @param nutrients Nutrients of the item
     */
    private void fillNutrition(FoodNutrient[] nutrients) {
        LinearLayout layout = findViewById(R.id.nutrition_layout);
        for (FoodNutrient nutrient : nutrients) {
            NutritionItemView itemView = new NutritionItemView(this);
            itemView.initView(nutrient);
            layout.addView(itemView);
        }
    }

    /**
     * Fills the ingredient values for the ingredients table
     * @param ingredientsText Ingredients of the item
     */
    private void fillIngredients(String ingredientsText) {
        LinearLayout layout = findViewById(R.id.ingredients_layout);
        String[] ingredients = ingredientsText.split(",");
        for (String ingredient : ingredients) {
            TextView view = new TextView(this);
            view.setText(ingredient);
            layout.addView(view);
        }
    }
}