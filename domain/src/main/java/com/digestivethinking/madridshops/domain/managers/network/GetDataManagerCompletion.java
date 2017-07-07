package com.digestivethinking.madridshops.domain.managers.network;

import android.support.annotation.NonNull;

import com.digestivethinking.madridshops.domain.managers.network.entities.ShopEntity;

import java.util.List;

public interface GetDataManagerCompletion {

    void completion(@NonNull final List<ShopEntity> shopEntities);

}
