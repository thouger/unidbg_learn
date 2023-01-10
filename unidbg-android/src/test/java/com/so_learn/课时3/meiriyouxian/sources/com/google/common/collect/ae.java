package com.google.common.collect;

import com.google.common.collect.ba;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* compiled from: ForwardingTable */
public abstract class ae<R, C, V> extends z implements ba<R, C, V> {
    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.z
    public abstract ba<R, C, V> delegate();

    protected ae() {
    }

    @Override // com.google.common.collect.ba
    public Set<ba.a<R, C, V>> cellSet() {
        return delegate().cellSet();
    }

    @Override // com.google.common.collect.ba
    public void clear() {
        delegate().clear();
    }

    @Override // com.google.common.collect.ba
    public Map<R, V> column(C c) {
        return delegate().column(c);
    }

    @Override // com.google.common.collect.ba
    public Set<C> columnKeySet() {
        return delegate().columnKeySet();
    }

    @Override // com.google.common.collect.ba
    public Map<C, Map<R, V>> columnMap() {
        return delegate().columnMap();
    }

    @Override // com.google.common.collect.ba
    public boolean contains(Object obj, Object obj2) {
        return delegate().contains(obj, obj2);
    }

    @Override // com.google.common.collect.ba
    public boolean containsColumn(Object obj) {
        return delegate().containsColumn(obj);
    }

    @Override // com.google.common.collect.ba
    public boolean containsRow(Object obj) {
        return delegate().containsRow(obj);
    }

    @Override // com.google.common.collect.ba
    public boolean containsValue(Object obj) {
        return delegate().containsValue(obj);
    }

    @Override // com.google.common.collect.ba
    public V get(Object obj, Object obj2) {
        return (V) delegate().get(obj, obj2);
    }

    @Override // com.google.common.collect.ba
    public boolean isEmpty() {
        return delegate().isEmpty();
    }

    @Override // com.google.common.collect.ba
    public V put(R r, C c, V v) {
        return (V) delegate().put(r, c, v);
    }

    @Override // com.google.common.collect.ba
    public void putAll(ba<? extends R, ? extends C, ? extends V> baVar) {
        delegate().putAll(baVar);
    }

    @Override // com.google.common.collect.ba
    public V remove(Object obj, Object obj2) {
        return (V) delegate().remove(obj, obj2);
    }

    @Override // com.google.common.collect.ba
    public Map<C, V> row(R r) {
        return delegate().row(r);
    }

    @Override // com.google.common.collect.ba
    public Set<R> rowKeySet() {
        return delegate().rowKeySet();
    }

    @Override // com.google.common.collect.ba
    public Map<R, Map<C, V>> rowMap() {
        return delegate().rowMap();
    }

    @Override // com.google.common.collect.ba
    public int size() {
        return delegate().size();
    }

    @Override // com.google.common.collect.ba
    public Collection<V> values() {
        return delegate().values();
    }

    @Override // com.google.common.collect.ba, java.lang.Object
    public boolean equals(Object obj) {
        return obj == this || delegate().equals(obj);
    }

    @Override // com.google.common.collect.ba, java.lang.Object
    public int hashCode() {
        return delegate().hashCode();
    }
}
