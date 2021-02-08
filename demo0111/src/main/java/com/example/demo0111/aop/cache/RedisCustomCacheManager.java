package com.example.demo0111.aop.cache;

import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class RedisCustomCacheManager implements CustomCacheManager{


    @Override
    public AbstractCustomCache getCache(String name) {
        return null;
    }

    @Override
    public Collection<String> getCacheStorageNames() {
        return null;
    }
}
