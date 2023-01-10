package io.reactivex.disposables;

import io.reactivex.internal.functions.a;
import java.util.concurrent.atomic.AtomicReference;

/* access modifiers changed from: package-private */
public abstract class ReferenceDisposable<T> extends AtomicReference<T> implements b {
    private static final long serialVersionUID = 6537757548749041217L;

    /* access modifiers changed from: protected */
    public abstract void onDisposed(T t);

    ReferenceDisposable(T t) {
        super(a.a((Object) t, "value is null"));
    }

    @Override // io.reactivex.disposables.b
    public final void dispose() {
        T andSet;
        if (get() != null && (andSet = getAndSet(null)) != null) {
            onDisposed(andSet);
        }
    }

    @Override // io.reactivex.disposables.b
    public final boolean isDisposed() {
        return get() == null;
    }
}
