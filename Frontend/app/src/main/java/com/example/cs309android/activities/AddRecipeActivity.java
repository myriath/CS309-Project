package com.example.cs309android.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.models.gson.request.recipes.AddRecipe;
import com.example.cs309android.models.gson.response.GenericResponse;
import com.example.cs309android.util.Constants;
import com.example.cs309android.util.Util;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;

import java.util.Objects;

public class AddRecipeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        Button addRecipe = findViewById(R.id.add_recipe_button);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        addRecipe.setOnClickListener(view1 -> {
//            TextView RnameInput = findViewById(R.id.recipeNameInput);
//
//            new AddRecipe(((GlobalClass) requireActivity().getApplicationContext()).getToken(), RnameInput.getText().toString(), "put this in that and do stuff").request(response -> {
//                try {
//                    System.out.print(response.toString(4));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                GenericResponse recipeResponse = Util.objFromJson(response, GenericResponse.class);
//                TextView recipes = findViewById(R.id.recipeName);
//                if (recipeResponse.getResult() == Constants.RESULT_RECIPE_CREATED) {
//                    recipes.setText("Recipe Created");
//                }
//                else if(recipeResponse.getResult() == Constants.RESULT_ERROR_RID_TAKEN) {
//                    recipes.setText("That RID is taken");
//                }
//                else {
//                    recipes.setText("Something went wrong");
//                }
//            },getContext());
            Snackbar mySnackbar = Snackbar.make(findViewById(R.id.add_recipe_coordinator_layout),
                    R.string.recipes_snackbar_added, Snackbar.LENGTH_SHORT);
            mySnackbar.show();

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