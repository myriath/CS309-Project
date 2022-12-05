package com.example.cs309android.util;

import static com.example.cs309android.util.security.NukeSSLCerts.trustAllCerts;

import android.content.Context;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Singleton class for custom Picasso instance
 * Used because of self-signed SSL certificate
 *
 * @author Mitch Hudson
 */
public class PicassoSingleton {
    /**
     * Max disk cache size in bytes (100MB)
     */
    public static final long DISK_CACHE_SIZE = 100L * 1024L * 1024L;

    /**
     * Picasso instance for the application
     */
    private Picasso instance;

    /**
     * Getter for the Picasso singleton
     *
     * @param context Volley context
     * @return Picasso instance for the application
     */
    public Picasso getInstance(Context context) {
        if (instance == null) {
            Picasso.Builder picassoBuilder = new Picasso.Builder(context);
            SSLContext sslContext;
            try {
                sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, trustAllCerts, new SecureRandom());
                SSLSocketFactory factory = sslContext.getSocketFactory();
                OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
                clientBuilder.cache(new Cache(context.getCacheDir(), DISK_CACHE_SIZE));
                clientBuilder.sslSocketFactory(factory, (X509TrustManager) trustAllCerts[0]);
                clientBuilder.hostnameVerifier((hostname, session) -> true);
                picassoBuilder.downloader(new OkHttp3Downloader(clientBuilder.build()));
                instance = picassoBuilder.build();
                instance.setIndicatorsEnabled(true);
            } catch (NoSuchAlgorithmException | KeyManagementException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
}
