package com.google.common.collect;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* compiled from: Table */
public interface ba<R, C, V> {

    /* compiled from: Table */
    public interface a<R, C, V> {
        C getColumnKey();

        R getRowKey();

        V getValue();
    }

    Set<a<R, C, V>> cellSet();

    void clear();

    Map<R, V> column(C c);

    Set<C> columnKeySet();

    Map<C, Map<R, V>> columnMap();

    boolean contains(Object obj, Object obj2);

    boolean containsColumn(Object obj);

    boolean containsRow(Object obj);

    boolean containsValue(Object obj);

    @Override // java.lang.Object
    boolean equals(Object obj);

    V get(Object obj, Object obj2);

    @Override // java.lang.Object
    int hashCode();

    boolean isEmpty();

    V put(R r, C c, V v);

    void putAll(ba<? extends R, ? extends C, ? extends V> baVar);

    V remove(Object obj, Object obj2);

    Map<C, V> row(R r);

    Set<R> rowKeySet();

    Map<R, Map<C, V>> rowMap();

    int size();

    Collection<V> values();
}
