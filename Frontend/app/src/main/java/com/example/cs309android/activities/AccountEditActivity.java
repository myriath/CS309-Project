package com.example.cs309android.activities;

import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.fragments.ModalImageSelect;
import com.example.cs309android.interfaces.CallbackFragment;
import com.example.cs309android.models.gson.request.social.UpdateBannerImageRequest;
import com.example.cs309android.models.gson.request.social.UpdateProfileImageRequest;
import com.example.cs309android.models.gson.request.social.UpdateProfileRequest;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

/**
 * Activity used to edit a user's profile
 *
 * @author Mitch Hudson
 */
public class AccountEditActivity extends AppCompatActivity implements CallbackFragment {
    /**
     * Images to be filled in when a user sets an image
     */
    private Bitmap profileImage, bannerImage;

    /**
     * Parcel constants
     */
    public static final String PARCEL_IMAGE_URI = "image_uri";

    /**
     * Opcodes
     */
    public static final int OPCODE_IMAGE_URI = 0;

    /**
     * Image intent constants
     */
    private static final int NONE = -1;
    private static final int BANNER = 0;
    private static final int PFP = 1;
    /**
     * Current intent
     */
    private int imageDestination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_edit);

        imageDestination = NONE;

        GlobalClass global = (GlobalClass) getApplicationContext();

        ((TextView) findViewById(R.id.unameView)).setText(global.getUsername());
        TextInputLayout bioInput = findViewById(R.id.bioEdit);
        Objects.requireNonNull(bioInput.getEditText()).setText(global.getBio());

        findViewById(R.id.add_pfp).setOnClickListener(view -> {
            imageDestination = PFP;
            imageChooser();
        });
        findViewById(R.id.add_banner).setOnClickListener(view -> {
            imageDestination = BANNER;
            imageChooser();
        });

        ((ImageView) findViewById(R.id.profile_picture)).setImageBitmap(global.getPfp());
        ((ImageView) findViewById(R.id.banner)).setImageBitmap(global.getBanner());

        ((TextView) findViewById(R.id.followerCount))
                .setText(String.format(Locale.getDefault(), "%d Followers", global.getFollowers()));
        ((TextView) findViewById(R.id.followingCount))
                .setText(String.format(Locale.getDefault(), "%d Following", global.getFollowing()));

        findViewById(R.id.saveButton).setOnClickListener(view -> {
            new UpdateProfileRequest(global.getToken(), bioInput.getEditText().getText().toString()).request(response -> {
                // TODO: Parse response
            }, AccountEditActivity.this);

            if (profileImage != null) {
                new UpdateProfileImageRequest(global.getToken(), profileImage).request(response -> {
                    // TODO: Parse response
                }, AccountEditActivity.this);
            }

            if (bannerImage != null) {
                new UpdateBannerImageRequest(global.getToken(), bannerImage).request(response -> {
                    // TODO: Parse response
                }, AccountEditActivity.this);
            }

            setResult(RESULT_OK);
            finish();
        });

        findViewById(R.id.backButton).setOnClickListener(view -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }

    /**
     * Shows the image source selection modal bottom sheet
     */
    public void imageChooser() {
        ModalImageSelect select = new ModalImageSelect();
        select.setCallbackFragment(this);
        select.show(getSupportFragmentManager(), ModalImageSelect.TAG);
    }

    @Override
    public void callback(int op, Bundle bundle) {
        switch(op) {
            case (OPCODE_IMAGE_URI): {
                try {
                    Uri imageUri = bundle.getParcelable(PARCEL_IMAGE_URI);
                    ImageDecoder.Source source = ImageDecoder.createSource(getContentResolver(), imageUri);
                    if (imageDestination == BANNER) {
                        bannerImage = ImageDecoder.decodeBitmap(source);
                        ((ImageView) findViewById(R.id.banner)).setImageBitmap(bannerImage);
                    } else if (imageDestination == PFP) {
                        profileImage = ImageDecoder.decodeBitmap(source);
                        ((ImageView) findViewById(R.id.profile_picture)).setImageBitmap(profileImage);
                    }
                    imageDestination = NONE;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Do nothing, no parent
     * @param fragment Callback fragment.
     */
    @Override
    public void setCallbackFragment(CallbackFragment fragment) {}
}