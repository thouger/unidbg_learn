package com.google.common.collect;

import com.google.common.base.i;
import com.google.common.base.m;
import com.google.common.collect.Multisets;
import com.google.common.collect.aj;
import com.google.common.collect.as;
import com.google.common.primitives.Ints;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Set;

public final class TreeMultiset<E> extends h<E> implements Serializable {
    private static final long serialVersionUID = 1;
    private final transient a<E> header;
    private final transient GeneralRange<E> range;
    private final transient b<a<E>> rootReference;

    @Override // com.google.common.collect.h, com.google.common.collect.aw, com.google.common.collect.au
    public /* bridge */ /* synthetic */ Comparator comparator() {
        return super.comparator();
    }

    @Override // com.google.common.collect.d, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.aj
    public /* bridge */ /* synthetic */ boolean contains(Object obj) {
        return super.contains(obj);
    }

    @Override // com.google.common.collect.h, com.google.common.collect.aw
    public /* bridge */ /* synthetic */ aw descendingMultiset() {
        return super.descendingMultiset();
    }

    @Override // com.google.common.collect.h, com.google.common.collect.d, com.google.common.collect.aj
    public /* bridge */ /* synthetic */ NavigableSet elementSet() {
        return super.elementSet();
    }

    @Override // com.google.common.collect.d, com.google.common.collect.aj
    public /* bridge */ /* synthetic */ Set entrySet() {
        return super.entrySet();
    }

    @Override // com.google.common.collect.h, com.google.common.collect.aw
    public /* bridge */ /* synthetic */ aj.a firstEntry() {
        return super.firstEntry();
    }

    @Override // com.google.common.collect.d, java.util.AbstractCollection, java.util.Collection
    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    @Override // com.google.common.collect.h, com.google.common.collect.aw
    public /* bridge */ /* synthetic */ aj.a lastEntry() {
        return super.lastEntry();
    }

    @Override // com.google.common.collect.h, com.google.common.collect.aw
    public /* bridge */ /* synthetic */ aj.a pollFirstEntry() {
        return super.pollFirstEntry();
    }

    @Override // com.google.common.collect.h, com.google.common.collect.aw
    public /* bridge */ /* synthetic */ aj.a pollLastEntry() {
        return super.pollLastEntry();
    }

    @Override // com.google.common.collect.h, com.google.common.collect.aw
    public /* bridge */ /* synthetic */ aw subMultiset(Object obj, BoundType boundType, Object obj2, BoundType boundType2) {
        return super.subMultiset(obj, boundType, obj2, boundType2);
    }

    public static <E extends Comparable> TreeMultiset<E> create() {
        return new TreeMultiset<>(Ordering.natural());
    }

    public static <E> TreeMultiset<E> create(Comparator<? super E> comparator) {
        return comparator == null ? new TreeMultiset<>(Ordering.natural()) : new TreeMultiset<>(comparator);
    }

    public static <E extends Comparable> TreeMultiset<E> create(Iterable<? extends E> iterable) {
        TreeMultiset<E> create = create();
        ag.a((Collection) create, (Iterable) iterable);
        return create;
    }

    TreeMultiset(b<a<E>> bVar, GeneralRange<E> generalRange, a<E> aVar) {
        super(generalRange.comparator());
        this.rootReference = bVar;
        this.range = generalRange;
        this.header = aVar;
    }

    TreeMultiset(Comparator<? super E> comparator) {
        super(comparator);
        this.range = GeneralRange.all(comparator);
        this.header = new a<>(null, 1);
        a<E> aVar = this.header;
        successor(aVar, aVar);
        this.rootReference = new b<>(null);
    }

