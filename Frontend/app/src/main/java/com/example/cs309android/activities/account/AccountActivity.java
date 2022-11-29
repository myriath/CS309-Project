package com.example.cs309android.activities.account;

import static com.example.cs309android.util.Constants.Parcels.PARCEL_ACCOUNT_LIST;
import static com.example.cs309android.util.Constants.Parcels.PARCEL_FOLLOWING;
import static com.example.cs309android.util.Constants.Parcels.PARCEL_TITLE;
import static com.example.cs309android.util.Constants.Parcels.PARCEL_USERNAME;
import static com.example.cs309android.util.Constants.UserType.USER_ADM;
import static com.example.cs309android.util.Constants.UserType.USER_MOD;
import static com.example.cs309android.util.Constants.UserType.USER_REG;
import static com.example.cs309android.util.Util.objFromJson;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.models.adapters.RecipeListAdapter;
import com.example.cs309android.models.api.request.profile.GetBannerRequest;
import com.example.cs309android.models.api.request.profile.GetProfilePictureRequest;
import com.example.cs309android.models.api.request.profile.GetProfileRequest;
import com.example.cs309android.models.api.request.recipes.GetRecipesRequest;
import com.example.cs309android.models.api.request.social.FollowRequest;
import com.example.cs309android.models.api.request.social.GetFollowersRequest;
import com.example.cs309android.models.api.request.social.GetFollowingRequest;
import com.example.cs309android.models.api.request.social.UnfollowRequest;
import com.example.cs309android.models.api.request.users.ChangeUserTypeRequest;
import com.example.cs309android.models.api.request.users.DeleteUserRequest;
import com.example.cs309android.models.api.response.GenericResponse;
import com.example.cs309android.models.api.response.recipes.GetRecipesResponse;
import com.example.cs309android.models.api.response.social.FollowResponse;
import com.example.cs309android.models.api.response.social.GetProfileResponse;
import com.example.cs309android.util.Constants;
import com.example.cs309android.util.Toaster;
import com.example.cs309android.util.Util;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

/**
 * Account activity used to view an account's details
 * when clicked on
 * (ex: from a recipe details page or search)
 *
 * @author Mitch Hudson
 */
public class AccountActivity extends AppCompatActivity {
    /**
     * Used to track following status for the follow button
     */
    private boolean isFollowing;
    /**
     * Keeps track of the follower count to display
     * Default 0
     */
    private int followers = 0;
    /**
     * Used to edit a user's account
     */
    private ActivityResultLauncher<Intent> editUserLauncher;

