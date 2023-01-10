package com.google.common.collect;

import com.google.common.base.m;
import com.google.common.collect.aj;
import com.google.common.primitives.Ints;
import java.util.Comparator;

/* access modifiers changed from: package-private */
public final class RegularImmutableSortedMultiset<E> extends ImmutableSortedMultiset<E> {
    static final ImmutableSortedMultiset<Comparable> NATURAL_EMPTY_MULTISET = new RegularImmutableSortedMultiset(Ordering.natural());
    private static final long[] ZERO_CUMULATIVE_COUNTS = {0};
    private final transient long[] cumulativeCounts;
    final transient RegularImmutableSortedSet<E> elementSet;
    private final transient int length;
    private final transient int offset;

    RegularImmutableSortedMultiset(Comparator<? super E> comparator) {
        this.elementSet = ImmutableSortedSet.emptySet(comparator);
        this.cumulativeCounts = ZERO_CUMULATIVE_COUNTS;
        this.offset = 0;
        this.length = 0;
    }

    RegularImmutableSortedMultiset(RegularImmutableSortedSet<E> regularImmutableSortedSet, long[] jArr, int i, int i2) {
        this.elementSet = regularImmutableSortedSet;
        this.cumulativeCounts = jArr;
        this.offset = i;
        this.length = i2;
    }

    private int getCount(int i) {
        long[] jArr = this.cumulativeCounts;
        int i2 = this.offset;
        return (int) (jArr[(i2 + i) + 1] - jArr[i2 + i]);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMultiset
    public aj.a<E> getEntry(int i) {
        return Multisets.a(this.elementSet.asList().get(i), getCount(i));
    }

    @Override // com.google.common.collect.aw
    public aj.a<E> firstEntry() {
        if (isEmpty()) {
            return null;
        }
        return getEntry(0);
    }

    @Override // com.google.common.collect.aw
    public aj.a<E> lastEntry() {
        if (isEmpty()) {
            return null;
        }
        return getEntry(this.length - 1);
    }

    @Override // com.google.common.collect.aj
    public int count(Object obj) {
        int indexOf = this.elementSet.indexOf(obj);
        if (indexOf >= 0) {
            return getCount(indexOf);
        }
        return 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.aj
    public int size() {
        long[] jArr = this.cumulativeCounts;
        int i = this.offset;
        return Ints.b(jArr[this.length + i] - jArr[i]);
    }

    @Override // com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.ImmutableMultiset, com.google.common.collect.aj
    public ImmutableSortedSet<E> elementSet() {
        return this.elementSet;
    }

    @Override // com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.aw
    public ImmutableSortedMultiset<E> headMultiset(E e, BoundType boundType) {
        return getSubMultiset(0, this.elementSet.headIndex(e, m.a(boundType) == BoundType.CLOSED));
    }

    @Override // com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.aw
    public ImmutableSortedMultiset<E> tailMultiset(E e, BoundType boundType) {
        return getSubMultiset(this.elementSet.tailIndex(e, m.a(boundType) == BoundType.CLOSED), this.length);
    }

    /* access modifiers changed from: package-private */
    public ImmutableSortedMultiset<E> getSubMultiset(int i, int i2) {
        m.a(i, i2, this.length);
        if (i == i2) {
            return emptyMultiset(comparator());
        }
        if (i == 0 && i2 == this.length) {
            return this;
        }
        return new RegularImmutableSortedMultiset(this.elementSet.getSubSet(i, i2), this.cumulativeCounts, this.offset + i, i2 - i);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return this.offset > 0 || this.length < this.cumulativeCounts.length - 1;
    }
}
