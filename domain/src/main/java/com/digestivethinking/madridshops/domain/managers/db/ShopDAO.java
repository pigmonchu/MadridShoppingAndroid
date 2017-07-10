package com.digestivethinking.madridshops.domain.managers.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.digestivethinking.madridshops.domain.model.Shop;
import com.digestivethinking.madridshops.domain.model.Shops;

import java.util.ArrayList;
import java.util.List;

import static com.digestivethinking.madridshops.domain.managers.db.DBConstants.*;

public class ShopDAO implements DAOReadable<Shop>, DAOWritable<Shop> {


    private static final long EMPTY_SHOP = 0;
    private SQLiteDatabase dbReadConnection;
    private SQLiteDatabase dbWriteConnection;

    public ShopDAO(DBHelper dbHelper) {
        dbReadConnection = dbHelper.getReadableDatabase();
        dbWriteConnection = dbHelper.getWritableDatabase();

    }

    public ShopDAO(Context context) {
        this(new DBHelper(context));
    }

    @Override
    public Shop query(@NonNull long id) {

        String idAsString = String.format("%d", id);

        List<Shop> shops = query(KEY_SHOP_ID +" = ?", new String[]{idAsString}, null);

        if (shops == null || shops.size() != 1) {
            return null;
        }

        return shops.get(0);
    }

    @Override
    public @Nullable List<Shop> query(String where, String[] whereArgs, String orderBy) {
        Cursor c = dbReadConnection.query(TABLE_SHOP,
                ALL_COLUMNS,
                where,         //where
                whereArgs,     //where args
                orderBy,       //order by
                null,          //group
                null);         // having

        if (c == null || c.getCount() == 0) {
            return null;
        }

        List<Shop> shopList = new ArrayList<>();

        while (c.moveToNext()) {
            long id = c.getLong(c.getColumnIndex(KEY_SHOP_ID));
            String name = c.getString(c.getColumnIndex(KEY_SHOP_NAME));
            String address = c.getString(c.getColumnIndex(KEY_SHOP_ADDRESS));;
            String description_en = c.getString(c.getColumnIndex(KEY_SHOP_DESCRIPTION));;
            float gps_lat = c.getFloat(c.getColumnIndex(KEY_SHOP_LATITUDE));
            float gps_lon = c.getFloat(c.getColumnIndex(KEY_SHOP_LONGITUDE));
            String img_url = c.getString(c.getColumnIndex(KEY_SHOP_IMAGE_URL));;
            String logo_img_url = c.getString(c.getColumnIndex(KEY_SHOP_LOGO_IMAGE_URL));;
            String url = c.getString(c.getColumnIndex(KEY_SHOP_URL));

            Shop shop = Shop.of(id, name)
                    .setAddress(address)
                    .setDescription_en(description_en)
                    .setGps_lat(gps_lat)
                    .setGps_lon(gps_lon)
                    .setImg_url(img_url)
                    .setLogo_img_url(logo_img_url)
                    .setUrl(url);
            shopList.add(shop);
        }

        return shopList;
    }


    @Override
    public @Nullable List<Shop> query() {
        return query(null, null, KEY_SHOP_ID);

    }

    @Override
    public long insert(@NonNull Shop element) {
        if (element == null) {
            return EMPTY_SHOP;
        }
        dbWriteConnection.beginTransaction();
        long id = DBHelper.INVALID_ID;

        try {
            id = dbWriteConnection.insert(DBConstants.TABLE_SHOP, null, getContentValues(element));
            element.setId(id);

            dbWriteConnection.setTransactionSuccessful();
        } finally {
            dbWriteConnection.endTransaction();
        }

        return id;


    }

    private ContentValues getContentValues(Shop shop) {
        final ContentValues contentValues = new ContentValues();

        if (shop == null) {
            return contentValues;
        }

        contentValues.put(KEY_SHOP_ADDRESS, shop.getAddress());
        contentValues.put(KEY_SHOP_DESCRIPTION, shop.getDescription_en());
        contentValues.put(KEY_SHOP_IMAGE_URL, shop.getImg_url());
        contentValues.put(KEY_SHOP_LOGO_IMAGE_URL, shop.getLogo_img_url());
        contentValues.put(KEY_SHOP_LATITUDE, shop.getGps_lat());
        contentValues.put(KEY_SHOP_LONGITUDE, shop.getGps_lon());
        if (shop.getName() != null) {
            contentValues.put(KEY_SHOP_NAME, shop.getName());
        } else {
            contentValues.put(KEY_SHOP_NAME, "");
        }
        contentValues.put(KEY_SHOP_URL, shop.getUrl());

        return contentValues;
    }

    @Override
    public long update(@NonNull long id, @NonNull Shop element) {
        if (element == null || id < 1) {
            return EMPTY_SHOP;
        }
        dbWriteConnection.beginTransaction();

        int countUpdated = 0;
        try {
            String idAsString = "" + id;
            countUpdated = dbWriteConnection.update(DBConstants.TABLE_SHOP, getContentValues(element), DBConstants.KEY_SHOP_ID + " = ?", new String[] {idAsString});

            dbWriteConnection.setTransactionSuccessful();
        } finally {
            dbWriteConnection.endTransaction();
        }

        return countUpdated == 1 ? id : null;

    }

    @Override
    public long delete(@NonNull long id) {
        return delete(KEY_SHOP_ID + " = ?", "" + id);
    }


    @Override
    public long delete(@NonNull Shop element) {
        return delete(element.getId());

    }

    @Override
    public void deleteAll() {
        delete(null, null);
    }

    @Override
    public long delete(String where, String... whereClause) {
        dbWriteConnection.beginTransaction();
        int deletedRegs = 0;

        try {
            deletedRegs = dbWriteConnection.delete(TABLE_SHOP, where, whereClause);
            dbWriteConnection.setTransactionSuccessful();
        } finally {
            dbWriteConnection.endTransaction();
        }

        return deletedRegs;

    }

    @Override
    public int numRecords() {
        List<Shop> shopList = query();

        return shopList == null ? 0 : shopList.size();
    }
}
