package com.digestivethinking.madridshops.domain.model;

import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Shops implements ShopsIterable, ShopsUpdatable{

    private List<Shop> shops;

    public static Shops from(@NonNull final List<Shop> shopList) {
        Shops shops = new Shops();

        if (shopList == null) {
            return shops;
        }

        for (final Shop shop : shopList) {
            shops.add(shop);
        }
        return shops;
    }

    public Shops() {

    }


    private List<Shop> getShops() {
        if (shops == null) {
            shops = new LinkedList<>();
        }
        return shops;
    }

    @Override
    public int size() {
        return getShops().size();
    }

    @Override
    public Shop getAt(long index) {
        return getShops().get((int)index);
    }

    @Override
    public List<Shop> getAll() {
        List<Shop> finalCopy = new LinkedList<>();

        for (Shop shop : getShops()) {
            finalCopy.add(shop);
        }

        return finalCopy;
    }

    @Override
    public void add(Shop shop) {
        getShops().add(shop);
    }

    @Override
    public void delete(Shop shop) {
        getShops().remove(shop);

    }

    @Override
    public void update(Shop newShop, long index) {
        getShops().set((int)index, newShop);
    }

}
