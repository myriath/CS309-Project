package com.example.cs309android.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.cs309android.R;
import com.example.cs309android.fragments.LoginFragment;
import com.example.cs309android.fragments.RegisterFragment;

public class MainActivity extends AppCompatActivity {
    Fragment fragment;
    LoginFragment loginFragment;
    RegisterFragment registerFragment;
    FragmentManager manager;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginFragment = new LoginFragment();
        registerFragment = new RegisterFragment();

        addFragment();
    }

    public void addFragment() {
        fragment = loginFragment;
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.fragmentContainer, fragment);
        transaction.commit();
    }

    public void replaceFragment() {
        fragment = registerFragment;
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commit();
    }
}