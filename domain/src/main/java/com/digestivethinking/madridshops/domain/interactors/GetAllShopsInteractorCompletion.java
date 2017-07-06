package com.digestivethinking.madridshops.domain.interactors;

import android.support.annotation.NonNull;

import com.digestivethinking.madridshops.domain.model.Shops;

public interface GetAllShopsInteractorCompletion {

    void completion(@NonNull final Shops shops);

}
