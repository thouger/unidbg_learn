package io.reactivex.internal.observers;

import io.reactivex.c;
import io.reactivex.disposables.b;
import io.reactivex.e.a;
import io.reactivex.exceptions.OnErrorNotImplementedException;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;

public final class EmptyCompletableObserver extends AtomicReference<b> implements c, b {
    private static final long serialVersionUID = -7545121636549663526L;

    public boolean hasCustomOnError() {
        return false;
    }

    @Override // io.reactivex.disposables.b
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    @Override // io.reactivex.disposables.b
    public boolean isDisposed() {
        return get() == DisposableHelper.DISPOSED;
    }

    @Override // io.reactivex.c
    public void onComplete() {
        lazySet(DisposableHelper.DISPOSED);
    }

    @Override // io.reactivex.c
    public void onError(Throwable th) {
        lazySet(DisposableHelper.DISPOSED);
        a.a(new OnErrorNotImplementedException(th));
    }

    @Override // io.reactivex.c
    public void onSubscribe(b bVar) {
        DisposableHelper.setOnce(this, bVar);
    }
}
