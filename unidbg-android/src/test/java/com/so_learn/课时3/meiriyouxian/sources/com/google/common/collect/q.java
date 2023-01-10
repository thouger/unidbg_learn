package com.google.common.collect;

import com.google.common.collect.Multisets;
import com.google.common.collect.aj;
import com.google.common.collect.ay;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;

/* compiled from: DescendingMultiset */
/* access modifiers changed from: package-private */
public abstract class q<E> extends y<E> implements aw<E> {
    private transient Comparator<? super E> a;
    private transient NavigableSet<E> b;
    private transient Set<aj.a<E>> c;

    /* access modifiers changed from: package-private */
    public abstract aw<E> a();

    /* access modifiers changed from: package-private */
    public abstract Iterator<aj.a<E>> b();

    q() {
    }

    @Override // com.google.common.collect.aw, com.google.common.collect.au
    public Comparator<? super E> comparator() {
        Comparator<? super E> comparator = this.a;
        if (comparator != null) {
            return comparator;
        }
        Ordering reverse = Ordering.from(a().comparator()).reverse();
        this.a = reverse;
        return reverse;
    }

    @Override // com.google.common.collect.y, com.google.common.collect.aj
    public NavigableSet<E> elementSet() {
        NavigableSet<E> navigableSet = this.b;
        if (navigableSet != null) {
            return navigableSet;
        }
        ay.b bVar = new ay.b(this);
        this.b = bVar;
        return bVar;
    }

    @Override // com.google.common.collect.aw
    public aj.a<E> pollFirstEntry() {
        return a().pollLastEntry();
    }

    @Override // com.google.common.collect.aw
    public aj.a<E> pollLastEntry() {
        return a().pollFirstEntry();
    }

    @Override // com.google.common.collect.aw
    public aw<E> headMultiset(E e, BoundType boundType) {
        return a().tailMultiset(e, boundType).descendingMultiset();
    }

    @Override // com.google.common.collect.aw
    public aw<E> subMultiset(E e, BoundType boundType, E e2, BoundType boundType2) {
        return a().subMultiset(e2, boundType2, e, boundType).descendingMultiset();
    }

    @Override // com.google.common.collect.aw
    public aw<E> tailMultiset(E e, BoundType boundType) {
        return a().headMultiset(e, boundType).descendingMultiset();
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.y, com.google.common.collect.s, com.google.common.collect.z
    public aj<E> delegate() {
        return a();
    }

    @Override // com.google.common.collect.aw
    public aw<E> descendingMultiset() {
        return a();
    }

    @Override // com.google.common.collect.aw
    public aj.a<E> firstEntry() {
        return a().lastEntry();
    }

    @Override // com.google.common.collect.aw
    public aj.a<E> lastEntry() {
        return a().firstEntry();
    }

    @Override // com.google.common.collect.y, com.google.common.collect.aj
    public Set<aj.a<E>> entrySet() {
        Set<aj.a<E>> set = this.c;
        if (set != null) {
            return set;
        }
        Set<aj.a<E>> c = c();
        this.c = c;
        return c;
    }

    /* compiled from: DescendingMultiset */
    /* access modifiers changed from: package-private */
    public class a extends Multisets.c<E> {
        a() {
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Multisets.c
        public aj<E> a() {
            return q.this;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<aj.a<E>> iterator() {
            return q.this.b();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return q.this.a().entrySet().size();
        }
    }

    /* access modifiers changed from: package-private */
    public Set<aj.a<E>> c() {
        return new a();
    }

    @Override // com.google.common.collect.s, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<E> iterator() {
        return Multisets.b((aj) this);
    }

    @Override // com.google.common.collect.s, java.util.Collection, java.util.Set
    public Object[] toArray() {
        return standardToArray();
    }

    @Override // com.google.common.collect.s, java.util.Collection, java.util.Set
    public <T> T[] toArray(T[] tArr) {
        return (T[]) standardToArray(tArr);
    }

    @Override // com.google.common.collect.z, java.lang.Object
    public String toString() {
        return entrySet().toString();
    }
}
