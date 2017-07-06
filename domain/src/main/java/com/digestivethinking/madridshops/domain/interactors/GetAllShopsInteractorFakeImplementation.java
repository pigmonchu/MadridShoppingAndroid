package com.digestivethinking.madridshops.domain.interactors;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.digestivethinking.madridshops.domain.model.Shop;
import com.digestivethinking.madridshops.domain.model.Shops;

public class GetAllShopsInteractorFakeImplementation implements GetAllShopsInteractor {

    @Override
    public void execute(@NonNull final GetAllShopsInteractorCompletion completion, @Nullable final InteractorErrorCompletion onError) {
        Shops shops = new Shops();

        for (int i = 0; i < 10; i++) {
            Shop shop = Shop.of(i, "My shop " + i);
            shops.add(shop);
        }

        if (completion != null) {
            completion.completion(shops);
        }


    }
}
