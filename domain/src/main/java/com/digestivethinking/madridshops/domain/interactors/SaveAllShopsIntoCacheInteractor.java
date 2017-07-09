package com.digestivethinking.madridshops.domain.interactors;


import com.digestivethinking.madridshops.domain.model.Shops;

public interface SaveAllShopsIntoCacheInteractor {
    void execute(Shops shops, Runnable completion);
}
