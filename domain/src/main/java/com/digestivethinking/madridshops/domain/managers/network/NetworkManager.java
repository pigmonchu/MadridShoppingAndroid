package com.digestivethinking.madridshops.domain.managers.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface NetworkManager {
    void getDataFromServer(@NonNull final GetDataManagerCompletion completion, @Nullable final ManagerErrorCompletion errorCompletion);

}
