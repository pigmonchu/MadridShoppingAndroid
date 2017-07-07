package com.digestivethinking.madridshops.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.digestivethinking.madridshops.R;
import com.digestivethinking.madridshops.domain.interactors.GetAllShopsInteractor;
import com.digestivethinking.madridshops.domain.interactors.GetAllShopsInteractorCompletion;
import com.digestivethinking.madridshops.domain.interactors.GetAllShopsInteractorFakeImplementation;
import com.digestivethinking.madridshops.domain.interactors.GetAllShopsInteractorImplementation;
import com.digestivethinking.madridshops.domain.interactors.InteractorErrorCompletion;
import com.digestivethinking.madridshops.domain.managers.network.GetAllShopsManagerImplementation;
import com.digestivethinking.madridshops.domain.managers.network.NetworkManager;
import com.digestivethinking.madridshops.domain.model.Shop;
import com.digestivethinking.madridshops.domain.model.Shops;
import com.digestivethinking.madridshops.fragments.ShopsFragment;
import com.digestivethinking.madridshops.navigator.Navigator;
import com.digestivethinking.madridshops.views.OnElementClick;

import java.lang.ref.WeakReference;

public class ShopListActivity extends AppCompatActivity {

    private ShopsFragment shopsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);

        shopsFragment = (ShopsFragment) getSupportFragmentManager().findFragmentById(R.id.activity_shop_list__fragment_shops);

        // obtain shops list
        NetworkManager manager = new GetAllShopsManagerImplementation(this);
        GetAllShopsInteractor getAllShopsInteractor = new GetAllShopsInteractorImplementation(manager);

        getAllShopsInteractor.execute(
                new GetAllShopsInteractorCompletion() {
                                          @Override
                                          public void completion(Shops shops) {
                                              shopsFragment.setShops(shops);

                                              shopsFragment.setOnElementClickListener(new OnElementClick<Shop>() {
                                                  @Override
                                                  public void clickedOn(@NonNull Shop element, int position) {
                                                      Navigator.navigateFromShopListActivityToShopDetailActivity(ShopListActivity.this, element, position);
                                                  }
                                              });
                                          }
                                      },
                new InteractorErrorCompletion() {
                    @Override
                    public void error(String errorDescription) {

                    }
                });


    }
}
