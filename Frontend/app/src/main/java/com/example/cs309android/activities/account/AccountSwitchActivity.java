package com.example.cs309android.activities.account;

import static com.example.cs309android.util.Constants.CALLBACK_START_LOGIN;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_user);

        ((Toolbar) findViewById(R.id.toolbar)).setNavigationOnClickListener(view -> onBackPressed());

        GlobalClass global = (GlobalClass) getApplicationContext();

        SwitchUserAdapter adapter = new SwitchUserAdapter(this, global.getAccounts(), global, this);
        ((ListView) findViewById(R.id.list)).setAdapter(adapter);

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
        if (op == CALLBACK_START_LOGIN) {
            setResult(CALLBACK_START_LOGIN);
        }
        finish();
    }

    /**
     * No parent, ignored
     *
     * @param fragment ignored
     */
    @Override
    public void setCallbackFragment(CallbackFragment fragment) {
    }
}