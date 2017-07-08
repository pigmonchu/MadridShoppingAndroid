package com.digestivethinking.madridshops.util;

import android.support.annotation.NonNull;

import com.digestivethinking.madridshops.domain.model.Shop;

public class StaticMapImage {
    public static String getMapImageUrl(@NonNull final Shop shop) {
        String url = String.format(Constants.STATIC_MAP, shop.getGps_lat(), shop.getGps_lon());
        return url;
    }
}
