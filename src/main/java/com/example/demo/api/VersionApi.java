package com.example.demo.api;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class VersionApi {
    private final Map<String, String> versions = new ConcurrentHashMap<>();

    @CacheEvict(cacheNames = "version", allEntries = true)
    @PostMapping("/version")
    public String addVersion(@RequestParam("v") String version) {
        versions.put(version, version);
        return version;
    }

    @CacheEvict(cacheNames = "version", allEntries = true)
    @DeleteMapping("/version")
    public boolean removeVersion(@RequestParam("v") String version) {
        String removed = versions.remove(version);
        return StringUtils.isNotBlank(removed);
    }

    @Cacheable(cacheNames = "version", keyGenerator = "cacheKey")
    @GetMapping("/version")
    public List<String> getVersions() {
        return versions.values().stream().toList();
    }
}
