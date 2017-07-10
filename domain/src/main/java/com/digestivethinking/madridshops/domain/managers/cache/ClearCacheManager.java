package com.digestivethinking.madridshops.domain.managers.cache;


public interface ClearCacheManager {
    void execute(final Runnable completion);
}
