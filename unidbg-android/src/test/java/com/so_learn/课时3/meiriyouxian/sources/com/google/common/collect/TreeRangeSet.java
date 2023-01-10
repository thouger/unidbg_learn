package com.google.common.collect;

import com.google.common.base.i;
import com.google.common.base.m;
import java.io.Serializable;
import java.lang.Comparable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeMap;

public class TreeRangeSet<C extends Comparable<?>> extends f<C> implements Serializable {
    private transient Set<Range<C>> asDescendingSetOfRanges;
    private transient Set<Range<C>> asRanges;
    private transient aq<C> complement;
    final NavigableMap<Cut<C>, Range<C>> rangesByLowerBound;

    @Override // com.google.common.collect.f
    public /* bridge */ /* synthetic */ void addAll(aq aqVar) {
        super.addAll(aqVar);
    }

    @Override // com.google.common.collect.f
    public /* bridge */ /* synthetic */ void addAll(Iterable iterable) {
        super.addAll(iterable);
    }

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

    @Override // com.google.common.collect.f, com.google.common.collect.aq
    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    @Override // com.google.common.collect.f, com.google.common.collect.aq
    public /* bridge */ /* synthetic */ void removeAll(aq aqVar) {
        super.removeAll(aqVar);
    }

    @Override // com.google.common.collect.f
    public /* bridge */ /* synthetic */ void removeAll(Iterable iterable) {
        super.removeAll(iterable);
    }

    public static <C extends Comparable<?>> TreeRangeSet<C> create() {
        return new TreeRangeSet<>(new TreeMap());
    }

    public static <C extends Comparable<?>> TreeRangeSet<C> create(aq<C> aqVar) {
        TreeRangeSet<C> create = create();
        create.addAll(aqVar);
        return create;
    }

    public static <C extends Comparable<?>> TreeRangeSet<C> create(Iterable<Range<C>> iterable) {
        TreeRangeSet<C> create = create();
        create.addAll(iterable);
        return create;
    }

    private TreeRangeSet(NavigableMap<Cut<C>, Range<C>> navigableMap) {
        this.rangesByLowerBound = navigableMap;
    }

    @Override // com.google.common.collect.aq
    public Set<Range<C>> asRanges() {
        Set<Range<C>> set = this.asRanges;
        if (set != null) {
            return set;
        }
        a aVar = new a(this.rangesByLowerBound.values());
        this.asRanges = aVar;
        return aVar;
    }

    public Set<Range<C>> asDescendingSetOfRanges() {
        Set<Range<C>> set = this.asDescendingSetOfRanges;
        if (set != null) {
            return set;
        }
        a aVar = new a(this.rangesByLowerBound.descendingMap().values());
        this.asDescendingSetOfRanges = aVar;
        return aVar;
    }

    final class a extends s<Range<C>> implements Set<Range<C>> {
        final Collection<Range<C>> a;

        a(Collection<Range<C>> collection) {
            this.a = collection;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.s, com.google.common.collect.z
        public Collection<Range<C>> delegate() {
            return (Collection<Range<C>>) this.a;
        }

        @Override // java.util.Collection, java.lang.Object, java.util.Set
        public int hashCode() {
            return Sets.a(this);
        }

        @Override // java.util.Collection, java.lang.Object, java.util.Set
        public boolean equals(Object obj) {
            return Sets.a(this, obj);
        }
    }

    @Override // com.google.common.collect.f
    public Range<C> rangeContaining(C c2) {
        m.a(c2);
        Map.Entry<Cut<C>, Range<C>> floorEntry = this.rangesByLowerBound.floorEntry(Cut.belowValue(c2));
        if (floorEntry == null || !floorEntry.getValue().contains(c2)) {
            return null;
        }
        return floorEntry.getValue();
    }

    @Override // com.google.common.collect.f
    public boolean intersects(Range<C> range) {
        m.a(range);
        Map.Entry<Cut<C>, Range<C>> ceilingEntry = this.rangesByLowerBound.ceilingEntry(range.lowerBound);
        if (ceilingEntry != null && ceilingEntry.getValue().isConnected(range) && !ceilingEntry.getValue().intersection(range).isEmpty()) {
            return true;
        }
        Map.Entry<Cut<C>, Range<C>> lowerEntry = this.rangesByLowerBound.lowerEntry(range.lowerBound);
        if (lowerEntry == null || !lowerEntry.getValue().isConnected(range) || lowerEntry.getValue().intersection(range).isEmpty()) {
            return false;
        }
        return true;
    }

