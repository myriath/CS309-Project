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

        detailsLayout = findViewById(R.id.details_layout);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");

        Space spacer = new Space(this);
        spacer.setMinimumHeight((int) dp16 * 10);

        Intent i = getIntent();
        int fdcId = ((SimpleFoodItem) i.getParcelableExtra(MainActivity.PARCEL_FOODITEM)).getFdcId();
        new FoodsCriteria(fdcId, Format.FULL, null).unspinOnComplete(response -> {
            DataTypeModel dataType = Util.objFromJsonAdapted(response.toString(), DataTypeModel.class, new DataTypeModel.DataTypeModelDeserializer());
            switch (dataType.getDataType()) {
                // TODO: Write all of these, currently only need Branded/Foundation because of search
                case BRANDED: {
                    BrandedFoodItem foodItem = Util.objFromJson(response, BrandedFoodItem.class);
                    item = new SimpleFoodItem(foodItem.getFdcId(), foodItem.getDescription(), foodItem.getBrandOwner());
                    setTitle(foodItem.getDescription(), toolbar);
                    fillIngredients(foodItem.getIngredients());
                    fillNutrition(foodItem.getFoodNutrients());
                    detailsLayout.addView(spacer);
                    break;
                }
                case FOUNDATION: {
                    FoundationFoodItem foodItem = Util.objFromJson(response, FoundationFoodItem.class);
                    item = new SimpleFoodItem(foodItem.getFdcId(), foodItem.getDescription(), null);
                    setTitle(foodItem.getDescription(), toolbar);
                    fillNutrition(foodItem.getFoodNutrients());
                    detailsLayout.addView(spacer);
                    break;
                }
                case SURVEY: {
                    SurveyFoodItem foodItem = Util.objFromJson(response, SurveyFoodItem.class);
                    item = new SimpleFoodItem(foodItem.getFdcId(), foodItem.getDescription(), null);
                    setTitle(foodItem.getDescription(), toolbar);
                    detailsLayout.addView(spacer);
                    break;
                }
                case LEGACY: {
                    SRLegacyFoodItem foodItem = Util.objFromJson(response, SRLegacyFoodItem.class);
                    item = new SimpleFoodItem(foodItem.getFdcId(), foodItem.getDescription(), null);
                    setTitle(foodItem.getDescription(), toolbar);
                    fillNutrition(foodItem.getFoodNutrients());
                    detailsLayout.addView(spacer);
                    break;
                }
                default: {
                    AbridgedFoodItem foodItem = Util.objFromJson(response, AbridgedFoodItem.class);
                    item = new SimpleFoodItem(foodItem.getFdcId(), foodItem.getDescription(), foodItem.getBrandOwner());
                    setTitle(foodItem.getDescription(), toolbar);
                    fillNutrition(foodItem.getFoodNutrients());
                    detailsLayout.addView(spacer);
                }
            }
        }, FoodDetailsActivity.this, getWindow().getDecorView());

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);

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
        if (title.length() > 20) {
            toolbar.setTitle(title.substring(0, 20) + "...");
        } else {
            toolbar.setTitle(title);
        }
    }

    /*
<TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:textSize="30sp"
                android:paddingHorizontal="16dp"
                android:paddingVertical="8dp"
                android:text="@string/nutrition"/>

            <androidx.cardview.widget.CardView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                app:cardCornerRadius="8dp" >

                <LinearLayout
                    android:id="@+id/nutrition_layout"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:orientation="vertical">

                </LinearLayout>

            </androidx.cardview.widget.CardView>
 */

    /**
     * Fills the nutrition values for the nutrition table
     *
     * @param nutrients Nutrients of the item
     */
    private void fillNutrition(FoodNutrient[] nutrients) {
        // Title TextView
        TextView title = new TextView(this);
        title.setText(getResources().getString(R.string.nutrition));
        title.setTextSize(30f);
        title.setPadding((int) dp16, (int) dp8, (int) dp16, (int) dp8);
        detailsLayout.addView(title);

        // Body CardView
        CardView cardView = new CardView(this);
        cardView.setRadius(dp16);
        // Linear layout inside CardView with details
        LinearLayout layout = new LinearLayout(this);
        for (FoodNutrient nutrient : nutrients) {
            NutritionItemView itemView = new NutritionItemView(this);
            itemView.initView(nutrient);
            layout.addView(itemView);
        }
        cardView.addView(layout);
        detailsLayout.addView(cardView);
    }

    /**
     * Fills the nutrition values for the nutrition table
     *
     * @param nutrients Nutrients of the item
     */
    private void fillNutrition(AbridgedFoodNutrient[] nutrients) {
        // Title TextView
        TextView title = new TextView(this);
        title.setText(getResources().getString(R.string.nutrition));
        title.setTextSize(30f);
        title.setPadding((int) dp16, (int) dp8, (int) dp16, (int) dp8);
        detailsLayout.addView(title);

        // Body CardView
        CardView cardView = new CardView(this);
        cardView.setRadius(dp16);
        // Linear layout inside CardView with details
        LinearLayout layout = new LinearLayout(this);
        layout.setPadding((int) dp16, (int) dp16, (int) dp16, (int) dp16);
        for (AbridgedFoodNutrient nutrient : nutrients) {
            NutritionItemView itemView = new NutritionItemView(this);
            itemView.initView(nutrient);
            layout.addView(itemView);
        }
        cardView.addView(layout);
        detailsLayout.addView(cardView);
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