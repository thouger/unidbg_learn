package com.google.common.collect;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/* compiled from: Platform */
/* access modifiers changed from: package-private */
public final class ao {
    static <K, V> Map<K, V> a(int i) {
        return CompactHashMap.createWithExpectedSize(i);
    }

    static <K, V> Map<K, V> b(int i) {
        return CompactLinkedHashMap.createWithExpectedSize(i);
    }

    static <E> Set<E> c(int i) {
        return CompactHashSet.createWithExpectedSize(i);
    }

    static <E> Set<E> d(int i) {
        return CompactLinkedHashSet.createWithExpectedSize(i);
    }

    static <K, V> Map<K, V> a() {
        return CompactHashMap.create();
    }

    static <E> Set<E> b() {
        return CompactHashSet.create();
    }

    static <T> T[] a(T[] tArr, int i) {
        return (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), i));
    }

    static <T> T[] a(Object[] objArr, int i, int i2, T[] tArr) {
        return (T[]) Arrays.copyOfRange(objArr, i, i2, tArr.getClass());
    }

    static MapMaker a(MapMaker mapMaker) {
        return mapMaker.d();
    }
}
