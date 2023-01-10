package com.google.common.collect;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* compiled from: Multiset */
public interface aj<E> extends Collection<E> {

    /* compiled from: Multiset */
    public interface a<E> {
        int getCount();

        E getElement();

        String toString();
    }

    int add(E e, int i);

    @Override // java.util.Collection, com.google.common.collect.aj
    boolean contains(Object obj);

    @Override // java.util.Collection
    boolean containsAll(Collection<?> collection);

    int count(Object obj);

    Set<E> elementSet();

    Set<a<E>> entrySet();

    @Override // java.lang.Object, com.google.common.collect.aj
    boolean equals(Object obj);

    @Override // java.lang.Object, com.google.common.collect.aj
    int hashCode();

    @Override // java.util.Collection, java.lang.Iterable, com.google.common.collect.aj
    Iterator<E> iterator();

    int remove(Object obj, int i);

    @Override // java.util.Collection
    boolean remove(Object obj);

    int setCount(E e, int i);

    boolean setCount(E e, int i, int i2);

    @Override // java.util.Collection, com.google.common.collect.aj
    int size();
}
