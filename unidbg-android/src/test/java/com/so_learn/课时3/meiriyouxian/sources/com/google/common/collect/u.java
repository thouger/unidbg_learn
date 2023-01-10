package com.google.common.collect;

import java.util.Iterator;

/* compiled from: ForwardingIterator */
public abstract class u<T> extends z implements Iterator<T> {
    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract Iterator<T> delegate();

    protected u() {
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return delegate().hasNext();
    }

    @Override // java.util.Iterator
    public T next() {
        return delegate().next();
    }

    @Override // java.util.Iterator
    public void remove() {
        delegate().remove();
    }
}
