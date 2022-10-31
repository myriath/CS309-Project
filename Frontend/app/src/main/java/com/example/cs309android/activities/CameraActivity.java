package com.example.cs309android.activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Preview;
import androidx.camera.core.UseCase;
import androidx.camera.core.UseCaseGroup;
import androidx.camera.core.ViewPort;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.util.Rational;
import android.util.TypedValue;
import android.widget.ImageView;

import com.example.cs309android.R;
import com.example.cs309android.util.Analyzer;
import com.example.cs309android.util.Toaster;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class CameraActivity extends AppCompatActivity {
    public static final String INTENT_BARCODES = "barcodes";

    ActivityResultLauncher<String> permissionsLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        permissionsLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                granted -> {
                    if (granted) startCamera();
                    else {
                        Toaster.toastLong("Needs permission to take pictures", this);
                        setResult(RESULT_CANCELED);
                        finish();
                    }
                }
        );

        permissionsLauncher.launch(Manifest.permission.CAMERA);
    }

    public void startCamera() {
        ListenableFuture<ProcessCameraProvider> future = ProcessCameraProvider.getInstance(this);

        future.addListener(() -> {
            try {
                ProcessCameraProvider provider = future.get();

                PreviewView previewView = findViewById(R.id.preview);
                PreviewView smallPreviewView = findViewById(R.id.smallPreview);

                Preview preview = new Preview.Builder().build();
                preview.setSurfaceProvider(previewView.getSurfaceProvider());

                Preview smallPreview = new Preview.Builder().build();
                smallPreview.setSurfaceProvider(smallPreviewView.getSurfaceProvider());

//                Bitmap src = previewView.getBitmap();

//                if (src != null) {
//                    int x = (int) (src.getWidth() / 2 - DP150);
//                    int y = (int) (src.getHeight() / 2 - DP90);
//                    Bitmap cropped = Bitmap.createBitmap(src, x, y, (int) DP300, (int) DP180);
//                    ((ImageView) findViewById(R.id.barcodeSection)).setImageBitmap(cropped);
//                }

                CameraSelector selector = CameraSelector.DEFAULT_BACK_CAMERA;

                try {
                    provider.unbindAll();
//                    provider.bindToLifecycle(this, selector, preview);
                    provider.bindToLifecycle(this, selector);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }, getApplicationContext().getMainExecutor());
    }
}