package com.google.common.collect;

import com.google.common.base.m;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.SortedLists;
import com.google.common.primitives.Ints;
import java.io.Serializable;
import java.lang.Comparable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public final class ImmutableRangeSet<C extends Comparable> extends f<C> implements Serializable {
    private static final ImmutableRangeSet<Comparable<?>> ALL = new ImmutableRangeSet<>(ImmutableList.of(Range.all()));
    private static final ImmutableRangeSet<Comparable<?>> EMPTY = new ImmutableRangeSet<>(ImmutableList.of());
    private transient ImmutableRangeSet<C> complement;
    private final transient ImmutableList<Range<C>> ranges;

    @Override // com.google.common.collect.f
    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    @Override // com.google.common.collect.f
    public /* bridge */ /* synthetic */ boolean contains(Comparable comparable) {
        return super.contains(comparable);
    }

    @Override // com.google.common.collect.f
    public /* bridge */ /* synthetic */ boolean enclosesAll(aq aqVar) {
        return super.enclosesAll(aqVar);
    }

    @Override // com.google.common.collect.f
    public /* bridge */ /* synthetic */ boolean enclosesAll(Iterable iterable) {
        return super.enclosesAll(iterable);
    }

    @Override // com.google.common.collect.f, java.lang.Object
    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public static <C extends Comparable> ImmutableRangeSet<C> of() {
        return EMPTY;
    }

    public static <C extends Comparable> ImmutableRangeSet<C> of(Range<C> range) {
        m.a(range);
        if (range.isEmpty()) {
            return of();
        }
        if (range.equals(Range.all())) {
            return all();
        }
        return new ImmutableRangeSet<>(ImmutableList.of(range));
    }

    static <C extends Comparable> ImmutableRangeSet<C> all() {
        return ALL;
    }

    public static <C extends Comparable> ImmutableRangeSet<C> copyOf(aq<C> aqVar) {
        m.a(aqVar);
        if (aqVar.isEmpty()) {
            return of();
        }
        if (aqVar.encloses(Range.all())) {
            return all();
        }
        if (aqVar instanceof ImmutableRangeSet) {
            ImmutableRangeSet<C> immutableRangeSet = (ImmutableRangeSet) aqVar;
            if (!immutableRangeSet.isPartialView()) {
                return immutableRangeSet;
            }
        }
        return new ImmutableRangeSet<>(ImmutableList.copyOf((Collection) aqVar.asRanges()));
    }

    public static <C extends Comparable<?>> ImmutableRangeSet<C> copyOf(Iterable<Range<C>> iterable) {
        return new a().a(iterable).a();
    }

    public static <C extends Comparable<?>> ImmutableRangeSet<C> unionOf(Iterable<Range<C>> iterable) {
        return copyOf(TreeRangeSet.create(iterable));
    }

    ImmutableRangeSet(ImmutableList<Range<C>> immutableList) {
        this.ranges = immutableList;
    }

    private ImmutableRangeSet(ImmutableList<Range<C>> immutableList, ImmutableRangeSet<C> immutableRangeSet) {
        this.ranges = immutableList;
        this.complement = immutableRangeSet;
    }

    @Override // com.google.common.collect.f
    public boolean intersects(Range<C> range) {
        int a2 = SortedLists.a(this.ranges, Range.lowerBoundFn(), range.lowerBound, Ordering.natural(), SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_HIGHER);
        if (a2 < this.ranges.size() && this.ranges.get(a2).isConnected(range) && !this.ranges.get(a2).intersection(range).isEmpty()) {
            return true;
        }
        if (a2 > 0) {
            int i = a2 - 1;
            if (this.ranges.get(i).isConnected(range) && !this.ranges.get(i).intersection(range).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.common.collect.f, com.google.common.collect.aq
    public boolean encloses(Range<C> range) {
        int a2 = SortedLists.a(this.ranges, Range.lowerBoundFn(), range.lowerBound, Ordering.natural(), SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_LOWER);
        return a2 != -1 && this.ranges.get(a2).encloses(range);
    }

    @Override // com.google.common.collect.f
    public Range<C> rangeContaining(C c) {
        int a2 = SortedLists.a(this.ranges, Range.lowerBoundFn(), Cut.belowValue(c), Ordering.natural(), SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_LOWER);
        if (a2 == -1) {
            return null;
        }
        Range<C> range = this.ranges.get(a2);
        if (range.contains(c)) {
            return range;
        }
        return null;
    }

    public Range<C> span() {
        if (!this.ranges.isEmpty()) {
            Cut<C> cut = this.ranges.get(0).lowerBound;
            ImmutableList<Range<C>> immutableList = this.ranges;
            return Range.create(cut, immutableList.get(immutableList.size() - 1).upperBound);
        }
        throw new NoSuchElementException();
    }

    @Override // com.google.common.collect.f, com.google.common.collect.aq
    public boolean isEmpty() {
        return this.ranges.isEmpty();
    }

    @Override // com.google.common.collect.f
    @Deprecated
    public void add(Range<C> range) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.f
    @Deprecated
    public void addAll(aq<C> aqVar) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.f
    @Deprecated
    public void addAll(Iterable<Range<C>> iterable) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.f
    @Deprecated
    public void remove(Range<C> range) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.f, com.google.common.collect.aq
    @Deprecated
    public void removeAll(aq<C> aqVar) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.f
    @Deprecated
    public void removeAll(Iterable<Range<C>> iterable) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.aq
    public ImmutableSet<Range<C>> asRanges() {
        if (this.ranges.isEmpty()) {
            return ImmutableSet.of();
        }
        return new RegularImmutableSortedSet(this.ranges, Range.rangeLexOrdering());
    }

    public ImmutableSet<Range<C>> asDescendingSetOfRanges() {
        if (this.ranges.isEmpty()) {
            return ImmutableSet.of();
        }
        return new RegularImmutableSortedSet(this.ranges.reverse(), Range.rangeLexOrdering().reverse());
    }

    /* access modifiers changed from: private */
    public final class ComplementRanges extends ImmutableList<Range<C>> {
        private final boolean positiveBoundedAbove;
        private final boolean positiveBoundedBelow;
        private final int size;

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }

        ComplementRanges() {
            this.positiveBoundedBelow = ImmutableRangeSet.this.ranges.get(0).hasLowerBound();
            this.positiveBoundedAbove = ((Range) ag.d(ImmutableRangeSet.this.ranges)).hasUpperBound();
            int size = ImmutableRangeSet.this.ranges.size() - 1;
            size = this.positiveBoundedBelow ? size + 1 : size;
            this.size = this.positiveBoundedAbove ? size + 1 : size;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.size;
        }

        @Override // java.util.List
        public Range<C> get(int i) {
            Cut<C> cut;
            Cut<C> cut2;
            m.a(i, this.size);
            if (this.positiveBoundedBelow) {
                cut = i == 0 ? Cut.belowAll() : ImmutableRangeSet.this.ranges.get(i - 1).upperBound;
            } else {
                cut = ImmutableRangeSet.this.ranges.get(i).upperBound;
            }
            if (!this.positiveBoundedAbove || i != this.size - 1) {
                cut2 = ImmutableRangeSet.this.ranges.get(i + (!this.positiveBoundedBelow ? 1 : 0)).lowerBound;
            } else {
                cut2 = Cut.aboveAll();
            }
            return Range.create(cut, cut2);
        }
    }

    @Override // com.google.common.collect.aq
    public ImmutableRangeSet<C> complement() {
        ImmutableRangeSet<C> immutableRangeSet = this.complement;
        if (immutableRangeSet != null) {
            return immutableRangeSet;
        }
        if (this.ranges.isEmpty()) {
            ImmutableRangeSet<C> all = all();
            this.complement = all;
            return all;
        } else if (this.ranges.size() != 1 || !this.ranges.get(0).equals(Range.all())) {
            ImmutableRangeSet<C> immutableRangeSet2 = new ImmutableRangeSet<>(new ComplementRanges(), this);
            this.complement = immutableRangeSet2;
            return immutableRangeSet2;
        } else {
            ImmutableRangeSet<C> of = of();
            this.complement = of;
            return of;
        }
    }

    public ImmutableRangeSet<C> union(aq<C> aqVar) {
        return unionOf(ag.a((Iterable) asRanges(), (Iterable) aqVar.asRanges()));
    }

    public ImmutableRangeSet<C> intersection(aq<C> aqVar) {
        TreeRangeSet create = TreeRangeSet.create(this);
        create.removeAll(aqVar.complement());
        return copyOf(create);
    }

    public ImmutableRangeSet<C> difference(aq<C> aqVar) {
        TreeRangeSet create = TreeRangeSet.create(this);
        create.removeAll(aqVar);
        return copyOf(create);
    }

    private ImmutableList<Range<C>> intersectRanges(Range<C> range) {
        int i;
        int i2;
        if (this.ranges.isEmpty() || range.isEmpty()) {
            return ImmutableList.of();
        }
        if (range.encloses(span())) {
            return this.ranges;
        }
        if (range.hasLowerBound()) {
            i = SortedLists.a(this.ranges, Range.upperBoundFn(), range.lowerBound, SortedLists.KeyPresentBehavior.FIRST_AFTER, SortedLists.KeyAbsentBehavior.NEXT_HIGHER);
        } else {
            i = 0;
        }
        if (range.hasUpperBound()) {
            i2 = SortedLists.a(this.ranges, Range.lowerBoundFn(), range.upperBound, SortedLists.KeyPresentBehavior.FIRST_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_HIGHER);
        } else {
            i2 = this.ranges.size();
        }
        int i3 = i2 - i;
        if (i3 == 0) {
            return ImmutableList.of();
        }
        return new AnonymousClass1(i3, i, range);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.collect.ImmutableRangeSet$1  reason: invalid class name */
    public class AnonymousClass1 extends ImmutableList<Range<C>> {
        final /* synthetic */ int val$fromIndex;
        final /* synthetic */ int val$length;
        final /* synthetic */ Range val$range;

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }

        AnonymousClass1(int i, int i2, Range range) {
            this.val$length = i;
            this.val$fromIndex = i2;
            this.val$range = range;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.val$length;
        }

        @Override // java.util.List
        public Range<C> get(int i) {
            m.a(i, this.val$length);
            if (i == 0 || i == this.val$length - 1) {
                return ImmutableRangeSet.this.ranges.get(i + this.val$fromIndex).intersection(this.val$range);
            }
            return ImmutableRangeSet.this.ranges.get(i + this.val$fromIndex);
        }
    }

    @Override // com.google.common.collect.aq
    public ImmutableRangeSet<C> subRangeSet(Range<C> range) {
        if (!isEmpty()) {
            Range<C> span = span();
            if (range.encloses(span)) {
                return this;
            }
            if (range.isConnected(span)) {
                return new ImmutableRangeSet<>(intersectRanges(range));
            }
        }
        return of();
    }

    public ImmutableSortedSet<C> asSet(DiscreteDomain<C> discreteDomain) {
        m.a(discreteDomain);
        if (isEmpty()) {
            return ImmutableSortedSet.of();
        }
        Range<C> canonical = span().canonical(discreteDomain);
        if (canonical.hasLowerBound()) {
            if (!canonical.hasUpperBound()) {
                try {
                    discreteDomain.maxValue();
                } catch (NoSuchElementException unused) {
                    throw new IllegalArgumentException("Neither the DiscreteDomain nor this range set are bounded above");
                }
            }
            return new AsSet(discreteDomain);
        }
        throw new IllegalArgumentException("Neither the DiscreteDomain nor this range set are bounded below");
    }

    /* access modifiers changed from: private */
    public final class AsSet extends ImmutableSortedSet<C> {
        private final DiscreteDomain<C> domain;
        private transient Integer size;

        /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.google.common.collect.ImmutableRangeSet$AsSet */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableSortedSet
        public /* bridge */ /* synthetic */ ImmutableSortedSet headSetImpl(Object obj, boolean z) {
            return headSetImpl((AsSet) ((Comparable) obj), z);
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.google.common.collect.ImmutableRangeSet$AsSet */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableSortedSet
        public /* bridge */ /* synthetic */ ImmutableSortedSet subSetImpl(Object obj, boolean z, Object obj2, boolean z2) {
            return subSetImpl((boolean) ((Comparable) obj), z, (boolean) ((Comparable) obj2), z2);
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.google.common.collect.ImmutableRangeSet$AsSet */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableSortedSet
        public /* bridge */ /* synthetic */ ImmutableSortedSet tailSetImpl(Object obj, boolean z) {
            return tailSetImpl((AsSet) ((Comparable) obj), z);
        }

        AsSet(DiscreteDomain<C> discreteDomain) {
            super(Ordering.natural());
            this.domain = discreteDomain;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            Integer num = this.size;
            if (num == null) {
                long j = 0;
                bf it2 = ImmutableRangeSet.this.ranges.iterator();
                while (it2.hasNext()) {
                    j += (long) ContiguousSet.create(it2.next(), this.domain).size();
                    if (j >= 2147483647L) {
                        break;
                    }
                }
                num = Integer.valueOf(Ints.b(j));
                this.size = num;
            }
            return num.intValue();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: com.google.common.collect.ImmutableRangeSet$AsSet$1  reason: invalid class name */
        public class AnonymousClass1 extends AbstractIterator<C> {
            final Iterator<Range<C>> a = ImmutableRangeSet.this.ranges.iterator();
            Iterator<C> b = Iterators.a();

            AnonymousClass1() {
            }

            /* access modifiers changed from: protected */
            /* renamed from: c */
            public C a() {
                while (!this.b.hasNext()) {
                    if (!this.a.hasNext()) {
                        return (C) b();
                    }
                    this.b = ContiguousSet.create((Range) this.a.next(), AsSet.this.domain).iterator();
                }
                return (C) ((Comparable) this.b.next());
            }
        }

        @Override // com.google.common.collect.ImmutableSortedSet, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet
        public bf<C> iterator() {
            return new AnonymousClass1();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: com.google.common.collect.ImmutableRangeSet$AsSet$2  reason: invalid class name */
        public class AnonymousClass2 extends AbstractIterator<C> {
            final Iterator<Range<C>> a = ImmutableRangeSet.this.ranges.reverse().iterator();
            Iterator<C> b = Iterators.a();

            AnonymousClass2() {
            }

            /* access modifiers changed from: protected */
            /* renamed from: c */
            public C a() {
                while (!this.b.hasNext()) {
                    if (!this.a.hasNext()) {
                        return (C) b();
                    }
                    this.b = ContiguousSet.create((Range) this.a.next(), AsSet.this.domain).descendingIterator();
                }
                return (C) ((Comparable) this.b.next());
            }
        }

        @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
        public bf<C> descendingIterator() {
            return new AnonymousClass2();
        }

        /* access modifiers changed from: package-private */
        public ImmutableSortedSet<C> subSet(Range<C> range) {
            return ImmutableRangeSet.this.subRangeSet((Range) range).asSet(this.domain);
        }

        /* access modifiers changed from: package-private */
        public ImmutableSortedSet<C> headSetImpl(C c, boolean z) {
            return subSet(Range.upTo(c, BoundType.forBoolean(z)));
        }

        /* access modifiers changed from: package-private */
        public ImmutableSortedSet<C> subSetImpl(C c, boolean z, C c2, boolean z2) {
            if (z || z2 || Range.compareOrThrow(c, c2) != 0) {
                return subSet(Range.range(c, BoundType.forBoolean(z), c2, BoundType.forBoolean(z2)));
            }
            return ImmutableSortedSet.of();
        }

        /* access modifiers changed from: package-private */
        public ImmutableSortedSet<C> tailSetImpl(C c, boolean z) {
            return subSet(Range.downTo(c, BoundType.forBoolean(z)));
        }

        @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (obj == null) {
                return false;
            }
            try {
                return ImmutableRangeSet.this.contains((Comparable) obj);
            } catch (ClassCastException unused) {
                return false;
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r3v2, resolved type: com.google.common.collect.Range */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableSortedSet
        public int indexOf(Object obj) {
            if (!contains(obj)) {
                return -1;
            }
            Comparable comparable = (Comparable) obj;
            long j = 0;
            bf it2 = ImmutableRangeSet.this.ranges.iterator();
            while (it2.hasNext()) {
                E next = it2.next();
                if (next.contains(comparable)) {
                    return Ints.b(j + ((long) ContiguousSet.create(next, this.domain).indexOf(comparable)));
                }
                j += (long) ContiguousSet.create(next, this.domain).size();
            }
            throw new AssertionError("impossible");
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableSortedSet
        public ImmutableSortedSet<C> createDescendingSet() {
            return new DescendingImmutableSortedSet(this);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return ImmutableRangeSet.this.ranges.isPartialView();
        }

        @Override // java.util.AbstractCollection, java.lang.Object
        public String toString() {
            return ImmutableRangeSet.this.ranges.toString();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableSortedSet, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
        public Object writeReplace() {
            return new AsSetSerializedForm(ImmutableRangeSet.this.ranges, this.domain);
        }
    }

    private static class AsSetSerializedForm<C extends Comparable> implements Serializable {
        private final DiscreteDomain<C> domain;
        private final ImmutableList<Range<C>> ranges;

        AsSetSerializedForm(ImmutableList<Range<C>> immutableList, DiscreteDomain<C> discreteDomain) {
            this.ranges = immutableList;
            this.domain = discreteDomain;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return new ImmutableRangeSet(this.ranges).asSet(this.domain);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isPartialView() {
        return this.ranges.isPartialView();
    }

    public static <C extends Comparable<?>> a<C> builder() {
        return new a<>();
    }

    public static class a<C extends Comparable<?>> {
        private final List<Range<C>> a = Lists.a();

        public a<C> a(Range<C> range) {
            m.a(!range.isEmpty(), "range must not be empty, but was %s", range);
            this.a.add(range);
            return this;
        }

        public a<C> a(Iterable<Range<C>> iterable) {
            for (Range<C> range : iterable) {
                a(range);
            }
            return this;
        }

        public ImmutableRangeSet<C> a() {
            ImmutableList.a aVar = new ImmutableList.a(this.a.size());
            Collections.sort(this.a, Range.rangeLexOrdering());
            an i = Iterators.i(this.a.iterator());
            while (i.hasNext()) {
                Range range = (Range) i.next();
                while (i.hasNext()) {
                    Range<C> range2 = (Range) i.a();
                    if (!range.isConnected(range2)) {
                        break;
                    }
                    m.a(range.intersection(range2).isEmpty(), "Overlapping ranges not permitted but found %s overlapping %s", range, range2);
                    range = range.span((Range) i.next());
                }
                aVar.b(range);
            }
            ImmutableList a = aVar.a();
            if (a.isEmpty()) {
                return ImmutableRangeSet.of();
            }
            if (a.size() != 1 || !((Range) ag.b(a)).equals(Range.all())) {
                return new ImmutableRangeSet<>(a);
            }
            return ImmutableRangeSet.all();
        }
    }

    private static final class SerializedForm<C extends Comparable> implements Serializable {
        private final ImmutableList<Range<C>> ranges;

        SerializedForm(ImmutableList<Range<C>> immutableList) {
            this.ranges = immutableList;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            if (this.ranges.isEmpty()) {
                return ImmutableRangeSet.of();
            }
            if (this.ranges.equals(ImmutableList.of(Range.all()))) {
                return ImmutableRangeSet.all();
            }
            return new ImmutableRangeSet(this.ranges);
        }
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializedForm(this.ranges);
    }
}
