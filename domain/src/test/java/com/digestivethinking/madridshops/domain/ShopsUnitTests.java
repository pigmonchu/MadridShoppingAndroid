package com.digestivethinking.madridshops.domain;

import com.digestivethinking.madridshops.domain.model.Shop;
import com.digestivethinking.madridshops.domain.model.Shops;

import org.junit.Test;

import static org.junit.Assert.*;


public class ShopsUnitTests {
    @Test
    public void after_creation_shops_size_is_zero() throws Exception {

        Shops shops = new Shops();
        assertEquals(0, shops.size());
    }

    @Test
    public void shops_adding_one_shop_size_is_one() throws Exception {

        Shops sut = new Shops();

        sut.add(Shop.of(1, "My shop"));

        assertEquals(1, sut.size());
    }

    @Test
    public void shops_adding_one_shop_and_deleting_size_is_one() throws Exception {

        Shops sut = new Shops();

        Shop shop = Shop.of(1, "My shop");
        sut.add(shop);
        sut.delete(shop);

        assertEquals(0, sut.size());
    }

    @Test
    public void shops_adding_one_shop_and_getting_returns_that_shop() throws Exception {

        Shops sut = new Shops();

        Shop shop = Shop.of(1, "My shop");
        sut.add(shop);
        Shop retrievedShop = sut.getAt(0);

//        assertEquals(shop, retrievedShop);
        assertEquals(shop.getName(), retrievedShop.getName());
        assertEquals(shop.getId(), retrievedShop.getId());
    }

    @Test
    public void shops_adding_serveral_shops_returns_all_shops() throws Exception {
        Shops sut = new Shops();

        for (int i = 0; i < 10; i++) {
            sut.add(Shop.of(i, "My shop " + i));
        }

        assertEquals(10, sut.size());
        assertEquals(10, sut.getAll().size());

        for (int i = 0; i < 10; i++) {
            assertEquals(i, sut.getAll().get(i).getId());
            assertEquals("My shop " + i, sut.getAll().get(i).getName());
        }

    }

    @Test
    public void shops_adding_one_shop_and_update_one_shop_retrieve_modified_shop() throws Exception {
        Shops sut = new Shops();

        sut.add(Shop.of(1, "My shop is 1"));
        Shop newShop = Shop.of(2, "My shop is two");

        sut.update(newShop, 0);

        assertEquals(2, sut.getAt(0).getId());
        assertEquals("My shop is two", sut.getAt(0).getName());


    }
}