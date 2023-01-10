package com.google.common.collect;

import com.google.common.base.j;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* compiled from: ForwardingMap */
public abstract class v<K, V> extends z implements Map<K, V> {
    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.z
    public abstract Map<K, V> delegate();

    protected v() {
    }

    @Override // java.util.Map
    public int size() {
        return delegate().size();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return delegate().isEmpty();
    }

    @Override // java.util.Map
    public V remove(Object obj) {
        return delegate().remove(obj);
    }

    @Override // java.util.Map
    public void clear() {
        delegate().clear();
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return delegate().containsKey(obj);
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return delegate().containsValue(obj);
    }

    @Override // java.util.Map
    public V get(Object obj) {
        return delegate().get(obj);
    }

    @Override // java.util.Map
    public V put(K k, V v) {
        return delegate().put(k, v);
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        delegate().putAll(map);
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        return delegate().keySet();
    }

    @Override // java.util.Map, com.google.common.collect.k
    public Collection<V> values() {
        return delegate().values();
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        return delegate().entrySet();
    }

    @Override // java.util.Map, java.lang.Object
    public boolean equals(Object obj) {
        return obj == this || delegate().equals(obj);
    }

    @Override // java.util.Map, java.lang.Object
    public int hashCode() {
        return delegate().hashCode();
    }

    /* access modifiers changed from: protected */
    public void standardPutAll(Map<? extends K, ? extends V> map) {
        Maps.a((Map) this, (Map) map);
    }

    /* access modifiers changed from: protected */
    public V standardRemove(Object obj) {
        Iterator<Map.Entry<K, V>> it2 = entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry<K, V> next = it2.next();
            if (j.a(next.getKey(), obj)) {
                V value = next.getValue();
                it2.remove();
                return value;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void standardClear() {
        Iterators.h(entrySet().iterator());
    }

    /* access modifiers changed from: protected */
    public boolean standardContainsKey(Object obj) {
        return Maps.d(this, obj);
    }

    /* access modifiers changed from: protected */
    public boolean standardContainsValue(Object obj) {
        return Maps.e(this, obj);
    }

    /* access modifiers changed from: protected */
    public boolean standardIsEmpty() {
        return !entrySet().iterator().hasNext();
    }

    /* access modifiers changed from: protected */
    public boolean standardEquals(Object obj) {
        return Maps.f(this, obj);
    }

    /* access modifiers changed from: protected */
    public int standardHashCode() {
        return Sets.a(entrySet());
    }

    /* access modifiers changed from: protected */
    public String standardToString() {
        return Maps.a(this);
    }
}
