package com.digestivethinking.madridshops.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.digestivethinking.madridshops.R;
import com.digestivethinking.madridshops.domain.interactors.ClearCacheInteractor;
import com.digestivethinking.madridshops.domain.interactors.ClearCacheInteractorImplementation;
import com.digestivethinking.madridshops.domain.interactors.SetAllShopsCacheInteractor;
import com.digestivethinking.madridshops.domain.interactors.SetAllShopsCacheInteractorImplementation;
import com.digestivethinking.madridshops.domain.managers.cache.ClearCacheManager;
import com.digestivethinking.madridshops.domain.managers.cache.ClearCacheManagerDAOImplementation;
import com.digestivethinking.madridshops.navigator.Navigator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.activity_main__shops_button) Button shopsButton;
    @BindView(R.id.activity_main__activities_button) Button activitiesButton;
//    @BindView(R.id.activity_main__clear_cache_button) Button clearCacheButton;

    public static final String CLASS_NAME =  MainActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        shopsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v(CLASS_NAME, "clic en tiendas");

                Navigator.navigateFromMainActivityToShopListActivity(MainActivity.this);


            }
        });

        activitiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v(CLASS_NAME, "clic en actividades");
            }
        });


    }

    @OnClick(R.id.un_boton) void clearCache() {
        ClearCacheManager cacheManager = new ClearCacheManagerDAOImplementation(this);
        ClearCacheInteractor cacheInteractor = new ClearCacheInteractorImplementation(cacheManager);
        cacheInteractor.execute(new Runnable() {
            @Override
            public void run() {
                SetAllShopsCacheInteractor setAllShopsCacheinteractor = new SetAllShopsCacheInteractorImplementation(getBaseContext());
                setAllShopsCacheinteractor.execute(false);

            }
        });
    }
}
