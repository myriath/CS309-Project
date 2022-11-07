package com.example.cs309android.activities.recipe;

import static com.example.cs309android.util.Constants.CALLBACK_IMAGE_URI;
import static com.example.cs309android.util.Constants.PARCEL_IMAGE_URI;

import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.cs309android.R;
import com.example.cs309android.fragments.ModalImageSelect;
import com.example.cs309android.interfaces.CallbackFragment;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;

public class AddRecipeActivity extends AppCompatActivity implements CallbackFragment {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        ExtendedFloatingActionButton addRecipe = findViewById(R.id.add_recipe_button);

        Toolbar toolbar = findViewById(R.id.toolbar);

//        setSupportActionBar(toolbar);
//        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
//        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
//        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

//        addRecipe.setOnClickListener(view1 -> {
//            TextInputLayout RnameInput = findViewById(R.id.recipeName);
//            TextInputLayout RInstructionsInput = findViewById(R.id.recipeInstructionsInput);
//            if(!validateFields(RnameInput, RInstructionsInput)) {
//                return;
//            }
//            new AddRecipeRequest(((GlobalClass) getApplicationContext()).getToken(), RnameInput.getEditText().getText().toString(), RInstructionsInput.getEditText().getText().toString()).request(response -> {
//                try {
//                    System.out.print(response.toString(4));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                GenericResponse recipeResponse = Util.objFromJson(response, GenericResponse.class);
//
//                if (recipeResponse.getResult() == Constants.RESULT_RECIPE_CREATED) {
//                    Toaster.toastShort("Recipe Added", this);
//                    RnameInput.getEditText().setText("");
//                    RInstructionsInput.getEditText().setText("");
//                }
//                else if(recipeResponse.getResult() == Constants.RESULT_ERROR_RID_TAKEN) {
//                    Toaster.toastShort("That recipe is already taken", this);
//
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