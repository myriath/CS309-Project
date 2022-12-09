package com.example.cs309android.fragments;

import static android.app.Activity.RESULT_OK;
import static com.example.cs309android.util.Constants.Callbacks.CALLBACK_IMAGE_URI;
import static com.example.cs309android.util.Constants.Parcels.PARCEL_IMAGE_URI;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.FileProvider;

import com.example.cs309android.BuildConfig;
import com.example.cs309android.R;
import com.example.cs309android.interfaces.CallbackFragment;
import com.example.cs309android.util.Toaster;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.File;
import java.util.Objects;

/**
 * Modal bottom sheet for selecting image source
 * (camera or gallery)
 *
 * @author Mitch Hudson
 */
public class ModalImageSelect extends BottomSheetDialogFragment implements CallbackFragment {
    /**
     * Tag for the dialog
     */
    public static final String TAG = "ImageSelector";
    /**
     * Launcher for the gallery select activity
     */
    ActivityResultLauncher<Intent> imageChooserLauncher;
    /**
     * Launcher for the camera app
     */
    ActivityResultLauncher<Uri> cameraLauncher;
    /**
     * Checks for permissions
     */
    ActivityResultLauncher<String> permissionsLauncher;
    /**
     * Callback fragment
     */
    private CallbackFragment callbackFragment;

    /**
     * Runs when the dialog is created
     *
     * @param savedInstanceState Saved state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        File file = new File(requireActivity().getExternalFilesDir("images"), "camera_img");
        Uri uri = FileProvider.getUriForFile(requireContext(), BuildConfig.APPLICATION_ID + ".provider", file);

        imageChooserLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Bundle bundle = new Bundle();
                        bundle.putParcelable(PARCEL_IMAGE_URI, Objects.requireNonNull(result.getData()).getData());
                        callbackFragment.callback(CALLBACK_IMAGE_URI, bundle);
                    }
                    dismiss();
                }
        );

        cameraLauncher = registerForActivityResult(
                new ActivityResultContracts.TakePicture(),
                success -> {
                    if (success) {
                        Bundle bundle = new Bundle();
                        bundle.putParcelable(PARCEL_IMAGE_URI, uri);
                        callbackFragment.callback(CALLBACK_IMAGE_URI, bundle);
                    }
                    dismiss();
                }
        );

        permissionsLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                granted -> {
                    if (granted) {
                        cameraLauncher.launch(uri);
                    } else {
                        Toaster.toastLong("Needs permission to take picture", requireContext());
                    }
                }
        );
    }

    /**
     * Runs when the view is created
     *
     * @param inflater           Inflates the dialog fragment
     * @param container          Parent for the dialog fragment
     * @param savedInstanceState Saved state
     * @return Inflated dialog fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.bottom_sheet_choose_photo, container, false);

        view.findViewById(R.id.take_photo).setOnClickListener(view1 ->
                permissionsLauncher.launch(Manifest.permission.CAMERA)
        );

        view.findViewById(R.id.from_gallery).setOnClickListener(view1 -> {
            Intent i = new Intent();
            i.setType("image/*");
            i.setAction(Intent.ACTION_GET_CONTENT);

            imageChooserLauncher.launch(Intent.createChooser(i, "Select Picture"));
        });

        return view;
    }

    /**
     * Do nothing, no children
     *
     * @param op     ignored
     * @param bundle ignored
     */
    @Override
    public void callback(int op, Bundle bundle) {
    }

    /**
     * Sets the callback fragment=
     *
     * @param fragment Callback fragment.
     */
    @Override
    public void setCallbackFragment(CallbackFragment fragment) {
        this.callbackFragment = fragment;
    }
}
