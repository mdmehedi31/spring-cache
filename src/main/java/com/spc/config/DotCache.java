package com.spc.config;

import org.jspecify.annotations.Nullable;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

public class DotCache implements Cache {

    private final ConcurrentHashMap<Object, Object> cache;
    private final String name;

    public DotCache(String name) {
        this.name = name;
        this.cache = new ConcurrentHashMap<>();
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public Object getNativeCache() {
        return null;
    }

    @Override
    public @Nullable ValueWrapper get(Object key) {
        Object value = cache.get(key);
        return value !=null ? new SimpleValueWrapper(value) : null;
    }

    @Override
    public <T> @Nullable T get(Object key, @Nullable Class<T> type) {
        return null;
    }

    @Override
    public <T> @Nullable T get(Object key, Callable<T> valueLoader) {
        return null;
    }

    @Override
    public void put(Object key, @Nullable Object value) {
    this.cache.put(key, value);
    }

    @Override
    public void evict(Object key) {

    }

    @Override
    public void clear() {

    }
}
