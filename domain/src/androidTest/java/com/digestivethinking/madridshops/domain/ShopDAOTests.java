package com.digestivethinking.madridshops.domain;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.core.deps.guava.util.concurrent.ExecutionError;
import android.support.test.runner.AndroidJUnit4;

import com.digestivethinking.madridshops.domain.managers.db.ShopDAO;
import com.digestivethinking.madridshops.domain.model.Shop;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)
public class ShopDAOTests {

    public static final int TEST_ID = 888;
    public static final String TEST_NAME = "name";

    @Test
    public void given_shop_DAO_inserts_shop() throws Exception {

        Context appContext = InstrumentationRegistry.getTargetContext();

        ShopDAO sut = new ShopDAO(appContext);

        Shop shop = Shop.of(1, "Shop one").setAddress("C/ Villa Romana, 12").setGps_lat(10).setGps_lon(11);

        long idShop = sut.insert(shop);

        assertTrue(idShop > 0);
    }

    @Test
    public void given_inserted_shops_DAO_queries_all_shops() throws Exception {

        Context appContext = InstrumentationRegistry.getTargetContext();
        ShopDAO sut = new ShopDAO(appContext);

        Shop shop = insertShop(sut, "Shop test", "address", 10, 11);

        List<Shop> shops = sut.query();

        assertNotNull(shops);
        assertTrue(shops.size() >= 1);
    }

    @Test
    public void given_inserted_shops_deleteAll_returns_empty_table() throws Exception {

        Context appContext = InstrumentationRegistry.getTargetContext();
        ShopDAO sut = new ShopDAO(appContext);

        insertShops();

        sut.deleteAll();

        List<Shop> shops = sut.query();

        assertNull(shops);
    }

    @Test
    public void given_inserted_shop_I_can_delete_that_shop() throws Exception {

        Context appContext = InstrumentationRegistry.getTargetContext();
        ShopDAO sut = new ShopDAO(appContext);


        sut.deleteAll();
        Shop insertedShop = insertShop(sut, TEST_NAME, "", 10, 10);

        Shop shop = sut.query(insertedShop.getId());

        assertEquals(insertedShop.getId(), shop.getId());
        assertEquals(TEST_NAME, shop.getName());
    }

    @Test
    public void given_N_inserted_shops_I_can_count_N_registers() throws Exception {

        Context appContext = InstrumentationRegistry.getTargetContext();
        ShopDAO sut = new ShopDAO(appContext);

        sut.deleteAll();
        int numberOfInsertedShops = insertShops();

        assertEquals(numberOfInsertedShops, sut.numRecords());
    }

    @Test
    public void given_a_inserted_shop_I_can_update_this_shop() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        ShopDAO sut = new ShopDAO(appContext);

        sut.deleteAll();
        Shop theShop = insertShop(sut, "Tienda original", "en dirección original", 10, 11);

        Shop theNewShop = Shop.of(-1, "Tienda modificada").setAddress("en dirección modificada").setGps_lat(11).setGps_lon(12);

        long idUpdated = sut.update(theShop.getId(), theNewShop);

        Shop queryShop = sut.query(idUpdated);

        assertEquals(theNewShop.getName(), queryShop.getName());
        assertEquals(theNewShop.getAddress(), queryShop.getAddress());
        assertEquals(theNewShop.getGps_lat(), queryShop.getGps_lat(), 0.01);
        assertEquals(theNewShop.getGps_lon(), queryShop.getGps_lon(), 0.01);
        assertNotEquals(theNewShop.getName(), theShop.getName());
        assertNotEquals(theNewShop.getAddress(), theShop.getAddress());
        assertNotEquals(theNewShop.getGps_lat(), theShop.getGps_lat(), 0.01);
        assertNotEquals(theNewShop.getGps_lon(), theShop.getGps_lon(), 0.01);
    }


    private Shop insertShop(ShopDAO sut, String name, String address, float lat, float lng) {

        Shop shop = Shop.of(-1, name).setAddress(address).setGps_lat(lat).setGps_lon(lng);

        long insertedId = sut.insert(shop);
        return shop;
    }

    private int insertShops() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        ShopDAO sut = new ShopDAO(appContext);

        int i = 0;
        for (i = 0; i < 10; i++) {
            insertShop(sut, "Shop " + i, "Address " + i, i+1, i);
        }

        return i;
    }
}
