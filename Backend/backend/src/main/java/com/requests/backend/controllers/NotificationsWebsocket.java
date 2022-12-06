package com.requests.backend.controllers;

import com.requests.backend.models.Notification;
import com.requests.backend.models.TextDecoder;
import com.requests.backend.models.TextEncoder;
import com.requests.backend.models.User;
import com.requests.backend.repositories.FollowRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

@ServerEndpoint(value = "/websocket/{username}", decoders = TextDecoder.class, encoders = TextEncoder.class)
@Component
public class NotificationsWebsocket {

    @Autowired
    private FollowRepository followRepository;

    // Store all socket session and their corresponding username.
    private static Map<Session, String > sessionUsernameMap = new Hashtable< >();
    private static Map < String, Session > usernameSessionMap = new Hashtable < > ();

    @OnOpen
    public void onOpen(Session session, @PathVariable("username") String username)
            throws IOException {

        sessionUsernameMap.put(session, username);
        usernameSessionMap.put(username, session);

    }

    @OnClose
    public void onClose(Session session) throws IOException {

        String username = sessionUsernameMap.get(session);
        sessionUsernameMap.remove(session);
        usernameSessionMap.remove(username);

    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }

    public void sendPostNotifications(Notification notification) throws IOException {
        broadcast(notification);
    }

    private void broadcast(Notification notification) {

        String[] followers = followRepository.queryGetFollowers(notification.getFromUsername());

        for (String follower : followers) {
            // If the follower has an active session
            if (usernameSessionMap.containsKey(follower)) {
                try {
                    // Get the active session object for the follower
                    Session session = usernameSessionMap.get(follower);

                    // Send the notification object to the follower
                    session.getBasicRemote().sendObject(notification);
                    
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (EncodeException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}