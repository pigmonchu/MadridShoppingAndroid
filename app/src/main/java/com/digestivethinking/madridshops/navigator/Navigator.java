package com.digestivethinking.madridshops.navigator;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.digestivethinking.madridshops.activities.MainActivity;
import com.digestivethinking.madridshops.activities.ShopListActivity;

public class Navigator {
    public static Intent navigateFromMainActivityToShopListActivity(@NonNull MainActivity mainActivity) {

        assert(mainActivity != null);

        final Intent i = new Intent(mainActivity, ShopListActivity.class);

        mainActivity.startActivity(i);

        return i;

    }
}
