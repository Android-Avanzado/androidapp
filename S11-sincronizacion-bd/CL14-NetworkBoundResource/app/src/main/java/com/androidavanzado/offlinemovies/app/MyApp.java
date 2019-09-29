package com.androidavanzado.offlinemovies.app;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {
    private static MyApp instance;

    public static MyApp getInstance() {return instance; }

    public static Context getContext() { return instance; }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
}
