package io.reactivex.internal.disposables;

import io.reactivex.c.f;
import io.reactivex.disposables.b;
import io.reactivex.exceptions.a;
import java.util.concurrent.atomic.AtomicReference;

public final class CancellableDisposable extends AtomicReference<f> implements b {
    private static final long serialVersionUID = 5718521705281392066L;

    public CancellableDisposable(f fVar) {
        super(fVar);
    }

    @Override // io.reactivex.disposables.b
    public boolean isDisposed() {
        return get() == null;
    }

    @Override // io.reactivex.disposables.b
    public void dispose() {
        f andSet;
        if (get() != null && (andSet = getAndSet(null)) != null) {
            try {
                andSet.a();
            } catch (Exception e) {
                a.b(e);
                io.reactivex.e.a.a(e);
            }
        }
    }
}
