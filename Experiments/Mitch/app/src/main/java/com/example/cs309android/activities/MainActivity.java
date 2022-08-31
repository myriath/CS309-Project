package com.example.cs309android.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;

import com.example.cs309android.R;
import com.example.cs309android.fragments.CallbackFragment;
import com.example.cs309android.fragments.LoginFragment;
import com.example.cs309android.fragments.RegisterFragment;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements CallbackFragment {
    // TODO: False for prod
    public static final boolean DEBUG = true;

    public static final String PREF_NAME = "COMS309";
    private SharedPreferences pref;

    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        String username = pref.getString("username", null);
        String encodedHash = pref.getString("enc_hash", null);
        if (username != null && encodedHash != null) {
            byte[] hashB64 = Base64.decode(encodedHash, Base64.DEFAULT);
            if (DEBUG) {
                Log.i("Null B64 decode", Arrays.toString(hashB64));
            }
            // TODO: Check for login on db
        } else {
            startLoginFragment();
        }

        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(view -> {
            SharedPreferences.Editor editor = pref.edit();
            editor.remove("username");
            editor.remove("enc_hash");
            editor.apply();
            startLoginFragment();
        });
    }

    public void closeFragment() {
        findViewById(R.id.mainLayout).setAlpha(1);
        findViewById(R.id.loginPopup).setClickable(false);
    }

    public void startLoginFragment() {
        findViewById(R.id.mainLayout).setAlpha(0.5f);
        findViewById(R.id.loginPopup).setClickable(true);

        LoginFragment fragment = new LoginFragment();
        fragment.setCallbackFragment(this);
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        transaction.add(R.id.loginPopup, fragment);
        transaction.commit();
    }

    @Override
    public void changeFragment() {
        RegisterFragment fragment = new RegisterFragment();
        fragment.setCallbackFragment(this);
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
        transaction.addToBackStack(null);
        transaction.replace(R.id.loginPopup, fragment, null);
        transaction.commit();
    }
}