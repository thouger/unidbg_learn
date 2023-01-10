package com.google.common.collect;

import java.util.Comparator;
import java.util.SortedSet;

/* compiled from: SortedSetMultimap */
public interface az<K, V> extends at<K, V> {
    @Override // com.google.common.collect.at, com.google.common.collect.ai, com.google.common.collect.ah
    SortedSet<V> get(K k);

    @Override // com.google.common.collect.at, com.google.common.collect.ai, com.google.common.collect.ah
    SortedSet<V> removeAll(Object obj);

    @Override // com.google.common.collect.at, com.google.common.collect.ai, com.google.common.collect.ah
    SortedSet<V> replaceValues(K k, Iterable<? extends V> iterable);

    Comparator<? super V> valueComparator();
}
