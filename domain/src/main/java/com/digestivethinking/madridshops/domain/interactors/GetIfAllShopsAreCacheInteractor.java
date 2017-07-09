package com.digestivethinking.madridshops.domain.interactors;

public interface GetIfAllShopsAreCacheInteractor {

    String SHOP_SAVED = "shopsSaved";

    void execute(Runnable onAllShopsAreCached, Runnable onAllShopsAreNotCached);
}
