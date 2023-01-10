package com.google.common.collect;

import java.lang.Comparable;

/* compiled from: AbstractRangeSet */
/* access modifiers changed from: package-private */
public abstract class f<C extends Comparable> implements aq<C> {
    @Override // com.google.common.collect.aq
    public abstract boolean encloses(Range<C> range);

    public abstract Range<C> rangeContaining(C c);

    f() {
    }

    public boolean contains(C c) {
        return rangeContaining(c) != null;
    }

    @Override // com.google.common.collect.aq
    public boolean isEmpty() {
        return asRanges().isEmpty();
    }

    public void add(Range<C> range) {
        throw new UnsupportedOperationException();
    }

    public void remove(Range<C> range) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        remove(Range.all());
    }

    public boolean enclosesAll(aq<C> aqVar) {
        return enclosesAll(aqVar.asRanges());
    }

    public boolean enclosesAll(Iterable<Range<C>> iterable) {
        for (Range<C> range : iterable) {
            if (!encloses(range)) {
                return false;
            }
        }
        return true;
    }

    public void addAll(aq<C> aqVar) {
        addAll(aqVar.asRanges());
    }

    public void addAll(Iterable<Range<C>> iterable) {
        for (Range<C> range : iterable) {
            add(range);
        }
    }

    @Override // com.google.common.collect.aq
    public void removeAll(aq<C> aqVar) {
        removeAll(aqVar.asRanges());
    }

    public void removeAll(Iterable<Range<C>> iterable) {
        for (Range<C> range : iterable) {
            remove(range);
        }
    }

    public boolean intersects(Range<C> range) {
        return !subRangeSet(range).isEmpty();
    }

    @Override // java.lang.Object
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof aq) {
            return asRanges().equals(((aq) obj).asRanges());
        }
        return false;
    }

    public final int hashCode() {
        return asRanges().hashCode();
    }

    public final String toString() {
        return asRanges().toString();
    }
}
