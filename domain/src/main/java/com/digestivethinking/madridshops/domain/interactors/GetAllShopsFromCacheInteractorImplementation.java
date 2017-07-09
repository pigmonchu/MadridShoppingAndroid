package com.digestivethinking.madridshops.domain.interactors;


import android.support.annotation.NonNull;

import com.digestivethinking.madridshops.domain.managers.cache.GetAllShopsFromCacheManager;
import com.digestivethinking.madridshops.domain.managers.cache.GetAllShopsFromCacheManagerCompletion;
import com.digestivethinking.madridshops.domain.model.Shops;


//TODO implementar errorCompletion

public class GetAllShopsFromCacheInteractorImplementation implements GetAllShopsFromCacheInteractor {

    private GetAllShopsFromCacheManager cacheManager;

    public GetAllShopsFromCacheInteractorImplementation(final GetAllShopsFromCacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public void execute(@NonNull final GetAllShopsInteractorCompletion completion) {

        cacheManager.execute(new GetAllShopsFromCacheManagerCompletion() {
            @Override
            public void completion(@NonNull Shops shops) {
                completion.completion(shops);

            }
        });

    }
}
