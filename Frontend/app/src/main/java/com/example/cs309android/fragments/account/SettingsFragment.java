package com.example.cs309android.fragments.account;

import static com.example.cs309android.util.Constants.CALLBACK_MOVE_TO_HOME;
import static com.example.cs309android.util.Constants.CALLBACK_START_LOGIN;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.preference.Preference;

import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.activities.account.AccountSwitchActivity;
import com.example.cs309android.fragments.BasePreferenceFragment;
import com.example.cs309android.fragments.shopping.ShoppingFragment;
import com.example.cs309android.util.Util;

import java.util.Objects;

/**
 * Settings fragment used to show user settings and give them options
 *
 * @author Mitch Hudson
 */
public class SettingsFragment extends BasePreferenceFragment {
    /**
     * Launcher for the SwitchUserActivity
     */
    ActivityResultLauncher<Intent> switchUserLauncher;

    /**
     * Runs when the window is created. Here is where preference code is written
     *
     * @param savedInstanceState Saved state
     * @param rootKey            For building the preferences screen.
     */
    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.settings_screen, rootKey);
        GlobalClass global = ((GlobalClass) requireActivity().getApplicationContext());

        switchUserLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == CALLBACK_START_LOGIN) {
                        callbackFragment.callback(CALLBACK_START_LOGIN, null);
                    } else {
                        callbackFragment.callback(CALLBACK_MOVE_TO_HOME, null);
                    }
                }
        );

        // Switch user changes the logged in user
        Preference switchUser = Objects.requireNonNull(findPreference("switch_user"));
        switchUser.setOnPreferenceClickListener(preference -> {
            ShoppingFragment.clearItems();
            Intent intent = new Intent(getContext(), AccountSwitchActivity.class);
            switchUserLauncher.launch(intent);
            return true;
        });

        // Logout button removes stored creds and prompts login
        Preference logout = Objects.requireNonNull(findPreference("logout"));
        logout.setOnPreferenceClickListener(preference -> {
            Util.logout(global, global.getUsername());
            ShoppingFragment.clearItems();
            Intent intent = new Intent(getContext(), AccountSwitchActivity.class);
            switchUserLauncher.launch(intent);
            return true;
        });
    }

    /**
     * Runs when the view is created. Here is where you do stuff to the view of the fragment (like
     * add window insets)
     *
     * @param inflater           Inflates the view for the preferences
     * @param container          Parent of the preferences page
     * @param savedInstanceState Saved state
     * @return View of the preferences screen created by super
     */
    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ViewCompat.setOnApplyWindowInsetsListener(view, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            ((ViewGroup.MarginLayoutParams) v.getLayoutParams()).topMargin = insets.top;
            return WindowInsetsCompat.CONSUMED;
        });
        return view;
    }
}
