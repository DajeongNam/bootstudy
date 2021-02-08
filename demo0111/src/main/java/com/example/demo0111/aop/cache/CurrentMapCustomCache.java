package com.example.demo0111.aop.cache;

import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
public class CurrentMapCustomCache extends AbstractCustomCache{

    private final String name;
    private final ConcurrentHashMap<String, Object> store;

    public String getName() { return this.name; }
    public Object getNativeCache() {
        return this.store;
    }

    @Override
    protected Optional<Object> lookup(Object key) {
        return Optional.ofNullable(this.store.get(key));
    }

    @Override
    public boolean put(String key, Object value) {
        this.store.put(key, value);
        return true;
    }

    @Override
    public void evict(Object key) {

    }

    @Override
    public void clear() {

    }
}
