package com.example.cs309android.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.cs309android.R;
import com.example.cs309android.fragments.CallbackFragment;
import com.example.cs309android.fragments.LoginFragment;
import com.example.cs309android.fragments.RegisterFragment;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements CallbackFragment {
    FragmentManager manager;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFragment();
    }

    public void addFragment() {
        LoginFragment fragment = new LoginFragment();
        fragment.setCallbackFragment(this);
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        transaction.add(R.id.fragmentContainer, fragment);
        transaction.commit();
    }

    public void replaceFragment() {
        RegisterFragment fragment = new RegisterFragment();
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out);
        transaction.addToBackStack(null);
        transaction.replace(R.id.fragmentContainer, fragment, null);
        transaction.commit();
    }

    @Override
    public void changeFragment() {
        replaceFragment();
    }
}