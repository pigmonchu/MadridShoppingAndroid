package com.digestivethinking.madridshops.domain.managers.network.jsonparser;

import android.support.annotation.NonNull;

import com.digestivethinking.madridshops.domain.managers.network.entities.ShopEntity;
import com.digestivethinking.madridshops.domain.managers.network.entities.ShopsResponseEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

public class ShopsJsonParser {
    public List<ShopEntity> parse(@NonNull final String response) {
        List<ShopEntity> shopEntities = null;

        try {
            Gson gson = new GsonBuilder().create();

            Reader reader = new StringReader(response);
            ShopsResponseEntity shopsResponseEntity = gson.fromJson(reader, ShopsResponseEntity.class);
            shopEntities = shopsResponseEntity.getResult();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return shopEntities;
    }

}
