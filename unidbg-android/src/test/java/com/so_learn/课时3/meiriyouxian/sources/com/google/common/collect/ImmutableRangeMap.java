package com.google.common.collect;

import com.google.common.base.m;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.SortedLists;
import java.io.Serializable;
import java.lang.Comparable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class ImmutableRangeMap<K extends Comparable<?>, V> implements ap<K, V>, Serializable {
    private static final ImmutableRangeMap<Comparable<?>, Object> EMPTY = new ImmutableRangeMap<>(ImmutableList.of(), ImmutableList.of());
    private static final long serialVersionUID = 0;
    private final transient ImmutableList<Range<K>> ranges;
    private final transient ImmutableList<V> values;

    public static <K extends Comparable<?>, V> ImmutableRangeMap<K, V> of() {
        return (ImmutableRangeMap<K, V>) EMPTY;
    }

    public static <K extends Comparable<?>, V> ImmutableRangeMap<K, V> of(Range<K> range, V v) {
        return new ImmutableRangeMap<>(ImmutableList.of(range), ImmutableList.of(v));
    }

    public static <K extends Comparable<?>, V> ImmutableRangeMap<K, V> copyOf(ap<K, ? extends V> apVar) {
        if (apVar instanceof ImmutableRangeMap) {
            return (ImmutableRangeMap) apVar;
        }
        Map<Range<K>, ? extends V> asMapOfRanges = apVar.asMapOfRanges();
        ImmutableList.a aVar = new ImmutableList.a(asMapOfRanges.size());
        ImmutableList.a aVar2 = new ImmutableList.a(asMapOfRanges.size());
        for (Map.Entry<Range<K>, ? extends V> entry : asMapOfRanges.entrySet()) {
            aVar.b(entry.getKey());
            aVar2.b(entry.getValue());
        }
        return new ImmutableRangeMap<>(aVar.a(), aVar2.a());
    }

    public static <K extends Comparable<?>, V> a<K, V> builder() {
        return new a<>();
    }

    public static final class a<K extends Comparable<?>, V> {
        private final List<Map.Entry<Range<K>, V>> a = Lists.a();

        public a<K, V> a(Range<K> range, V v) {
            m.a(range);
            m.a(v);
            m.a(!range.isEmpty(), "Range must not be empty, but was %s", range);
            this.a.add(Maps.a(range, v));
            return this;
        }

        public ImmutableRangeMap<K, V> a() {
            Collections.sort(this.a, Range.rangeLexOrdering().onKeys());
            ImmutableList.a aVar = new ImmutableList.a(this.a.size());
            ImmutableList.a aVar2 = new ImmutableList.a(this.a.size());
            for (int i = 0; i < this.a.size(); i++) {
                Range<K> key = this.a.get(i).getKey();
                if (i > 0) {
                    Range<K> key2 = this.a.get(i - 1).getKey();
                    if (key.isConnected(key2) && !key.intersection(key2).isEmpty()) {
                        throw new IllegalArgumentException("Overlapping ranges: range " + key2 + " overlaps with entry " + key);
                    }
                }
                aVar.b(key);
                aVar2.b(this.a.get(i).getValue());
            }
            return new ImmutableRangeMap<>(aVar.a(), aVar2.a());
        }
    }

    ImmutableRangeMap(ImmutableList<Range<K>> immutableList, ImmutableList<V> immutableList2) {
        this.ranges = immutableList;
        this.values = immutableList2;
    }

    public V get(K k) {
        int a2 = SortedLists.a(this.ranges, Range.lowerBoundFn(), Cut.belowValue(k), SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_LOWER);
        if (a2 != -1 && this.ranges.get(a2).contains(k)) {
            return (V) this.values.get(a2);
        }
        return null;
    }

    public Map.Entry<Range<K>, V> getEntry(K k) {
        int a2 = SortedLists.a(this.ranges, Range.lowerBoundFn(), Cut.belowValue(k), SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_LOWER);
        if (a2 == -1) {
            return null;
        }
        Range<K> range = this.ranges.get(a2);
        if (range.contains(k)) {
            return Maps.a(range, this.values.get(a2));
        }
        return null;
    }

    public Range<K> span() {
        if (!this.ranges.isEmpty()) {
            ImmutableList<Range<K>> immutableList = this.ranges;
            return Range.create(this.ranges.get(0).lowerBound, immutableList.get(immutableList.size() - 1).upperBound);
        }
        throw new NoSuchElementException();
    }

    @Deprecated
    public void put(Range<K> range, V v) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public void putCoalescing(Range<K> range, V v) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public void putAll(ap<K, V> apVar) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public void remove(Range<K> range) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.ap
    public ImmutableMap<Range<K>, V> asMapOfRanges() {
        if (this.ranges.isEmpty()) {
            return ImmutableMap.of();
        }
        return new ImmutableSortedMap(new RegularImmutableSortedSet(this.ranges, Range.rangeLexOrdering()), this.values);
    }

    public ImmutableMap<Range<K>, V> asDescendingMapOfRanges() {
        if (this.ranges.isEmpty()) {
            return ImmutableMap.of();
        }
        return new ImmutableSortedMap(new RegularImmutableSortedSet(this.ranges.reverse(), Range.rangeLexOrdering().reverse()), this.values.reverse());
    }

    public ImmutableRangeMap<K, V> subRangeMap(Range<K> range) {
        if (((Range) m.a(range)).isEmpty()) {
            return of();
        }
        if (this.ranges.isEmpty() || range.encloses(span())) {
            return this;
        }
        int a2 = SortedLists.a(this.ranges, Range.upperBoundFn(), range.lowerBound, SortedLists.KeyPresentBehavior.FIRST_AFTER, SortedLists.KeyAbsentBehavior.NEXT_HIGHER);
        int a3 = SortedLists.a(this.ranges, Range.lowerBoundFn(), range.upperBound, SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_HIGHER);
        if (a2 >= a3) {
            return of();
        }
        return new AnonymousClass2(new AnonymousClass1(a3 - a2, a2, range), this.values.subList(a2, a3), range, this);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.collect.ImmutableRangeMap$1  reason: invalid class name */
    public class AnonymousClass1 extends ImmutableList<Range<K>> {
        final /* synthetic */ int val$len;
        final /* synthetic */ int val$off;
        final /* synthetic */ Range val$range;

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }

        AnonymousClass1(int i, int i2, Range range) {
            this.val$len = i;
            this.val$off = i2;
            this.val$range = range;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.val$len;
        }

        @Override // java.util.List
        public Range<K> get(int i) {
            m.a(i, this.val$len);
            if (i == 0 || i == this.val$len - 1) {
                return ImmutableRangeMap.this.ranges.get(i + this.val$off).intersection(this.val$range);
            }
            return ImmutableRangeMap.this.ranges.get(i + this.val$off);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.collect.ImmutableRangeMap$2  reason: invalid class name */
    public class AnonymousClass2 extends ImmutableRangeMap<K, V> {
        final /* synthetic */ ImmutableRangeMap val$outer;
        final /* synthetic */ Range val$range;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        AnonymousClass2(ImmutableList immutableList, ImmutableList immutableList2, Range range, ImmutableRangeMap immutableRangeMap) {
            super(immutableList, immutableList2);
            this.val$range = range;
            this.val$outer = immutableRangeMap;
        }

        @Override // com.google.common.collect.ImmutableRangeMap
        public /* bridge */ /* synthetic */ Map asDescendingMapOfRanges() {
            return ImmutableRangeMap.super.asDescendingMapOfRanges();
        }

        @Override // com.google.common.collect.ImmutableRangeMap, com.google.common.collect.ap
        public /* bridge */ /* synthetic */ Map asMapOfRanges() {
            return ImmutableRangeMap.super.asMapOfRanges();
        }

        @Override // com.google.common.collect.ImmutableRangeMap
        public ImmutableRangeMap<K, V> subRangeMap(Range<K> range) {
            if (this.val$range.isConnected(range)) {
                return this.val$outer.subRangeMap((Range) range.intersection(this.val$range));
            }
            return ImmutableRangeMap.of();
        }
    }

    @Override // java.lang.Object
    public int hashCode() {
        return asMapOfRanges().hashCode();
    }

    @Override // java.lang.Object
    public boolean equals(Object obj) {
        if (obj instanceof ap) {
            return asMapOfRanges().equals(((ap) obj).asMapOfRanges());
        }
        return false;
    }

    @Override // java.lang.Object
    public String toString() {
        return asMapOfRanges().toString();
    }

    private static class SerializedForm<K extends Comparable<?>, V> implements Serializable {
        private static final long serialVersionUID = 0;
        private final ImmutableMap<Range<K>, V> mapOfRanges;

        SerializedForm(ImmutableMap<Range<K>, V> immutableMap) {
            this.mapOfRanges = immutableMap;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            if (this.mapOfRanges.isEmpty()) {
                return ImmutableRangeMap.of();
            }
            return createRangeMap();
        }

        /* access modifiers changed from: package-private */
        public Object createRangeMap() {
            a aVar = new a();
            bf<Map.Entry<Range<K>, V>> it2 = this.mapOfRanges.entrySet().iterator();
            while (it2.hasNext()) {
                Map.Entry<Range<K>, V> next = it2.next();
                aVar.a(next.getKey(), next.getValue());
            }
            return aVar.a();
        }
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializedForm(asMapOfRanges());
    }
}
