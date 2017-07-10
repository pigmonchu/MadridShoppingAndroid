package com.digestivethinking.madridshops;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.digestivethinking.madridshops.domain.interactors.GetAllShopsInteractor;
import com.digestivethinking.madridshops.domain.interactors.GetAllShopsInteractorCompletion;
import com.digestivethinking.madridshops.domain.interactors.GetAllShopsInteractorImplementation;
import com.digestivethinking.madridshops.domain.interactors.InteractorErrorCompletion;
import com.digestivethinking.madridshops.domain.managers.network.GetAllShopsManagerImplementation;
import com.digestivethinking.madridshops.domain.managers.network.GetDataManagerCompletion;
import com.digestivethinking.madridshops.domain.managers.network.ManagerErrorCompletion;
import com.digestivethinking.madridshops.domain.managers.network.NetworkManager;
import com.digestivethinking.madridshops.domain.model.Shops;
import com.squareup.picasso.Picasso;

public class MadridShopsApp extends MultiDexApplication {

    public static final String APP_NAME =  MadridShopsApp.class.getCanonicalName();

    @Override
    public void onCreate() {
        super.onCreate();

        Log.v(APP_NAME, "App starting");

//        Picasso.with(getApplicationContext()).setLoggingEnabled(true);
        Picasso.with(getApplicationContext()).setIndicatorsEnabled(true);

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();


    }
}
