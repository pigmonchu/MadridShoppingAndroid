package com.digestivethinking.madridshops.domain.managers.cache;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.digestivethinking.madridshops.domain.managers.db.ShopDAO;
import com.digestivethinking.madridshops.domain.model.Shop;
import com.digestivethinking.madridshops.domain.model.Shops;

import java.lang.ref.WeakReference;

public class SaveAllShopsIntoCacheManagerDAOImplementation implements SaveAllShopsIntoCacheManager {
    private WeakReference<Context> contextWeakReference;

    public SaveAllShopsIntoCacheManagerDAOImplementation(Context contextWeakReference) {
        this.contextWeakReference = new WeakReference<Context>(contextWeakReference);
    }

    @Override
    public void execute(@NonNull final Shops shops, @Nullable final Runnable completion) {
        ShopDAO shopDAO = new ShopDAO(contextWeakReference.get());

        for (Shop shop : shops.getAll()) {
            shopDAO.insert(shop);
        }

        if (completion != null) {
            completion.run();
        }
    }
}
