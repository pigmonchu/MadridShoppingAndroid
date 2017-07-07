package com.digestivethinking.madridshops.domain.interactors;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.digestivethinking.madridshops.domain.managers.network.GetDataManagerCompletion;
import com.digestivethinking.madridshops.domain.managers.network.ManagerErrorCompletion;
import com.digestivethinking.madridshops.domain.managers.network.NetworkManager;
import com.digestivethinking.madridshops.domain.managers.network.entities.ShopEntity;
import com.digestivethinking.madridshops.domain.managers.network.mappers.ShopEntityIntoShopsMapper;
import com.digestivethinking.madridshops.domain.model.Shops;

import java.util.List;

public class GetAllShopsInteractorImplementation implements GetAllShopsInteractor {

    private NetworkManager networkManager;

    public GetAllShopsInteractorImplementation(@NonNull final NetworkManager networkManager) {
        this.networkManager = networkManager;
    }

    @Override
    public void execute(@NonNull final GetAllShopsInteractorCompletion completion, @Nullable final InteractorErrorCompletion onError) {
        if (this.networkManager == null) {
            if (onError == null) {
                throw new IllegalStateException("Network manager can't be null");
            } else {
                onError.error("Error en la conexión");
            }

        }

        this.networkManager.getDataFromServer(new GetDataManagerCompletion() {
            @Override
            public void completion(@NonNull List<ShopEntity> shopEntities) {
                Log.d("SHOP", shopEntities.toString());

                if (completion != null) {
                    Shops shops = ShopEntityIntoShopsMapper.map(shopEntities);
                    completion.completion(shops);
                }

            }
        }, new ManagerErrorCompletion() {
            @Override
            public void onError(String errorDescription) {
                if (onError != null) {
                    onError.error(errorDescription);
                }
            }
        });
    }
}
