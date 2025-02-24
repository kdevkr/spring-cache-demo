package com.example.demo.cache;

import com.github.benmanes.caffeine.cache.stats.CacheStats;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CacheStatsDebugger {
    private final CacheManager cacheManager;

    @Scheduled(cron = "0 */5 * * * *")
    public void loggingStats() {
        if (!log.isDebugEnabled()) {
            return;
        }
        cacheManager.getCacheNames().forEach(cacheName -> {
            Cache cache = cacheManager.getCache(cacheName);
            if (cache instanceof CaffeineCache caffeineCache) {
                CacheStats stats = caffeineCache.getNativeCache().stats();
                log.debug("[cache-name: {}] -> {}", cacheName, stats);
            }
        });
    }
}
