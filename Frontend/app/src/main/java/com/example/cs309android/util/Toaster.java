package com.example.cs309android.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Static util class for making toasts.
 *
 * @author Mitch Hudson
 */
public class Toaster {
    /**
     * Private constructor (Static class)
     */
    private Toaster() {
    }

    /**
     * Creates a toast for a short amount of time
     *
     * @param text    Text to toast
     * @param context Context to show the toast on
     */
    public static void toastShort(String text, Context context) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * Creates a toast for a long amount of time
     *
     * @param text    Text to toast
     * @param context Context to show the toast on
     */
    public static void toastLong(String text, Context context) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }
}
