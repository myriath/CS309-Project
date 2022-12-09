package com.example.cs309android.models.api;

import static com.example.cs309android.util.Constants.ITEM_ID_NULL;
import static com.example.cs309android.util.Constants.Intents.INTENT_NONE;
import static com.example.cs309android.util.Constants.Parcels.PARCEL_RECIPE_ID;
import static com.example.cs309android.util.Constants.Parcels.PARCEL_USERNAME;
import static com.example.cs309android.util.Constants.Urls.NOTIFICATIONS_URL;

import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.util.Log;

import com.example.cs309android.GlobalClass;
import com.example.cs309android.activities.account.AccountActivity;
import com.example.cs309android.activities.recipe.RecipeDetailsActivity;
import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.util.Constants;
import com.example.cs309android.util.Util;
import com.example.cs309android.util.WSNotification;
import com.example.cs309android.util.security.NukeSSLCerts;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

/**
 * Handles notifications with a websocket in a service
 * Bad for battery but works without Google Play Services
 *
 * @author Mitch Hudson
 */
public class NotificationWebsocketController extends WebSocketClient {
    /**
     * GlobalClass for context
     */
    private final GlobalClass global;
    /**
     * Parent service running this controller
     */
    private final Service parent;

    /**
     * Public constructor
     *
     * @param global GlobalClass for getting authentication details
     */
    public NotificationWebsocketController(GlobalClass global, Service parent) {
        super(new ParameterizedRequestURL(NOTIFICATIONS_URL).addPathVar(global.getToken()).toURI());
        this.global = global;
        this.parent = parent;
        setSocketFactory(NukeSSLCerts.getSocketFactory());
        connect();
    }

    /**
     * Sends a notification to another user
     *
     * @param notification Notification to send. Needs toUsername filled out
     */
    public void send(WSNotification notification) {
        send(Util.GSON.toJson(notification));
    }

    /**
     * Runs when the websocket opens
     *
     * @param handshake Handshake details
     */
    @Override
    public void onOpen(ServerHandshake handshake) {
        Log.d("WEBSOCKET", "Session starting...");
        send("Hello World!");
    }

    /**
     * Runs when the websocket receives a message
     *
     * @param message Message to handle (converted to a WSNotification)
     */
    @Override
    public void onMessage(String message) {
        Log.d("WEBSOCKET", "Received: " + message);
        if (message.equals("Hello World!")) {
            Log.d("WEBSOCKET", "New connection started");
            return;
        }
        WSNotification notification = Util.objFromJson(message, WSNotification.class);
        PendingIntent intent = null;
        TaskStackBuilder builder = TaskStackBuilder.create(global);
        int flags = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE;
        int type = ITEM_ID_NULL;
        switch (notification.getType()) {
            case COMMENT: {
                type = 0;
                Intent intent1 = new Intent(global, RecipeDetailsActivity.class);
                intent1.putExtra(PARCEL_RECIPE_ID, notification.getRid());
                builder.addNextIntentWithParentStack(intent1);
                intent = builder.getPendingIntent(INTENT_NONE, flags);
                break;
            }
            case LIKE: {
                type = 1;
                Intent intent1 = new Intent(global, RecipeDetailsActivity.class);
                intent1.putExtra(PARCEL_RECIPE_ID, notification.getRid());
                builder.addNextIntentWithParentStack(intent1);
                intent = builder.getPendingIntent(INTENT_NONE, flags);
                break;
            }
            case FOLLOWER: {
                type = 2;
                Intent intent1 = new Intent(global, AccountActivity.class);
                builder.addNextIntentWithParentStack(intent1);
                intent1.putExtra(PARCEL_USERNAME, notification.getFromUsername());
                intent = builder.getPendingIntent(INTENT_NONE, flags);
                break;
            }
            case RECIPE: {
                type = 3;
                Intent intent1 = new Intent(global, RecipeDetailsActivity.class);
                intent1.putExtra(PARCEL_RECIPE_ID, notification.getRid());
                builder.addNextIntentWithParentStack(intent1);
                intent = builder.getPendingIntent(INTENT_NONE, flags);
                break;
            }
        }
        Constants.Notifications.notify(global, type, notification.getFromUsername(), intent);
    }

    /**
     * Runs when the websocket closes
     *
     * @param code   Code
     * @param reason Reason for close
     * @param remote Remote
     */
    @Override
    public void onClose(int code, String reason, boolean remote) {
        Log.d("WEBSOCKET", "Closed for: " + reason);
        Log.d("WEBSOCKET", "Closed code: " + code + "; Remote: " + remote);
        parent.stopSelf();
    }

    /**
     * Runs when the websocket encounters an error
     *
     * @param ex Exception encountered
     */
    @Override
    public void onError(Exception ex) {
        Log.e("WEBSOCKET", "Error", ex);
        parent.stopSelf();
    }
}
