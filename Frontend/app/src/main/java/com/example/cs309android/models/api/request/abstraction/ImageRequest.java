package com.example.cs309android.models.api.request.abstraction;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.example.cs309android.util.PicassoSingleton;
import com.squareup.picasso.Callback;

import java.io.IOException;

/**
 * Used for making requests with Bitmaps
 *
 * @author Mitch Hudson
 */
public abstract class ImageRequest {
    /**
     * Getter for the url
     *
     * @return url for the request
     */
    public abstract String getURL();

    /**
     * Do something with the bitmap from picasso
     * DO NOT USE TO UPDATE VIEWS, that is what the other request methods are for!
     *
     * @param runner  Runs with the bitmap on success
     * @param context Context for Picasso
     */
    public void request(RunWithBitmap runner, Context context) {
        new Thread(() -> {
            try {
                runner.run(PicassoSingleton.getInstance(context).load(getURL()).get());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Makes a request for the given image url
     *
     * @param view          Target ImageView for the image
     * @param context       Context for Picasso
     */
    public void request(ImageView view, Context context) {
        PicassoSingleton.getInstance(context).load(getURL()).into(view);
    }

    /**
     * Makes a request for the given image url (custom error listener)
     *
     * @param view     Target ImageView for the image
     * @param callback Error callback
     * @param context  Context for Picasso
     */
    public void request(ImageView view, Callback callback, Context context) {
        PicassoSingleton.getInstance(context).load(getURL()).into(view, callback);
    }

    /**
     * Asynchronous method called on getting a bitmap from Picasso
     */
    public interface RunWithBitmap {
        void run(Bitmap bitmap);
    }
}
