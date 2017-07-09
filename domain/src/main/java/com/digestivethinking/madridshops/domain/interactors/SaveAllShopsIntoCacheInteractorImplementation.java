package com.digestivethinking.madridshops.domain.interactors;


import com.digestivethinking.madridshops.domain.managers.cache.SaveAllShopsIntoCacheManager;
import com.digestivethinking.madridshops.domain.model.Shops;

public class SaveAllShopsIntoCacheInteractorImplementation implements SaveAllShopsIntoCacheInteractor {

    private SaveAllShopsIntoCacheManager cacheManager;

    public SaveAllShopsIntoCacheInteractorImplementation(SaveAllShopsIntoCacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }


    @Override
    public void execute(Shops shops, Runnable completion) {
        cacheManager.execute(shops, completion);
    }
}
