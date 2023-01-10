package io.reactivex.internal.observers;

import io.reactivex.internal.a.c;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class BasicIntQueueDisposable<T> extends AtomicInteger implements c<T> {
    private static final long serialVersionUID = -1001730202384742097L;

    @Override // io.reactivex.internal.a.h
    public final boolean offer(T t) {
        throw new UnsupportedOperationException("Should not be called");
    }

    public final boolean offer(T t, T t2) {
        throw new UnsupportedOperationException("Should not be called");
    }
}
