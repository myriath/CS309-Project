package com.example.cs309android.activities.account;

import static com.example.cs309android.util.Constants.PARCEL_FOLLOWING;
import static com.example.cs309android.util.Constants.PARCEL_OWNER;
import static com.example.cs309android.util.Constants.PARCEL_USERNAME;
import static com.example.cs309android.util.Util.objFromJson;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.models.adapters.FeedAdapter;
import com.example.cs309android.models.api.request.profile.GetBannerRequest;
import com.example.cs309android.models.api.request.profile.GetProfilePictureRequest;
import com.example.cs309android.models.api.request.profile.GetProfileRequest;
import com.example.cs309android.models.api.request.recipes.GetRecipesRequest;
import com.example.cs309android.models.api.request.social.FollowRequest;
import com.example.cs309android.models.api.request.social.UnfollowRequest;
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
    private boolean following;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        GlobalClass global = (GlobalClass) getApplicationContext();

        Intent intent = getIntent();
        boolean owner = intent.getBooleanExtra(PARCEL_OWNER, false);
        following = intent.getBooleanExtra(PARCEL_FOLLOWING, false);
        String username = intent.getStringExtra(PARCEL_USERNAME);

        ImageButton backButton = findViewById(R.id.backButton);

        ExtendedFloatingActionButton followButton = findViewById(R.id.followButton);
        backButton.setOnClickListener(view1 -> onBackPressed());

        if (!owner) {
            followButton.setOnClickListener(view1 -> {
                if (!isFollowing()) {
                    new FollowRequest(global.getToken(), username).request(response -> {
                        FollowResponse followResponse = Util.objFromJson(response, FollowResponse.class);
                        if (followResponse.getResult() == Constants.RESULT_OK) {
                            followButton.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_unfollow, getTheme()));
                            followButton.setText(getResources().getString(R.string.unfollow));
                            setFollowing(true);
                        } else {
                            Toaster.toastShort("Error", this);
                        }
                    }, AccountActivity.this);
                } else {
                    new UnfollowRequest(global.getToken(), username).request(response -> {
                        FollowResponse followResponse = Util.objFromJson(response, FollowResponse.class);
                        if (followResponse.getResult() == Constants.RESULT_OK) {
                            followButton.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_favorite, getTheme()));
                            followButton.setText(getResources().getString(R.string.follow));
                            setFollowing(false);
                        } else {
                            Toaster.toastShort("Error", this);
                        }
                    }, AccountActivity.this);
                }
            });
            followButton.setVisibility(View.VISIBLE);
        }

        new GetProfileRequest(username).request(response -> {
            GetProfileResponse profileResponse = objFromJson(response, GetProfileResponse.class);
            if (profileResponse.getResult() == Constants.RESULT_OK) {
                ((TextView) findViewById(R.id.unameView)).setText(username);
                ((TextView) findViewById(R.id.bioTextView)).setText(profileResponse.getBio());
                ((TextView) findViewById(R.id.followerCount))
                        .setText(String.format(Locale.getDefault(), "%d Followers", profileResponse.getFollowers()));
                ((TextView) findViewById(R.id.followingCount))
                        .setText(String.format(Locale.getDefault(), "%d Following", profileResponse.getFollowing()));

                ((TextView) findViewById(R.id.bioTextView)).setText(profileResponse.getBio());
            } else {
                Toaster.toastShort("Error", this);
            }
        }, AccountActivity.this);

        new GetProfilePictureRequest(username).request(response -> {
            ((ImageView) findViewById(R.id.profile_picture)).setImageBitmap(response);
        }, AccountActivity.this);

        new GetBannerRequest(username).request(response -> {
            ((ImageView) findViewById(R.id.banner)).setImageBitmap(response);
        }, AccountActivity.this);

        new GetRecipesRequest(username).request(response -> {
            GetRecipesResponse postsResponse = objFromJson(response, GetRecipesResponse.class);
            ((ListView) findViewById(R.id.yourRecipesList)).setAdapter(new FeedAdapter(this, new ArrayList<>(Arrays.asList(postsResponse.getItems()))));
        }, AccountActivity.this);
    }

    /**
     * Returns the current following boolean
     * @return True if the account displayed is being followed
     */
    public boolean isFollowing() {
        return following;
    }

    /**
     * Sets the current following boolean
     * @param following True if the account displayed is being followed
     */
    public void setFollowing(boolean following) {
        this.following = following;
    }
}
