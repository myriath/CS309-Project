package com.example.cs309android.util;

import android.media.Image;

import androidx.annotation.NonNull;
import androidx.camera.core.ExperimentalGetImage;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import androidx.camera.view.TransformExperimental;
import androidx.camera.view.transform.ImageProxyTransformFactory;
import androidx.camera.view.transform.OutputTransform;

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
public class Analyzer implements ImageAnalysis.Analyzer {
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

    /**
     * Public constructor
     * @param listener Runs when the barcodes are found
     * @param errorListener Runs when the analysis fails
     */
    public Analyzer(OnSuccessListener<String[]> listener, OnFailureListener errorListener) {
        this.listener = listener;
        this.errorListener = errorListener;
    }

    /**
     * Analyzes an image to read the barcodes
     * @param proxy CameraX ImageProxy
     */
    @Override
    @ExperimentalGetImage
    @TransformExperimental
    public void analyze(@NonNull ImageProxy proxy) {
        Image media = proxy.getImage();
        if (media != null) {
            InputImage image = InputImage.fromMediaImage(media, proxy.getImageInfo().getRotationDegrees());
            // Pass image to ML Kit
            BarcodeScanner scanner = BarcodeScanning.getClient(BARCODE_SCANNER_OPTIONS);
            scanner.process(image)
                    .addOnSuccessListener(barcodes -> listener.onSuccess(readBarcodes(barcodes)))
                    .addOnFailureListener(errorListener);
        }
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
