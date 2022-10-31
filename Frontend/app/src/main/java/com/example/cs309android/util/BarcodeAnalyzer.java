package com.example.cs309android.util;

import android.graphics.Bitmap;
import android.media.Image;
import android.util.TypedValue;

import androidx.annotation.NonNull;
import androidx.camera.core.ExperimentalGetImage;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import androidx.camera.view.TransformExperimental;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;

import java.util.List;

/**
 * Basic class to read barcodes from a CameraX ImageProxy
 *
 * @author Mitch Hudson (tutorial from https://developers.google.com/ml-kit/vision/barcode-scanning/android#java)
 */
public class BarcodeAnalyzer {
    /**
     * Barcode scanner options.
     * Only care about upc barcodes
     */
    public static final BarcodeScannerOptions BARCODE_SCANNER_OPTIONS = new BarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_UPC_A, Barcode.FORMAT_UPC_E)
            .build();

    /**
     * Runs when the barcodes are found
     */
    private final OnSuccessListener<String[]> listener;
    /**
     * Runs if there is an error in analysis
     */
    private final OnFailureListener errorListener;

//    public static float DP300;
//    public static float DP150;
//    public static float DP180;
//    public static float DP90;

    /**
     * Public constructor
     * @param listener Runs when the barcodes are found
     * @param errorListener Runs when the analysis fails
     */
    public BarcodeAnalyzer(OnSuccessListener<String[]> listener, OnFailureListener errorListener) {
        this.listener = listener;
        this.errorListener = errorListener;

//        DP300 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300f, getResources().getDisplayMetrics());
//        DP150 = DP300 / 2;
//        DP180 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 180f, getResources().getDisplayMetrics());
//        DP90 = DP180 / 2;
    }

    public void analyze(@NonNull Bitmap bitmap) {
//        int x = (int) (bitmap.getWidth() / 2 - DP150);
//        int y = (int) (bitmap.getHeight() / 2 - DP90);
//        Bitmap cropped = Bitmap.createBitmap(bitmap, x, y, (int) DP300, (int) DP180);
        BarcodeScanner scanner = BarcodeScanning.getClient(BARCODE_SCANNER_OPTIONS);
        scanner.process(bitmap, 0)
                .addOnSuccessListener(barcodes -> listener.onSuccess(readBarcodes(barcodes)))
                .addOnFailureListener(errorListener);
    }

    /**
     * Gets the raw values from a list of Barcodes
     * @param barcodes List of barcodes to get the values of
     * @return String[] of barcode values
     */
    public static String[] readBarcodes(List<Barcode> barcodes) {
        int size = barcodes.size();
        String[] values = new String[size];
        for (int i = 0; i < size; i++) {
            values[i] = barcodes.get(i).getRawValue();
        }
        return values;
    }
}
