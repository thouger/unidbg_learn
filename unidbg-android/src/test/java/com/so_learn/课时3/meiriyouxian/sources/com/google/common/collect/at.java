package com.google.common.collect;

import java.util.Map;
import java.util.Set;

/* compiled from: SetMultimap */
public interface at<K, V> extends ai<K, V> {
    @Override // com.google.common.collect.ai
    Set<Map.Entry<K, V>> entries();

    @Override // com.google.common.collect.ai, com.google.common.collect.ah
    Set<V> get(K k);

    @Override // com.google.common.collect.ai, com.google.common.collect.ah
    Set<V> removeAll(Object obj);

    @Override // com.google.common.collect.ai, com.google.common.collect.ah
    Set<V> replaceValues(K k, Iterable<? extends V> iterable);
}
