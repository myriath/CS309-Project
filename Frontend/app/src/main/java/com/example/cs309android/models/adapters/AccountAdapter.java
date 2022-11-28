package com.example.cs309android.models.adapters;

import static com.example.cs309android.util.Constants.Callbacks.CALLBACK_OPEN_ACCOUNT;
import static com.example.cs309android.util.Constants.Parcels.PARCEL_USERNAME;
import static com.example.cs309android.util.Constants.Results.RESULT_OK;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cs309android.R;
import com.example.cs309android.interfaces.CallbackFragment;
import com.example.cs309android.models.api.request.profile.GetProfilePictureRequest;
import com.example.cs309android.models.api.request.profile.GetProfileRequest;
import com.example.cs309android.models.api.response.social.GetProfileResponse;
import com.example.cs309android.util.Util;

/**
 * Custom adapter to display a list of accounts
 *
 * @author Mitch Hudson
 */
public class AccountAdapter extends ArrayAdapter<String> {
    /**
     * List of items in the shopping list
     */
    private final String[] accounts;
    /**
     * Used to tell the activity when to open an account page
     */
    private final CallbackFragment callbackFragment;

    /**
     * Public constructor.
     *
     * @param context  context used by the superclass {@link ArrayAdapter}
     * @param accounts list of accounts to display.
     */
    public AccountAdapter(Context context, String[] accounts, CallbackFragment callbackFragment) {
        super(context, R.layout.shopping_list_item, accounts);
        this.accounts = accounts;
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
            convertView = View.inflate(parent.getContext(), R.layout.adapter_account, null);
        }

        convertView.setClickable(true);
        convertView.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString(PARCEL_USERNAME, username);
            callbackFragment.callback(CALLBACK_OPEN_ACCOUNT, bundle);
        });

        ((TextView) convertView.findViewById(R.id.username)).setText(username);

        ImageView profilePicture = convertView.findViewById(R.id.profile_picture);
        new GetProfilePictureRequest(username).request(profilePicture, getContext());

        TextView bio = convertView.findViewById(R.id.bio);
        new GetProfileRequest(username).request(response -> {
            GetProfileResponse profileResponse = Util.objFromJson(response, GetProfileResponse.class);

            if (profileResponse.getResult() == RESULT_OK) {
                bio.setText(profileResponse.getBio());
            }
        }, getContext());

        return convertView;
    }
}
