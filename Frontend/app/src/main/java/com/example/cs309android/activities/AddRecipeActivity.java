package com.example.cs309android.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.models.gson.request.recipes.AddRecipe;
import com.example.cs309android.models.gson.response.GenericResponse;
import com.example.cs309android.util.Constants;
import com.example.cs309android.util.Util;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;

public class AddRecipeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        Button addRecipe = findViewById(R.id.button2);

        addRecipe.setOnClickListener(view1 -> {
            finish();
        });
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
    }
}