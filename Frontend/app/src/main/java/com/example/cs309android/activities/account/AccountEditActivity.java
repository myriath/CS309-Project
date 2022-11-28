package com.example.cs309android.activities.account;

import static com.example.cs309android.util.Constants.Callbacks.CALLBACK_IMAGE_URI;
import static com.example.cs309android.util.Constants.Parcels.PARCEL_IMAGE_URI;

import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.fragments.ModalImageSelect;
import com.example.cs309android.interfaces.CallbackFragment;
import com.example.cs309android.models.api.request.profile.UpdateBannerImageRequest;
import com.example.cs309android.models.api.request.profile.UpdateProfileImageRequest;
import com.example.cs309android.models.api.request.profile.UpdateProfileRequest;
import com.example.cs309android.models.api.response.GenericResponse;
import com.example.cs309android.util.Constants;
import com.example.cs309android.util.Toaster;
import com.example.cs309android.util.Util;
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
     * The destination for the image is none
     */
    private static final int NONE = -1;
    /**
     * The target image for callback is the banner
     */
    private static final int BANNER = 0;
    /**
     * The target image for callback is profile picture
     */
    private static final int PFP = 1;
    /**
     * Images to be filled in when a user sets an image
     */
    private Bitmap profileImage, bannerImage;
    /**
     * Current target
     * Default NONE
     */
    private int imageDestination = NONE;

    /**
     * Runs when the activity is started
     *
     * @param savedInstanceState saved state
     */
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
            Util.spin(this);
            new UpdateProfileRequest(global.getToken(), bioInput.getEditText().getText().toString()).request(response -> {
                GenericResponse genericResponse = Util.objFromJson(response, GenericResponse.class);
                if (genericResponse.getResult() == Constants.Results.RESULT_OK) {
                    global.setBio(bioInput.getEditText().getText().toString());

                    if (profileImage == null) {
                        profileImage = global.getPfp();
                    }
                    new UpdateProfileImageRequest(global.getToken(), profileImage).request(response1 -> {
                        String json = new String(response1.data);
                        GenericResponse genericResponse1 = Util.objFromJson(json, GenericResponse.class);
                        if (genericResponse1.getResult() == Constants.Results.RESULT_OK) {
                            global.setPfp(profileImage);

                            if (bannerImage == null) {
                                bannerImage = global.getBanner();
                            }
                            new UpdateBannerImageRequest(global.getToken(), bannerImage).request(response2 -> {
                                String json1 = new String(response2.data);
                                GenericResponse genericResponse2 = Util.objFromJson(json1, GenericResponse.class);
                                if (genericResponse2.getResult() == Constants.Results.RESULT_OK) {
                                    global.setBanner(bannerImage);
                                    setResult(RESULT_OK);
                                } else {
                                    Toaster.toastShort("Unexpected error", this);
                                    setResult(RESULT_CANCELED);
                                }
                                finish();
                            }, AccountEditActivity.this);
                        } else {
                            Toaster.toastShort("Unexpected error", this);
                            setResult(RESULT_CANCELED);
                            finish();
                        }
                    }, AccountEditActivity.this);
                } else {
                    Toaster.toastShort("Unexpected error", this);
                    setResult(RESULT_CANCELED);
                    finish();
                }
            }, AccountEditActivity.this);
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

    /**
     * Takes opcode and bundle and does something with it
     *
     * @param op     Tells the activity what to do
     * @param bundle Callback args
     */
    @Override
    public void callback(int op, Bundle bundle) {
        if (op == CALLBACK_IMAGE_URI) {
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

    /**
     * Do nothing, no parent
     *
     * @param fragment ignored
     */
    @Override
    public void setCallbackFragment(CallbackFragment fragment) {
    }
}