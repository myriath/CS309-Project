package com.example.cs309android.activities.food;

import static com.example.cs309android.util.Constants.ITEM_ID_NULL;
import static com.example.cs309android.util.Constants.Parcels.PARCEL_FOODITEM;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Space;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.models.api.models.CustomFoodItem;
import com.example.cs309android.models.api.models.SimpleFoodItem;
import com.example.cs309android.models.api.request.food.CustomFoodAddRequest;
import com.example.cs309android.models.api.request.food.EditFoodItemRequest;
import com.example.cs309android.models.api.response.GenericResponse;
import com.example.cs309android.models.api.response.food.CustomFoodAddResponse;
import com.example.cs309android.util.Constants;
import com.example.cs309android.util.Toaster;
import com.example.cs309android.util.Util;
import com.example.cs309android.views.CustomNutritionItemView;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

/**
 * Activity for creating a custom food item
 *
 * @author Mitch Hudson
 */
public class NewFoodActivity extends AppCompatActivity {
    /**
     * Runs when the activity starts
     *
     * @param savedInstanceState saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_food);
        GlobalClass global = (GlobalClass) getApplicationContext();

        Intent intent = getIntent();
        String title, subtitle;

        Object parcelable = intent.getParcelableExtra(PARCEL_FOODITEM);
        if (parcelable instanceof SimpleFoodItem) {
            title = ((SimpleFoodItem) parcelable).getDescription();
            subtitle = ((SimpleFoodItem) parcelable).getBrand();

            findViewById(R.id.add_item).setOnClickListener(view -> {
                String ingredients = Objects.requireNonNull(((TextInputLayout) findViewById(R.id.ingredients)).getEditText()).getText().toString();
                float calories = ((CustomNutritionItemView) findViewById(R.id.calories)).getValue();
                float fat = ((CustomNutritionItemView) findViewById(R.id.fat)).getValue();
                float carbs = ((CustomNutritionItemView) findViewById(R.id.carbs)).getValue();
                float protein = ((CustomNutritionItemView) findViewById(R.id.protein)).getValue();
                CustomFoodItem customFoodItem = new CustomFoodItem(ITEM_ID_NULL, title, ingredients, calories, fat, carbs, protein);

                Util.spin(this);
                new CustomFoodAddRequest(customFoodItem, global.getToken()).unspinOnComplete(response -> {
                            CustomFoodAddResponse addResponse = Util.objFromJson(response, CustomFoodAddResponse.class);
                            if (addResponse.getResult() == Constants.Results.RESULT_OK) {
                                Intent intent1 = new Intent();
                                intent1.putExtra(PARCEL_FOODITEM, new SimpleFoodItem(addResponse.getDbId(), title, "User added", true));
                                setResult(RESULT_OK, intent1);
                            } else {
                                Toaster.toastShort("Error!", this);
                                setResult(RESULT_CANCELED);
                            }
                            finish();
                        }, error -> Toaster.toastShort("Error!", this),
                        NewFoodActivity.this,
                        getWindow().getDecorView().getRootView());
            });
        } else {
            CustomFoodItem item = (CustomFoodItem) parcelable;
            title = item.getName();
            subtitle = "User added";

            TextInputLayout ingredientsView = findViewById(R.id.ingredients);
            Objects.requireNonNull(ingredientsView.getEditText()).setText(item.getIngredients());

            CustomNutritionItemView caloriesView = findViewById(R.id.calories);
            caloriesView.setValue(item.getCalories());

            CustomNutritionItemView fatView = findViewById(R.id.fat);
            fatView.setValue(item.getFat());

            CustomNutritionItemView carbsView = findViewById(R.id.carbs);
            carbsView.setValue(item.getCarbs());

            CustomNutritionItemView proteinView = findViewById(R.id.protein);
            proteinView.setValue(item.getProtein());

            findViewById(R.id.add_item).setOnClickListener(view -> {
                String ingredients = ingredientsView.getEditText().getText().toString();
                float calories = caloriesView.getValue();
                float fat = fatView.getValue();
                float carbs = carbsView.getValue();
                float protein = proteinView.getValue();
                CustomFoodItem customFoodItem = new CustomFoodItem(item.getDbId(), title, ingredients, calories, fat, carbs, protein);

                Util.spin(this);
                new EditFoodItemRequest(customFoodItem, global.getToken()).unspinOnComplete(response -> {
                    GenericResponse genericResponse = Util.objFromJson(response, GenericResponse.class);
                    if (genericResponse.getResult() != Constants.Results.RESULT_OK) {
                        Toaster.toastShort("Error", this);
                    }
                    finish();
                }, NewFoodActivity.this, getWindow().getDecorView());
            });
        }

        Space spacer = new Space(this);
        spacer.setMinimumHeight((int) Util.scalePixels(160));

        LinearLayout detailsLayout = findViewById(R.id.details_layout);
        detailsLayout.addView(spacer);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        Util.setTitle(title, toolbar);
        Util.setSubtitle(subtitle, toolbar);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
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
}