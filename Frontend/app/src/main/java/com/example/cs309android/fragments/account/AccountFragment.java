package com.example.cs309android.fragments.account;

import static com.example.cs309android.util.Constants.CALLBACK_MOVE_TO_SETTINGS;
import static com.example.cs309android.util.Constants.PARCEL_ACCOUNT_LIST;
import static com.example.cs309android.util.Constants.PARCEL_TITLE;
import static com.example.cs309android.util.Util.objFromJson;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.activities.account.AccountEditActivity;
import com.example.cs309android.activities.account.AccountListActivity;
import com.example.cs309android.fragments.BaseFragment;
import com.example.cs309android.models.api.request.profile.GetProfileRequest;
import com.example.cs309android.models.api.request.social.GetFollowersRequest;
import com.example.cs309android.models.api.request.social.GetFollowingRequest;
import com.example.cs309android.models.api.response.social.FollowResponse;
import com.example.cs309android.models.api.response.social.GetProfileResponse;
import com.example.cs309android.util.Util;

import java.util.Locale;

/**
 * Fragment to display account details
 *
 * @author Mitch Hudson
 */
public class AccountFragment extends BaseFragment {
    /**
     * Stores the profile details
     */
    public static final String ARGS_USERNAME = "username";
    private String username;
    public static final String ARGS_OWNER = "owner";
    private boolean owner;

    /**
     * Launcher for the account edit activity
     */
    private ActivityResultLauncher<Intent> accountEditLauncher;

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

    /**
     * Runs when the activity is created
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            username = args.getString(ARGS_USERNAME);
            owner = args.getBoolean(ARGS_OWNER);
        }
    }

    /**
     * Runs when the view is created
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
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

        ImageButton settingsButton = view.findViewById(R.id.settingsButton);
        ImageButton editButton = view.findViewById(R.id.editButton);

        settingsButton.setOnClickListener(view1 -> {
            callbackFragment.callback(CALLBACK_MOVE_TO_SETTINGS, null);
        });

        editButton.setOnClickListener(view1 -> {
            Intent intent = new Intent(requireContext(), AccountEditActivity.class);
            accountEditLauncher.launch(intent);
        });

        refreshAccount(view, global);

        new GetProfileRequest(username).request(response -> {
            GetProfileResponse profileResponse = objFromJson(response, GetProfileResponse.class);
            global.setBio(profileResponse.getBio());
            global.setFollowers(profileResponse.getFollowers());
            global.setFollowing(profileResponse.getFollowing());

            ((TextView) view.findViewById(R.id.followerCount))
                    .setText(String.format(Locale.getDefault(), "%d Followers", global.getFollowers()));
            ((TextView) view.findViewById(R.id.followingCount))
                    .setText(String.format(Locale.getDefault(), "%d Following", global.getFollowing()));
            ((TextView) view.findViewById(R.id.bioTextView))
                    .setText(global.getBio());

        }, requireContext());

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

    /**
     * Refresh the account page
     * @param view view to find subviews
     * @param global global containing account data
     */
    public static void refreshAccount(View view, GlobalClass global) {
        ((ImageView) view.findViewById(R.id.banner)).setImageBitmap(global.getBanner());
        ((ImageView) view.findViewById(R.id.profile_picture)).setImageBitmap(global.getPfp());
        ((TextView) view.findViewById(R.id.unameView)).setText(global.getUsername());
        ((TextView) view.findViewById(R.id.bioTextView)).setText(global.getBio());

    }
}
