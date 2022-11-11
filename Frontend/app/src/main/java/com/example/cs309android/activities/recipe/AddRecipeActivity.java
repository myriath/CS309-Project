package com.example.cs309android.activities.recipe;

import static com.example.cs309android.util.Constants.CALLBACK_IMAGE_URI;
import static com.example.cs309android.util.Constants.INTENT_RECIPE_ADD;
import static com.example.cs309android.util.Constants.ITEM_ID_NULL;
import static com.example.cs309android.util.Constants.PARCEL_IMAGE_URI;
import static com.example.cs309android.util.Constants.PARCEL_INTENT_CODE;
import static com.example.cs309android.util.Constants.PARCEL_RECIPE;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.activities.SearchActivity;
import com.example.cs309android.fragments.ModalImageSelect;
import com.example.cs309android.interfaces.CallbackFragment;
import com.example.cs309android.models.api.models.Ingredient;
import com.example.cs309android.models.api.models.Instruction;
import com.example.cs309android.models.api.models.Recipe;
import com.example.cs309android.models.api.models.SimpleFoodItem;
import com.example.cs309android.models.api.request.recipes.AddRecipeImageRequest;
import com.example.cs309android.models.api.request.recipes.AddRecipeRequest;
import com.example.cs309android.models.api.request.recipes.GetRecipeImageRequest;
import com.example.cs309android.models.api.request.recipes.UpdateRecipeRequest;
import com.example.cs309android.models.api.response.GenericResponse;
import com.example.cs309android.models.api.response.recipes.AddRecipeResponse;
import com.example.cs309android.util.Constants;
import com.example.cs309android.util.Toaster;
import com.example.cs309android.util.Util;
import com.example.cs309android.views.IngredientEditView;
import com.example.cs309android.views.InstructionEditView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

/**
 * Activity for adding new recipes
 *
 * @author Travis Massner
 * @author Mitch Hudson
 */
public class AddRecipeActivity extends AppCompatActivity implements CallbackFragment {
    /**
     * Launcher for adding ingredients
     */
    private ActivityResultLauncher<Intent> foodSearchLauncher;

    /**
     * Image for the recipe
     */
    private Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        LinearLayout ingredientList = findViewById(R.id.ingredients);
        LinearLayout instructionList = findViewById(R.id.instructions);

        foodSearchLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        SimpleFoodItem item = Objects.requireNonNull(data).getParcelableExtra(Constants.PARCEL_FOODITEM);

                        final int ingredientNum = ingredientList.getChildCount();
                        IngredientEditView ingredientView = new IngredientEditView(this);
                        ingredientView.initView(item, "TODO", view1 -> {
                            ingredientList.removeViewAt(ingredientView.getPosition());
                            for (int i = ingredientView.getPosition(); i < ingredientList.getChildCount(); i++) {
                                ((IngredientEditView) ingredientList.getChildAt(i)).setPosition(i);
                            }
                        }, ingredientNum);

