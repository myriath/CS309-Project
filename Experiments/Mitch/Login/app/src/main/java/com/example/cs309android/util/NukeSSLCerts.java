package com.example.cs309android.util;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


/**
 * Static class that ignores self signed certificates
 * TODO: REMOVE FOR PRODUCTION
 * EXPERIMENT 5
 *
 * @author Code borrowed from: https://stackoverflow.com/a/44905278
 */
public class NukeSSLCerts {
    /**
     * Tag for Volley
     */
    protected static final String TAG = "NukeSSLCerts";

    /**
     * Private constructor; util class
     */
    private NukeSSLCerts() {}

    /**
     * Creates a custom TrustManager that trusts all certificates, regardless of CA.
     * (For testing purposes only, HTTPS certificates should be signed for the final)
     */
    public static void nuke() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {}

                        @Override
                        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {}

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            X509Certificate[] trustedAnchors = new X509Certificate[0];
                            return trustedAnchors;
                        }
                    }
            };

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier((s, sslSession) -> true);
        } catch (NoSuchAlgorithmException | KeyManagementException ignored) {}
    }
}
