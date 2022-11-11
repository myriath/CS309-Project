package com.example.cs309android.activities.recipe;

import static com.example.cs309android.util.Constants.PARCEL_RECIPE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.models.api.models.Ingredient;
import com.example.cs309android.models.api.models.Instruction;
import com.example.cs309android.models.api.models.Recipe;
import com.example.cs309android.models.api.request.recipes.GetRecipeImageRequest;

import java.util.Arrays;
import java.util.Objects;

/**
 * Activity for viewing a recipe
 *
 * @author Travis Massner
 * @author Mitch Hudson
 */
public class RecipeDetailsActivity extends AppCompatActivity {
    /**
     * Launcher for the edit recipe activity
     */
    private ActivityResultLauncher<Intent> editLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        GlobalClass global = (GlobalClass) getApplicationContext();

        editLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        clearDetails();
                        Recipe recipe = Objects.requireNonNull(result.getData()).getParcelableExtra(PARCEL_RECIPE);
                        setDetails(recipe, global);
                    }
                }
        );

        Intent i = getIntent();
        Recipe recipe = i.getParcelableExtra(PARCEL_RECIPE);

        setDetails(recipe, global);
    }

    /**
     * Clears the linear layouts to be repopulated
     */
    public void clearDetails() {
        ((LinearLayout) findViewById(R.id.ingredients)).removeAllViews();
        ((LinearLayout) findViewById(R.id.instructions)).removeAllViews();
    }

    /**
     * Fills in the activity with recipe details
     * @param recipe Recipe to fill the details for
     * @param global Global to check account with
     */
    public void setDetails(Recipe recipe, GlobalClass global) {
        ImageView image = findViewById(R.id.image_view);
        new GetRecipeImageRequest(String.valueOf(recipe.getRecipeID())).request(image::setImageBitmap,
                RecipeDetailsActivity.this);

        ((TextView) findViewById(R.id.name)).setText(recipe.getRecipeName());
        ((TextView) findViewById(R.id.recipeDescription)).setText(recipe.getDescription());

        LinearLayout ingredientsList = findViewById(R.id.ingredients);
        for (Ingredient ingredient : recipe.getIngredients()) {
            View view = View.inflate(this, R.layout.ingredient_display_list_item, null);
            String text = ingredient.getQuantity() + " " + ingredient.getUnit();
            ((TextView) view.findViewById(R.id.quantity)).setText(text);
            ((TextView) view.findViewById(R.id.name)).setText(ingredient.getFood().getCappedDescription(25));

            ingredientsList.addView(view);
        }

        LinearLayout instructionsList = findViewById(R.id.instructions);
        Instruction[] instructions = recipe.getInstructions();
        Arrays.sort(instructions, new Instruction.Sorter());
        for (Instruction instruction : instructions) {
            View view = View.inflate(this, R.layout.instruction_layout, null);
            ((TextView) view.findViewById(R.id.stepNum)).setText(instruction.getStepNum());
            ((TextView) view.findViewById(R.id.stepText)).setText(instruction.getStepText());

            instructionsList.addView(view);
        }

        if (recipe.getUsername().equals(global.getUsername())) {
            findViewById(R.id.editCard).setVisibility(View.VISIBLE);
            findViewById(R.id.editButton).setOnClickListener(view -> {
                Intent intent = new Intent(this, AddRecipeActivity.class);
                intent.putExtra(PARCEL_RECIPE, recipe);
                editLauncher.launch(intent);
            });
        }
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