package com.requests.backend.controllers;

import com.requests.backend.models.Notification;
import com.requests.backend.models.TextDecoder;
import com.requests.backend.models.TextEncoder;
import com.requests.backend.models.User;
import com.requests.backend.repositories.FollowRepository;
import com.requests.backend.repositories.TokenRepository;
import com.util.security.Hasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import static com.util.Constants.LOGGER;

@ServerEndpoint(value = "/websocket/{token}", decoders = TextDecoder.class, encoders = TextEncoder.class)
@Component
public class NotificationsWebsocket {

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private TokenRepository tokenRepository;

    // Store all socket session and their corresponding username.
    private static final Map<Session, User> sessionUsernameMap = new Hashtable<>();
    private static final Map <String, Session> usernameSessionMap = new Hashtable<> ();

    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token)
            throws IOException {
        LOGGER.info("WEBSOCKETS");
        LOGGER.info(token);
        LOGGER.info(tokenRepository.queryGetToken(Hasher.sha256(token))[0].toString());
        LOGGER.info(tokenRepository.queryGetToken(Hasher.sha256(token))[0].getUser().getUsername());
        User user = tokenRepository.queryGetToken(Hasher.sha256(token))[0].getUser();
        sessionUsernameMap.put(session, user);
        usernameSessionMap.put(user.getUsername(), session);

    }

    @OnClose
    public void onClose(Session session) throws IOException {

//        User user = sessionUsernameMap.get(session);
//        sessionUsernameMap.remove(session);
//        usernameSessionMap.remove(user.getUsername());

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
