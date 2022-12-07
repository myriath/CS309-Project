package com.requests.backend.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.websocket.*;

public class TextDecoder implements Decoder.Text<Notification> {
    public static Gson gson;

    @Override
    public Notification decode(String s) throws DecodeException {
        return gson.fromJson(s, Notification.class);
    }

    @Override
    public boolean willDecode(String s) {
        return s != null;
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
