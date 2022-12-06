package com.example.cs309android.activities.account;

import static com.example.cs309android.util.Constants.Callbacks.CALLBACK_OPEN_ACCOUNT;
import static com.example.cs309android.util.Constants.Parcels.PARCEL_ACCOUNT_LIST;
import static com.example.cs309android.util.Constants.Parcels.PARCEL_FOLLOWING;
import static com.example.cs309android.util.Constants.Parcels.PARCEL_OWNER;
import static com.example.cs309android.util.Constants.Parcels.PARCEL_TITLE;
import static com.example.cs309android.util.Constants.Parcels.PARCEL_USERNAME;
import static com.example.cs309android.util.Constants.Results.RESULT_USER_DELETED;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.interfaces.CallbackFragment;
import com.example.cs309android.models.adapters.AccountAdapter;
import com.example.cs309android.models.api.request.social.IsFollowingRequest;
import com.example.cs309android.models.api.response.social.FollowResponse;
import com.example.cs309android.util.Util;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Displays a list of accounts.
 * Ex: shows the list of followers for an account
 *
 * @author Mitch Hudson
 */
public class AccountListActivity extends AppCompatActivity implements CallbackFragment {
    /**
     * Array of accounts to display
     */
    private String[] accounts;
    /**
     * Launches the account page
     */
    private ActivityResultLauncher<Intent> accountLauncher;
    /**
     * Holds the currently opened account username
     */
    private static String opened;

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
        accounts = intent.getStringArrayExtra(PARCEL_ACCOUNT_LIST);
        String title = intent.getStringExtra(PARCEL_TITLE);

        accountLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_USER_DELETED) {
                        ArrayList<String> users = new ArrayList<>(Arrays.asList(accounts));
                        users.removeIf(filter -> filter.equals(opened));
                        accounts = users.toArray(new String[0]);

                        ListView listView = findViewById(R.id.list);
                        AccountAdapter adapter = new AccountAdapter(this, accounts, this);
                        listView.setAdapter(adapter);
                    }
                }
        );

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
     *
     * @param op     Tells the class what to do.
     * @param bundle Callback arguments
     */
    @Override
    public void callback(int op, Bundle bundle) {
        if (op == CALLBACK_OPEN_ACCOUNT) {
            String username = bundle.getString(PARCEL_USERNAME);
            GlobalClass global = (GlobalClass) getApplicationContext();
            AccountListActivity.opened = username;
            new IsFollowingRequest(global.getUsername(), username).request(response -> {
                FollowResponse followResponse = Util.objFromJson(response, FollowResponse.class);

                Intent intent = new Intent(this, AccountActivity.class);
                intent.putExtra(PARCEL_FOLLOWING, followResponse.getUsers() != null && followResponse.getUsers().length != 0);
                intent.putExtra(PARCEL_USERNAME, username);
                intent.putExtra(PARCEL_OWNER, username.equals(global.getUsername()));
                accountLauncher.launch(intent);
            }, error -> {
                Intent intent = new Intent(this, AccountActivity.class);
                intent.putExtra(PARCEL_USERNAME, username);
                intent.putExtra(PARCEL_OWNER, username.equals(global.getUsername()));
                accountLauncher.launch(intent);
            }, this);
        }
    }

    /**
     * Does nothing, no parent
     *
     * @param fragment Ignored
     */
    @Override
    public void setCallbackFragment(CallbackFragment fragment) {
    }
}