package com.google.common.collect;

import com.google.common.collect.Multisets;
import com.google.common.collect.aj;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.SortedSet;

/* compiled from: SortedMultisets */
final class ay {

    /* compiled from: SortedMultisets */
    static class a<E> extends Multisets.b<E> implements SortedSet<E> {
        private final aw<E> a;

        a(aw<E> awVar) {
            this.a = awVar;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public final aw<E> a() {
            return this.a;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<E> iterator() {
            return Multisets.a(a().entrySet().iterator());
        }

        @Override // java.util.SortedSet
        public Comparator<? super E> comparator() {
            return a().comparator();
        }

        @Override // java.util.SortedSet
        public SortedSet<E> subSet(E e, E e2) {
            return a().subMultiset(e, BoundType.CLOSED, e2, BoundType.OPEN).elementSet();
        }

        @Override // java.util.SortedSet
        public SortedSet<E> headSet(E e) {
            return a().headMultiset(e, BoundType.OPEN).elementSet();
        }

        @Override // java.util.SortedSet
        public SortedSet<E> tailSet(E e) {
            return a().tailMultiset(e, BoundType.CLOSED).elementSet();
        }

        @Override // java.util.SortedSet
        public E first() {
            return (E) ay.c(a().firstEntry());
        }

        @Override // java.util.SortedSet
        public E last() {
            return (E) ay.c(a().lastEntry());
        }
    }

    /* compiled from: SortedMultisets */
    /* access modifiers changed from: package-private */
    public static class b<E> extends a<E> implements NavigableSet<E> {
        b(aw<E> awVar) {
            super(awVar);
        }

        @Override // java.util.NavigableSet
        public E lower(E e) {
            return (E) ay.d(a().headMultiset(e, BoundType.OPEN).lastEntry());
        }

        @Override // java.util.NavigableSet
        public E floor(E e) {
            return (E) ay.d(a().headMultiset(e, BoundType.CLOSED).lastEntry());
        }

        @Override // java.util.NavigableSet
        public E ceiling(E e) {
            return (E) ay.d(a().tailMultiset(e, BoundType.CLOSED).firstEntry());
        }

        @Override // java.util.NavigableSet
        public E higher(E e) {
            return (E) ay.d(a().tailMultiset(e, BoundType.OPEN).firstEntry());
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> descendingSet() {
            return new b(a().descendingMultiset());
        }

        @Override // java.util.NavigableSet
        public Iterator<E> descendingIterator() {
            return descendingSet().iterator();
        }

        @Override // java.util.NavigableSet
        public E pollFirst() {
            return (E) ay.d(a().pollFirstEntry());
        }

        @Override // java.util.NavigableSet
        public E pollLast() {
            return (E) ay.d(a().pollLastEntry());
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> subSet(E e, boolean z, E e2, boolean z2) {
            return new b(a().subMultiset(e, BoundType.forBoolean(z), e2, BoundType.forBoolean(z2)));
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> headSet(E e, boolean z) {
            return new b(a().headMultiset(e, BoundType.forBoolean(z)));
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> tailSet(E e, boolean z) {
            return new b(a().tailMultiset(e, BoundType.forBoolean(z)));
        }
    }

    /* access modifiers changed from: private */
    public static <E> E c(aj.a<E> aVar) {
        if (aVar != null) {
            return (E) aVar.getElement();
        }
        throw new NoSuchElementException();
    }

    /* access modifiers changed from: private */
    public static <E> E d(aj.a<E> aVar) {
        if (aVar == null) {
            return null;
        }
        return (E) aVar.getElement();
    }
}
