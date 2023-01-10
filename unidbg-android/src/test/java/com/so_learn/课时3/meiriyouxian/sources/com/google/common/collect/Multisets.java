package com.google.common.collect;

import com.google.common.base.j;
import com.google.common.base.m;
import com.google.common.collect.Sets;
import com.google.common.collect.aj;
import com.google.common.primitives.Ints;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public final class Multisets {
    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: com.google.common.collect.aj<? extends E> */
    /* JADX WARN: Multi-variable type inference failed */
    public static <E> aj<E> a(aj<? extends E> ajVar) {
        return ((ajVar instanceof UnmodifiableMultiset) || (ajVar instanceof ImmutableMultiset)) ? ajVar : new UnmodifiableMultiset((aj) m.a(ajVar));
    }

    /* access modifiers changed from: package-private */
    public static class UnmodifiableMultiset<E> extends y<E> implements Serializable {
        private static final long serialVersionUID = 0;
        final aj<? extends E> delegate;
        transient Set<E> elementSet;
        transient Set<aj.a<E>> entrySet;

        UnmodifiableMultiset(aj<? extends E> ajVar) {
            this.delegate = ajVar;
        }

        /* JADX DEBUG: Type inference failed for r0v0. Raw type applied. Possible types: com.google.common.collect.aj<? extends E>, com.google.common.collect.aj<E> */
        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.y, com.google.common.collect.s, com.google.common.collect.z
        public aj<E> delegate() {
            return (aj<? extends E>) this.delegate;
        }

        /* access modifiers changed from: package-private */
        public Set<E> createElementSet() {
            return Collections.unmodifiableSet(this.delegate.elementSet());
        }

        @Override // com.google.common.collect.y, com.google.common.collect.aj
        public Set<E> elementSet() {
            Set<E> set = this.elementSet;
            if (set != null) {
                return set;
            }
            Set<E> createElementSet = createElementSet();
            this.elementSet = createElementSet;
            return createElementSet;
        }

        @Override // com.google.common.collect.y, com.google.common.collect.aj
        public Set<aj.a<E>> entrySet() {
            Set<aj.a<E>> set = this.entrySet;
            if (set != null) {
                return set;
            }
            Set<aj.a<E>> unmodifiableSet = Collections.unmodifiableSet(this.delegate.entrySet());
            this.entrySet = unmodifiableSet;
            return unmodifiableSet;
        }

        @Override // com.google.common.collect.s, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<E> iterator() {
            return Iterators.a((Iterator) this.delegate.iterator());
        }

        @Override // com.google.common.collect.s, java.util.Collection, java.util.Queue
        public boolean add(E e) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.y, com.google.common.collect.aj
        public int add(E e, int i) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.s, java.util.Collection
        public boolean addAll(Collection<? extends E> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.s, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.y, com.google.common.collect.aj
        public int remove(Object obj, int i) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.s, java.util.Collection, java.util.Set
        public boolean removeAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.s, java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.s, java.util.Collection, java.util.Set
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.y, com.google.common.collect.aj
        public int setCount(E e, int i) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.y, com.google.common.collect.aj
        public boolean setCount(E e, int i, int i2) {
            throw new UnsupportedOperationException();
        }
    }

    public static <E> aw<E> a(aw<E> awVar) {
        return new UnmodifiableSortedMultiset((aw) m.a(awVar));
    }

    public static <E> aj.a<E> a(E e, int i) {
        return new ImmutableEntry(e, i);
    }

    static class ImmutableEntry<E> extends a<E> implements Serializable {
        private static final long serialVersionUID = 0;
        private final int count;
        private final E element;

        public ImmutableEntry<E> nextInBucket() {
            return null;
        }

        ImmutableEntry(E e, int i) {
            this.element = e;
            this.count = i;
            n.a(i, "count");
        }

        @Override // com.google.common.collect.aj.a
        public final E getElement() {
            return this.element;
        }

        @Override // com.google.common.collect.aj.a
        public final int getCount() {
            return this.count;
        }
    }

    static int a(Iterable<?> iterable) {
        if (iterable instanceof aj) {
            return ((aj) iterable).elementSet().size();
        }
        return 11;
    }

    /* access modifiers changed from: package-private */
    public static abstract class a<E> implements aj.a<E> {
        a() {
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof aj.a)) {
                return false;
            }
            aj.a aVar = (aj.a) obj;
            if (getCount() != aVar.getCount() || !j.a(getElement(), aVar.getElement())) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int i;
            E element = getElement();
            if (element == null) {
                i = 0;
            } else {
                i = element.hashCode();
            }
            return i ^ getCount();
        }

        @Override // com.google.common.collect.aj.a
        public String toString() {
            String valueOf = String.valueOf(getElement());
            int count = getCount();
            if (count == 1) {
                return valueOf;
            }
            return valueOf + " x " + count;
        }
    }

    static boolean a(aj<?> ajVar, Object obj) {
        if (obj == ajVar) {
            return true;
        }
        if (obj instanceof aj) {
            aj ajVar2 = (aj) obj;
            if (ajVar.size() == ajVar2.size() && ajVar.entrySet().size() == ajVar2.entrySet().size()) {
                for (aj.a aVar : ajVar2.entrySet()) {
                    if (ajVar.count(aVar.getElement()) != aVar.getCount()) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    static <E> boolean a(aj<E> ajVar, Collection<? extends E> collection) {
        m.a(ajVar);
        m.a(collection);
        if (collection instanceof aj) {
            return a((aj) ajVar, b(collection));
        }
        if (collection.isEmpty()) {
            return false;
        }
        return Iterators.a(ajVar, collection.iterator());
    }

    private static <E> boolean a(aj<E> ajVar, aj<? extends E> ajVar2) {
        if (ajVar2 instanceof AbstractMapBasedMultiset) {
            return a((aj) ajVar, (AbstractMapBasedMultiset) ajVar2);
        }
        if (ajVar2.isEmpty()) {
            return false;
        }
        for (aj.a<? extends E> aVar : ajVar2.entrySet()) {
            ajVar.add(aVar.getElement(), aVar.getCount());
        }
        return true;
    }

    private static <E> boolean a(aj<E> ajVar, AbstractMapBasedMultiset<? extends E> abstractMapBasedMultiset) {
        if (abstractMapBasedMultiset.isEmpty()) {
            return false;
        }
        abstractMapBasedMultiset.addTo(ajVar);
        return true;
    }

    static boolean b(aj<?> ajVar, Collection<?> collection) {
        if (collection instanceof aj) {
            collection = ((aj) collection).elementSet();
        }
        return ajVar.elementSet().removeAll(collection);
    }

    static boolean c(aj<?> ajVar, Collection<?> collection) {
        m.a(collection);
        if (collection instanceof aj) {
            collection = ((aj) collection).elementSet();
        }
        return ajVar.elementSet().retainAll(collection);
    }

    static <E> int a(aj<E> ajVar, E e, int i) {
        n.a(i, "count");
        int count = ajVar.count(e);
        int i2 = i - count;
        if (i2 > 0) {
            ajVar.add(e, i2);
        } else if (i2 < 0) {
            ajVar.remove(e, -i2);
        }
        return count;
    }

    static <E> boolean a(aj<E> ajVar, E e, int i, int i2) {
        n.a(i, "oldCount");
        n.a(i2, "newCount");
        if (ajVar.count(e) != i) {
            return false;
        }
        ajVar.setCount(e, i2);
        return true;
    }

    /* renamed from: com.google.common.collect.Multisets$1  reason: invalid class name */
    static class AnonymousClass1 extends bc<aj.a<E>, E> {
        AnonymousClass1(Iterator it2) {
            super(it2);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.bc
        public /* bridge */ /* synthetic */ Object a(Object obj) {
            return a((aj.a<Object>) ((aj.a) obj));
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [E, java.lang.Object] */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public E a(com.google.common.collect.aj.a<E> r1) {
            /*
                r0 = this;
                java.lang.Object r1 = r1.getElement()
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.Multisets.AnonymousClass1.a(com.google.common.collect.aj$a):java.lang.Object");
        }
    }

    static <E> Iterator<E> a(Iterator<aj.a<E>> it2) {
        return new AnonymousClass1(it2);
    }

    static abstract class b<E> extends Sets.b<E> {
        /* access modifiers changed from: package-private */
        public abstract aj<E> a();

        b() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            a().clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return a().contains(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean containsAll(Collection<?> collection) {
            return a().containsAll(collection);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return a().isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return a().remove(obj, Integer.MAX_VALUE) > 0;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return a().entrySet().size();
        }
    }

    static abstract class c<E> extends Sets.b<aj.a<E>> {
        /* access modifiers changed from: package-private */
        public abstract aj<E> a();

        c() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (!(obj instanceof aj.a)) {
                return false;
            }
            aj.a aVar = (aj.a) obj;
            if (aVar.getCount() > 0 && a().count(aVar.getElement()) == aVar.getCount()) {
                return true;
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if (obj instanceof aj.a) {
                aj.a aVar = (aj.a) obj;
                Object element = aVar.getElement();
                int count = aVar.getCount();
                if (count != 0) {
                    return a().setCount(element, count, 0);
                }
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            a().clear();
        }
    }

    static <E> Iterator<E> b(aj<E> ajVar) {
        return new d(ajVar, ajVar.entrySet().iterator());
    }

    static final class d<E> implements Iterator<E> {
        private final aj<E> a;
        private final Iterator<aj.a<E>> b;
        private aj.a<E> c;
        private int d;
        private int e;
        private boolean f;

        d(aj<E> ajVar, Iterator<aj.a<E>> it2) {
            this.a = ajVar;
            this.b = it2;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.d > 0 || this.b.hasNext();
        }

        @Override // java.util.Iterator
        public E next() {
            if (hasNext()) {
                if (this.d == 0) {
                    this.c = this.b.next();
                    int count = this.c.getCount();
                    this.d = count;
                    this.e = count;
                }
                this.d--;
                this.f = true;
                return (E) this.c.getElement();
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            n.a(this.f);
            if (this.e == 1) {
                this.b.remove();
            } else {
                this.a.remove(this.c.getElement());
            }
            this.e--;
            this.f = false;
        }
    }

    static int c(aj<?> ajVar) {
        long j = 0;
        for (aj.a<?> aVar : ajVar.entrySet()) {
            j += (long) aVar.getCount();
        }
        return Ints.b(j);
    }

    static <T> aj<T> b(Iterable<T> iterable) {
        return (aj) iterable;
    }
}
