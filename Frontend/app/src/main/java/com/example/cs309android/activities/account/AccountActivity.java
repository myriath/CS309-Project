package com.example.cs309android.activities.account;

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

import com.example.cs309android.R;
import com.example.cs309android.models.adapters.FeedAdapter;
import com.example.cs309android.models.api.request.profile.GetBannerRequest;
import com.example.cs309android.models.api.request.profile.GetProfilePictureRequest;
import com.example.cs309android.models.api.request.profile.GetProfileRequest;
import com.example.cs309android.models.api.request.recipes.GetRecipesRequest;
import com.example.cs309android.models.api.response.recipes.GetRecipesResponse;
import com.example.cs309android.models.api.response.social.GetProfileResponse;
import com.example.cs309android.util.Constants;
import com.example.cs309android.util.Toaster;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class AccountActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Intent intent = getIntent();
        boolean owner = intent.getBooleanExtra(PARCEL_OWNER, false);
        String username = intent.getStringExtra(PARCEL_USERNAME);

        ImageButton backButton = findViewById(R.id.backButton);

        ExtendedFloatingActionButton followButton = findViewById(R.id.followButton);
        backButton.setOnClickListener(view1 -> onBackPressed());

        if (!owner) {
            followButton.setOnClickListener(view1 -> {
                // TODO: Follow functionality
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
}