    @Override // com.google.common.collect.f, com.google.common.collect.aq
    public boolean encloses(Range<C> range) {
        m.a(range);
        Map.Entry<Cut<C>, Range<C>> floorEntry = this.rangesByLowerBound.floorEntry(range.lowerBound);
        return floorEntry != null && floorEntry.getValue().encloses(range);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Range<C> rangeEnclosing(Range<C> range) {
        m.a(range);
        Map.Entry<Cut<C>, Range<C>> floorEntry = this.rangesByLowerBound.floorEntry(range.lowerBound);
        if (floorEntry == null || !floorEntry.getValue().encloses(range)) {
            return null;
        }
        return floorEntry.getValue();
    }

    public Range<C> span() {
        Map.Entry<Cut<C>, Range<C>> firstEntry = this.rangesByLowerBound.firstEntry();
        Map.Entry<Cut<C>, Range<C>> lastEntry = this.rangesByLowerBound.lastEntry();
        if (firstEntry != null) {
            return Range.create(firstEntry.getValue().lowerBound, lastEntry.getValue().upperBound);
        }
        throw new NoSuchElementException();
    }

    @Override // com.google.common.collect.f
    public void add(Range<C> range) {
        m.a(range);
        if (!range.isEmpty()) {
            Cut<C> cut = range.lowerBound;
            Cut<C> cut2 = range.upperBound;
            Map.Entry<Cut<C>, Range<C>> lowerEntry = this.rangesByLowerBound.lowerEntry(cut);
            if (lowerEntry != null) {
                Range<C> value = lowerEntry.getValue();
                if (value.upperBound.compareTo((Cut) cut) >= 0) {
                    if (value.upperBound.compareTo((Cut) cut2) >= 0) {
                        cut2 = value.upperBound;
                    }
                    cut = value.lowerBound;
                }
            }
            Map.Entry<Cut<C>, Range<C>> floorEntry = this.rangesByLowerBound.floorEntry(cut2);
            if (floorEntry != null) {
                Range<C> value2 = floorEntry.getValue();
                if (value2.upperBound.compareTo((Cut) cut2) >= 0) {
                    cut2 = value2.upperBound;
                }
            }
            this.rangesByLowerBound.subMap(cut, cut2).clear();
            replaceRangeWithSameLowerBound(Range.create(cut, cut2));
        }
    }

    @Override // com.google.common.collect.f
    public void remove(Range<C> range) {
        m.a(range);
        if (!range.isEmpty()) {
            Map.Entry<Cut<C>, Range<C>> lowerEntry = this.rangesByLowerBound.lowerEntry(range.lowerBound);
            if (lowerEntry != null) {
                Range<C> value = lowerEntry.getValue();
                if (value.upperBound.compareTo((Cut) range.lowerBound) >= 0) {
                    if (range.hasUpperBound() && value.upperBound.compareTo((Cut) range.upperBound) >= 0) {
                        replaceRangeWithSameLowerBound(Range.create(range.upperBound, value.upperBound));
                    }
                    replaceRangeWithSameLowerBound(Range.create(value.lowerBound, range.lowerBound));
                }
            }
            Map.Entry<Cut<C>, Range<C>> floorEntry = this.rangesByLowerBound.floorEntry(range.upperBound);
            if (floorEntry != null) {
                Range<C> value2 = floorEntry.getValue();
                if (range.hasUpperBound() && value2.upperBound.compareTo((Cut) range.upperBound) >= 0) {
                    replaceRangeWithSameLowerBound(Range.create(range.upperBound, value2.upperBound));
                }
            }
            this.rangesByLowerBound.subMap(range.lowerBound, range.upperBound).clear();
        }
    }

    private void replaceRangeWithSameLowerBound(Range<C> range) {
        if (range.isEmpty()) {
            this.rangesByLowerBound.remove(range.lowerBound);
        } else {
            this.rangesByLowerBound.put(range.lowerBound, range);
        }
    }

    @Override // com.google.common.collect.aq
    public aq<C> complement() {
        aq<C> aqVar = this.complement;
        if (aqVar != null) {
            return aqVar;
        }
        Complement complement = new Complement();
        this.complement = complement;
        return complement;
    }

    /* access modifiers changed from: package-private */
    public static final class c<C extends Comparable<?>> extends e<Cut<C>, Range<C>> {
        private final NavigableMap<Cut<C>, Range<C>> a;
        private final Range<Cut<C>> b;

        c(NavigableMap<Cut<C>, Range<C>> navigableMap) {
            this.a = navigableMap;
            this.b = Range.all();
        }

        private c(NavigableMap<Cut<C>, Range<C>> navigableMap, Range<Cut<C>> range) {
            this.a = navigableMap;
            this.b = range;
        }

        private NavigableMap<Cut<C>, Range<C>> a(Range<Cut<C>> range) {
            if (range.isConnected(this.b)) {
                return new c(this.a, range.intersection(this.b));
            }
            return ImmutableSortedMap.of();
        }

        /* renamed from: a */
        public NavigableMap<Cut<C>, Range<C>> subMap(Cut<C> cut, boolean z, Cut<C> cut2, boolean z2) {
            return a(Range.range(cut, BoundType.forBoolean(z), cut2, BoundType.forBoolean(z2)));
        }

        /* renamed from: a */
        public NavigableMap<Cut<C>, Range<C>> headMap(Cut<C> cut, boolean z) {
            return a(Range.upTo(cut, BoundType.forBoolean(z)));
        }

        /* renamed from: b */
        public NavigableMap<Cut<C>, Range<C>> tailMap(Cut<C> cut, boolean z) {
            return a(Range.downTo(cut, BoundType.forBoolean(z)));
        }

        @Override // java.util.SortedMap
        public Comparator<? super Cut<C>> comparator() {
            return Ordering.natural();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return get(obj) != null;
        }

        /* renamed from: a */
        public Range<C> get(Object obj) {
            Map.Entry<Cut<C>, Range<C>> lowerEntry;
            if (obj instanceof Cut) {
                try {
                    Cut<C> cut = (Cut) obj;
                    if (this.b.contains(cut) && (lowerEntry = this.a.lowerEntry(cut)) != null && lowerEntry.getValue().upperBound.equals(cut)) {
                        return lowerEntry.getValue();
                    }
                } catch (ClassCastException unused) {
                }
            }
            return null;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v3, resolved type: java.util.NavigableMap<com.google.common.collect.Cut<C extends java.lang.Comparable<?>>, com.google.common.collect.Range<C extends java.lang.Comparable<?>>> */
        /* JADX DEBUG: Multi-variable search result rejected for r0v5, resolved type: java.util.NavigableMap<com.google.common.collect.Cut<C extends java.lang.Comparable<?>>, com.google.common.collect.Range<C extends java.lang.Comparable<?>>> */
        /* JADX DEBUG: Multi-variable search result rejected for r1v8, resolved type: java.util.NavigableMap<com.google.common.collect.Cut<C extends java.lang.Comparable<?>>, com.google.common.collect.Range<C extends java.lang.Comparable<?>>> */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.d
        public Iterator<Map.Entry<Cut<C>, Range<C>>> b() {
            Iterator<Range<C>> it2;
            if (!this.b.hasLowerBound()) {
                it2 = this.a.values().iterator();
            } else {
                Map.Entry lowerEntry = this.a.lowerEntry(this.b.lowerEndpoint());
                if (lowerEntry == null) {
                    it2 = this.a.values().iterator();
                } else if (this.b.lowerBound.isLessThan(((Range) lowerEntry.getValue()).upperBound)) {
                    it2 = this.a.tailMap(lowerEntry.getKey(), true).values().iterator();
                } else {
                    it2 = this.a.tailMap(this.b.lowerEndpoint(), true).values().iterator();
                }
            }
            return new AnonymousClass1(it2);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: com.google.common.collect.TreeRangeSet$c$1  reason: invalid class name */
        public class AnonymousClass1 extends AbstractIterator<Map.Entry<Cut<C>, Range<C>>> {
            final /* synthetic */ Iterator a;

            AnonymousClass1(Iterator it2) {
                this.a = it2;
            }

            /* access modifiers changed from: protected */
            /* renamed from: c */
            public Map.Entry<Cut<C>, Range<C>> a() {
                if (!this.a.hasNext()) {
                    return (Map.Entry) b();
                }
                Range range = (Range) this.a.next();
                if (c.this.b.upperBound.isLessThan(range.upperBound)) {
                    return (Map.Entry) b();
                }
                return Maps.a(range.upperBound, range);
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v8, resolved type: java.util.NavigableMap<com.google.common.collect.Cut<C extends java.lang.Comparable<?>>, com.google.common.collect.Range<C extends java.lang.Comparable<?>>> */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.e
        public Iterator<Map.Entry<Cut<C>, Range<C>>> a() {
            Collection<Range<C>> collection;
            if (this.b.hasUpperBound()) {
                collection = this.a.headMap(this.b.upperEndpoint(), false).descendingMap().values();
            } else {
                collection = this.a.descendingMap().values();
            }
            an i = Iterators.i(collection.iterator());
            if (i.hasNext() && this.b.upperBound.isLessThan(((Range) i.a()).upperBound)) {
                i.next();
            }
            return new AnonymousClass2(i);
        }

        /* renamed from: com.google.common.collect.TreeRangeSet$c$2  reason: invalid class name */
        class AnonymousClass2 extends AbstractIterator<Map.Entry<Cut<C>, Range<C>>> {
            final /* synthetic */ an a;

            AnonymousClass2(an anVar) {
                this.a = anVar;
            }

            /* access modifiers changed from: protected */
            /* renamed from: c */
            public Map.Entry<Cut<C>, Range<C>> a() {
                if (!this.a.hasNext()) {
                    return (Map.Entry) b();
                }
                Range range = (Range) this.a.next();
                if (c.this.b.lowerBound.isLessThan(range.upperBound)) {
                    return Maps.a(range.upperBound, range);
                }
                return (Map.Entry) b();
            }
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            if (this.b.equals(Range.all())) {
                return this.a.size();
            }
            return Iterators.b(b());
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean isEmpty() {
            if (this.b.equals(Range.all())) {
                return this.a.isEmpty();
            }
            return !b().hasNext();
        }
    }

    /* access modifiers changed from: private */
    public static final class b<C extends Comparable<?>> extends e<Cut<C>, Range<C>> {
        private final NavigableMap<Cut<C>, Range<C>> a;
        private final NavigableMap<Cut<C>, Range<C>> b;
        private final Range<Cut<C>> c;

        b(NavigableMap<Cut<C>, Range<C>> navigableMap) {
            this(navigableMap, Range.all());
        }

        private b(NavigableMap<Cut<C>, Range<C>> navigableMap, Range<Cut<C>> range) {
            this.a = navigableMap;
            this.b = new c(navigableMap);
            this.c = range;
        }

        private NavigableMap<Cut<C>, Range<C>> a(Range<Cut<C>> range) {
            if (!this.c.isConnected(range)) {
                return ImmutableSortedMap.of();
            }
            return new b(this.a, range.intersection(this.c));
        }

        /* renamed from: a */
        public NavigableMap<Cut<C>, Range<C>> subMap(Cut<C> cut, boolean z, Cut<C> cut2, boolean z2) {
            return a(Range.range(cut, BoundType.forBoolean(z), cut2, BoundType.forBoolean(z2)));
        }

        /* renamed from: a */
        public NavigableMap<Cut<C>, Range<C>> headMap(Cut<C> cut, boolean z) {
            return a(Range.upTo(cut, BoundType.forBoolean(z)));
        }

        /* renamed from: b */
        public NavigableMap<Cut<C>, Range<C>> tailMap(Cut<C> cut, boolean z) {
            return a(Range.downTo(cut, BoundType.forBoolean(z)));
        }

        @Override // java.util.SortedMap
        public Comparator<? super Cut<C>> comparator() {
            return Ordering.natural();
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v8, resolved type: java.util.NavigableMap<com.google.common.collect.Cut<C extends java.lang.Comparable<?>>, com.google.common.collect.Range<C extends java.lang.Comparable<?>>> */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.d
        public Iterator<Map.Entry<Cut<C>, Range<C>>> b() {
            Collection<Range<C>> collection;
            Cut cut;
            if (this.c.hasLowerBound()) {
                collection = this.b.tailMap(this.c.lowerEndpoint(), this.c.lowerBoundType() == BoundType.CLOSED).values();
            } else {
                collection = this.b.values();
            }
            an i = Iterators.i(collection.iterator());
            if (this.c.contains(Cut.belowAll()) && (!i.hasNext() || ((Range) i.a()).lowerBound != Cut.belowAll())) {
                cut = Cut.belowAll();
            } else if (!i.hasNext()) {
                return Iterators.a();
            } else {
                cut = ((Range) i.next()).upperBound;
            }
            return new AnonymousClass1(cut, i);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: com.google.common.collect.TreeRangeSet$b$1  reason: invalid class name */
        public class AnonymousClass1 extends AbstractIterator<Map.Entry<Cut<C>, Range<C>>> {
            Cut<C> a = this.b;
            final /* synthetic */ Cut b;
            final /* synthetic */ an c;

            AnonymousClass1(Cut cut, an anVar) {
                this.b = cut;
                this.c = anVar;
            }

            /* access modifiers changed from: protected */
            /* renamed from: c */
            public Map.Entry<Cut<C>, Range<C>> a() {
                Range range;
                if (b.this.c.upperBound.isLessThan(this.a) || this.a == Cut.aboveAll()) {
                    return (Map.Entry) b();
                }
                if (this.c.hasNext()) {
                    Range range2 = (Range) this.c.next();
                    range = Range.create(this.a, range2.lowerBound);
                    this.a = range2.upperBound;
                } else {
                    range = Range.create(this.a, Cut.aboveAll());
                    this.a = Cut.aboveAll();
                }
                return Maps.a(range.lowerBound, range);
            }
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.e
        public Iterator<Map.Entry<Cut<C>, Range<C>>> a() {
            Cut<C> cut;
            Cut<C> cut2;
            if (this.c.hasUpperBound()) {
                cut = (Cut) this.c.upperEndpoint();
            } else {
                cut = Cut.aboveAll();
            }
            an i = Iterators.i(this.b.headMap(cut, this.c.hasUpperBound() && this.c.upperBoundType() == BoundType.CLOSED).descendingMap().values().iterator());
            if (i.hasNext()) {
                if (((Range) i.a()).upperBound == Cut.aboveAll()) {
                    cut2 = ((Range) i.next()).lowerBound;
                } else {
                    cut2 = this.a.higherKey(((Range) i.a()).upperBound);
                }
            } else if (!this.c.contains(Cut.belowAll()) || this.a.containsKey(Cut.belowAll())) {
                return Iterators.a();
            } else {
                cut2 = this.a.higherKey(Cut.belowAll());
            }
            return new AnonymousClass2((Cut) i.a(cut2, Cut.aboveAll()), i);
        }

        /* renamed from: com.google.common.collect.TreeRangeSet$b$2  reason: invalid class name */
        class AnonymousClass2 extends AbstractIterator<Map.Entry<Cut<C>, Range<C>>> {
            Cut<C> a = this.b;
            final /* synthetic */ Cut b;
            final /* synthetic */ an c;

            AnonymousClass2(Cut cut, an anVar) {
                this.b = cut;
                this.c = anVar;
            }

            /* access modifiers changed from: protected */
            /* renamed from: c */
            public Map.Entry<Cut<C>, Range<C>> a() {
                if (this.a == Cut.belowAll()) {
                    return (Map.Entry) b();
                }
                if (this.c.hasNext()) {
                    Range range = (Range) this.c.next();
                    Range create = Range.create(range.upperBound, this.a);
                    this.a = range.lowerBound;
                    if (b.this.c.lowerBound.isLessThan(create.lowerBound)) {
                        return Maps.a(create.lowerBound, create);
                    }
                } else if (b.this.c.lowerBound.isLessThan(Cut.belowAll())) {
                    Range create2 = Range.create(Cut.belowAll(), this.a);
                    this.a = Cut.belowAll();
                    return Maps.a(Cut.belowAll(), create2);
                }
                return (Map.Entry) b();
            }
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            return Iterators.b(b());
        }

        /* renamed from: a */
        public Range<C> get(Object obj) {
            if (obj instanceof Cut) {
                try {
                    Cut cut = (Cut) obj;
                    Map.Entry<Cut<C>, Range<C>> firstEntry = tailMap(cut, true).firstEntry();
                    if (firstEntry != null && firstEntry.getKey().equals(cut)) {
                        return firstEntry.getValue();
                    }
                } catch (ClassCastException unused) {
                }
            }
            return null;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return get(obj) != null;
        }
    }

    private final class Complement extends TreeRangeSet<C> {
        Complement() {
            super(new b(TreeRangeSet.this.rangesByLowerBound));
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.f
        public void add(Range<C> range) {
            TreeRangeSet.this.remove(range);
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.f
        public void remove(Range<C> range) {
            TreeRangeSet.this.add(range);
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.f
        public boolean contains(C c) {
            return !TreeRangeSet.this.contains(c);
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.aq
        public aq<C> complement() {
            return TreeRangeSet.this;
        }
    }

    /* access modifiers changed from: private */
    public static final class d<C extends Comparable<?>> extends e<Cut<C>, Range<C>> {
        private final Range<Cut<C>> a;
        private final Range<C> b;
        private final NavigableMap<Cut<C>, Range<C>> c;
        private final NavigableMap<Cut<C>, Range<C>> d;

        private d(Range<Cut<C>> range, Range<C> range2, NavigableMap<Cut<C>, Range<C>> navigableMap) {
            this.a = (Range) m.a(range);
            this.b = (Range) m.a(range2);
            this.c = (NavigableMap) m.a(navigableMap);
            this.d = new c(navigableMap);
        }

        private NavigableMap<Cut<C>, Range<C>> a(Range<Cut<C>> range) {
            if (!range.isConnected(this.a)) {
                return ImmutableSortedMap.of();
            }
            return new d(this.a.intersection(range), this.b, this.c);
        }

        /* renamed from: a */
        public NavigableMap<Cut<C>, Range<C>> subMap(Cut<C> cut, boolean z, Cut<C> cut2, boolean z2) {
            return a(Range.range(cut, BoundType.forBoolean(z), cut2, BoundType.forBoolean(z2)));
        }

        /* renamed from: a */
        public NavigableMap<Cut<C>, Range<C>> headMap(Cut<C> cut, boolean z) {
            return a(Range.upTo(cut, BoundType.forBoolean(z)));
        }

        /* renamed from: b */
        public NavigableMap<Cut<C>, Range<C>> tailMap(Cut<C> cut, boolean z) {
            return a(Range.downTo(cut, BoundType.forBoolean(z)));
        }

        @Override // java.util.SortedMap
        public Comparator<? super Cut<C>> comparator() {
            return Ordering.natural();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return get(obj) != null;
        }

        /* renamed from: a */
        public Range<C> get(Object obj) {
            if (obj instanceof Cut) {
                try {
                    Cut<C> cut = (Cut) obj;
                    if (this.a.contains(cut) && cut.compareTo((Cut<C>) this.b.lowerBound) >= 0) {
                        if (cut.compareTo((Cut<C>) this.b.upperBound) < 0) {
                            if (cut.equals(this.b.lowerBound)) {
                                Range range = (Range) Maps.c(this.c.floorEntry(cut));
                                if (range != null && range.upperBound.compareTo((Cut) this.b.lowerBound) > 0) {
                                    return range.intersection(this.b);
                                }
                            } else {
                                Range range2 = (Range) this.c.get(cut);
                                if (range2 != null) {
                                    return range2.intersection(this.b);
                                }
                            }
                        }
                    }
                } catch (ClassCastException unused) {
                }
            }
            return null;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v9, resolved type: java.util.NavigableMap<com.google.common.collect.Cut<C extends java.lang.Comparable<?>>, com.google.common.collect.Range<C extends java.lang.Comparable<?>>> */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.d
        public Iterator<Map.Entry<Cut<C>, Range<C>>> b() {
            Iterator<Range<C>> it2;
            if (this.b.isEmpty()) {
                return Iterators.a();
            }
            if (this.a.upperBound.isLessThan(this.b.lowerBound)) {
                return Iterators.a();
            }
            boolean z = false;
            if (this.a.lowerBound.isLessThan(this.b.lowerBound)) {
                it2 = this.d.tailMap(this.b.lowerBound, false).values().iterator();
            } else {
                NavigableMap<Cut<C>, Range<C>> navigableMap = this.c;
                Comparable endpoint = this.a.lowerBound.endpoint();
                if (this.a.lowerBoundType() == BoundType.CLOSED) {
                    z = true;
                }
                it2 = navigableMap.tailMap(endpoint, z).values().iterator();
            }
            return new AnonymousClass1(it2, (Cut) Ordering.natural().min(this.a.upperBound, Cut.belowValue(this.b.upperBound)));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: com.google.common.collect.TreeRangeSet$d$1  reason: invalid class name */
        public class AnonymousClass1 extends AbstractIterator<Map.Entry<Cut<C>, Range<C>>> {
            final /* synthetic */ Iterator a;
            final /* synthetic */ Cut b;

            AnonymousClass1(Iterator it2, Cut cut) {
                this.a = it2;
                this.b = cut;
            }

            /* access modifiers changed from: protected */
            /* renamed from: c */
            public Map.Entry<Cut<C>, Range<C>> a() {
                if (!this.a.hasNext()) {
                    return (Map.Entry) b();
                }
                Range range = (Range) this.a.next();
                if (this.b.isLessThan(range.lowerBound)) {
                    return (Map.Entry) b();
                }
                Range intersection = range.intersection(d.this.b);
                return Maps.a(intersection.lowerBound, intersection);
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: java.util.NavigableMap<com.google.common.collect.Cut<C extends java.lang.Comparable<?>>, com.google.common.collect.Range<C extends java.lang.Comparable<?>>> */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.e
        public Iterator<Map.Entry<Cut<C>, Range<C>>> a() {
            if (this.b.isEmpty()) {
                return Iterators.a();
            }
            Cut cut = (Cut) Ordering.natural().min(this.a.upperBound, Cut.belowValue(this.b.upperBound));
            return new AnonymousClass2(this.c.headMap(cut.endpoint(), cut.typeAsUpperBound() == BoundType.CLOSED).descendingMap().values().iterator());
        }

        /* renamed from: com.google.common.collect.TreeRangeSet$d$2  reason: invalid class name */
        class AnonymousClass2 extends AbstractIterator<Map.Entry<Cut<C>, Range<C>>> {
            final /* synthetic */ Iterator a;

            AnonymousClass2(Iterator it2) {
                this.a = it2;
            }

            /* access modifiers changed from: protected */
            /* renamed from: c */
            public Map.Entry<Cut<C>, Range<C>> a() {
                if (!this.a.hasNext()) {
                    return (Map.Entry) b();
                }
                Range range = (Range) this.a.next();
                if (d.this.b.lowerBound.compareTo((Cut) range.upperBound) >= 0) {
                    return (Map.Entry) b();
                }
                Range intersection = range.intersection(d.this.b);
                if (d.this.a.contains(intersection.lowerBound)) {
                    return Maps.a(intersection.lowerBound, intersection);
                }
                return (Map.Entry) b();
            }
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            return Iterators.b(b());
        }
    }

    @Override // com.google.common.collect.aq
    public aq<C> subRangeSet(Range<C> range) {
        return range.equals(Range.all()) ? this : new SubRangeSet(range);
    }

    private final class SubRangeSet extends TreeRangeSet<C> {
        private final Range<C> restriction;

        SubRangeSet(Range<C> range) {
            super(new d(Range.all(), range, TreeRangeSet.this.rangesByLowerBound));
            this.restriction = range;
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.f, com.google.common.collect.aq
        public boolean encloses(Range<C> range) {
            Range rangeEnclosing;
            if (this.restriction.isEmpty() || !this.restriction.encloses(range) || (rangeEnclosing = TreeRangeSet.this.rangeEnclosing(range)) == null || rangeEnclosing.intersection(this.restriction).isEmpty()) {
                return false;
            }
            return true;
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.f
        public Range<C> rangeContaining(C c) {
            Range<C> rangeContaining;
            if (this.restriction.contains(c) && (rangeContaining = TreeRangeSet.this.rangeContaining(c)) != null) {
                return rangeContaining.intersection(this.restriction);
            }
            return null;
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.f
        public void add(Range<C> range) {
            m.a(this.restriction.encloses(range), "Cannot add range %s to subRangeSet(%s)", range, this.restriction);
            TreeRangeSet.super.add(range);
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.f
        public void remove(Range<C> range) {
            if (range.isConnected(this.restriction)) {
                TreeRangeSet.this.remove(range.intersection(this.restriction));
            }
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.f
        public boolean contains(C c) {
            return this.restriction.contains(c) && TreeRangeSet.this.contains(c);
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.google.common.collect.TreeRangeSet */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.f
        public void clear() {
            TreeRangeSet.this.remove(this.restriction);
        }

        @Override // com.google.common.collect.TreeRangeSet, com.google.common.collect.aq
        public aq<C> subRangeSet(Range<C> range) {
            if (range.encloses(this.restriction)) {
                return this;
            }
            if (range.isConnected(this.restriction)) {
                return new SubRangeSet(this.restriction.intersection(range));
            }
            return ImmutableRangeSet.of();
        }
    }
}
