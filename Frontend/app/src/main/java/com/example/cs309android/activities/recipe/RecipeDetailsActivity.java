package com.example.cs309android.activities.recipe;

import static com.example.cs309android.util.Constants.ITEM_ID_NULL;
import static com.example.cs309android.util.Constants.Parcels.PARCEL_FOODITEM;
import static com.example.cs309android.util.Constants.Parcels.PARCEL_RECIPE;
import static com.example.cs309android.util.Constants.Parcels.PARCEL_RECIPE_ID;
import static com.example.cs309android.util.Constants.UserType.USER_REG;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.activities.food.FoodDetailsActivity;
import com.example.cs309android.models.api.models.Comment;
import com.example.cs309android.models.api.models.Ingredient;
import com.example.cs309android.models.api.models.Instruction;
import com.example.cs309android.models.api.models.Recipe;
import com.example.cs309android.models.api.models.User;
import com.example.cs309android.models.api.request.profile.GetProfilePictureRequest;
import com.example.cs309android.models.api.request.recipes.GetRecipeImageRequest;
import com.example.cs309android.models.api.request.recipes.RemoveRecipeRequest;
import com.example.cs309android.models.api.request.social.CommentRequest;
import com.example.cs309android.models.api.request.social.GetCommentsRequest;
import com.example.cs309android.models.api.request.users.GetUserTypeRequest;
import com.example.cs309android.models.api.response.GenericResponse;
import com.example.cs309android.models.api.response.social.CommentsResponse;
import com.example.cs309android.util.Constants;
import com.example.cs309android.util.Toaster;
import com.example.cs309android.util.Util;
import com.example.cs309android.views.CommentView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Arrays;
import java.util.Objects;

/**
 * Activity for viewing a recipe's details
 *
 * @author Travis Massner
 * @author Mitch Hudson
 */
public class RecipeDetailsActivity extends AppCompatActivity {
    /**
     * Launcher for the edit recipe activity
     */
    private ActivityResultLauncher<Intent> editLauncher;

