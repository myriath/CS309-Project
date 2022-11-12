package com.example.cs309android.activities.account;

import static com.example.cs309android.util.Constants.CALLBACK_OPEN_ACCOUNT;
import static com.example.cs309android.util.Constants.PARCEL_ACCOUNT_LIST;
import static com.example.cs309android.util.Constants.PARCEL_TITLE;
import static com.example.cs309android.util.Constants.PARCEL_USERNAME;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.interfaces.CallbackFragment;
import com.example.cs309android.models.adapters.AccountAdapter;
import com.example.cs309android.util.Util;

/**
 * Displays a list of accounts.
 * Ex: shows the list of followers for an account
 *
 * @author Mitch Hudson
 */
public class AccountListActivity extends AppCompatActivity implements CallbackFragment {
    /**
     * Runs when the activity starts
     *
     * @param savedInstanceState saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_list);

        Intent intent = getIntent();
        String[] accounts = intent.getStringArrayExtra(PARCEL_ACCOUNT_LIST);
        String title = intent.getStringExtra(PARCEL_TITLE);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

        if (accounts.length != 0) {
            findViewById(R.id.empty_text).setVisibility(View.GONE);
        }

        ListView listView = findViewById(R.id.list);
        AccountAdapter adapter = new AccountAdapter(this, accounts, this);
        listView.setAdapter(adapter);
    }

    /**
     * Used by the AccountAdapter class
     * @param op     Tells the class what to do.
     * @param bundle Callback arguments
     */
    @Override
    public void callback(int op, Bundle bundle) {
        if (op == CALLBACK_OPEN_ACCOUNT) {
            String username = bundle.getString(PARCEL_USERNAME);
            Util.openAccountPage((GlobalClass) getApplicationContext(), username, AccountListActivity.this);
        }
    }

    /**
     * Does nothing, no parent
     * @param fragment Ignored
     */
    @Override
    public void setCallbackFragment(CallbackFragment fragment) {
    }
}