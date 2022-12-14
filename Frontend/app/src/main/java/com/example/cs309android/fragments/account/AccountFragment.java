package com.example.cs309android.fragments.account;

import static com.example.cs309android.util.Constants.Callbacks.CALLBACK_MOVE_TO_SETTINGS;
import static com.example.cs309android.util.Constants.Callbacks.CALLBACK_START_LOGIN;
import static com.example.cs309android.util.Constants.ITEM_ID_NULL;
import static com.example.cs309android.util.Constants.Parcels.PARCEL_ACCOUNT_LIST;
import static com.example.cs309android.util.Constants.Parcels.PARCEL_RECIPE;
import static com.example.cs309android.util.Constants.Parcels.PARCEL_RECIPE_ID;
import static com.example.cs309android.util.Constants.Parcels.PARCEL_TITLE;
import static com.example.cs309android.util.Constants.Results.RESULT_DELETED;
import static com.example.cs309android.util.Constants.Results.RESULT_UPDATED;
import static com.example.cs309android.util.Util.objFromJson;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.activities.account.AccountEditActivity;
import com.example.cs309android.activities.account.AccountListActivity;
import com.example.cs309android.activities.recipe.RecipeDetailsActivity;
import com.example.cs309android.fragments.BaseFragment;
import com.example.cs309android.models.api.models.Recipe;
import com.example.cs309android.models.api.request.profile.GetProfileRequest;
import com.example.cs309android.models.api.request.recipes.GetRecipesRequest;
import com.example.cs309android.models.api.request.social.GetFollowersRequest;
import com.example.cs309android.models.api.request.social.GetFollowingRequest;
import com.example.cs309android.models.api.request.users.GetUserTypeRequest;
import com.example.cs309android.models.api.response.GenericResponse;
import com.example.cs309android.models.api.response.recipes.GetRecipesResponse;
import com.example.cs309android.models.api.response.social.FollowResponse;
import com.example.cs309android.models.api.response.social.GetProfileResponse;
import com.example.cs309android.util.Util;
import com.example.cs309android.views.HomeRecipeView;

import org.json.JSONException;

import java.util.Locale;
import java.util.Objects;

/**
 * Fragment to display account details
 *
 * @author Mitch Hudson
 */
public class AccountFragment extends BaseFragment {
    /**
     * Launcher for the account edit activity
     */
    private ActivityResultLauncher<Intent> accountEditLauncher;
    /**
     * Launcher for the recipe details activity
     */
    private ActivityResultLauncher<Intent> recipeDetailsLauncher;

