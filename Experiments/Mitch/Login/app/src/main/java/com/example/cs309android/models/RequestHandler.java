package com.example.cs309android.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import androidx.annotation.Nullable;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Volley RequestQueue singleton class
 * EXPERIMENT 2
 *
 * @author Mitch Hudson
 */
public class RequestHandler {
    /**
     * RequestHandler instance.
     */
    private static RequestHandler instance;
    /**
     * Context for the RequestHandler;
     */
    private static Context context;

    /**
     * RequestQueue to make requests with
     */
    private RequestQueue queue;
    private Cache cache;
    private Network network;

    /**
     * ImageLoader to handle images.
     */
    private final ImageLoader imageLoader;

    /**
     * Private constructor, instantiated by getInstance()
     * @param context   RequestQueue context.
     */
    private RequestHandler(Context context) {
        RequestHandler.context = context;
        queue = getQueue();

        imageLoader = new ImageLoader(queue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> cache = new LruCache<>(20);

            @Nullable
            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
    }

    /**
     * Instantiates all variables for use.
     * @param context   RequestQueue context.
     * @return          RequestHandler instance.
     */
    public static synchronized RequestHandler getInstance(Context context) {
        if (instance == null) {
            instance = new RequestHandler(context);
        }
        return instance;
    }

    /**
     * Getter for the queue. Generates it if it doesn't exist yet.
     * @return  RequestQueue for the app.
     */
    public RequestQueue getQueue() {
        if (queue == null) {
//            cache = new DiskBasedCache(context.getApplicationContext().getCacheDir(), 1024 * 1024);
//            network = new BasicNetwork(new HurlStack());
//
//            queue = new RequestQueue(cache, network);
            queue = Volley.newRequestQueue(context);
        }
        return queue;
    }

    /**
     * Adds a request to the queue.
     * @param request   Request to add to the queue.
     * @param <T>       Type of the request.
     */
    public <T> void add(Request<T> request) {
        getQueue().add(request);
    }

    /**
     * Cancels all requests with the given tag.
     * @param tag   Tag of requests to cancel.
     * @param <T>   Type of tag.
     */
    public <T> void cancel(T tag) {
        getQueue().cancelAll(tag);
    }

    /**
     * Cancels all requests being handled.
     */
    public void cancelAll() {
        getQueue().cancelAll(request -> true);
    }

    /**
     * Getter for the ImageLoader.
     * @return  ImageLoader for storing and loading images.
     */
    public ImageLoader getImageLoader() {
        return imageLoader;
    }
}
