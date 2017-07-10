package com.digestivethinking.madridshops.domain.interactors;


import com.digestivethinking.madridshops.domain.managers.cache.ClearCacheManager;

public class ClearCacheInteractorImplementation implements ClearCacheInteractor {

    private ClearCacheManager manager;

    public ClearCacheInteractorImplementation(ClearCacheManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute(Runnable completion) {
        manager.execute(completion);


    }
}
