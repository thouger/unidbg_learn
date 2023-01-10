package com.google.common.collect;

import com.google.common.collect.aj;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.Set;

/* compiled from: SortedMultiset */
public interface aw<E> extends au<E>, ax<E> {
    @Override // com.google.common.collect.au
    Comparator<? super E> comparator();

    aw<E> descendingMultiset();

    @Override // com.google.common.collect.aj
    NavigableSet<E> elementSet();

    @Override // com.google.common.collect.aj
    Set<aj.a<E>> entrySet();

    aj.a<E> firstEntry();

    aw<E> headMultiset(E e, BoundType boundType);

    aj.a<E> lastEntry();

    aj.a<E> pollFirstEntry();

    aj.a<E> pollLastEntry();

    aw<E> subMultiset(E e, BoundType boundType, E e2, BoundType boundType2);

    aw<E> tailMultiset(E e, BoundType boundType);
}
