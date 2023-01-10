package com.google.common.cache;

import com.google.common.base.g;
import com.google.common.collect.ImmutableMap;
import java.util.concurrent.ExecutionException;

/* compiled from: LoadingCache */
public interface e<K, V> extends g<K, V>, b<K, V> {
    @Override // com.google.common.base.g
    @Deprecated
    V apply(K k);

    V get(K k) throws ExecutionException;

    ImmutableMap<K, V> getAll(Iterable<? extends K> iterable) throws ExecutionException;

    V getUnchecked(K k);

    void refresh(K k);
}
