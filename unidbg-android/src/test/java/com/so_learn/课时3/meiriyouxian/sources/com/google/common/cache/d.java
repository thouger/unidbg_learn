package com.google.common.cache;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.z;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;

/* compiled from: ForwardingCache */
public abstract class d<K, V> extends z implements b<K, V> {
    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.z
    public abstract b<K, V> delegate();

    protected d() {
    }

    @Override // com.google.common.cache.b
    public V getIfPresent(Object obj) {
        return (V) delegate().getIfPresent(obj);
    }

    @Override // com.google.common.cache.b
    public V get(K k, Callable<? extends V> callable) throws ExecutionException {
        return (V) delegate().get(k, callable);
    }

    @Override // com.google.common.cache.b
    public ImmutableMap<K, V> getAllPresent(Iterable<?> iterable) {
        return delegate().getAllPresent(iterable);
    }

    @Override // com.google.common.cache.b
    public void put(K k, V v) {
        delegate().put(k, v);
    }

    @Override // com.google.common.cache.b
    public void putAll(Map<? extends K, ? extends V> map) {
        delegate().putAll(map);
    }

    @Override // com.google.common.cache.b
    public void invalidate(Object obj) {
        delegate().invalidate(obj);
    }

    @Override // com.google.common.cache.b
    public void invalidateAll(Iterable<?> iterable) {
        delegate().invalidateAll(iterable);
    }

    @Override // com.google.common.cache.b
    public void invalidateAll() {
        delegate().invalidateAll();
    }

    @Override // com.google.common.cache.b
    public long size() {
        return delegate().size();
    }

    @Override // com.google.common.cache.b
    public c stats() {
        return delegate().stats();
    }

    @Override // com.google.common.cache.b
    public ConcurrentMap<K, V> asMap() {
        return delegate().asMap();
    }

    @Override // com.google.common.cache.b
    public void cleanUp() {
        delegate().cleanUp();
    }
}
