package com.digestivethinking.madridshops.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.digestivethinking.madridshops.R;
import com.digestivethinking.madridshops.domain.interactors.GetAllShopsFromCacheInteractor;
import com.digestivethinking.madridshops.domain.interactors.GetAllShopsFromCacheInteractorImplementation;
import com.digestivethinking.madridshops.domain.interactors.GetAllShopsInteractor;
import com.digestivethinking.madridshops.domain.interactors.GetAllShopsInteractorCompletion;
import com.digestivethinking.madridshops.domain.interactors.GetAllShopsInteractorImplementation;
import com.digestivethinking.madridshops.domain.interactors.GetIfAllShopsAreCacheInteractor;
import com.digestivethinking.madridshops.domain.interactors.GetIfAllShopsAreCacheInteractorImplementations;
import com.digestivethinking.madridshops.domain.interactors.InteractorErrorCompletion;
import com.digestivethinking.madridshops.domain.interactors.SaveAllShopsIntoCacheInteractor;
import com.digestivethinking.madridshops.domain.interactors.SaveAllShopsIntoCacheInteractorImplementation;
import com.digestivethinking.madridshops.domain.interactors.SetAllShopsCacheInteractor;
import com.digestivethinking.madridshops.domain.interactors.SetAllShopsCacheInteractorImplementation;
import com.digestivethinking.madridshops.domain.managers.cache.GetAllShopsFromCacheManager;
import com.digestivethinking.madridshops.domain.managers.cache.GetAllShopsFromCacheManagerDAOImplementation;
import com.digestivethinking.madridshops.domain.managers.cache.SaveAllShopsIntoCacheManager;
import com.digestivethinking.madridshops.domain.managers.cache.SaveAllShopsIntoCacheManagerDAOImplementation;
import com.digestivethinking.madridshops.domain.managers.network.GetAllShopsManagerImplementation;
import com.digestivethinking.madridshops.domain.managers.network.NetworkManager;
import com.digestivethinking.madridshops.domain.model.Shop;
import com.digestivethinking.madridshops.domain.model.Shops;
import com.digestivethinking.madridshops.fragments.ShopsFragment;
import com.digestivethinking.madridshops.navigator.Navigator;
import com.digestivethinking.madridshops.views.OnElementClick;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopListActivity extends AppCompatActivity {

    @BindView(R.id.activity_shop_list__spinner) ProgressBar spinner;

    private ShopsFragment shopsFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);
        ButterKnife.bind(this);

        shopsFragment = (ShopsFragment) getSupportFragmentManager().findFragmentById(R.id.activity_shop_list__fragment_shops);

        GetIfAllShopsAreCacheInteractor getIfAllShopsAreCacheInteractor = new GetIfAllShopsAreCacheInteractorImplementations(this);

        getIfAllShopsAreCacheInteractor.execute(new Runnable() {
            @Override
            public void run() {
                readDataFromCache();
            }
        }, new Runnable() {
            @Override
            public void run() {
                obtainShopsListFromCloud();
            }
        });


    }

    private void readDataFromCache() {
        GetAllShopsFromCacheManager getAllShopsFromCacheManager = new GetAllShopsFromCacheManagerDAOImplementation(this);
        GetAllShopsFromCacheInteractor getAllShopsFromCacheInteractor = new GetAllShopsFromCacheInteractorImplementation(getAllShopsFromCacheManager);
        getAllShopsFromCacheInteractor.execute(new GetAllShopsInteractorCompletion() {
            @Override
            public void completion(@NonNull Shops shops) {
                configShopsFragment(shops);
            }
        });
    }

    private void obtainShopsListFromCloud() {
        spinner.setVisibility(View.VISIBLE);
        NetworkManager manager = new GetAllShopsManagerImplementation(this);
        GetAllShopsInteractor getAllShopsInteractor = new GetAllShopsInteractorImplementation(manager);

        getAllShopsInteractor.execute(
                new GetAllShopsInteractorCompletion() {
                  @Override
                  public void completion(Shops shops) {


                      SaveAllShopsIntoCacheManager cacheManager = new SaveAllShopsIntoCacheManagerDAOImplementation(getBaseContext());
                      SaveAllShopsIntoCacheInteractor saveCacheInteractor = new SaveAllShopsIntoCacheInteractorImplementation(cacheManager);
                      saveCacheInteractor.execute(shops, new Runnable() {
                          @Override
                          public void run() {
                              SetAllShopsCacheInteractor setAllShopsCacheinteractor = new SetAllShopsCacheInteractorImplementation(getBaseContext());
                              setAllShopsCacheinteractor.execute(true);

                          }
                      });

                      configShopsFragment(shops);
                      spinner.setVisibility(View.INVISIBLE);
                  }
              },
                new InteractorErrorCompletion() {
                    @Override
                    public void error(String errorDescription) {

                    }
                });
    }

    private void configShopsFragment(final Shops shops) {
        shopsFragment.setShops(shops);
        shopsFragment.setOnElementClickListener(new OnElementClick<Shop>() {
            @Override
            public void clickedOn(@NonNull Shop element, int position) {
                Navigator.navigateFromShopListActivityToShopDetailActivity(ShopListActivity.this, element, position);
            }
        });
    }
}
