package com.google.common.collect;

import java.lang.Comparable;
import java.util.Map;

/* compiled from: RangeMap */
public interface ap<K extends Comparable, V> {
    Map<Range<K>, V> asMapOfRanges();
}
