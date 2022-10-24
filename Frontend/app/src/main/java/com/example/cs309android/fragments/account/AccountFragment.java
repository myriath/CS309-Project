package com.example.cs309android.fragments.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.activities.MainActivity;
import com.example.cs309android.fragments.BaseFragment;

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

        ((ImageView) view.findViewById(R.id.banner)).setImageBitmap(global.getBanner());
        ((ImageView) view.findViewById(R.id.profile_picture)).setImageBitmap(global.getPfp());
        ((TextView) view.findViewById(R.id.unameView)).setText(global.getUsername());

        ImageButton settingsButton = view.findViewById(R.id.settingsButton);
        ImageButton editButton = view.findViewById(R.id.editButton);
        if (owner) {
            settingsButton.setOnClickListener(view1 -> {
                callbackFragment.callback(MainActivity.CALLBACK_MOVE_TO_SETTINGS, null);
            });
            settingsButton.setVisibility(View.VISIBLE);

            editButton.setOnClickListener(view1 -> {
                callbackFragment.callback(MainActivity.CALLBACK_EDIT_ACCOUNT, null);
            });
            editButton.setVisibility(View.VISIBLE);
        }

        return view;
    }
}
