package com.google.common.collect;

import com.google.common.base.m;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.SortedMap;

/* compiled from: ForwardingSortedMap */
public abstract class ac<K, V> extends v<K, V> implements SortedMap<K, V> {
    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.v, com.google.common.collect.z
    public abstract SortedMap<K, V> delegate();

    protected ac() {
    }

    @Override // java.util.SortedMap
    public Comparator<? super K> comparator() {
        return delegate().comparator();
    }

    @Override // java.util.SortedMap
    public K firstKey() {
        return delegate().firstKey();
    }

    @Override // java.util.SortedMap, java.util.NavigableMap
    public SortedMap<K, V> headMap(K k) {
        return delegate().headMap(k);
    }

    @Override // java.util.SortedMap
    public K lastKey() {
        return delegate().lastKey();
    }

    @Override // java.util.SortedMap, java.util.NavigableMap
    public SortedMap<K, V> subMap(K k, K k2) {
        return delegate().subMap(k, k2);
    }

    @Override // java.util.SortedMap, java.util.NavigableMap
    public SortedMap<K, V> tailMap(K k) {
        return delegate().tailMap(k);
    }

    private int unsafeCompare(Object obj, Object obj2) {
        Comparator<? super K> comparator = comparator();
        if (comparator == null) {
            return ((Comparable) obj).compareTo(obj2);
        }
        return comparator.compare(obj, obj2);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.v
    public boolean standardContainsKey(Object obj) {
        try {
            if (unsafeCompare(tailMap(obj).firstKey(), obj) == 0) {
                return true;
            }
            return false;
        } catch (ClassCastException | NullPointerException | NoSuchElementException unused) {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public SortedMap<K, V> standardSubMap(K k, K k2) {
        m.a(unsafeCompare(k, k2) <= 0, "fromKey must be <= toKey");
        return tailMap(k).headMap(k2);
    }
}
