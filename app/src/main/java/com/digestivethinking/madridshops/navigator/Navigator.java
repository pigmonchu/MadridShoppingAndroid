package com.digestivethinking.madridshops.navigator;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.digestivethinking.madridshops.activities.MainActivity;
import com.digestivethinking.madridshops.activities.ShopDetailActivity;
import com.digestivethinking.madridshops.activities.ShopListActivity;
import com.digestivethinking.madridshops.domain.model.Shop;

import static com.digestivethinking.madridshops.util.Constants.INTENT_SHOP_DETAIL;

public class Navigator {
    public static Intent navigateFromMainActivityToShopListActivity(@NonNull final MainActivity mainActivity) {

        assert(mainActivity != null);

        final Intent i = new Intent(mainActivity, ShopListActivity.class);

        mainActivity.startActivity(i);

        return i;

    }

    public static Intent navigateFromShopListActivityToShopDetailActivity(@NonNull final ShopListActivity shopListActivity, final Shop shop, final int position) {

        assert(shopListActivity != null);

        final Intent i = new Intent(shopListActivity, ShopDetailActivity.class);
        i.putExtra(INTENT_SHOP_DETAIL, shop);

        shopListActivity.startActivity(i);

        return i;
    }
}
