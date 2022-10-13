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
import com.example.cs309android.util.Toaster;
import com.example.cs309android.util.Util;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

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
            TextInputLayout RnameInput = findViewById(R.id.recipeNameInput);
            TextInputLayout RInstructionsInput = findViewById(R.id.recipeInstructionsInput);
            if(!validateFields(RnameInput, RInstructionsInput)) {
                return;
            }
            new AddRecipe(((GlobalClass) getApplicationContext()).getToken(), RnameInput.getEditText().getText().toString(), RInstructionsInput.getEditText().getText().toString()).request(response -> {
                try {
                    System.out.print(response.toString(4));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                GenericResponse recipeResponse = Util.objFromJson(response, GenericResponse.class);

                if (recipeResponse.getResult() == Constants.RESULT_RECIPE_CREATED) {
                    Toaster.toastShort("Recipe Added", this);
                    RnameInput.getEditText().setText("");
                    RInstructionsInput.getEditText().setText("");
                }
                else if(recipeResponse.getResult() == Constants.RESULT_ERROR_RID_TAKEN) {
                    Toaster.toastShort("That recipe is already taken", this);

                }
                else {
                    Toaster.toastShort("Something went wrong", this);
                }
            },this);
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

    /**
     * Validates the user input is not empty for recipe name and recipe instructions
     */
    private boolean validateFields(TextInputLayout RecipeName, TextInputLayout RecipeInstructions) {
        boolean notEmpty = true;
        if (RecipeName.getEditText().getText().length() <= 0) {
            RecipeName.setError("Input cannot be empty");
            notEmpty = false;
        }
        else {
            RecipeName.setError(null);
        }

        if (RecipeInstructions.getEditText().getText().length() <= 0) {
            RecipeInstructions.setError("Input cannot be empty");
            notEmpty = false;
        }
        else{
            RecipeInstructions.setError(null);
        }
        return notEmpty;
    }
}