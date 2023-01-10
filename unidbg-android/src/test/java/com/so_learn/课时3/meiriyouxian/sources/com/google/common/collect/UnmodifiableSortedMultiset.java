package com.google.common.collect;

import com.google.common.collect.Multisets;
import com.google.common.collect.aj;
import java.util.Comparator;
import java.util.NavigableSet;

/* access modifiers changed from: package-private */
public final class UnmodifiableSortedMultiset<E> extends Multisets.UnmodifiableMultiset<E> implements aw<E> {
    private static final long serialVersionUID = 0;
    private transient UnmodifiableSortedMultiset<E> descendingMultiset;

    UnmodifiableSortedMultiset(aw<E> awVar) {
        super(awVar);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.Multisets.UnmodifiableMultiset, com.google.common.collect.y, com.google.common.collect.s, com.google.common.collect.z
    public aw<E> delegate() {
        return (aw) super.delegate();
    }

    @Override // com.google.common.collect.aw, com.google.common.collect.au
    public Comparator<? super E> comparator() {
        return delegate().comparator();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.Multisets.UnmodifiableMultiset
    public NavigableSet<E> createElementSet() {
        return Sets.a((NavigableSet) delegate().elementSet());
    }

    @Override // com.google.common.collect.Multisets.UnmodifiableMultiset, com.google.common.collect.y, com.google.common.collect.aj
    public NavigableSet<E> elementSet() {
        return (NavigableSet) super.elementSet();
    }

    @Override // com.google.common.collect.aw
    public aw<E> descendingMultiset() {
        UnmodifiableSortedMultiset<E> unmodifiableSortedMultiset = this.descendingMultiset;
        if (unmodifiableSortedMultiset != null) {
            return unmodifiableSortedMultiset;
        }
        UnmodifiableSortedMultiset<E> unmodifiableSortedMultiset2 = new UnmodifiableSortedMultiset<>(delegate().descendingMultiset());
        unmodifiableSortedMultiset2.descendingMultiset = this;
        this.descendingMultiset = unmodifiableSortedMultiset2;
        return unmodifiableSortedMultiset2;
    }

    @Override // com.google.common.collect.aw
    public aj.a<E> firstEntry() {
        return delegate().firstEntry();
    }

    @Override // com.google.common.collect.aw
    public aj.a<E> lastEntry() {
        return delegate().lastEntry();
    }

    @Override // com.google.common.collect.aw
    public aj.a<E> pollFirstEntry() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.aw
    public aj.a<E> pollLastEntry() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.aw
    public aw<E> headMultiset(E e, BoundType boundType) {
        return Multisets.a((aw) delegate().headMultiset(e, boundType));
    }

    @Override // com.google.common.collect.aw
    public aw<E> subMultiset(E e, BoundType boundType, E e2, BoundType boundType2) {
        return Multisets.a((aw) delegate().subMultiset(e, boundType, e2, boundType2));
    }

    @Override // com.google.common.collect.aw
    public aw<E> tailMultiset(E e, BoundType boundType) {
        return Multisets.a((aw) delegate().tailMultiset(e, boundType));
    }
}
