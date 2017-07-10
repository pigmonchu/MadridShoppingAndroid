package com.digestivethinking.madridshops.domain.managers.cache;


import android.content.Context;
import android.support.annotation.Nullable;

import com.digestivethinking.madridshops.domain.managers.db.ShopDAO;

import java.lang.ref.WeakReference;

public class ClearCacheManagerDAOImplementation implements ClearCacheManager {
    private WeakReference<Context> contextWeakReference;

    public ClearCacheManagerDAOImplementation(Context contextWeakReference) {
        this.contextWeakReference = new WeakReference<Context>(contextWeakReference);
    }

    @Override
    public void execute(@Nullable final Runnable completion) {
        ShopDAO shopDAO = new ShopDAO(contextWeakReference.get());

        shopDAO.deleteAll();

        if (completion != null) {
            completion.run();
        }
    }
}
