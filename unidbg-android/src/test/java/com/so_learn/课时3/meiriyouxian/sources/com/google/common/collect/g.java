package com.google.common.collect;

import java.util.NoSuchElementException;

/* compiled from: AbstractSequentialIterator */
public abstract class g<T> extends bf<T> {
    private T a;

    /* access modifiers changed from: protected */
    public abstract T a(T t);

    protected g(T t) {
        this.a = t;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.a != null;
    }

    @Override // java.util.Iterator
    public final T next() {
        if (hasNext()) {
            try {
                return this.a;
            } finally {
                this.a = (T) a(this.a);
            }
        } else {
            throw new NoSuchElementException();
        }
    }
}
