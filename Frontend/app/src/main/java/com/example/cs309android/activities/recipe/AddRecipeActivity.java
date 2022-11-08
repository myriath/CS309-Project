package com.example.cs309android.activities.recipe;

import static com.example.cs309android.util.Constants.CALLBACK_IMAGE_URI;
import static com.example.cs309android.util.Constants.INTENT_RECIPE_ADD;
import static com.example.cs309android.util.Constants.PARCEL_IMAGE_URI;
import static com.example.cs309android.util.Constants.PARCEL_INTENT_CODE;

import android.content.Intent;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cs309android.R;
import com.example.cs309android.activities.SearchActivity;
import com.example.cs309android.fragments.ModalImageSelect;
import com.example.cs309android.interfaces.CallbackFragment;
import com.example.cs309android.models.gson.models.SimpleFoodItem;
import com.example.cs309android.util.Constants;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.util.Objects;

public class AddRecipeActivity extends AppCompatActivity implements CallbackFragment {
    /**
     * Launcher for adding ingredients
     */
    private ActivityResultLauncher<Intent> foodSearchLauncher;

    private float dp16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        dp16 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16f, getResources().getDisplayMetrics());

        LinearLayout ingredientList = findViewById(R.id.ingredients);
        LinearLayout instructionList = findViewById(R.id.instructions);

        foodSearchLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        SimpleFoodItem item = Objects.requireNonNull(data).getParcelableExtra(Constants.PARCEL_FOODITEM);

                        View inflatedView = View.inflate(this, R.layout.ingredient_add_list_item, null);
                        ((TextView) inflatedView.findViewById(R.id.name)).setText(item.getCappedDescription(25));
                        ingredientList.addView(inflatedView);
                    }
                }
        );

        findViewById(R.id.addIngredient).setOnClickListener(view -> {
            Intent intent = new Intent(this, SearchActivity.class);
            intent.putExtra(PARCEL_INTENT_CODE, INTENT_RECIPE_ADD);
            foodSearchLauncher.launch(intent);
        });

        findViewById(R.id.addInstruction).setOnClickListener(view -> {
            View inflatedView = View.inflate(this, R.layout.text_layout, null);
            TextInputLayout inputLayout = inflatedView.findViewById(R.id.inputLayout);
            TextInputEditText editText = inflatedView.findViewById(R.id.editText);

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 0, (int) dp16);
            inputLayout.setLayoutParams(params);

            editText.setPadding((int) dp16, (int) dp16, (int) dp16, (int) dp16);
            editText.setHint("Step " + (instructionList.getChildCount() + 1) + ".");

            instructionList.addView(inflatedView);
        });

        ExtendedFloatingActionButton addRecipe = findViewById(R.id.add_recipe_button);

//        addRecipe.setOnClickListener(view1 -> {
//            TextInputLayout nameInput = findViewById(R.id.recipeName);
//            TextInputLayout descriptionInput = findViewById(R.id.recipeDescription);
//            if (!validateFields(nameInput, descriptionInput)) return;
//
//            String name = Objects.requireNonNull(nameInput.getEditText()).getText().toString();
//            String description = Objects.requireNonNull(descriptionInput.getEditText()).getText().toString();
//            Ingredient[] ingredients = ;
//            String[] instructions;
//            new AddRecipeRequest(((GlobalClass) getApplicationContext()).getToken(), name, description, ingredients, instructions).request(response -> {
//                GenericResponse recipeResponse = Util.objFromJson(response, GenericResponse.class);
//
//                if (recipeResponse.getResult() == Constants.RESULT_RECIPE_CREATED) {
//                    Toaster.toastShort("Recipe Added", this);
//                    finish();
//                }
//                else if(recipeResponse.getResult() == Constants.RESULT_ERROR_RID_TAKEN) {
//                    Toaster.toastShort("That recipe is already taken", this);
//                }
//                else {
//                    Toaster.toastShort("Something went wrong", this);
//                }
//            },this);
//        });

        findViewById(R.id.addImageButton).setOnClickListener(view -> {
            ModalImageSelect select = new ModalImageSelect();
            select.setCallbackFragment(this);
            select.show(getSupportFragmentManager(), ModalImageSelect.TAG);
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
            RecipeName.setError("Recipe Name cannot be empty");
            notEmpty = false;
        }
        else {
            RecipeName.setError(null);
        }

        if (RecipeInstructions.getEditText().getText().length() <= 0) {
            RecipeInstructions.setError("Recipe Instructions cannot be empty");
            notEmpty = false;
        } else {
            RecipeInstructions.setError(null);
        }
        return notEmpty;
    }

    @Override
    public void callback(int op, Bundle bundle) {
        if (op == CALLBACK_IMAGE_URI) {
            try {
                Uri imageUri = bundle.getParcelable(PARCEL_IMAGE_URI);
                ImageDecoder.Source source = ImageDecoder.createSource(getContentResolver(), imageUri);
                ((ImageView) findViewById(R.id.image_view)).setImageBitmap(ImageDecoder.decodeBitmap(source));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Does nothing because this has no parent
     *
     * @param fragment Callback fragment.
     */
    @Override
    public void setCallbackFragment(CallbackFragment fragment) {
    }
}