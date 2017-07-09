package com.digestivethinking.madridshops.domain.managers.cache;


import android.content.Context;
import android.support.annotation.NonNull;

import com.digestivethinking.madridshops.domain.managers.db.ShopDAO;
import com.digestivethinking.madridshops.domain.model.Shop;
import com.digestivethinking.madridshops.domain.model.Shops;

import java.lang.ref.WeakReference;
import java.util.List;

public class GetAllShopsFromCacheManagerDAOImplementation implements GetAllShopsFromCacheManager{

    private WeakReference<Context> contextWeakReference;

    public GetAllShopsFromCacheManagerDAOImplementation(Context contextWeakReference) {
        this.contextWeakReference = new WeakReference<Context>(contextWeakReference);
    }

    @Override
    public void execute(@NonNull GetAllShopsFromCacheManagerCompletion completion) {

        //TODO control de errores
        ShopDAO dao = new ShopDAO(contextWeakReference.get());
        List<Shop> shopList = dao.query();
        if (shopList == null) {
            completion.completion(null);
        }
        Shops shops = Shops.from(shopList);
        completion.completion(shops);
    }
}
