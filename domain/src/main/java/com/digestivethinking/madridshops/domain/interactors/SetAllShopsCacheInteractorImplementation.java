package com.digestivethinking.madridshops.domain.interactors;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.lang.ref.WeakReference;

public class SetAllShopsCacheInteractorImplementation implements SetAllShopsCacheInteractor {

    private WeakReference<Context> context;

    public SetAllShopsCacheInteractorImplementation(Context context) {
        this.context = new WeakReference<Context>(context);
    }

    @Override
    public void execute(boolean shopsSaved) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.get());

        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean(GetIfAllShopsAreCacheInteractor.SHOP_SAVED, shopsSaved);

        editor.commit();
    }
}