    /* access modifiers changed from: private */
    public enum Aggregate {
        SIZE {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.TreeMultiset.Aggregate
            public int nodeAggregate(a<?> aVar) {
                return ((a) aVar).b;
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.TreeMultiset.Aggregate
            public long treeAggregate(a<?> aVar) {
                if (aVar == null) {
                    return 0;
                }
                return ((a) aVar).d;
            }
        },
        DISTINCT {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.TreeMultiset.Aggregate
            public int nodeAggregate(a<?> aVar) {
                return 1;
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.TreeMultiset.Aggregate
            public long treeAggregate(a<?> aVar) {
                if (aVar == null) {
                    return 0;
                }
                return (long) ((a) aVar).c;
            }
        };

        /* access modifiers changed from: package-private */
        public abstract int nodeAggregate(a<?> aVar);

        /* access modifiers changed from: package-private */
        public abstract long treeAggregate(a<?> aVar);

        /* synthetic */ Aggregate(AnonymousClass1 r3) {
            this();
        }
    }

    private long aggregateForEntries(Aggregate aggregate) {
        a<?> aVar = (a) this.rootReference.a();
        long treeAggregate = aggregate.treeAggregate(aVar);
        if (this.range.hasLowerBound()) {
            treeAggregate -= aggregateBelowRange(aggregate, aVar);
        }
        return this.range.hasUpperBound() ? treeAggregate - aggregateAboveRange(aggregate, aVar) : treeAggregate;
    }

    private long aggregateBelowRange(Aggregate aggregate, a<E> aVar) {
        long treeAggregate;
        long aggregateBelowRange;
        if (aVar == null) {
            return 0;
        }
        int compare = comparator().compare(this.range.getLowerEndpoint(), ((a) aVar).a);
        if (compare < 0) {
            return aggregateBelowRange(aggregate, ((a) aVar).f);
        }
        if (compare == 0) {
            int i = AnonymousClass4.a[this.range.getLowerBoundType().ordinal()];
            if (i == 1) {
                treeAggregate = (long) aggregate.nodeAggregate(aVar);
                aggregateBelowRange = aggregate.treeAggregate(((a) aVar).f);
            } else if (i == 2) {
                return aggregate.treeAggregate(((a) aVar).f);
            } else {
                throw new AssertionError();
            }
        } else {
            treeAggregate = aggregate.treeAggregate(((a) aVar).f) + ((long) aggregate.nodeAggregate(aVar));
            aggregateBelowRange = aggregateBelowRange(aggregate, ((a) aVar).g);
        }
        return treeAggregate + aggregateBelowRange;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.collect.TreeMultiset$4  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] a = new int[BoundType.values().length];

        static {
            try {
                a[BoundType.OPEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[BoundType.CLOSED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private long aggregateAboveRange(Aggregate aggregate, a<E> aVar) {
        long treeAggregate;
        long aggregateAboveRange;
        if (aVar == null) {
            return 0;
        }
        int compare = comparator().compare(this.range.getUpperEndpoint(), ((a) aVar).a);
        if (compare > 0) {
            return aggregateAboveRange(aggregate, ((a) aVar).g);
        }
        if (compare == 0) {
            int i = AnonymousClass4.a[this.range.getUpperBoundType().ordinal()];
            if (i == 1) {
                treeAggregate = (long) aggregate.nodeAggregate(aVar);
                aggregateAboveRange = aggregate.treeAggregate(((a) aVar).g);
            } else if (i == 2) {
                return aggregate.treeAggregate(((a) aVar).g);
            } else {
                throw new AssertionError();
            }
        } else {
            treeAggregate = aggregate.treeAggregate(((a) aVar).g) + ((long) aggregate.nodeAggregate(aVar));
            aggregateAboveRange = aggregateAboveRange(aggregate, ((a) aVar).f);
        }
        return treeAggregate + aggregateAboveRange;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.aj
    public int size() {
        return Ints.b(aggregateForEntries(Aggregate.SIZE));
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.d
    public int distinctElements() {
        return Ints.b(aggregateForEntries(Aggregate.DISTINCT));
    }

    static int distinctElements(a<?> aVar) {
        if (aVar == null) {
            return 0;
        }
        return ((a) aVar).c;
    }

    @Override // com.google.common.collect.aj
    public int count(Object obj) {
        try {
            a aVar = (a) this.rootReference.a();
            if (this.range.contains(obj)) {
                if (aVar != null) {
                    return aVar.a(comparator(), (Comparator<? super E>) obj);
                }
            }
        } catch (ClassCastException | NullPointerException unused) {
        }
        return 0;
    }

    @Override // com.google.common.collect.d, com.google.common.collect.aj
    public int add(E e, int i) {
        n.a(i, "occurrences");
        if (i == 0) {
            return count(e);
        }
        m.a(this.range.contains(e));
        a aVar = (a) this.rootReference.a();
        if (aVar == null) {
            comparator().compare(e, e);
            a aVar2 = new a(e, i);
            a<E> aVar3 = this.header;
            successor(aVar3, aVar2, aVar3);
            this.rootReference.a(aVar, aVar2);
            return 0;
        }
        int[] iArr = new int[1];
        this.rootReference.a(aVar, aVar.a(comparator(), e, i, iArr));
        return iArr[0];
    }

    @Override // com.google.common.collect.d, com.google.common.collect.aj
    public int remove(Object obj, int i) {
        n.a(i, "occurrences");
        if (i == 0) {
            return count(obj);
        }
        a aVar = (a) this.rootReference.a();
        int[] iArr = new int[1];
        try {
            if (this.range.contains(obj)) {
                if (aVar != null) {
                    this.rootReference.a(aVar, aVar.b(comparator(), obj, i, iArr));
                    return iArr[0];
                }
            }
        } catch (ClassCastException | NullPointerException unused) {
        }
        return 0;
    }

    @Override // com.google.common.collect.d, com.google.common.collect.aj
    public int setCount(E e, int i) {
        n.a(i, "count");
        boolean z = true;
        if (!this.range.contains(e)) {
            if (i != 0) {
                z = false;
            }
            m.a(z);
            return 0;
        }
        a aVar = (a) this.rootReference.a();
        if (aVar == null) {
            if (i > 0) {
                add(e, i);
            }
            return 0;
        }
        int[] iArr = new int[1];
        this.rootReference.a(aVar, aVar.c(comparator(), e, i, iArr));
        return iArr[0];
    }

    @Override // com.google.common.collect.d, com.google.common.collect.aj
    public boolean setCount(E e, int i, int i2) {
        n.a(i2, "newCount");
        n.a(i, "oldCount");
        m.a(this.range.contains(e));
        a aVar = (a) this.rootReference.a();
        if (aVar != null) {
            int[] iArr = new int[1];
            this.rootReference.a(aVar, aVar.a(comparator(), e, i, i2, iArr));
            return iArr[0] == i;
        } else if (i != 0) {
            return false;
        } else {
            if (i2 > 0) {
                add(e, i2);
            }
            return true;
        }
    }

    @Override // com.google.common.collect.d, java.util.AbstractCollection, java.util.Collection
    public void clear() {
        if (this.range.hasLowerBound() || this.range.hasUpperBound()) {
            Iterators.h(entryIterator());
            return;
        }
        a<E> aVar = ((a) this.header).i;
        while (true) {
            a<E> aVar2 = this.header;
            if (aVar != aVar2) {
                a<E> aVar3 = ((a) aVar).i;
                ((a) aVar).b = 0;
                ((a) aVar).f = null;
                ((a) aVar).g = null;
                ((a) aVar).h = null;
                ((a) aVar).i = null;
                aVar = aVar3;
            } else {
                successor(aVar2, aVar2);
                this.rootReference.b();
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.collect.TreeMultiset$1  reason: invalid class name */
    public class AnonymousClass1 extends Multisets.a<E> {
        final /* synthetic */ a a;

        AnonymousClass1(a aVar) {
            this.a = aVar;
        }

        @Override // com.google.common.collect.aj.a
        public E getElement() {
            return (E) this.a.a();
        }

        @Override // com.google.common.collect.aj.a
        public int getCount() {
            int b = this.a.b();
            return b == 0 ? TreeMultiset.this.count(getElement()) : b;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private aj.a<E> wrapEntry(a<E> aVar) {
        return new AnonymousClass1(aVar);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private a<E> firstNode() {
        a<E> aVar;
        if (((a) this.rootReference.a()) == null) {
            return null;
        }
        if (this.range.hasLowerBound()) {
            Object lowerEndpoint = this.range.getLowerEndpoint();
            a<E> b2 = ((a) this.rootReference.a()).b((Comparator<? super Comparator>) comparator(), (Comparator) lowerEndpoint);
            if (b2 == null) {
                return null;
            }
            if (this.range.getLowerBoundType() == BoundType.OPEN && comparator().compare(lowerEndpoint, b2.a()) == 0) {
                b2 = ((a) b2).i;
            }
            aVar = b2;
        } else {
            aVar = ((a) this.header).i;
        }
        if (aVar == this.header || !this.range.contains(aVar.a())) {
            return null;
        }
        return aVar;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private a<E> lastNode() {
        a<E> aVar;
        if (((a) this.rootReference.a()) == null) {
            return null;
        }
        if (this.range.hasUpperBound()) {
            Object upperEndpoint = this.range.getUpperEndpoint();
            a<E> c = ((a) this.rootReference.a()).c((Comparator<? super Comparator>) comparator(), (Comparator) upperEndpoint);
            if (c == null) {
                return null;
            }
            if (this.range.getUpperBoundType() == BoundType.OPEN && comparator().compare(upperEndpoint, c.a()) == 0) {
                c = ((a) c).h;
            }
            aVar = c;
        } else {
            aVar = ((a) this.header).h;
        }
        if (aVar == this.header || !this.range.contains(aVar.a())) {
            return null;
        }
        return aVar;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.d
    public Iterator<E> elementIterator() {
        return Multisets.a(entryIterator());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.collect.TreeMultiset$2  reason: invalid class name */
    public class AnonymousClass2 implements Iterator<aj.a<E>> {
        a<E> a = TreeMultiset.this.firstNode();
        aj.a<E> b;

        AnonymousClass2() {
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.a == null) {
                return false;
            }
            if (!TreeMultiset.this.range.tooHigh(this.a.a())) {
                return true;
            }
            this.a = null;
            return false;
        }

        /* renamed from: a */
        public aj.a<E> next() {
            if (hasNext()) {
                aj.a<E> wrapEntry = TreeMultiset.this.wrapEntry(this.a);
                this.b = wrapEntry;
                if (((a) this.a).i == TreeMultiset.this.header) {
                    this.a = null;
                } else {
                    this.a = ((a) this.a).i;
                }
                return wrapEntry;
            }
            throw new NoSuchElementException();
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v2, resolved type: com.google.common.collect.TreeMultiset */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Iterator
        public void remove() {
            n.a(this.b != null);
            TreeMultiset.this.setCount(this.b.getElement(), 0);
            this.b = null;
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.d
    public Iterator<aj.a<E>> entryIterator() {
        return new AnonymousClass2();
    }

    /* renamed from: com.google.common.collect.TreeMultiset$3  reason: invalid class name */
    class AnonymousClass3 implements Iterator<aj.a<E>> {
        a<E> a = TreeMultiset.this.lastNode();
        aj.a<E> b = null;

        AnonymousClass3() {
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.a == null) {
                return false;
            }
            if (!TreeMultiset.this.range.tooLow(this.a.a())) {
                return true;
            }
            this.a = null;
            return false;
        }

        /* renamed from: a */
        public aj.a<E> next() {
            if (hasNext()) {
                aj.a<E> wrapEntry = TreeMultiset.this.wrapEntry(this.a);
                this.b = wrapEntry;
                if (((a) this.a).h == TreeMultiset.this.header) {
                    this.a = null;
                } else {
                    this.a = ((a) this.a).h;
                }
                return wrapEntry;
            }
            throw new NoSuchElementException();
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v2, resolved type: com.google.common.collect.TreeMultiset */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Iterator
        public void remove() {
            n.a(this.b != null);
            TreeMultiset.this.setCount(this.b.getElement(), 0);
            this.b = null;
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.h
    public Iterator<aj.a<E>> descendingEntryIterator() {
        return new AnonymousClass3();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.google.common.collect.aj
    public Iterator<E> iterator() {
        return Multisets.b((aj) this);
    }

    @Override // com.google.common.collect.aw
    public aw<E> headMultiset(E e, BoundType boundType) {
        return new TreeMultiset(this.rootReference, this.range.intersect(GeneralRange.upTo(comparator(), e, boundType)), this.header);
    }

    @Override // com.google.common.collect.aw
    public aw<E> tailMultiset(E e, BoundType boundType) {
        return new TreeMultiset(this.rootReference, this.range.intersect(GeneralRange.downTo(comparator(), e, boundType)), this.header);
    }

    /* access modifiers changed from: private */
    public static final class b<T> {
        private T a;

        private b() {
        }

        /* synthetic */ b(AnonymousClass1 r1) {
            this();
        }

        public T a() {
            return this.a;
        }

        public void a(T t, T t2) {
            if (this.a == t) {
                this.a = t2;
                return;
            }
            throw new ConcurrentModificationException();
        }

        /* access modifiers changed from: package-private */
        public void b() {
            this.a = null;
        }
    }

    /* access modifiers changed from: private */
    public static final class a<E> {
        private final E a;
        private int b;
        private int c;
        private long d;
        private int e;
        private a<E> f;
        private a<E> g;
        private a<E> h;
        private a<E> i;

        a(E e, int i) {
            m.a(i > 0);
            this.a = e;
            this.b = i;
            this.d = (long) i;
            this.c = 1;
            this.e = 1;
            this.f = null;
            this.g = null;
        }

        public int a(Comparator<? super E> comparator, E e) {
            int compare = comparator.compare(e, this.a);
            if (compare < 0) {
                a<E> aVar = this.f;
                if (aVar == null) {
                    return 0;
                }
                return aVar.a(comparator, e);
            } else if (compare <= 0) {
                return this.b;
            } else {
                a<E> aVar2 = this.g;
                if (aVar2 == null) {
                    return 0;
                }
                return aVar2.a(comparator, e);
            }
        }

        private a<E> a(E e, int i) {
            this.g = new a<>(e, i);
            TreeMultiset.successor(this, this.g, this.i);
            this.e = Math.max(2, this.e);
            this.c++;
            this.d += (long) i;
            return this;
        }

        private a<E> b(E e, int i) {
            this.f = new a<>(e, i);
            TreeMultiset.successor(this.h, this.f, this);
            this.e = Math.max(2, this.e);
            this.c++;
            this.d += (long) i;
            return this;
        }

        /* access modifiers changed from: package-private */
        public a<E> a(Comparator<? super E> comparator, E e, int i, int[] iArr) {
            int compare = comparator.compare(e, this.a);
            boolean z = true;
            if (compare < 0) {
                a<E> aVar = this.f;
                if (aVar == null) {
                    iArr[0] = 0;
                    return b(e, i);
                }
                int i2 = aVar.e;
                this.f = aVar.a(comparator, e, i, iArr);
                if (iArr[0] == 0) {
                    this.c++;
                }
                this.d += (long) i;
                return this.f.e == i2 ? this : g();
            } else if (compare > 0) {
                a<E> aVar2 = this.g;
                if (aVar2 == null) {
                    iArr[0] = 0;
                    return a((a<E>) e, i);
                }
                int i3 = aVar2.e;
                this.g = aVar2.a(comparator, e, i, iArr);
                if (iArr[0] == 0) {
                    this.c++;
                }
                this.d += (long) i;
                return this.g.e == i3 ? this : g();
            } else {
                int i4 = this.b;
                iArr[0] = i4;
                long j = (long) i;
                if (((long) i4) + j > 2147483647L) {
                    z = false;
                }
                m.a(z);
                this.b += i;
                this.d += j;
                return this;
            }
        }

        /* access modifiers changed from: package-private */
        public a<E> b(Comparator<? super E> comparator, E e, int i, int[] iArr) {
            int compare = comparator.compare(e, this.a);
            if (compare < 0) {
                a<E> aVar = this.f;
                if (aVar == null) {
                    iArr[0] = 0;
                    return this;
                }
                this.f = aVar.b(comparator, e, i, iArr);
                if (iArr[0] > 0) {
                    if (i >= iArr[0]) {
                        this.c--;
                        this.d -= (long) iArr[0];
                    } else {
                        this.d -= (long) i;
                    }
                }
                return iArr[0] == 0 ? this : g();
            } else if (compare > 0) {
                a<E> aVar2 = this.g;
                if (aVar2 == null) {
                    iArr[0] = 0;
                    return this;
                }
                this.g = aVar2.b(comparator, e, i, iArr);
                if (iArr[0] > 0) {
                    if (i >= iArr[0]) {
                        this.c--;
                        this.d -= (long) iArr[0];
                    } else {
                        this.d -= (long) i;
                    }
                }
                return g();
            } else {
                int i2 = this.b;
                iArr[0] = i2;
                if (i >= i2) {
                    return c();
                }
                this.b = i2 - i;
                this.d -= (long) i;
                return this;
            }
        }

        /* access modifiers changed from: package-private */
        public a<E> c(Comparator<? super E> comparator, E e, int i, int[] iArr) {
            int compare = comparator.compare(e, this.a);
            if (compare < 0) {
                a<E> aVar = this.f;
                if (aVar == null) {
                    iArr[0] = 0;
                    return i > 0 ? b(e, i) : this;
                }
                this.f = aVar.c(comparator, e, i, iArr);
                if (i == 0 && iArr[0] != 0) {
                    this.c--;
                } else if (i > 0 && iArr[0] == 0) {
                    this.c++;
                }
                this.d += (long) (i - iArr[0]);
                return g();
            } else if (compare > 0) {
                a<E> aVar2 = this.g;
                if (aVar2 == null) {
                    iArr[0] = 0;
                    return i > 0 ? a((a<E>) e, i) : this;
                }
                this.g = aVar2.c(comparator, e, i, iArr);
                if (i == 0 && iArr[0] != 0) {
                    this.c--;
                } else if (i > 0 && iArr[0] == 0) {
                    this.c++;
                }
                this.d += (long) (i - iArr[0]);
                return g();
            } else {
                int i2 = this.b;
                iArr[0] = i2;
                if (i == 0) {
                    return c();
                }
                this.d += (long) (i - i2);
                this.b = i;
                return this;
            }
        }

        /* access modifiers changed from: package-private */
        public a<E> a(Comparator<? super E> comparator, E e, int i, int i2, int[] iArr) {
            int compare = comparator.compare(e, this.a);
            if (compare < 0) {
                a<E> aVar = this.f;
                if (aVar == null) {
                    iArr[0] = 0;
                    return (i != 0 || i2 <= 0) ? this : b(e, i2);
                }
                this.f = aVar.a(comparator, e, i, i2, iArr);
                if (iArr[0] == i) {
                    if (i2 == 0 && iArr[0] != 0) {
                        this.c--;
                    } else if (i2 > 0 && iArr[0] == 0) {
                        this.c++;
                    }
                    this.d += (long) (i2 - iArr[0]);
                }
                return g();
            } else if (compare > 0) {
                a<E> aVar2 = this.g;
                if (aVar2 == null) {
                    iArr[0] = 0;
                    return (i != 0 || i2 <= 0) ? this : a((a<E>) e, i2);
                }
                this.g = aVar2.a(comparator, e, i, i2, iArr);
                if (iArr[0] == i) {
                    if (i2 == 0 && iArr[0] != 0) {
                        this.c--;
                    } else if (i2 > 0 && iArr[0] == 0) {
                        this.c++;
                    }
                    this.d += (long) (i2 - iArr[0]);
                }
                return g();
            } else {
                int i3 = this.b;
                iArr[0] = i3;
                if (i == i3) {
                    if (i2 == 0) {
                        return c();
                    }
                    this.d += (long) (i2 - i3);
                    this.b = i2;
                }
                return this;
            }
        }

        private a<E> c() {
            int i = this.b;
            this.b = 0;
            TreeMultiset.successor(this.h, this.i);
            a<E> aVar = this.f;
            if (aVar == null) {
                return this.g;
            }
            a<E> aVar2 = this.g;
            if (aVar2 == null) {
                return aVar;
            }
            if (aVar.e >= aVar2.e) {
                a<E> aVar3 = this.h;
                aVar3.f = aVar.j(aVar3);
                aVar3.g = this.g;
                aVar3.c = this.c - 1;
                aVar3.d = this.d - ((long) i);
                return aVar3.g();
            }
            a<E> aVar4 = this.i;
            aVar4.g = aVar2.i(aVar4);
            aVar4.f = this.f;
            aVar4.c = this.c - 1;
            aVar4.d = this.d - ((long) i);
            return aVar4.g();
        }

        private a<E> i(a<E> aVar) {
            a<E> aVar2 = this.f;
            if (aVar2 == null) {
                return this.g;
            }
            this.f = aVar2.i(aVar);
            this.c--;
            this.d -= (long) aVar.b;
            return g();
        }

        private a<E> j(a<E> aVar) {
            a<E> aVar2 = this.g;
            if (aVar2 == null) {
                return this.f;
            }
            this.g = aVar2.j(aVar);
            this.c--;
            this.d -= (long) aVar.b;
            return g();
        }

        private void d() {
            this.c = TreeMultiset.distinctElements(this.f) + 1 + TreeMultiset.distinctElements(this.g);
            this.d = ((long) this.b) + k(this.f) + k(this.g);
        }

        private void e() {
            this.e = Math.max(l(this.f), l(this.g)) + 1;
        }

        private void f() {
            d();
            e();
        }

        private a<E> g() {
            int h = h();
            if (h == -2) {
                if (this.g.h() > 0) {
                    this.g = this.g.j();
                }
                return i();
            } else if (h != 2) {
                e();
                return this;
            } else {
                if (this.f.h() < 0) {
                    this.f = this.f.i();
                }
                return j();
            }
        }

        private int h() {
            return l(this.f) - l(this.g);
        }

        private a<E> i() {
            m.b(this.g != null);
            a<E> aVar = this.g;
            this.g = aVar.f;
            aVar.f = this;
            aVar.d = this.d;
            aVar.c = this.c;
            f();
            aVar.e();
            return aVar;
        }

        private a<E> j() {
            m.b(this.f != null);
            a<E> aVar = this.f;
            this.f = aVar.g;
            aVar.g = this;
            aVar.d = this.d;
            aVar.c = this.c;
            f();
            aVar.e();
            return aVar;
        }

        private static long k(a<?> aVar) {
            if (aVar == null) {
                return 0;
            }
            return ((a) aVar).d;
        }

        private static int l(a<?> aVar) {
            if (aVar == null) {
                return 0;
            }
            return ((a) aVar).e;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private a<E> b(Comparator<? super E> comparator, E e) {
            int compare = comparator.compare(e, this.a);
            if (compare < 0) {
                a<E> aVar = this.f;
                return aVar == null ? this : (a) i.a(aVar.b(comparator, e), this);
            } else if (compare == 0) {
                return this;
            } else {
                a<E> aVar2 = this.g;
                if (aVar2 == null) {
                    return null;
                }
                return aVar2.b(comparator, e);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private a<E> c(Comparator<? super E> comparator, E e) {
            int compare = comparator.compare(e, this.a);
            if (compare > 0) {
                a<E> aVar = this.g;
                return aVar == null ? this : (a) i.a(aVar.c(comparator, e), this);
            } else if (compare == 0) {
                return this;
            } else {
                a<E> aVar2 = this.f;
                if (aVar2 == null) {
                    return null;
                }
                return aVar2.c(comparator, e);
            }
        }

        /* access modifiers changed from: package-private */
        public E a() {
            return this.a;
        }

        /* access modifiers changed from: package-private */
        public int b() {
            return this.b;
        }

        public String toString() {
            return Multisets.a(a(), b()).toString();
        }
    }

    /* access modifiers changed from: private */
    public static <T> void successor(a<T> aVar, a<T> aVar2) {
        ((a) aVar).i = aVar2;
        ((a) aVar2).h = aVar;
    }

    /* access modifiers changed from: private */
    public static <T> void successor(a<T> aVar, a<T> aVar2, a<T> aVar3) {
        successor(aVar, aVar2);
        successor(aVar2, aVar3);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(elementSet().comparator());
        as.a(this, objectOutputStream);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        Comparator comparator = (Comparator) objectInputStream.readObject();
        as.a(h.class, "comparator").a((as.a) this, (Object) comparator);
        as.a(TreeMultiset.class, "range").a((as.a) this, (Object) GeneralRange.all(comparator));
        as.a(TreeMultiset.class, "rootReference").a((as.a) this, (Object) new b(null));
        a aVar = new a(null, 1);
        as.a(TreeMultiset.class, "header").a((as.a) this, (Object) aVar);
        successor(aVar, aVar);
        as.a(this, objectInputStream);
    }
}
