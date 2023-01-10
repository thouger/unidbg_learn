package com.google.common.collect;

import com.google.common.base.m;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.aj;
import com.google.common.math.c;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public abstract class ImmutableSortedMultiset<E> extends ImmutableSortedMultisetFauxverideShim<E> implements aw<E> {
    transient ImmutableSortedMultiset<E> descendingMultiset;

    @Override // com.google.common.collect.ImmutableMultiset, com.google.common.collect.aj
    public abstract ImmutableSortedSet<E> elementSet();

    @Override // com.google.common.collect.aw
    public abstract ImmutableSortedMultiset<E> headMultiset(E e, BoundType boundType);

    @Override // com.google.common.collect.aw
    public abstract ImmutableSortedMultiset<E> tailMultiset(E e, BoundType boundType);

    public static <E> ImmutableSortedMultiset<E> of() {
        return (ImmutableSortedMultiset<E>) RegularImmutableSortedMultiset.NATURAL_EMPTY_MULTISET;
    }

    public static <E extends Comparable<? super E>> ImmutableSortedMultiset<E> of(E e) {
        return new RegularImmutableSortedMultiset((RegularImmutableSortedSet) ImmutableSortedSet.of((Comparable) e), new long[]{0, 1}, 0, 1);
    }

    public static <E extends Comparable<? super E>> ImmutableSortedMultiset<E> of(E e, E e2) {
        return copyOf(Ordering.natural(), Arrays.asList(e, e2));
    }

    public static <E extends Comparable<? super E>> ImmutableSortedMultiset<E> of(E e, E e2, E e3) {
        return copyOf(Ordering.natural(), Arrays.asList(e, e2, e3));
    }

    public static <E extends Comparable<? super E>> ImmutableSortedMultiset<E> of(E e, E e2, E e3, E e4) {
        return copyOf(Ordering.natural(), Arrays.asList(e, e2, e3, e4));
    }

    public static <E extends Comparable<? super E>> ImmutableSortedMultiset<E> of(E e, E e2, E e3, E e4, E e5) {
        return copyOf(Ordering.natural(), Arrays.asList(e, e2, e3, e4, e5));
    }

    public static <E extends Comparable<? super E>> ImmutableSortedMultiset<E> of(E e, E e2, E e3, E e4, E e5, E e6, E... eArr) {
        ArrayList b = Lists.b(eArr.length + 6);
        Collections.addAll(b, e, e2, e3, e4, e5, e6);
        Collections.addAll(b, eArr);
        return copyOf(Ordering.natural(), b);
    }

    public static <E extends Comparable<? super E>> ImmutableSortedMultiset<E> copyOf(E[] eArr) {
        return copyOf(Ordering.natural(), Arrays.asList(eArr));
    }

    public static <E> ImmutableSortedMultiset<E> copyOf(Iterable<? extends E> iterable) {
        return copyOf(Ordering.natural(), iterable);
    }

    public static <E> ImmutableSortedMultiset<E> copyOf(Iterator<? extends E> it2) {
        return copyOf(Ordering.natural(), it2);
    }

    public static <E> ImmutableSortedMultiset<E> copyOf(Comparator<? super E> comparator, Iterator<? extends E> it2) {
        m.a(comparator);
        return new a(comparator).b((Iterator) it2).a();
    }

    public static <E> ImmutableSortedMultiset<E> copyOf(Comparator<? super E> comparator, Iterable<? extends E> iterable) {
        if (iterable instanceof ImmutableSortedMultiset) {
            ImmutableSortedMultiset<E> immutableSortedMultiset = (ImmutableSortedMultiset) iterable;
            if (comparator.equals(immutableSortedMultiset.comparator())) {
                return immutableSortedMultiset.isPartialView() ? copyOfSortedEntries(comparator, immutableSortedMultiset.entrySet().asList()) : immutableSortedMultiset;
            }
        }
        return new a(comparator).b(iterable).a();
    }

    public static <E> ImmutableSortedMultiset<E> copyOfSorted(aw<E> awVar) {
        return copyOfSortedEntries(awVar.comparator(), Lists.a(awVar.entrySet()));
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: com.google.common.collect.ImmutableList$a */
    /* JADX WARN: Multi-variable type inference failed */
    private static <E> ImmutableSortedMultiset<E> copyOfSortedEntries(Comparator<? super E> comparator, Collection<aj.a<E>> collection) {
        if (collection.isEmpty()) {
            return emptyMultiset(comparator);
        }
        ImmutableList.a aVar = new ImmutableList.a(collection.size());
        long[] jArr = new long[(collection.size() + 1)];
        int i = 0;
        for (aj.a<E> aVar2 : collection) {
            aVar.b(aVar2.getElement());
            int i2 = i + 1;
            jArr[i2] = jArr[i] + ((long) aVar2.getCount());
            i = i2;
        }
        return new RegularImmutableSortedMultiset(new RegularImmutableSortedSet(aVar.a(), comparator), jArr, 0, collection.size());
    }

    static <E> ImmutableSortedMultiset<E> emptyMultiset(Comparator<? super E> comparator) {
        return Ordering.natural().equals(comparator) ? (ImmutableSortedMultiset<E>) RegularImmutableSortedMultiset.NATURAL_EMPTY_MULTISET : new RegularImmutableSortedMultiset(comparator);
    }

    ImmutableSortedMultiset() {
    }

    @Override // com.google.common.collect.aw, com.google.common.collect.au
    public final Comparator<? super E> comparator() {
        return elementSet().comparator();
    }

    @Override // com.google.common.collect.aw
    public ImmutableSortedMultiset<E> descendingMultiset() {
        ImmutableSortedMultiset<E> immutableSortedMultiset = this.descendingMultiset;
        if (immutableSortedMultiset == null) {
            immutableSortedMultiset = isEmpty() ? emptyMultiset(Ordering.from(comparator()).reverse()) : new DescendingImmutableSortedMultiset<>(this);
            this.descendingMultiset = immutableSortedMultiset;
        }
        return immutableSortedMultiset;
    }

    @Override // com.google.common.collect.aw
    @Deprecated
    public final aj.a<E> pollFirstEntry() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.aw
    @Deprecated
    public final aj.a<E> pollLastEntry() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.aw
    public ImmutableSortedMultiset<E> subMultiset(E e, BoundType boundType, E e2, BoundType boundType2) {
        m.a(comparator().compare(e, e2) <= 0, "Expected lowerBound <= upperBound but %s > %s", e, e2);
        return tailMultiset((ImmutableSortedMultiset<E>) e, boundType).headMultiset((ImmutableSortedMultiset<E>) e2, boundType2);
    }

    public static <E> a<E> orderedBy(Comparator<E> comparator) {
        return new a<>(comparator);
    }

    public static <E extends Comparable<?>> a<E> reverseOrder() {
        return new a<>(Ordering.natural().reverse());
    }

    public static <E extends Comparable<?>> a<E> naturalOrder() {
        return new a<>(Ordering.natural());
    }

    public static class a<E> extends ImmutableMultiset.a<E> {
        E[] d = ((E[]) new Object[4]);
        private final Comparator<? super E> e;
        private int[] f = new int[4];
        private int g;
        private boolean h;

        @Override // com.google.common.collect.ImmutableMultiset.a
        public /* synthetic */ ImmutableMultiset.a a(Object obj) {
            return c((a<E>) obj);
        }

        @Override // com.google.common.collect.ImmutableMultiset.a, com.google.common.collect.ImmutableCollection.b
        public /* synthetic */ ImmutableCollection.b b(Object obj) {
            return c((a<E>) obj);
        }

        public a(Comparator<? super E> comparator) {
            super(true);
            this.e = (Comparator) m.a(comparator);
        }

        private void c() {
            int i = this.g;
            E[] eArr = this.d;
            if (i == eArr.length) {
                a(true);
            } else if (this.h) {
                this.d = (E[]) Arrays.copyOf(eArr, eArr.length);
            }
            this.h = false;
        }

        private void a(boolean z) {
            int i = this.g;
            if (i != 0) {
                E[] eArr = (E[]) Arrays.copyOf(this.d, i);
                Arrays.sort(eArr, this.e);
                int i2 = 1;
                for (int i3 = 1; i3 < eArr.length; i3++) {
                    if (this.e.compare(eArr[i2 - 1], eArr[i3]) < 0) {
                        eArr[i2] = eArr[i3];
                        i2++;
                    }
                }
                Arrays.fill(eArr, i2, this.g, (Object) null);
                if (z) {
                    int i4 = i2 * 4;
                    int i5 = this.g;
                    if (i4 > i5 * 3) {
                        eArr = (E[]) Arrays.copyOf(eArr, c.c(i5, (i5 / 2) + 1));
                    }
                }
                int[] iArr = new int[eArr.length];
                for (int i6 = 0; i6 < this.g; i6++) {
                    int binarySearch = Arrays.binarySearch(eArr, 0, i2, this.d[i6], this.e);
                    int[] iArr2 = this.f;
                    if (iArr2[i6] >= 0) {
                        iArr[binarySearch] = iArr[binarySearch] + iArr2[i6];
                    } else {
                        iArr[binarySearch] = ~iArr2[i6];
                    }
                }
                this.d = eArr;
                this.f = iArr;
                this.g = i2;
            }
        }

        public a<E> c(E e) {
            return a(e, 1);
        }

        /* renamed from: c */
        public a<E> b(E... eArr) {
            for (E e : eArr) {
                c((a<E>) e);
            }
            return this;
        }

        /* renamed from: b */
        public a<E> a(E e, int i) {
            m.a(e);
            n.a(i, "occurrences");
            if (i == 0) {
                return this;
            }
            c();
            E[] eArr = this.d;
            int i2 = this.g;
            eArr[i2] = e;
            this.f[i2] = i;
            this.g = i2 + 1;
            return this;
        }

        /* renamed from: d */
        public a<E> b(Iterable<? extends E> iterable) {
            if (iterable instanceof aj) {
                for (aj.a<E> aVar : ((aj) iterable).entrySet()) {
                    a(aVar.getElement(), aVar.getCount());
                }
            } else {
                Iterator<? extends E> it2 = iterable.iterator();
                while (it2.hasNext()) {
                    c((a<E>) it2.next());
                }
            }
            return this;
        }

        /* renamed from: c */
        public a<E> b(Iterator<? extends E> it2) {
            while (it2.hasNext()) {
                c((a<E>) it2.next());
            }
            return this;
        }

        private void d() {
            a(false);
            int i = 0;
            int i2 = 0;
            while (true) {
                int i3 = this.g;
                if (i < i3) {
                    int[] iArr = this.f;
                    if (iArr[i] > 0) {
                        E[] eArr = this.d;
                        eArr[i2] = eArr[i];
                        iArr[i2] = iArr[i];
                        i2++;
                    }
                    i++;
                } else {
                    Arrays.fill(this.d, i2, i3, (Object) null);
                    Arrays.fill(this.f, i2, this.g, 0);
                    this.g = i2;
                    return;
                }
            }
        }

        /* renamed from: b */
        public ImmutableSortedMultiset<E> a() {
            d();
            int i = this.g;
            if (i == 0) {
                return ImmutableSortedMultiset.emptyMultiset(this.e);
            }
            RegularImmutableSortedSet regularImmutableSortedSet = (RegularImmutableSortedSet) ImmutableSortedSet.construct(this.e, i, this.d);
            long[] jArr = new long[(this.g + 1)];
            int i2 = 0;
            while (true) {
                int i3 = this.g;
                if (i2 < i3) {
                    int i4 = i2 + 1;
                    jArr[i4] = jArr[i2] + ((long) this.f[i2]);
                    i2 = i4;
                } else {
                    this.h = true;
                    return new RegularImmutableSortedMultiset(regularImmutableSortedSet, jArr, 0, i3);
                }
            }
        }
    }

    private static final class SerializedForm<E> implements Serializable {
        final Comparator<? super E> comparator;
        final int[] counts;
        final E[] elements;

        /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: E[] */
        /* JADX WARN: Multi-variable type inference failed */
        SerializedForm(aw<E> awVar) {
            this.comparator = awVar.comparator();
            int size = awVar.entrySet().size();
            this.elements = (E[]) new Object[size];
            this.counts = new int[size];
            int i = 0;
            for (aj.a<E> aVar : awVar.entrySet()) {
                this.elements[i] = aVar.getElement();
                this.counts[i] = aVar.getCount();
                i++;
            }
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            int length = this.elements.length;
            a aVar = new a(this.comparator);
            for (int i = 0; i < length; i++) {
                aVar.a(this.elements[i], this.counts[i]);
            }
            return aVar.a();
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMultiset, com.google.common.collect.ImmutableCollection
    public Object writeReplace() {
        return new SerializedForm(this);
    }
}
