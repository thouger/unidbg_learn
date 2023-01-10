package com.google.common.collect;

import java.util.Map;
import java.util.Set;

/* compiled from: BiMap */
public interface k<K, V> extends Map<K, V> {
    V forcePut(K k, V v);

    k<V, K> inverse();

    @Override // com.google.common.collect.k
    Set<V> values();
}
