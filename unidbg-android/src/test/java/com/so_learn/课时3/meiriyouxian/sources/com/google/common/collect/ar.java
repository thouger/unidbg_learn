package com.google.common.collect;

import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;

/* compiled from: RowSortedTable */
public interface ar<R, C, V> extends ba<R, C, V> {
    @Override // com.google.common.collect.ba
    SortedSet<R> rowKeySet();

    @Override // com.google.common.collect.ba
    SortedMap<R, Map<C, V>> rowMap();
}
