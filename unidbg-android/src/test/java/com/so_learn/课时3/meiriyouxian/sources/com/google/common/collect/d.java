package com.google.common.collect;

import com.google.common.collect.Multisets;
import com.google.common.collect.aj;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* compiled from: AbstractMultiset */
/* access modifiers changed from: package-private */
public abstract class d<E> extends AbstractCollection<E> implements aj<E> {
    private transient Set<E> elementSet;
    private transient Set<aj.a<E>> entrySet;

    @Override // java.util.AbstractCollection, java.util.Collection
    public abstract void clear();

    /* access modifiers changed from: package-private */
    public abstract int distinctElements();

    /* access modifiers changed from: package-private */
    public abstract Iterator<E> elementIterator();

    /* access modifiers changed from: package-private */
    public abstract Iterator<aj.a<E>> entryIterator();

    d() {
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return entrySet().isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.aj
    public boolean contains(Object obj) {
        return count(obj) > 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean add(E e) {
        add(e, 1);
        return true;
    }

    @Override // com.google.common.collect.aj
    public int add(E e, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.aj
    public final boolean remove(Object obj) {
        return remove(obj, 1) > 0;
    }

    @Override // com.google.common.collect.aj
    public int remove(Object obj, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.aj
    public int setCount(E e, int i) {
        return Multisets.a(this, e, i);
    }

    @Override // com.google.common.collect.aj
    public boolean setCount(E e, int i, int i2) {
        return Multisets.a(this, e, i, i2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean addAll(Collection<? extends E> collection) {
        return Multisets.a((aj) this, (Collection) collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean removeAll(Collection<?> collection) {
        return Multisets.b(this, collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean retainAll(Collection<?> collection) {
        return Multisets.c(this, collection);
    }

    @Override // com.google.common.collect.aj
    public Set<E> elementSet() {
        Set<E> set = this.elementSet;
        if (set != null) {
            return set;
        }
        Set<E> createElementSet = createElementSet();
        this.elementSet = createElementSet;
        return createElementSet;
    }

    /* access modifiers changed from: package-private */
    public Set<E> createElementSet() {
        return new a();
    }

    /* compiled from: AbstractMultiset */
    /* access modifiers changed from: package-private */
    public class a extends Multisets.b<E> {
        a() {
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Multisets.b
        public aj<E> a() {
            return d.this;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<E> iterator() {
            return d.this.elementIterator();
        }
    }

    @Override // com.google.common.collect.aj
    public Set<aj.a<E>> entrySet() {
        Set<aj.a<E>> set = this.entrySet;
        if (set != null) {
            return set;
        }
        Set<aj.a<E>> createEntrySet = createEntrySet();
        this.entrySet = createEntrySet;
        return createEntrySet;
    }

    /* compiled from: AbstractMultiset */
    /* access modifiers changed from: package-private */
    public class b extends Multisets.c<E> {
        b() {
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Multisets.c
        public aj<E> a() {
            return d.this;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<aj.a<E>> iterator() {
            return d.this.entryIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return d.this.distinctElements();
        }
    }

    /* access modifiers changed from: package-private */
    public Set<aj.a<E>> createEntrySet() {
        return new b();
    }

    @Override // java.util.Collection, java.lang.Object, com.google.common.collect.aj
    public final boolean equals(Object obj) {
        return Multisets.a(this, obj);
    }

    @Override // java.util.Collection, java.lang.Object, com.google.common.collect.aj
    public final int hashCode() {
        return entrySet().hashCode();
    }

    @Override // java.util.AbstractCollection, java.lang.Object
    public final String toString() {
        return entrySet().toString();
    }
}