    /**
     * Runs when the activity is created
     *
     * @param savedInstanceState Saved state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        GlobalClass global = (GlobalClass) getApplicationContext();

        Intent intent = getIntent();
        String username = intent.getStringExtra(PARCEL_USERNAME);
        isFollowing = intent.getBooleanExtra(PARCEL_FOLLOWING, false);
        boolean owner = global.getUsername().equals(username);

        ImageButton backButton = findViewById(R.id.backButton);

        ExtendedFloatingActionButton followButton = findViewById(R.id.followButton);
        backButton.setOnClickListener(view1 -> onBackPressed());

        TextView followerCount = findViewById(R.id.followerCount);
        TextView followingCount = findViewById(R.id.followingCount);

        editUserLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> update(username, followerCount, followingCount, owner, global)
        );

        if (!owner) {
            if (isFollowing) {
                followButton.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_unfollow, getTheme()));
                followButton.setText(getResources().getString(R.string.unfollow));
            }
            followButton.setOnClickListener(view1 -> {
                if (!isFollowing()) {
                    new FollowRequest(global.getToken(), username).request(response -> {
                        FollowResponse followResponse = Util.objFromJson(response, FollowResponse.class);
                        if (followResponse.getResult() == Constants.Results.RESULT_OK) {
                            followButton.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_unfollow, getTheme()));
                            followButton.setText(getResources().getString(R.string.unfollow));
                            setFollowing(true);
                            followers++;
                            followerCount.setText(String.format(Locale.getDefault(), "%d Followers", followers));
                        } else {
                            Toaster.toastShort("Error", this);
                        }
                    }, AccountActivity.this);
                } else {
                    new UnfollowRequest(global.getToken(), username).request(response -> {
                        FollowResponse followResponse = Util.objFromJson(response, FollowResponse.class);
                        if (followResponse.getResult() == Constants.Results.RESULT_OK) {
                            followButton.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_favorite, getTheme()));
                            followButton.setText(getResources().getString(R.string.follow));
                            setFollowing(false);
                            followers--;
                            followerCount.setText(String.format(Locale.getDefault(), "%d Followers", followers));
                        } else {
                            Toaster.toastShort("Error", this);
                        }
                    }, AccountActivity.this);
                }
            });
            followButton.setVisibility(View.VISIBLE);
        }

        findViewById(R.id.followerCount).setOnClickListener(view1 ->
                new GetFollowersRequest(username).request(response -> {
                    FollowResponse followResponse = Util.objFromJson(response, FollowResponse.class);
                    Intent intent1 = new Intent(this, AccountListActivity.class);
                    intent1.putExtra(PARCEL_ACCOUNT_LIST, followResponse.getUsers());
                    intent1.putExtra(PARCEL_TITLE, "Followers");
                    startActivity(intent1);
                }, AccountActivity.this));

        findViewById(R.id.followingCount).setOnClickListener(view1 ->
                new GetFollowingRequest(username).request(response -> {
                    FollowResponse followResponse = Util.objFromJson(response, FollowResponse.class);
                    Intent intent1 = new Intent(this, AccountListActivity.class);
                    intent1.putExtra(PARCEL_ACCOUNT_LIST, followResponse.getUsers());
                    intent1.putExtra(PARCEL_TITLE, "Following");
                    startActivity(intent1);
                }, AccountActivity.this));

        update(username, followerCount, followingCount, owner, global);
    }

    /**
     * Updates the view with new data
     *
     * @param username       Username of the account to show
     * @param followerCount  Follower count view
     * @param followingCount Following count view
     * @param owner          True if the account displayed is the account logged in
     * @param global         GlobalClass for getting authentication token from
     */
    public void update(String username, TextView followerCount, TextView followingCount, boolean owner, GlobalClass global) {
        new GetProfileRequest(username).request(response -> {
            GetProfileResponse profileResponse = objFromJson(response, GetProfileResponse.class);
            if (profileResponse.getResult() == Constants.Results.RESULT_OK) {
                followers = profileResponse.getFollowers();
                ((TextView) findViewById(R.id.unameView)).setText(username);
                ((TextView) findViewById(R.id.bioTextView)).setText(profileResponse.getBio());
                followerCount.setText(String.format(Locale.getDefault(), "%d Followers", profileResponse.getFollowers()));
                followingCount.setText(String.format(Locale.getDefault(), "%d Following", profileResponse.getFollowing()));

                ((TextView) findViewById(R.id.bioTextView)).setText(profileResponse.getBio());

                ImageView badge = findViewById(R.id.badge);
                Util.getBadge(global.getUserType(), badge);

                // Setup menu button if necessary
                if (global.getUserType() > USER_REG || owner) {
                    findViewById(R.id.menuCard).setVisibility(View.VISIBLE);
                    findViewById(R.id.menuButton).setOnClickListener(view -> {
                        PopupMenu menu = new PopupMenu(this, view);
                        menu.inflate(R.menu.moderation_menu);
                        menu.show();
                        if (owner) {
                            MenuItem deleteButton = menu.getMenu().findItem(R.id.delete);
                            deleteButton.setEnabled(false);
                            deleteButton.setVisible(false);
                        }
                        if (global.getUserType() > USER_ADM || (global.getUserType() > USER_MOD && profileResponse.getUserType() < USER_ADM)) {
                            MenuItem changeUserButton = menu.getMenu().findItem(R.id.change_type);
                            changeUserButton.setEnabled(true);
                            changeUserButton.setVisible(true);
                        }
                        menu.setOnMenuItemClickListener(item -> {
                            int id = item.getItemId();
                            if (id == R.id.edit) {
                                Intent intent1 = new Intent(this, AccountEditActivity.class);
                                intent1.putExtra(PARCEL_USERNAME, username);
                                editUserLauncher.launch(intent1);
                            } else if (id == R.id.delete) {
                                new DeleteUserRequest(username, global.getToken()).request(response1 -> {
                                    GenericResponse genericResponse = Util.objFromJson(response1, GenericResponse.class);
                                    if (genericResponse.getResult() != RESULT_OK) {
                                        Toaster.toastShort("Error", this);
                                    }
                                }, error -> Toaster.toastShort("Error", this), AccountActivity.this);
                                finish();
                            } else if (id == R.id.change_type) {
                                PopupMenu menu1 = new PopupMenu(this, menu.getMenu().findItem(R.id.change_type).getActionView());
                                menu1.inflate(R.menu.user_types);
                                menu1.show();
                                menu1.setOnMenuItemClickListener(item1 -> {
                                    int id1 = item1.getItemId();
                                    int newType;
                                    if (id1 == R.id.moderator) {
                                        newType = USER_MOD;
                                    } else if (id1 == R.id.admin) {
                                        newType = USER_ADM;
                                    } else {
                                        newType = USER_REG;
                                    }
                                    new ChangeUserTypeRequest(username, global.getToken(), newType).request(response1 -> {
                                        GenericResponse genericResponse = Util.objFromJson(response1, GenericResponse.class);
                                        if (genericResponse.getResult() != RESULT_OK) {
                                            Toaster.toastShort("Error", this);
                                        }
                                    }, error -> Toaster.toastShort("Error", this), AccountActivity.this);
                                    return true;
                                });
                            }
                            return true;
                        });
                    });
                } else {
                    Toaster.toastShort("Error", this);
                }
            }
        }, AccountActivity.this);

        new GetProfilePictureRequest(username).request((ImageView) findViewById(R.id.profile_picture), AccountActivity.this);
        new GetBannerRequest(username).request((ImageView) findViewById(R.id.banner), AccountActivity.this);

        new GetRecipesRequest(username).request(response -> {
            GetRecipesResponse postsResponse = objFromJson(response, GetRecipesResponse.class);
            if (postsResponse.getItems() != null && postsResponse.getItems().length > 0) {
                findViewById(R.id.recipesLabel).setVisibility(View.VISIBLE);
                ((ListView) findViewById(R.id.yourRecipesList)).setAdapter(new RecipeListAdapter(this, new ArrayList<>(Arrays.asList(postsResponse.getItems()))));
            }
        }, AccountActivity.this);
    }

    /**
     * Returns the current following boolean
     *
     * @return True if the account displayed is being followed
     */
    public boolean isFollowing() {
        return isFollowing;
    }

    /**
     * Sets the current following boolean
     *
     * @param following True if the account displayed is being followed
     */
    public void setFollowing(boolean following) {
        this.isFollowing = following;
    }
}
