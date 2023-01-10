package io.reactivex.internal.subscriptions;

import io.reactivex.internal.a.e;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class BasicIntQueueSubscription<T> extends AtomicInteger implements e<T> {
    private static final long serialVersionUID = -6671519529404341862L;

    @Override // io.reactivex.internal.a.h
    public final boolean offer(T t) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    public final boolean offer(T t, T t2) {
        throw new UnsupportedOperationException("Should not be called!");
    }
}