    /**
     * Runs when the activity starts
     *
     * @param savedInstanceState saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

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
     *
     * @param recipe Recipe to fill the details for
     * @param global Global to check account with
     */
    public void setDetails(Recipe recipe, GlobalClass global) {
        ImageView image = findViewById(R.id.image_view);
        String username = recipe.getUser().getUsername();
        new GetRecipeImageRequest(String.valueOf(recipe.getRecipeID())).request(image, this);

        new GetProfilePictureRequest(username).request((ImageView) findViewById(R.id.profile_picture), RecipeDetailsActivity.this);
        new GetUserTypeRequest(username).request(response -> {
            GenericResponse genericResponse = Util.objFromJson(response, GenericResponse.class);
            Util.getBadge(genericResponse.getResult(), findViewById(R.id.badge));
        }, RecipeDetailsActivity.this);

        ((TextView) findViewById(R.id.username)).setText(username);
        findViewById(R.id.creator).setOnClickListener(view -> Util.openAccountPage(global, username, this));

        ((TextView) findViewById(R.id.recipeTitle)).setText(recipe.getRname());
        ((TextView) findViewById(R.id.recipeDescription)).setText(recipe.getDescription());

        LinearLayout ingredientsList = findViewById(R.id.ingredients);
        for (Ingredient ingredient : recipe.getIngredients()) {
            View view = View.inflate(this, R.layout.ingredient_layout, null);
            String text = ingredient.getQuantity() + " " + ingredient.getUnit();
            ((TextView) view.findViewById(R.id.quantity)).setText(text);
            ((TextView) view.findViewById(R.id.name)).setText(ingredient.getFood().getCappedDescription(25));

            view.setOnClickListener(view1 -> {
                Intent intent = new Intent(this, FoodDetailsActivity.class);
                intent.putExtra(PARCEL_FOODITEM, ingredient.getFood());
                startActivity(intent);
            });

            ingredientsList.addView(view);
        }

        LinearLayout instructionsList = findViewById(R.id.instructions);
        Instruction[] instructions = recipe.getInstructions();
        Arrays.sort(instructions, new Instruction.Sorter());
        for (Instruction instruction : instructions) {
            View view = View.inflate(this, R.layout.instruction_layout, null);
            ((TextView) view.findViewById(R.id.stepNum)).setText(String.valueOf(instruction.getStepNum()));
            ((TextView) view.findViewById(R.id.stepText)).setText(instruction.getStepText());

            instructionsList.addView(view);
        }

        if (recipe.getUser().getUsername().equals(global.getUsername()) || global.getUserType() > USER_REG) {
            findViewById(R.id.menuCard).setVisibility(View.VISIBLE);
            findViewById(R.id.menuButton).setOnClickListener(view -> {
                PopupMenu menu = new PopupMenu(this, view);
                menu.inflate(R.menu.moderation_menu);
                menu.show();
                menu.setOnMenuItemClickListener(item -> {
                    int id = item.getItemId();
                    if (id == R.id.edit) {
                        Intent intent = new Intent(this, AddRecipeActivity.class);
                        intent.putExtra(PARCEL_RECIPE, recipe);
                        editLauncher.launch(intent);
                    } else if (id == R.id.delete) {
                        new RemoveRecipeRequest(recipe.getRecipeID(), global.getToken()).request(response -> {
                            GenericResponse genericResponse = Util.objFromJson(response, GenericResponse.class);
                            if (genericResponse.getResult() != Constants.Results.RESULT_OK) {
                                Toaster.toastShort("Error", this);
                                return;
                            }
                            Intent intent = new Intent();
                            intent.putExtra(PARCEL_RECIPE_ID, recipe.getRecipeID());
                            setResult(Constants.Results.RESULT_DELETED, intent);
                            finish();
                        }, error -> Toaster.toastShort("Error", this), RecipeDetailsActivity.this);
                    }
                    return true;
                });
            });

            findViewById(R.id.favoriteButton).setVisibility(View.GONE);
        }

        findViewById(R.id.backButton).setOnClickListener(view -> onBackPressed());

        LinearLayout comments = findViewById(R.id.comments);
        findViewById(R.id.postButton).setOnClickListener(view -> {
            EditText inputEditText = ((TextInputLayout) findViewById(R.id.newComment)).getEditText();
            String commentText = Objects.requireNonNull(inputEditText).getText().toString();
            new CommentRequest(commentText, recipe.getRecipeID(), global.getToken()).request(response -> {
                GenericResponse genericResponse = Util.objFromJson(response, GenericResponse.class);
                if (genericResponse.getResult() != Constants.Results.RESULT_OK) {
                    Toaster.toastShort("Error", this);
                    return;
                }
                CommentView commentView = new CommentView(this);
                Comment comment = new Comment(new User(global.getUsername(), 0, ""), commentText, ITEM_ID_NULL);
                commentView.initView(comment, onEdit -> commentView.toggleEditable(), onDelete -> comments.removeView(view), global);
                comments.addView(commentView, 0);
                inputEditText.setText(null);
                Util.hideKeyboard(view, this);

            }, error -> {
                error.printStackTrace();
                Toaster.toastShort("Error", this);
            }, RecipeDetailsActivity.this);
        });

        new GetCommentsRequest(recipe.getRecipeID()).request(response -> {
            CommentsResponse commentsResponse = Util.objFromJson(response, CommentsResponse.class);
            refreshComments(commentsResponse.getComments(), comments, global);
        }, RecipeDetailsActivity.this);

        refreshComments(recipe.getComments(), comments, global);
    }

    /**
     * Refreshes the comments
     *
     * @param comments New comments
     * @param layout   LinearLayout to display comments
     * @param global   GlobalClass for user type checking
     */
    public void refreshComments(Comment[] comments, LinearLayout layout, GlobalClass global) {
        if (comments == null) return;
        layout.removeAllViews();
        for (Comment comment : comments) {
            CommentView view = new CommentView(this);
            view.initView(comment, toEdit -> view.toggleEditable(), deleted -> layout.removeView(view), global);
            view.setOnClickListener(view1 -> Util.openAccountPage(global, comment.getUser().getUsername(), this));
            layout.addView(view);
        }
    }
}