    /**
     * Refresh the account page
     *
     * @param view   view to find subviews
     * @param global global containing account data
     */
    public void refreshAccount(View view, GlobalClass global) {
        if (global.getUsername() == null) {
            callbackFragment.callback(CALLBACK_START_LOGIN, null);
            return;
        }
        ImageView profilePicture = view.findViewById(R.id.profile_picture);
        ImageView bannerImage = view.findViewById(R.id.banner);

        profilePicture.setImageBitmap(global.getPfp());
        bannerImage.setImageBitmap(global.getBanner());
        ((TextView) view.findViewById(R.id.unameView)).setText(global.getUsername());

        ((TextView) view.findViewById(R.id.followerCount))
                .setText(String.format(Locale.getDefault(), "%d Followers", global.getFollowers()));
        ((TextView) view.findViewById(R.id.followingCount))
                .setText(String.format(Locale.getDefault(), "%d Following", global.getFollowing()));
        ((TextView) view.findViewById(R.id.bioTextView))
                .setText(global.getBio());
        Util.getBadge(global.getUserType(), view.findViewById(R.id.badge));

        // Checks for updates to the above values
        new GetUserTypeRequest(global.getUsername()).request(response -> {
            GenericResponse genericResponse = Util.objFromJson(response, GenericResponse.class);
            global.setUserType(genericResponse.getResult());
        }, requireContext());
        new GetProfileRequest(global.getUsername()).request(response -> {
            GetProfileResponse profileResponse = objFromJson(response, GetProfileResponse.class);
            global.setBio(profileResponse.getBio());
            global.setFollowers(profileResponse.getFollowers());
            global.setFollowing(profileResponse.getFollowing());

            ImageView badge = view.findViewById(R.id.badge);
            Util.getBadge(global.getUserType(), badge);

            ((TextView) view.findViewById(R.id.followerCount))
                    .setText(String.format(Locale.getDefault(), "%d Followers", global.getFollowers()));
            ((TextView) view.findViewById(R.id.followingCount))
                    .setText(String.format(Locale.getDefault(), "%d Following", global.getFollowing()));
            String bioText = profileResponse.getBio();
            if (bioText == null || bioText.length() == 0) {
                ((TextView) view.findViewById(R.id.bioTextView)).setText(R.string.nothing_yet);
            } else {
                ((TextView) view.findViewById(R.id.bioTextView)).setText(profileResponse.getBio());
            }
        }, requireContext());

        new GetRecipesRequest(global.getUsername()).request(response -> {
            try {
                System.out.println(response.toString(4));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            GetRecipesResponse postsResponse = objFromJson(response, GetRecipesResponse.class);
            if (postsResponse.getRecipes() == null || postsResponse.getRecipes().length < 1) return;

            view.findViewById(R.id.recipesLabel).setVisibility(View.VISIBLE);
            LinearLayout recipeList = view.findViewById(R.id.yourRecipesList);
            for (Recipe recipe : postsResponse.getRecipes()) {
                HomeRecipeView recipeView = new HomeRecipeView(requireContext());
                recipeView.initView(recipe, view1 -> {
                    Intent intent = new Intent(getContext(), RecipeDetailsActivity.class);
                    intent.putExtra(PARCEL_RECIPE, recipe);
                    recipeDetailsLauncher.launch(intent);
                });

                recipeList.addView(recipeView);
            }
        }, requireContext());
    }

    /**
     * Runs when the view is created
     *
     * @param inflater           Inflates the view of the fragment
     * @param container          Parent of the fragment
     * @param savedInstanceState Saved state
     * @return inflated view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        GlobalClass global = ((GlobalClass) requireActivity().getApplicationContext());

        accountEditLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> refreshAccount(view, global)
        );

        recipeDetailsLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    switch (result.getResultCode()) {
                        case RESULT_DELETED: {
//                            int id = Objects.requireNonNull(result.getData()).getIntExtra(PARCEL_RECIPE_ID, ITEM_ID_NULL);
//                            break;
                        }
                        case RESULT_UPDATED: {
//                            Recipe newRecipe = Objects.requireNonNull(result.getData()).getParcelableExtra(PARCEL_RECIPE);
//                            for ()
                            refreshAccount(view, global);
                            break;
                        }
                    }
                }
        );

        ImageButton settingsButton = view.findViewById(R.id.settingsButton);
        ImageButton editButton = view.findViewById(R.id.menuButton);

        settingsButton.setOnClickListener(view1 -> callbackFragment.callback(CALLBACK_MOVE_TO_SETTINGS, null));

        editButton.setOnClickListener(view1 -> {
            Intent intent = new Intent(requireContext(), AccountEditActivity.class);
            accountEditLauncher.launch(intent);
        });

        refreshAccount(view, global);

        String username = global.getUsername();
        view.findViewById(R.id.followerCount).setOnClickListener(view1 ->
                new GetFollowersRequest(username).request(response -> {
                    FollowResponse followResponse = Util.objFromJson(response, FollowResponse.class);
                    Intent intent1 = new Intent(getContext(), AccountListActivity.class);
                    intent1.putExtra(PARCEL_ACCOUNT_LIST, followResponse.getUsers());
                    intent1.putExtra(PARCEL_TITLE, "Followers");
                    startActivity(intent1);
                }, getContext()));

        view.findViewById(R.id.followingCount).setOnClickListener(view1 ->
                new GetFollowingRequest(username).request(response -> {
                    FollowResponse followResponse = Util.objFromJson(response, FollowResponse.class);
                    Intent intent1 = new Intent(getContext(), AccountListActivity.class);
                    intent1.putExtra(PARCEL_ACCOUNT_LIST, followResponse.getUsers());
                    intent1.putExtra(PARCEL_TITLE, "Following");
                    startActivity(intent1);
                }, getContext()));

        return view;
    }
}
