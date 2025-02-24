package com.example.demo.cache;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.stereotype.Component;

@Component
public class CacheCustomizer implements CacheManagerCustomizer<CacheManager> {
    @Override
    public void customize(CacheManager cacheManager) {
        if (cacheManager instanceof CaffeineCacheManager caffeineCacheManager) {
            caffeineCacheManager.setAsyncCacheMode(false); // If webflux, enable async mode.
            caffeineCacheManager.setAllowNullValues(false); // Prevent NULL, throw exception.
        }
    }
}
