package com.example.demo.cache;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class CacheKey implements KeyGenerator {
    @Override
    public Object generate(Object target, Method method, Object... params) {
        String key = StringUtils.join(params, "_");
        return String.join(":", target.getClass().getSimpleName(), method.getName(), key);
    }
}
