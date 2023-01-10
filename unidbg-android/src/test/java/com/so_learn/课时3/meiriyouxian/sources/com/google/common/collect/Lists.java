package com.google.common.collect;

import com.google.common.base.g;
import com.google.common.base.j;
import com.google.common.base.m;
import com.google.common.math.c;
import com.google.common.primitives.Ints;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public final class Lists {
    public static <E> ArrayList<E> a() {
        return new ArrayList<>();
    }

    public static <E> ArrayList<E> a(Iterable<? extends E> iterable) {
        m.a(iterable);
        if (iterable instanceof Collection) {
            return new ArrayList<>(o.a(iterable));
        }
        return a(iterable.iterator());
    }

    public static <E> ArrayList<E> a(Iterator<? extends E> it2) {
        ArrayList<E> a = a();
        Iterators.a(a, it2);
        return a;
    }

    static int a(int i) {
        n.a(i, "arraySize");
        return Ints.b(((long) i) + 5 + ((long) (i / 10)));
    }

    public static <E> ArrayList<E> b(int i) {
        n.a(i, "initialArraySize");
        return new ArrayList<>(i);
    }

    public static <E> ArrayList<E> c(int i) {
        return new ArrayList<>(a(i));
    }

    public static <E> List<E> a(E e, E[] eArr) {
        return new OnePlusArrayList(e, eArr);
    }

    /* access modifiers changed from: private */
    public static class OnePlusArrayList<E> extends AbstractList<E> implements Serializable, RandomAccess {
        private static final long serialVersionUID = 0;
        final E first;
        final E[] rest;

        OnePlusArrayList(E e, E[] eArr) {
            this.first = e;
            this.rest = (E[]) ((Object[]) m.a(eArr));
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public int size() {
            return c.c(this.rest.length, 1);
        }

        @Override // java.util.AbstractList, java.util.List
        public E get(int i) {
            m.a(i, size());
            return i == 0 ? this.first : this.rest[i - 1];
        }
    }

    private static class TwoPlusArrayList<E> extends AbstractList<E> implements Serializable, RandomAccess {
        private static final long serialVersionUID = 0;
        final E first;
        final E[] rest;
        final E second;

        TwoPlusArrayList(E e, E e2, E[] eArr) {
            this.first = e;
            this.second = e2;
            this.rest = (E[]) ((Object[]) m.a(eArr));
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public int size() {
            return c.c(this.rest.length, 2);
        }

        @Override // java.util.AbstractList, java.util.List
        public E get(int i) {
            if (i == 0) {
                return this.first;
            }
            if (i == 1) {
                return this.second;
            }
            m.a(i, size());
            return this.rest[i - 2];
        }
    }

    public static <F, T> List<T> a(List<F> list, g<? super F, ? extends T> gVar) {
        return list instanceof RandomAccess ? new TransformingRandomAccessList(list, gVar) : new TransformingSequentialList(list, gVar);
    }

    private static class TransformingSequentialList<F, T> extends AbstractSequentialList<T> implements Serializable {
        private static final long serialVersionUID = 0;
        final List<F> fromList;
        final g<? super F, ? extends T> function;

        TransformingSequentialList(List<F> list, g<? super F, ? extends T> gVar) {
            this.fromList = (List) m.a(list);
            this.function = (g) m.a(gVar);
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList
        public void clear() {
            this.fromList.clear();
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public int size() {
            return this.fromList.size();
        }

        /* renamed from: com.google.common.collect.Lists$TransformingSequentialList$1  reason: invalid class name */
        class AnonymousClass1 extends bd<F, T> {
            AnonymousClass1(ListIterator listIterator) {
                super(listIterator);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.bc
            public T a(F f) {
                return (T) TransformingSequentialList.this.function.apply(f);
            }
        }

        @Override // java.util.AbstractSequentialList, java.util.List, java.util.AbstractList
        public ListIterator<T> listIterator(int i) {
            return new AnonymousClass1(this.fromList.listIterator(i));
        }
    }

    private static class TransformingRandomAccessList<F, T> extends AbstractList<T> implements Serializable, RandomAccess {
        private static final long serialVersionUID = 0;
        final List<F> fromList;
        final g<? super F, ? extends T> function;

        TransformingRandomAccessList(List<F> list, g<? super F, ? extends T> gVar) {
            this.fromList = (List) m.a(list);
            this.function = (g) m.a(gVar);
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.List, java.util.Collection
        public void clear() {
            this.fromList.clear();
        }

        @Override // java.util.AbstractList, java.util.List
        public T get(int i) {
            return (T) this.function.apply(this.fromList.get(i));
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.List, java.util.Collection, java.lang.Iterable
        public Iterator<T> iterator() {
            return listIterator();
        }

        /* renamed from: com.google.common.collect.Lists$TransformingRandomAccessList$1  reason: invalid class name */
        class AnonymousClass1 extends bd<F, T> {
            AnonymousClass1(ListIterator listIterator) {
                super(listIterator);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.bc
            public T a(F f) {
                return (T) TransformingRandomAccessList.this.function.apply(f);
            }
        }

        @Override // java.util.AbstractList, java.util.List
        public ListIterator<T> listIterator(int i) {
            return new AnonymousClass1(this.fromList.listIterator(i));
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public boolean isEmpty() {
            return this.fromList.isEmpty();
        }

        @Override // java.util.AbstractList, java.util.List
        public T remove(int i) {
            return (T) this.function.apply(this.fromList.remove(i));
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public int size() {
            return this.fromList.size();
        }
    }

    public static ImmutableList<Character> a(String str) {
        return new StringAsImmutableList((String) m.a(str));
    }

    /* access modifiers changed from: private */
    public static final class StringAsImmutableList extends ImmutableList<Character> {
        private final String string;

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return false;
        }

        StringAsImmutableList(String str) {
            this.string = str;
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public int indexOf(Object obj) {
            if (obj instanceof Character) {
                return this.string.indexOf(((Character) obj).charValue());
            }
            return -1;
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public int lastIndexOf(Object obj) {
            if (obj instanceof Character) {
                return this.string.lastIndexOf(((Character) obj).charValue());
            }
            return -1;
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public ImmutableList<Character> subList(int i, int i2) {
            m.a(i, i2, size());
            return Lists.a(this.string.substring(i, i2));
        }

        @Override // java.util.List
        public Character get(int i) {
            m.a(i, size());
            return Character.valueOf(this.string.charAt(i));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.string.length();
        }
    }

    static boolean a(List<?> list, Object obj) {
        if (obj == m.a(list)) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        List list2 = (List) obj;
        int size = list.size();
        if (size != list2.size()) {
            return false;
        }
        if (!(list instanceof RandomAccess) || !(list2 instanceof RandomAccess)) {
            return Iterators.a(list.iterator(), (Iterator<?>) list2.iterator());
        }
        for (int i = 0; i < size; i++) {
            if (!j.a(list.get(i), list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    static int b(List<?> list, Object obj) {
        if (list instanceof RandomAccess) {
            return d(list, obj);
        }
        ListIterator<?> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            if (j.a(obj, listIterator.next())) {
                return listIterator.previousIndex();
            }
        }
        return -1;
    }

    private static int d(List<?> list, Object obj) {
        int size = list.size();
        int i = 0;
        if (obj == null) {
            while (i < size) {
                if (list.get(i) == null) {
                    return i;
                }
                i++;
            }
            return -1;
        }
        while (i < size) {
            if (obj.equals(list.get(i))) {
                return i;
            }
            i++;
        }
        return -1;
    }

    static int c(List<?> list, Object obj) {
        if (list instanceof RandomAccess) {
            return e(list, obj);
        }
        ListIterator<?> listIterator = list.listIterator(list.size());
        while (listIterator.hasPrevious()) {
            if (j.a(obj, listIterator.previous())) {
                return listIterator.nextIndex();
            }
        }
        return -1;
    }

    private static int e(List<?> list, Object obj) {
        if (obj == null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                if (list.get(size) == null) {
                    return size;
                }
            }
            return -1;
        }
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            if (obj.equals(list.get(size2))) {
                return size2;
            }
        }
        return -1;
    }
}
