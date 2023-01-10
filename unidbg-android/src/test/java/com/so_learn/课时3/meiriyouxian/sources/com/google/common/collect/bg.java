package com.google.common.collect;

import java.util.ListIterator;

/* compiled from: UnmodifiableListIterator */
public abstract class bg<E> extends bf<E> implements ListIterator<E> {
    protected bg() {
    }

    @Override // java.util.ListIterator
    @Deprecated
    public final void add(E e) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator
    @Deprecated
    public final void set(E e) {
        throw new UnsupportedOperationException();
    }
}
