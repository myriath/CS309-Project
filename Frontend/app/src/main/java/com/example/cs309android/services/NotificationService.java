package com.example.cs309android.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.cs309android.GlobalClass;
import com.example.cs309android.models.api.NotificationWebsocketController;

/**
 * Service used to handle notifications in the background
 *
 * @author Mitch Hudson
 */
public class NotificationService extends Service {
    /**
     * Controller that handles the websocket connection and creation of notifications
     */
    private NotificationWebsocketController controller;

    /**
     * Runs when the service is started
     *
     * @param intent  Intent for the service
     * @param flags   Flags for the service
     * @param startId StartID for the service
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        controller = new NotificationWebsocketController((GlobalClass) getApplicationContext(), this);
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * Runs when the service is stopped
     * Just posts a debug message to help find when it stops
     */
    @Override
    public void onDestroy() {
        Log.d("WEBSOCKET", "Destroying service " + getForegroundServiceType() + "...");
        super.onDestroy();
    }

    /**
     * Runs when the service is bound
     * Unused: returns null
     *
     * @param intent Binding intent
     * @return null
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * Getter for the notification websocket controller
     *
     * @return Controller for the websocket client
     */
    public NotificationWebsocketController getController() {
        return controller;
    }
}
