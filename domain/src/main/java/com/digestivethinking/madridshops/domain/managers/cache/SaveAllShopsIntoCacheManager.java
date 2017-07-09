package com.digestivethinking.madridshops.domain.managers.cache;


import com.digestivethinking.madridshops.domain.model.Shops;

public interface SaveAllShopsIntoCacheManager {
    void execute(Shops shops, Runnable completion);
}
