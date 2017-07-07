package com.digestivethinking.madridshops.domain.managers.network.mappers;

import android.util.Log;

import com.digestivethinking.madridshops.domain.managers.network.entities.ShopEntity;
import com.digestivethinking.madridshops.domain.model.Shop;
import com.digestivethinking.madridshops.domain.model.Shops;

import java.util.List;

public class ShopEntityIntoShopsMapper {

    //TODO test mapeo
    public static Shops map(final List<ShopEntity> shopEntities) {
        Shops shops = new Shops();

        for (ShopEntity shopEntity : shopEntities) {
            Shop shop = Shop.of(shopEntity.getId(), shopEntity.getName())
                    .setDescription_en(shopEntity.getDescription_en())
                    .setDescription_es(shopEntity.getDescription_es())
                    .setLogo_img_url(shopEntity.getLogo_img())
                    .setImg_url(shopEntity.getImg())
                    .setUrl(shopEntity.getUrl())
                    .setAddress(shopEntity.getAddress())
                    .setOpening_hours_en(shopEntity.getOpening_hours_en())
                    .setOpening_hours_es(shopEntity.getOpening_hours_es())
                    .setGps_lat(getCorrectCoordinateComponent(shopEntity.getGps_lat()))
                    .setGps_lon(getCorrectCoordinateComponent(shopEntity.getGps_lon()));

            shops.add(shop);
        }

        return shops;
    }

    public static float getCorrectCoordinateComponent(String coordinateComponent) {
        float coordinate = 0.0f;

        String s = coordinateComponent.replace(",", "");

        try {
            coordinate = Float.parseFloat(s);
        } catch (Exception e) {
            Log.d("ERROR CONVERTING", String.format("Can't convert %s", coordinateComponent));
        }

        return coordinate;
    }
}


