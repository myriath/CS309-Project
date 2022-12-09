package com.requests.backend.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class TextEncoder implements Encoder.Text<Notification> {
    public static Gson gson;

    @Override
    public String encode(Notification wsNotification) throws EncodeException {
        return gson.toJson(wsNotification);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        gson = new GsonBuilder()
                .serializeNulls()
                .create();
    }

    @Override
    public void destroy() {
        gson = null;
    }
}
