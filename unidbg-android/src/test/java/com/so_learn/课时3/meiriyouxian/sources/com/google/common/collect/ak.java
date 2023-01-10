package com.google.common.collect;

import com.google.common.base.m;
import java.util.Collection;
import java.util.Iterator;

/* compiled from: ObjectArrays */
public final class ak {
    public static <T> T[] a(T[] tArr, int i) {
        return (T[]) ao.a(tArr, i);
    }

    static <T> T[] a(Collection<?> collection, T[] tArr) {
        int size = collection.size();
        if (tArr.length < size) {
            tArr = (T[]) a((Object[]) tArr, size);
        }
        a((Iterable<?>) collection, (Object[]) tArr);
        if (tArr.length > size) {
            tArr[size] = null;
        }
        return tArr;
    }

    static <T> T[] a(Object[] objArr, int i, int i2, T[] tArr) {
        m.a(i, i + i2, objArr.length);
        if (tArr.length < i2) {
            tArr = (T[]) a((Object[]) tArr, i2);
        } else if (tArr.length > i2) {
            tArr[i2] = null;
        }
        System.arraycopy(objArr, i, tArr, 0, i2);
        return tArr;
    }

    static Object[] a(Collection<?> collection) {
        return a((Iterable<?>) collection, new Object[collection.size()]);
    }

    private static Object[] a(Iterable<?> iterable, Object[] objArr) {
        Iterator<?> it2 = iterable.iterator();
        int i = 0;
        while (it2.hasNext()) {
            objArr[i] = it2.next();
            i++;
        }
        return objArr;
    }

    static Object[] a(Object... objArr) {
        return b(objArr, objArr.length);
    }

    static Object[] b(Object[] objArr, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            a(objArr[i2], i2);
        }
        return objArr;
    }

    static Object a(Object obj, int i) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException("at index " + i);
    }
}
