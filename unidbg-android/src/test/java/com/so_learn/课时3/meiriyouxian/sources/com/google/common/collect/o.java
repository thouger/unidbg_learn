package com.google.common.collect;

import android.net.TrafficStats;
import com.android.internal.app.DumpHeapActivity;
import com.google.common.base.Predicates;
import com.google.common.base.m;
import com.google.common.base.n;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

/* compiled from: Collections2 */
public final class o {
    public static <E> Collection<E> a(Collection<E> collection, n<? super E> nVar) {
        if (collection instanceof a) {
            return ((a) collection).a(nVar);
        }
        return new a((Collection) m.a(collection), (n) m.a(nVar));
    }

    static boolean a(Collection<?> collection, Object obj) {
        m.a(collection);
        try {
            return collection.contains(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    static boolean b(Collection<?> collection, Object obj) {
        m.a(collection);
        try {
            return collection.remove(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    /* compiled from: Collections2 */
    static class a<E> extends AbstractCollection<E> {
        final Collection<E> a;
        final n<? super E> b;

        a(Collection<E> collection, n<? super E> nVar) {
            this.a = collection;
            this.b = nVar;
        }

        /* access modifiers changed from: package-private */
        public a<E> a(n<? super E> nVar) {
            return new a<>(this.a, Predicates.a(this.b, nVar));
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean add(E e) {
            m.a(this.b.apply(e));
            return this.a.add(e);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean addAll(Collection<? extends E> collection) {
            Iterator<? extends E> it2 = collection.iterator();
            while (it2.hasNext()) {
                m.a(this.b.apply(it2.next()));
            }
            return this.a.addAll(collection);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            ag.a((Iterable) this.a, (n) this.b);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            if (o.a((Collection<?>) this.a, obj)) {
                return this.b.apply(obj);
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean containsAll(Collection<?> collection) {
            return o.a((Collection<?>) this, collection);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return !ag.c(this.a, this.b);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<E> iterator() {
            return Iterators.b((Iterator) this.a.iterator(), (n) this.b);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object obj) {
            return contains(obj) && this.a.remove(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            Iterator<E> it2 = this.a.iterator();
            boolean z = false;
            while (it2.hasNext()) {
                E next = it2.next();
                if (this.b.apply(next) && collection.contains(next)) {
                    it2.remove();
                    z = true;
                }
            }
            return z;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            Iterator<E> it2 = this.a.iterator();
            boolean z = false;
            while (it2.hasNext()) {
                E next = it2.next();
                if (this.b.apply(next) && !collection.contains(next)) {
                    it2.remove();
                    z = true;
                }
            }
            return z;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            int i = 0;
            for (E e : this.a) {
                if (this.b.apply(e)) {
                    i++;
                }
            }
            return i;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public Object[] toArray() {
            return Lists.a(iterator()).toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            return (T[]) Lists.a(iterator()).toArray(tArr);
        }
    }

    static boolean a(Collection<?> collection, Collection<?> collection2) {
        Iterator<?> it2 = collection2.iterator();
        while (it2.hasNext()) {
            if (!collection.contains(it2.next())) {
                return false;
            }
        }
        return true;
    }

    static String a(Collection<?> collection) {
        StringBuilder a2 = a(collection.size());
        a2.append('[');
        boolean z = true;
        for (Object obj : collection) {
            if (!z) {
                a2.append(", ");
            }
            z = false;
            if (obj == collection) {
                a2.append("(this Collection)");
            } else {
                a2.append(obj);
            }
        }
        a2.append(']');
        return a2.toString();
    }

    static StringBuilder a(int i) {
        n.a(i, DumpHeapActivity.KEY_SIZE);
        return new StringBuilder((int) Math.min(((long) i) * 8, (long) TrafficStats.GB_IN_BYTES));
    }

    static <T> Collection<T> a(Iterable<T> iterable) {
        return (Collection) iterable;
    }
}
