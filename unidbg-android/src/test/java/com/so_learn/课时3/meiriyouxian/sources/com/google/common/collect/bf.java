package com.google.common.collect;

import java.util.Iterator;

/* compiled from: UnmodifiableIterator */
public abstract class bf<E> implements Iterator<E> {
    protected bf() {
    }

    @Override // java.util.Iterator
    @Deprecated
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
