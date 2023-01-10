package com.google.common.collect;

import java.util.concurrent.ConcurrentMap;

/* compiled from: ForwardingConcurrentMap */
public abstract class t<K, V> extends v<K, V> implements ConcurrentMap<K, V> {
    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.v, com.google.common.collect.z
    public abstract ConcurrentMap<K, V> delegate();

    protected t() {
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V putIfAbsent(K k, V v) {
        return delegate().putIfAbsent(k, v);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public boolean remove(Object obj, Object obj2) {
        return delegate().remove(obj, obj2);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V replace(K k, V v) {
        return delegate().replace(k, v);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public boolean replace(K k, V v, V v2) {
        return delegate().replace(k, v, v2);
    }
}
