package com.example.cs309android.models.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.interfaces.CallbackFragment;
import com.example.cs309android.models.gson.request.profile.GetProfilePictureRequest;
import com.example.cs309android.util.Util;

/**
 * Custom adapter to display the list of logged in accounts
 *
 * @author Mitch Hudson
 */
public class SwitchUserAdapter extends ArrayAdapter<String> {
    /**
     * List of accounts that are logged in
     */
    private final String[] accounts;
    /**
     * Global used for setting the current account
     */
    private final GlobalClass global;
    /**
     * Used to start the login account
     */
    private final CallbackFragment callbackFragment;

    /**
     * Public constructor.
     *
     * @param context          context used by the superclass {@link ArrayAdapter}
     * @param accounts         List of users to display
     * @param global           GlobalClass to get account details from
     * @param callbackFragment Used to start the login fragment
     */
    public SwitchUserAdapter(Context context, String[] accounts, GlobalClass global, CallbackFragment callbackFragment) {
        super(context, R.layout.adapter_logged_in, accounts);
        this.accounts = accounts;
        this.global = global;
        this.callbackFragment = callbackFragment;
    }

    /**
     * Ran for each of the child views (items in the list)
     * Here is where button functionality for each item is given.
     *
     * @param position    index of the item in the list
     * @param convertView converted view of the item in the list
     * @param parent      ListView parent
     * @return inflated view of the custom list_item view.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String username = accounts[position];
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.adapter_logged_in, null);
        }

        convertView.setClickable(true);
        convertView.setOnClickListener(view -> {
            Util.switchUser(global, username);
            callbackFragment.callback(0, null);
        });

        TextView usernameView = convertView.findViewById(R.id.username);
        usernameView.setText(username);

        ImageView profilePicture = convertView.findViewById(R.id.profile_picture);
        new GetProfilePictureRequest(username).request(profilePicture::setImageBitmap, getContext());

        return convertView;
    }
}
