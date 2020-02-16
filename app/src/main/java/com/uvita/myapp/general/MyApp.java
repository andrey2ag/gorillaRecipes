package com.uvita.myapp.general;

import com.uvita.myapp.models.pojos.Session;

import org.greenrobot.eventbus.EventBus;

// TODO migrate to kotlin @Singleton
public class MyApp {

    public static Session currentSession;
    public static boolean blockBack = false;

    public static EventBus getEventBus() {
        return EventBus.getDefault();
    }
}