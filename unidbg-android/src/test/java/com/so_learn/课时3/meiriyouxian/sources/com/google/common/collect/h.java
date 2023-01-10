package com.google.common.collect;

import com.google.common.base.m;
import com.google.common.collect.aj;
import com.google.common.collect.ay;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;

/* compiled from: AbstractSortedMultiset */
abstract class h<E> extends d<E> implements aw<E> {
    final Comparator<? super E> comparator;
    private transient aw<E> descendingMultiset;

    /* access modifiers changed from: package-private */
    public abstract Iterator<aj.a<E>> descendingEntryIterator();

    h() {
        this(Ordering.natural());
    }

    h(Comparator<? super E> comparator) {
        this.comparator = (Comparator) m.a(comparator);
    }

    @Override // com.google.common.collect.d, com.google.common.collect.aj
    public NavigableSet<E> elementSet() {
        return (NavigableSet) super.elementSet();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.d
    public NavigableSet<E> createElementSet() {
        return new ay.b(this);
    }

    @Override // com.google.common.collect.aw, com.google.common.collect.au
    public Comparator<? super E> comparator() {
        return this.comparator;
    }

    @Override // com.google.common.collect.aw
    public aj.a<E> firstEntry() {
        Iterator<aj.a<E>> entryIterator = entryIterator();
        if (entryIterator.hasNext()) {
            return entryIterator.next();
        }
        return null;
    }

    @Override // com.google.common.collect.aw
    public aj.a<E> lastEntry() {
        Iterator<aj.a<E>> descendingEntryIterator = descendingEntryIterator();
        if (descendingEntryIterator.hasNext()) {
            return descendingEntryIterator.next();
        }
        return null;
    }

    @Override // com.google.common.collect.aw
    public aj.a<E> pollFirstEntry() {
        Iterator<aj.a<E>> entryIterator = entryIterator();
        if (!entryIterator.hasNext()) {
            return null;
        }
        aj.a<E> next = entryIterator.next();
        aj.a<E> a2 = Multisets.a(next.getElement(), next.getCount());
        entryIterator.remove();
        return a2;
    }

    @Override // com.google.common.collect.aw
    public aj.a<E> pollLastEntry() {
        Iterator<aj.a<E>> descendingEntryIterator = descendingEntryIterator();
        if (!descendingEntryIterator.hasNext()) {
            return null;
        }
        aj.a<E> next = descendingEntryIterator.next();
        aj.a<E> a2 = Multisets.a(next.getElement(), next.getCount());
        descendingEntryIterator.remove();
        return a2;
    }

    @Override // com.google.common.collect.aw
    public aw<E> subMultiset(E e, BoundType boundType, E e2, BoundType boundType2) {
        m.a(boundType);
        m.a(boundType2);
        return tailMultiset(e, boundType).headMultiset(e2, boundType2);
    }

    /* access modifiers changed from: package-private */
    public Iterator<E> descendingIterator() {
        return Multisets.b((aj) descendingMultiset());
    }

    @Override // com.google.common.collect.aw
    public aw<E> descendingMultiset() {
        aw<E> awVar = this.descendingMultiset;
        if (awVar != null) {
            return awVar;
        }
        aw<E> createDescendingMultiset = createDescendingMultiset();
        this.descendingMultiset = createDescendingMultiset;
        return createDescendingMultiset;
    }

    /* compiled from: AbstractSortedMultiset */
    /* access modifiers changed from: package-private */
    public class a extends q<E> {
        a() {
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.q
        public aw<E> a() {
            return h.this;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.q
        public Iterator<aj.a<E>> b() {
            return h.this.descendingEntryIterator();
        }

        @Override // com.google.common.collect.q, com.google.common.collect.s, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<E> iterator() {
            return h.this.descendingIterator();
        }
    }

    /* access modifiers changed from: package-private */
    public aw<E> createDescendingMultiset() {
        return new a();
    }
}
