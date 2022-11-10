package com.example.cs309android.activities.food;

import static com.example.cs309android.util.Constants.ITEM_ID_NULL;
import static com.example.cs309android.util.Constants.PARCEL_FOODITEM;

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
import com.example.cs309android.models.api.response.food.CustomFoodAddResponse;
import com.example.cs309android.util.Constants;
import com.example.cs309android.util.Toaster;
import com.example.cs309android.util.Util;
import com.example.cs309android.views.CustomNutritionItemView;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class CustomFoodActivity extends AppCompatActivity {

    /**
     * Food item to display details for
     */
    private SimpleFoodItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_food);

        Intent intent = getIntent();
        item = intent.getParcelableExtra(PARCEL_FOODITEM);
        Space spacer = new Space(this);
        spacer.setMinimumHeight((int) Util.scalePixels(160));
        /**
         * Layout for displaying the food details
         */
        LinearLayout detailsLayout = findViewById(R.id.details_layout);
        detailsLayout.addView(spacer);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        Util.setTitle(item.getDescription(), toolbar);
        Util.setSubtitle(item.getBrand(), toolbar);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);

        findViewById(R.id.add_item).setOnClickListener(view -> {
            String name = item.getDescription();
            String ingredients = Objects.requireNonNull(((TextInputLayout) findViewById(R.id.ingredients)).getEditText()).getText().toString();
            float calories = ((CustomNutritionItemView) findViewById(R.id.calories)).getValue();
            float fat = ((CustomNutritionItemView) findViewById(R.id.fat)).getValue();
            float carbs = ((CustomNutritionItemView) findViewById(R.id.carbs)).getValue();
            float protein = ((CustomNutritionItemView) findViewById(R.id.protein)).getValue();
            CustomFoodItem customFoodItem = new CustomFoodItem(ITEM_ID_NULL, name, ingredients, calories, fat, carbs, protein);

            Util.spin(this);
            new CustomFoodAddRequest(customFoodItem, ((GlobalClass) getApplicationContext()).getToken()).unspinOnComplete(response -> {
                        CustomFoodAddResponse addResponse = Util.objFromJson(response, CustomFoodAddResponse.class);
                        if (addResponse.getResult() == Constants.RESULT_OK) {
                            Intent intent1 = new Intent();
                            intent1.putExtra(PARCEL_FOODITEM, new SimpleFoodItem(addResponse.getDbId(), name, "User added", true));
                            setResult(RESULT_OK, intent1);
                        } else {
                            Toaster.toastShort("Error!", this);
                            setResult(RESULT_CANCELED);
                        }
                        finish();
                    }, error -> Toaster.toastShort("Error!", this),
                    CustomFoodActivity.this,
                    getWindow().getDecorView().getRootView());
        });
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