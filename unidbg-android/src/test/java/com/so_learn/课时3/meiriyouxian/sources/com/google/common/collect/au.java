package com.google.common.collect;

import java.util.Comparator;

/* compiled from: SortedIterable */
/* access modifiers changed from: package-private */
public interface au<T> extends Iterable<T> {
    Comparator<? super T> comparator();
}
