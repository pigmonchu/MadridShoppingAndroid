package com.digestivethinking.madridshops.domain.interactors;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.lang.ref.WeakReference;

public class GetIfAllShopsAreCacheInteractorImplementations implements GetIfAllShopsAreCacheInteractor {

    private WeakReference<Context> context;

    public GetIfAllShopsAreCacheInteractorImplementations(Context context) {
        this.context = new WeakReference<Context>(context);
    }



    @Override
    public void execute(Runnable onAllShopsAreCached, Runnable onAllShopsAreNotCached) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.get());
        boolean shopsCached = preferences.getBoolean(SHOP_SAVED, false);

        if (shopsCached) {
            onAllShopsAreCached.run();
        } else {
            onAllShopsAreNotCached.run();
        }
    }
}
