package com.example.cs309android.fragments.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.activities.MainActivity;
import com.example.cs309android.fragments.BaseFragment;
import com.example.cs309android.models.adapters.FeedAdapter;
import com.example.cs309android.models.gson.request.social.GetBannerRequest;
import com.example.cs309android.models.gson.request.social.GetProfilePictureRequest;
import com.example.cs309android.models.gson.request.social.GetProfileRequest;
import com.example.cs309android.models.gson.request.social.GetUserPostsRequest;
import com.example.cs309android.models.gson.response.social.GetProfileResponse;
import com.example.cs309android.models.gson.response.social.GetUserPostsResponse;
import com.example.cs309android.util.Util;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

/**
 * Fragment to display account details
 *
 * @author Mitch Hudson
 */
public class AccountFragment extends BaseFragment {
    public static final String ARGS_USERNAME = "username";
    private String username;
    public static final String ARGS_OWNER = "owner";
    private boolean owner;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AccountFragment.
     */
    public static AccountFragment newInstance(String username, boolean owner) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putString(ARGS_USERNAME, username);
        args.putBoolean(ARGS_OWNER, owner);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            username = args.getString(ARGS_USERNAME);
            owner = args.getBoolean(ARGS_OWNER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        // TODO: Get images, etc, from db with username

        GlobalClass global = ((GlobalClass) requireActivity().getApplicationContext());

        ImageButton settingsButton = view.findViewById(R.id.settingsButton);
        ImageButton editButton = view.findViewById(R.id.editButton);
        ImageButton backButton = view.findViewById(R.id.backButton);
        ExtendedFloatingActionButton followButton = view.findViewById(R.id.followButton);
        if (owner) {
            settingsButton.setOnClickListener(view1 -> {
                callbackFragment.callback(MainActivity.CALLBACK_MOVE_TO_SETTINGS, null);
            });
            settingsButton.setVisibility(View.VISIBLE);

            editButton.setOnClickListener(view1 -> {
                callbackFragment.callback(MainActivity.CALLBACK_EDIT_ACCOUNT, null);
            });
            editButton.setVisibility(View.VISIBLE);

            ((ImageView) view.findViewById(R.id.banner)).setImageBitmap(global.getBanner());
            ((ImageView) view.findViewById(R.id.profile_picture)).setImageBitmap(global.getPfp());
            ((TextView) view.findViewById(R.id.unameView)).setText(global.getUsername());
            ((TextView) view.findViewById(R.id.bioTextView)).setText(global.getBio());
        } else {
            backButton.setOnClickListener(view1 -> {
                callbackFragment.callback(MainActivity.CALLBACK_CLOSE_PROFILE, null);
            });
            backButton.setVisibility(View.VISIBLE);
            followButton.setOnClickListener(view1 -> {
                callbackFragment.callback(MainActivity.CALLBACK_FOLLOW, null);
            });
            followButton.setVisibility(View.VISIBLE);

            new GetProfileRequest(username).request(response -> {
                GetProfileResponse profileResponse = Util.objFromJson(response, GetProfileResponse.class);

                ((TextView) view.findViewById(R.id.unameView)).setText(profileResponse.getUsername());
                ((TextView) view.findViewById(R.id.bioTextView)).setText(profileResponse.getBio());
                ((TextView) view.findViewById(R.id.followerCount))
                        .setText(String.format(Locale.getDefault(), "%d Followers", profileResponse.getFollowers()));
                ((TextView) view.findViewById(R.id.followingCount))
                        .setText(String.format(Locale.getDefault(), "%d Following", profileResponse.getFollowing()));

                ((TextView) view.findViewById(R.id.bioTextView)).setText(profileResponse.getBio());
            }, requireActivity());

            new GetProfilePictureRequest(username).request(response -> {
                ((ImageView) view.findViewById(R.id.profile_picture)).setImageBitmap(response);
            }, requireActivity());

            new GetBannerRequest(username).request(response -> {
                ((ImageView) view.findViewById(R.id.banner)).setImageBitmap(response);
            }, requireActivity());
        }

        new GetUserPostsRequest(username).request(response -> {
            GetUserPostsResponse postsResponse = Util.objFromJson(response, GetUserPostsResponse.class);
            ((ListView) view.findViewById(R.id.yourRecipesList)).setAdapter(new FeedAdapter(requireContext(), new ArrayList<>(Arrays.asList(postsResponse.getItems()))));
        }, requireActivity());

        return view;
    }
}
