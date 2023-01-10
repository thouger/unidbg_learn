package com.google.common.collect;

import android.app.backup.FullBackup;
import com.google.common.base.g;
import com.google.common.base.m;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Ordering<T> implements Comparator<T> {
    static final int LEFT_IS_GREATER = 1;
    static final int RIGHT_IS_GREATER = -1;

    private static class b {
        static final Ordering<Object> a = new a();
    }

    @Override // java.util.Comparator
    public abstract int compare(T t, T t2);

    public static <C extends Comparable> Ordering<C> natural() {
        return NaturalOrdering.INSTANCE;
    }

    public static <T> Ordering<T> from(Comparator<T> comparator) {
        return comparator instanceof Ordering ? (Ordering) comparator : new ComparatorOrdering(comparator);
    }

    @Deprecated
    public static <T> Ordering<T> from(Ordering<T> ordering) {
        return (Ordering) m.a(ordering);
    }

    public static <T> Ordering<T> explicit(List<T> list) {
        return new ExplicitOrdering(list);
    }

    public static <T> Ordering<T> explicit(T t, T... tArr) {
        return explicit(Lists.a(t, tArr));
    }

    public static Ordering<Object> allEqual() {
        return AllEqualOrdering.INSTANCE;
    }

    public static Ordering<Object> usingToString() {
        return UsingToStringOrdering.INSTANCE;
    }

    public static Ordering<Object> arbitrary() {
        return b.a;
    }

    static class a extends Ordering<Object> {
        private final AtomicInteger a = new AtomicInteger(0);
        private final ConcurrentMap<Object, Integer> b = ao.a(new MapMaker()).g();

        @Override // java.lang.Object
        public String toString() {
            return "Ordering.arbitrary()";
        }

        a() {
        }

        private Integer b(Object obj) {
            Integer num = this.b.get(obj);
            if (num != null) {
                return num;
            }
            Integer valueOf = Integer.valueOf(this.a.getAndIncrement());
            Integer putIfAbsent = this.b.putIfAbsent(obj, valueOf);
            return putIfAbsent != null ? putIfAbsent : valueOf;
        }

        @Override // com.google.common.collect.Ordering, java.util.Comparator
        public int compare(Object obj, Object obj2) {
            if (obj == obj2) {
                return 0;
            }
            if (obj == null) {
                return -1;
            }
            if (obj2 == null) {
                return 1;
            }
            int a = a(obj);
            int a2 = a(obj2);
            if (a != a2) {
                return a < a2 ? -1 : 1;
            }
            int compareTo = b(obj).compareTo(b(obj2));
            if (compareTo != 0) {
                return compareTo;
            }
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        public int a(Object obj) {
            return System.identityHashCode(obj);
        }
    }

    protected Ordering() {
    }

    public <S extends T> Ordering<S> reverse() {
        return new ReverseOrdering(this);
    }

    public <S extends T> Ordering<S> nullsFirst() {
        return new NullsFirstOrdering(this);
    }

    public <S extends T> Ordering<S> nullsLast() {
        return new NullsLastOrdering(this);
    }

    public <F> Ordering<F> onResultOf(g<F, ? extends T> gVar) {
        return new ByFunctionOrdering(gVar, this);
    }

    /* access modifiers changed from: package-private */
    public <T2 extends T> Ordering<Map.Entry<T2, ?>> onKeys() {
        return onResultOf(Maps.a());
    }

    public <U extends T> Ordering<U> compound(Comparator<? super U> comparator) {
        return new CompoundOrdering(this, (Comparator) m.a(comparator));
    }

    public static <T> Ordering<T> compound(Iterable<? extends Comparator<? super T>> iterable) {
        return new CompoundOrdering(iterable);
    }

    public <S extends T> Ordering<Iterable<S>> lexicographical() {
        return new LexicographicalOrdering(this);
    }

    public <E extends T> E min(Iterator<E> it2) {
        E next = it2.next();
        while (it2.hasNext()) {
            next = (E) min(next, it2.next());
        }
        return next;
    }

    public <E extends T> E min(Iterable<E> iterable) {
        return (E) min(iterable.iterator());
    }

    public <E extends T> E min(E e, E e2) {
        return compare(e, e2) <= 0 ? e : e2;
    }

    public <E extends T> E min(E e, E e2, E e3, E... eArr) {
        E e4 = (E) min(min(e, e2), e3);
        for (E e5 : eArr) {
            e4 = (E) min(e4, e5);
        }
        return e4;
    }

    public <E extends T> E max(Iterator<E> it2) {
        E next = it2.next();
        while (it2.hasNext()) {
            next = (E) max(next, it2.next());
        }
        return next;
    }

    public <E extends T> E max(Iterable<E> iterable) {
        return (E) max(iterable.iterator());
    }

    public <E extends T> E max(E e, E e2) {
        return compare(e, e2) >= 0 ? e : e2;
    }

    public <E extends T> E max(E e, E e2, E e3, E... eArr) {
        E e4 = (E) max(max(e, e2), e3);
        for (E e5 : eArr) {
            e4 = (E) max(e4, e5);
        }
        return e4;
    }

    public <E extends T> List<E> leastOf(Iterable<E> iterable, int i) {
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            if (((long) collection.size()) <= ((long) i) * 2) {
                Object[] array = collection.toArray();
                Arrays.sort(array, this);
                if (array.length > i) {
                    array = Arrays.copyOf(array, i);
                }
                return Collections.unmodifiableList(Arrays.asList(array));
            }
        }
        return leastOf(iterable.iterator(), i);
    }

    public <E extends T> List<E> leastOf(Iterator<E> it2, int i) {
        m.a(it2);
        n.a(i, FullBackup.KEY_VALUE_DATA_TOKEN);
        if (i == 0 || !it2.hasNext()) {
            return Collections.emptyList();
        }
        if (i >= 1073741823) {
            ArrayList a2 = Lists.a(it2);
            Collections.sort(a2, this);
            if (a2.size() > i) {
                a2.subList(i, a2.size()).clear();
            }
            a2.trimToSize();
            return Collections.unmodifiableList(a2);
        }
        bb a3 = bb.a(i, this);
        a3.a((Iterator) it2);
        return a3.a();
    }

    public <E extends T> List<E> greatestOf(Iterable<E> iterable, int i) {
        return reverse().leastOf(iterable, i);
    }

    public <E extends T> List<E> greatestOf(Iterator<E> it2, int i) {
        return reverse().leastOf(it2, i);
    }

    public <E extends T> List<E> sortedCopy(Iterable<E> iterable) {
        Object[] c = ag.c(iterable);
        Arrays.sort(c, this);
        return Lists.a(Arrays.asList(c));
    }

    public <E extends T> ImmutableList<E> immutableSortedCopy(Iterable<E> iterable) {
        return ImmutableList.sortedCopyOf(this, iterable);
    }

    public boolean isOrdered(Iterable<? extends T> iterable) {
        Iterator<? extends T> it2 = iterable.iterator();
        if (!it2.hasNext()) {
            return true;
        }
        Object next = it2.next();
        while (it2.hasNext()) {
            Object next2 = it2.next();
            if (compare(next, next2) > 0) {
                return false;
            }
            next = next2;
        }
        return true;
    }

    public boolean isStrictlyOrdered(Iterable<? extends T> iterable) {
        Iterator<? extends T> it2 = iterable.iterator();
        if (!it2.hasNext()) {
            return true;
        }
        Object next = it2.next();
        while (it2.hasNext()) {
            Object next2 = it2.next();
            if (compare(next, next2) >= 0) {
                return false;
            }
            next = next2;
        }
        return true;
    }

    @Deprecated
    public int binarySearch(List<? extends T> list, T t) {
        return Collections.binarySearch(list, t, this);
    }

    static class IncomparableValueException extends ClassCastException {
        private static final long serialVersionUID = 0;
        final Object value;

        IncomparableValueException(Object obj) {
            super("Cannot compare value: " + obj);
            this.value = obj;
        }
    }
}