                        ingredientList.addView(ingredientView);
                    }
                }
        );

        findViewById(R.id.addIngredient).setOnClickListener(view -> {
            Intent intent = new Intent(this, SearchActivity.class);
            intent.putExtra(PARCEL_INTENT_CODE, INTENT_RECIPE_ADD);
            foodSearchLauncher.launch(intent);
        });

        findViewById(R.id.addInstruction).setOnClickListener(view -> {
            final int instNum = instructionList.getChildCount();
            InstructionEditView instructionView = new InstructionEditView(this);
            instructionView.initView(view1 -> {
                instructionList.removeViewAt(instructionView.getPosition());
                for (int i = instructionView.getPosition(); i < instructionList.getChildCount(); i++) {
                    ((InstructionEditView) instructionList.getChildAt(i)).setPosition(i);
                }
            }, instNum);

            instructionList.addView(instructionView);
        });

        ExtendedFloatingActionButton addRecipe = findViewById(R.id.add_recipe_button);

        findViewById(R.id.addImageButton).setOnClickListener(view -> {
            ModalImageSelect select = new ModalImageSelect();
            select.setCallbackFragment(this);
            select.show(getSupportFragmentManager(), ModalImageSelect.TAG);
        });

        Intent launchIntent = getIntent();
        Recipe recipe = launchIntent.getParcelableExtra(PARCEL_RECIPE);
        if (recipe != null) {
            new GetRecipeImageRequest(String.valueOf(recipe.getRecipeID())).request(response -> {
                ((ImageView) findViewById(R.id.image_view)).setImageBitmap(response);
            }, AddRecipeActivity.this);

            ((TextView) findViewById(R.id.name)).setText(recipe.getRecipeName());
            ((TextView) findViewById(R.id.recipeDescription)).setText(recipe.getDescription());

            for (Ingredient ingredient : recipe.getIngredients()) {
                View view = View.inflate(this, R.layout.ingredient_display_list_item, null);
                String text = ingredient.getQuantity() + " " + ingredient.getUnit();
                ((TextView) view.findViewById(R.id.quantity)).setText(text);
                ((TextView) view.findViewById(R.id.name)).setText(ingredient.getFood().getCappedDescription(25));

                ingredientList.addView(view);
            }

            Instruction[] instructions = recipe.getInstructions();
            Arrays.sort(instructions, new Instruction.Sorter());
            for (Instruction instruction : instructions) {
                View view = View.inflate(this, R.layout.instruction_layout, null);
                ((TextView) view.findViewById(R.id.stepNum)).setText(instruction.getStepNum());
                ((TextView) view.findViewById(R.id.stepText)).setText(instruction.getStepText());

                instructionList.addView(view);
            }
        }

        addRecipe.setOnClickListener(view1 -> {
            save(ingredientList, instructionList, recipe != null ? recipe.getRecipeID() : ITEM_ID_NULL);
        });
    }

    /**
     * Checks for valid inputs and then either creates a new Recipe or updates it based on the rid given
     *
     * @param ingredientList    LinearLayout to check
     * @param instructionList   LinearLayout to check
     * @param id                ID to update. If this is an add, use ITEM_ID_NULL
     */
    public void save(LinearLayout ingredientList, LinearLayout instructionList, int id) {
        TextInputLayout nameInput = findViewById(R.id.recipeName);
        TextInputLayout descriptionInput = findViewById(R.id.recipeDescription);
        if (!validateFields(nameInput, descriptionInput)) return;

        String name = Objects.requireNonNull(nameInput.getEditText()).getText().toString();
        String description = Objects.requireNonNull(descriptionInput.getEditText()).getText().toString();

        if (ingredientList.getChildCount() < 1 || instructionList.getChildCount() < 1) return;
        Ingredient[] ingredients = new Ingredient[ingredientList.getChildCount()];
        for (int i = 0; i < ingredients.length; i++) {
            ingredients[i] = ((IngredientEditView) ingredientList.getChildAt(i)).getIngredient();
        }
        Instruction[] instructions = new Instruction[instructionList.getChildCount()];
        for (int i = 0; i < ingredients.length; i++) {
            instructions[i] = ((InstructionEditView) instructionList.getChildAt(i)).getInstruction();
        }

        String token = ((GlobalClass) getApplicationContext()).getToken();

        Response.Listener<JSONObject> listener = response -> {
            AddRecipeResponse recipeResponse = Util.objFromJson(response, AddRecipeResponse.class);

            if (recipeResponse.getResult() == Constants.RESULT_RECIPE_CREATED) {
                if (image != null) {
                    new AddRecipeImageRequest(image, token, String.valueOf(recipeResponse.getRid())).request(response1 -> {
                        GenericResponse recipeResponse1 = Util.objFromJson(response, GenericResponse.class);

                        if (recipeResponse1.getResult() != Constants.RESULT_OK) {
                            Toaster.toastShort("Something went wrong", this);
                        } else {
                            Toaster.toastShort("Recipe Added", this);
                        }
                        finish();
                    }, AddRecipeActivity.this);
                } else {
                    Toaster.toastShort("Recipe Added", this);
                    finish();
                }
            } else if (recipeResponse.getResult() == Constants.RESULT_ERROR_RID_TAKEN) {
                Toaster.toastShort("That recipe is already taken", this);
            } else {
                Toaster.toastShort("Something went wrong", this);
            }
        };

        if (id == Constants.ITEM_ID_NULL) {
            new AddRecipeRequest(token, name, description, ingredients, instructions).request(listener,
                    AddRecipeActivity.this);
        } else {
            new UpdateRecipeRequest(id, token, name, description, ingredients, instructions).request(listener,
                    AddRecipeActivity.this);
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

    /**
     * Validates the user input is not empty for recipe name and recipe instructions
     */
    private boolean validateFields(TextInputLayout RecipeName, TextInputLayout RecipeInstructions) {
        boolean notEmpty = true;
        if (Objects.requireNonNull(RecipeName.getEditText()).getText().length() <= 0) {
            RecipeName.setError("Recipe Name cannot be empty");
            notEmpty = false;
        }
        else {
            RecipeName.setError(null);
        }

        if (Objects.requireNonNull(RecipeInstructions.getEditText()).getText().length() <= 0) {
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
                image = ImageDecoder.decodeBitmap(source);
                ((ImageView) findViewById(R.id.image_view)).setImageBitmap(image);
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