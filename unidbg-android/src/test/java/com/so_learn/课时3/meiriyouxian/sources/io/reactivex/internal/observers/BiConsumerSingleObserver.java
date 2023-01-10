package io.reactivex.internal.observers;

import io.reactivex.disposables.b;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.a;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicReference;

public final class BiConsumerSingleObserver<T> extends AtomicReference<b> implements b, z<T> {
    private static final long serialVersionUID = 4943102778943297569L;
    final io.reactivex.c.b<? super T, ? super Throwable> onCallback;

    public BiConsumerSingleObserver(io.reactivex.c.b<? super T, ? super Throwable> bVar) {
        this.onCallback = bVar;
    }

    @Override // io.reactivex.z
    public void onError(Throwable th) {
        try {
            lazySet(DisposableHelper.DISPOSED);
            this.onCallback.a(null, th);
        } catch (Throwable th2) {
            a.b(th2);
            io.reactivex.e.a.a(new CompositeException(th, th2));
        }
    }

    @Override // io.reactivex.z
    public void onSubscribe(b bVar) {
        DisposableHelper.setOnce(this, bVar);
    }

    @Override // io.reactivex.z
    public void onSuccess(T t) {
        try {
            lazySet(DisposableHelper.DISPOSED);
            this.onCallback.a(t, null);
        } catch (Throwable th) {
            a.b(th);
            io.reactivex.e.a.a(th);
        }
    }

    @Override // io.reactivex.disposables.b
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    @Override // io.reactivex.disposables.b
    public boolean isDisposed() {
        return get() == DisposableHelper.DISPOSED;
    }
}
