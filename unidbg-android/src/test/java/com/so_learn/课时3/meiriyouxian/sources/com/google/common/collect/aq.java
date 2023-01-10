package com.google.common.collect;

import java.lang.Comparable;
import java.util.Set;

/* compiled from: RangeSet */
public interface aq<C extends Comparable> {
    Set<Range<C>> asRanges();

    aq<C> complement();

    boolean encloses(Range<C> range);

    boolean isEmpty();

    void removeAll(aq<C> aqVar);

    aq<C> subRangeSet(Range<C> range);
}
