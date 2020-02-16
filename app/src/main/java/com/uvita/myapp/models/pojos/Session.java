package com.uvita.myapp.models.pojos;

import androidx.annotation.Nullable;
import okhttp3.Headers;

import java.util.HashMap;
import java.util.Map;

public class Session {
    private static final String UID = "uid";
    private static final String CLIENT = "client";
    private static final String ACCESS_TOKEN = "access-token";

    private String uid;
    private String client;
    private String accessToken;

    public Session(String uid, String client, String accessToken) {
        this.uid = uid;
        this.client = client;
        this.accessToken = accessToken;
    }

    public static Session create(String uid, String client, String accessToken) {
        return new Session(uid, client, accessToken);
    }

    @Nullable
    public static Session create(Headers headers) {
        if (headers.get(UID) != null
                && headers.get(ACCESS_TOKEN) != null
                && headers.get(CLIENT) != null) {
            String uid = headers.get(UID);
            String client = headers.get(CLIENT);
            String token = headers.get(ACCESS_TOKEN);
            return new Session(uid, client, token);
        } else {
            return null;
        }
    }

    public Map<String, String> toHeaders() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put(UID, uid);
        headers.put(CLIENT, client);
        headers.put(ACCESS_TOKEN, accessToken);
        return headers;
    }
}