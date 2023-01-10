package io.reactivex.internal.observers;

import io.reactivex.internal.a.c;

/* compiled from: BasicQueueDisposable */
public abstract class b<T> implements c<T> {
    @Override // io.reactivex.internal.a.h
    public final boolean offer(T t) {
        throw new UnsupportedOperationException("Should not be called");
    }
}
