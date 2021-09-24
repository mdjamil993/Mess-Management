package com.example.juber.myapplication;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by Juber on 1/12/2018.
 */

public class MyApplication extends Application {
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
