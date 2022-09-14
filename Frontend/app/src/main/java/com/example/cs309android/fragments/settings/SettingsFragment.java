package com.example.cs309android.fragments.settings;

import static com.example.cs309android.activities.MainActivity.CALLBACK_START_LOGIN;
import static com.example.cs309android.activities.MainActivity.PREF_NAME;
import static com.example.cs309android.util.Util.hideKeyboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cs309android.R;
import com.example.cs309android.fragments.BaseFragment;

/**
 * Settings fragment used to show user settings and give them options
 *
 * @author Mitch Hudson
 */
public class SettingsFragment extends BaseFragment {

    /**
     * Preferences used to store user settings
     */
    private SharedPreferences pref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        pref = requireActivity().getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        // Logout button removes stored creds and prompts login
        Button logout = view.findViewById(R.id.logoutButton);
        logout.setOnClickListener(view1 -> {
            hideKeyboard(view1, this.requireActivity());

            SharedPreferences.Editor editor = pref.edit();
            editor.remove("username");
            editor.remove("enc_hash");
            editor.apply();
            callbackFragment.callback(CALLBACK_START_LOGIN);
        });
        ViewCompat.setOnApplyWindowInsetsListener(logout, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            ((ViewGroup.MarginLayoutParams) v.getLayoutParams()).topMargin = insets.top;
            return WindowInsetsCompat.CONSUMED;
        });

        return view;
    }
}