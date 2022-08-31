package com.example.cs309android.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.cs309android.R;
import com.example.cs309android.fragments.CallbackFragment;
import com.example.cs309android.fragments.LoginFragment;
import com.example.cs309android.fragments.RegisterFragment;

public class MainActivity extends AppCompatActivity implements CallbackFragment {
    private static final String PREF_NAME = "COMS309";
    int PRIVATE_MODE = 0;
    SharedPreferences pref = getApplicationContext().getSharedPreferences(PREF_NAME, PRIVATE_MODE);

    FragmentManager manager;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String unm = pref.getString("unm", null);
        String pwd = pref.getString("pwd", null);
        // TODO: Check for login on db
        if (unm != null && pwd != null) {
            startLoginFragment();
        }
    }

    public void startLoginFragment() {
        LoginFragment fragment = new LoginFragment();
        fragment.setCallbackFragment(this);
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        transaction.add(R.id.fragmentContainer, fragment);
        transaction.commit();
    }

    @Override
    public void changeFragment() {
        RegisterFragment fragment = new RegisterFragment();
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out);
        transaction.addToBackStack(null);
        transaction.replace(R.id.fragmentContainer, fragment, null);
        transaction.commit();
    }
}