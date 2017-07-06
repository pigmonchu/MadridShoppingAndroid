package com.digestivethinking.madridshops;

import android.app.Application;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.squareup.picasso.Picasso;

public class MadridShopsApp extends MultiDexApplication {

    public static final String APP_NAME =  MadridShopsApp.class.getCanonicalName();

    @Override
    public void onCreate() {
        super.onCreate();

        Log.v(APP_NAME, "App starting");

        Picasso.with(getApplicationContext()).setLoggingEnabled(true);
        Picasso.with(getApplicationContext()).setIndicatorsEnabled(true);

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();


    }
}
