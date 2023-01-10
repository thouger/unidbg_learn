package com.google.common.collect;

import com.google.common.base.m;
import java.io.Serializable;
import java.util.Iterator;

final class ReverseNaturalOrdering extends Ordering<Comparable> implements Serializable {
    static final ReverseNaturalOrdering INSTANCE = new ReverseNaturalOrdering();
    private static final long serialVersionUID = 0;

    @Override // java.lang.Object
    public String toString() {
        return "Ordering.natural().reverse()";
    }

    public int compare(Comparable comparable, Comparable comparable2) {
        m.a(comparable);
        if (comparable == comparable2) {
            return 0;
        }
        return comparable2.compareTo(comparable);
    }

    @Override // com.google.common.collect.Ordering
    public <S extends Comparable> Ordering<S> reverse() {
        return Ordering.natural();
    }

    public <E extends Comparable> E min(E e, E e2) {
        return (E) ((Comparable) NaturalOrdering.INSTANCE.max(e, e2));
    }

    public <E extends Comparable> E min(E e, E e2, E e3, E... eArr) {
        return (E) ((Comparable) NaturalOrdering.INSTANCE.max(e, e2, e3, eArr));
    }

    @Override // com.google.common.collect.Ordering
    public <E extends Comparable> E min(Iterator<E> it2) {
        return (E) ((Comparable) NaturalOrdering.INSTANCE.max(it2));
    }

    @Override // com.google.common.collect.Ordering
    public <E extends Comparable> E min(Iterable<E> iterable) {
        return (E) ((Comparable) NaturalOrdering.INSTANCE.max(iterable));
    }

    public <E extends Comparable> E max(E e, E e2) {
        return (E) ((Comparable) NaturalOrdering.INSTANCE.min(e, e2));
    }

    public <E extends Comparable> E max(E e, E e2, E e3, E... eArr) {
        return (E) ((Comparable) NaturalOrdering.INSTANCE.min(e, e2, e3, eArr));
    }

    @Override // com.google.common.collect.Ordering
    public <E extends Comparable> E max(Iterator<E> it2) {
        return (E) ((Comparable) NaturalOrdering.INSTANCE.min(it2));
    }

    @Override // com.google.common.collect.Ordering
    public <E extends Comparable> E max(Iterable<E> iterable) {
        return (E) ((Comparable) NaturalOrdering.INSTANCE.min(iterable));
    }

    private Object readResolve() {
        return INSTANCE;
    }

    private ReverseNaturalOrdering() {
    }
}
