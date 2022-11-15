package com.example.cs309android.activities.login;

import static com.example.cs309android.util.Constants.CALLBACK_REMOVE;
import static com.example.cs309android.util.Constants.CALLBACK_START_LOGIN;
import static com.example.cs309android.util.Constants.PARCEL_LOGGED_OUT;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.interfaces.CallbackFragment;
import com.example.cs309android.models.adapters.SwitchUserAdapter;

/**
 * Activity used to switch between logged in accounts
 *
 * @author Mitch Hudson
 */
public class AccountSwitchActivity extends AppCompatActivity implements CallbackFragment {
    /**
     * Tracks whether or not this was started because of the log out button
     */
    private boolean loggedOut = false;

    /**
     * Runs when the activity starts
     *
     * @param savedInstanceState saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_user);

        GlobalClass global = (GlobalClass) getApplicationContext();

        Toolbar toolbar = findViewById(R.id.toolbar);

        loggedOut = getIntent().getBooleanExtra(PARCEL_LOGGED_OUT, false);

        if (loggedOut) {
            toolbar.setNavigationIcon(null);
        } else {
            toolbar.setNavigationOnClickListener(view -> onBackPressed());
        }

        refreshView(global);

        findViewById(R.id.addAccount).setOnClickListener(view -> callback(CALLBACK_START_LOGIN, null));
    }

    /**
     * Used to set the result code
     *
     * @param op     Used to tell the activity what the result code will be
     * @param bundle ignored
     */
    @Override
    public void callback(int op, Bundle bundle) {
        setResult(RESULT_OK);
        switch (op) {
            case CALLBACK_REMOVE:
                refreshView((GlobalClass) getApplicationContext());
                break;
            case CALLBACK_START_LOGIN:
                setResult(CALLBACK_START_LOGIN);
            default:
                finish();
        }
    }

    public void refreshView(GlobalClass global) {
        String[] accounts = global.getAccounts();
        SwitchUserAdapter adapter = new SwitchUserAdapter(this, accounts, global, this);
        ((ListView) findViewById(R.id.list)).setAdapter(adapter);
        if (accounts.length == 0) {
            callback(CALLBACK_START_LOGIN, null);
        }
    }

    /**
     * No parent, ignored
     *
     * @param fragment ignored
     */
    @Override
    public void setCallbackFragment(CallbackFragment fragment) {
    }

    @Override
    public void onBackPressed() {
        if (!loggedOut) {
            super.onBackPressed();
        }
    }
}