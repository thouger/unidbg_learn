package com.google.common.collect;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* compiled from: Multimap */
public interface ai<K, V> {
    Map<K, Collection<V>> asMap();

    void clear();

    boolean containsEntry(Object obj, Object obj2);

    boolean containsKey(Object obj);

    boolean containsValue(Object obj);

    Collection<Map.Entry<K, V>> entries();

    @Override // java.lang.Object
    boolean equals(Object obj);

    @Override // com.google.common.collect.ah
    Collection<V> get(K k);

    @Override // java.lang.Object
    int hashCode();

    boolean isEmpty();

    Set<K> keySet();

    aj<K> keys();

    boolean put(K k, V v);

    boolean putAll(ai<? extends K, ? extends V> aiVar);

    boolean putAll(K k, Iterable<? extends V> iterable);

    boolean remove(Object obj, Object obj2);

    @Override // com.google.common.collect.ah
    Collection<V> removeAll(Object obj);

    @Override // com.google.common.collect.ah
    Collection<V> replaceValues(K k, Iterable<? extends V> iterable);

    int size();

    Collection<V> values();
}
