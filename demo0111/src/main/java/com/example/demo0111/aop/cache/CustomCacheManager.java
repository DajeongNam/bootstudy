package com.example.demo0111.aop.cache;

import java.util.Collection;

public interface CustomCacheManager {
    AbstractCustomCache getCache(String name);
    Collection<String> getCacheStorageNames();
}
