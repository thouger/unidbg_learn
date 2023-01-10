package com.google.common.collect;

import com.google.common.base.m;
import java.util.Comparator;
import java.util.SortedSet;

/* compiled from: SortedIterables */
/* access modifiers changed from: package-private */
public final class av {
    public static boolean a(Comparator<?> comparator, Iterable<?> iterable) {
        Comparator comparator2;
        m.a(comparator);
        m.a(iterable);
        if (iterable instanceof SortedSet) {
            comparator2 = a((SortedSet) iterable);
        } else if (!(iterable instanceof au)) {
            return false;
        } else {
            comparator2 = ((au) iterable).comparator();
        }
        return comparator.equals(comparator2);
    }

    public static <E> Comparator<? super E> a(SortedSet<E> sortedSet) {
        Comparator<? super E> comparator = sortedSet.comparator();
        return comparator == null ? Ordering.natural() : comparator;
    }
}
