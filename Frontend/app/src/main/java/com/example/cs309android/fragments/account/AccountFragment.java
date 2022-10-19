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

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AccountFragment.
     */
    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        GlobalClass global = ((GlobalClass) requireActivity().getApplicationContext());

        ((ImageView) view.findViewById(R.id.banner)).setImageBitmap(global.getBanner());
        ((ImageView) view.findViewById(R.id.profile_picture)).setImageBitmap(global.getPfp());
        ((TextView) view.findViewById(R.id.unameView)).setText(global.getUsername());

        ((ImageButton) view.findViewById(R.id.settingsButton)).setOnClickListener(view1 -> {
            callbackFragment.callback(MainActivity.CALLBACK_MOVE_TO_SETTINGS, null);
        });

        ((ImageButton) view.findViewById(R.id.editButton)).setOnClickListener(view1 -> {
            callbackFragment.callback(MainActivity.CALLBACK_EDIT_ACCOUNT, null);
        });

        return view;
    }
}