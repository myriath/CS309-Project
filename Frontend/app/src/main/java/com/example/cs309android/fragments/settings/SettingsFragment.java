package com.example.cs309android.fragments.settings;

import static com.example.cs309android.activities.MainActivity.CALLBACK_START_LOGIN;
import static com.example.cs309android.activities.MainActivity.PREF_NAME;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.preference.Preference;

import com.example.cs309android.R;
import com.example.cs309android.fragments.BasePreferenceFragment;

import java.util.Objects;

/**
 * Settings fragment used to show user settings and give them options
 *
 * @author Mitch Hudson
 */
public class SettingsFragment extends BasePreferenceFragment {

    /**
     * Preferences used to store user settings
     */
    private SharedPreferences pref;

    /**
     * Runs when the window is created. Here is where preference code is written
     *
     * @param savedInstanceState Instance state
     * @param rootKey            For building the preferences screen.
     */
    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.settings_screen, rootKey);
        pref = requireActivity().getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        // Logout button removes stored creds and prompts login
        Preference logout = Objects.requireNonNull(findPreference("logout"));
        logout.setOnPreferenceClickListener(preference -> {
            SharedPreferences.Editor editor = pref.edit();
            editor.remove("username");
            editor.remove("enc_hash");
            editor.apply();
            callbackFragment.callback(CALLBACK_START_LOGIN);
            return true;
        });
    }

    /**
     * Runs when the view is created. Here is where you do stuff to the view of the fragment (like
     * add window insets)
     *
     * @param inflater           Unused
     * @param container          Unused
     * @param savedInstanceState Unused
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