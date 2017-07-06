package com.digestivethinking.madridshops.domain.interactors;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.digestivethinking.madridshops.domain.model.Shop;
import com.digestivethinking.madridshops.domain.model.Shops;

public class GetAllShopsInteractorFakeImplementation implements GetAllShopsInteractor {

    @Override
    public void execute(@NonNull final GetAllShopsInteractorCompletion completion, @Nullable final InteractorErrorCompletion onError) {
        Shops shops = new Shops();

        for (int i = 0; i < 50; i++) {
            Shop shop = Shop.of(i, "My shop " + i)
                    .setOpening_hours_en("de 9:00 a 22:00")
                    .setLogo_img_url("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTT0a3EIC1cgEU6wl-EEOJEu0jwrqujAD4l6s7f3khv00cRWXNqdQ");
            shops.add(shop);
        }

        if (completion != null) {
            completion.completion(shops);
        }


    }
}
