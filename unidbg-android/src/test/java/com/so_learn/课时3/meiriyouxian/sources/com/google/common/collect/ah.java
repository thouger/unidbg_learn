package com.google.common.collect;

import java.util.List;

/* compiled from: ListMultimap */
public interface ah<K, V> extends ai<K, V> {
    List<V> get(K k);

    List<V> removeAll(Object obj);

    List<V> replaceValues(K k, Iterable<? extends V> iterable);
}
