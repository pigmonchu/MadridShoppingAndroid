package com.digestivethinking.madridshops.domain.managers.cache;

import android.support.annotation.NonNull;

import com.digestivethinking.madridshops.domain.model.Shops;

public interface GetAllShopsFromCacheManagerCompletion {

    void completion(@NonNull final Shops shops);

}
