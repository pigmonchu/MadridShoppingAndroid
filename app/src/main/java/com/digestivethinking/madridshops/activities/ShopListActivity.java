package com.digestivethinking.madridshops.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.digestivethinking.madridshops.R;
import com.digestivethinking.madridshops.domain.interactors.GetAllShopsInteractor;
import com.digestivethinking.madridshops.domain.interactors.GetAllShopsInteractorCompletion;
import com.digestivethinking.madridshops.domain.interactors.GetAllShopsInteractorFakeImplementation;
import com.digestivethinking.madridshops.domain.interactors.InteractorErrorCompletion;
import com.digestivethinking.madridshops.domain.model.Shops;

public class ShopListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);

        // obtain shops list
        GetAllShopsInteractor getAllShopsInteractor = new GetAllShopsInteractorFakeImplementation();

        getAllShopsInteractor.execute(
                new GetAllShopsInteractorCompletion() {
                                          @Override
                                          public void completion(Shops shops) {
                                              System.out.println("Hello Hello");
                                          }
                                      },
                new InteractorErrorCompletion() {
                    @Override
                    public void error(String errorDescription) {

                    }
                });


    }
}
