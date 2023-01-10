package com.google.common.collect;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* compiled from: ForwardingMultimap */
public abstract class x<K, V> extends z implements ai<K, V> {
    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.z
    public abstract ai<K, V> delegate();

    protected x() {
    }

    @Override // com.google.common.collect.ai
    public Map<K, Collection<V>> asMap() {
        return delegate().asMap();
    }

    @Override // com.google.common.collect.ai
    public void clear() {
        delegate().clear();
    }

    @Override // com.google.common.collect.ai
    public boolean containsEntry(Object obj, Object obj2) {
        return delegate().containsEntry(obj, obj2);
    }

    @Override // com.google.common.collect.ai
    public boolean containsKey(Object obj) {
        return delegate().containsKey(obj);
    }

    @Override // com.google.common.collect.ai
    public boolean containsValue(Object obj) {
        return delegate().containsValue(obj);
    }

    @Override // com.google.common.collect.ai
    public Collection<Map.Entry<K, V>> entries() {
        return delegate().entries();
    }

    @Override // com.google.common.collect.ai, com.google.common.collect.ah
    public Collection<V> get(K k) {
        return delegate().get(k);
    }

    @Override // com.google.common.collect.ai
    public boolean isEmpty() {
        return delegate().isEmpty();
    }

    @Override // com.google.common.collect.ai
    public aj<K> keys() {
        return delegate().keys();
    }

    @Override // com.google.common.collect.ai
    public Set<K> keySet() {
        return delegate().keySet();
    }

    @Override // com.google.common.collect.ai
    public boolean put(K k, V v) {
        return delegate().put(k, v);
    }

    @Override // com.google.common.collect.ai
    public boolean putAll(K k, Iterable<? extends V> iterable) {
        return delegate().putAll(k, iterable);
    }

    @Override // com.google.common.collect.ai
    public boolean putAll(ai<? extends K, ? extends V> aiVar) {
        return delegate().putAll(aiVar);
    }

    @Override // com.google.common.collect.ai
    public boolean remove(Object obj, Object obj2) {
        return delegate().remove(obj, obj2);
    }

    @Override // com.google.common.collect.ai, com.google.common.collect.ah
    public Collection<V> removeAll(Object obj) {
        return delegate().removeAll(obj);
    }

    @Override // com.google.common.collect.ai, com.google.common.collect.ah
    public Collection<V> replaceValues(K k, Iterable<? extends V> iterable) {
        return delegate().replaceValues(k, iterable);
    }

    @Override // com.google.common.collect.ai
    public int size() {
        return delegate().size();
    }

    @Override // com.google.common.collect.ai
    public Collection<V> values() {
        return delegate().values();
    }

    @Override // com.google.common.collect.ai, java.lang.Object
    public boolean equals(Object obj) {
        return obj == this || delegate().equals(obj);
    }

    @Override // com.google.common.collect.ai, java.lang.Object
    public int hashCode() {
        return delegate().hashCode();
    }
}
