package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.v;
import java.util.concurrent.atomic.AtomicReference;

public final class ObserverResourceWrapper<T> extends AtomicReference<b> implements b, v<T> {
    private static final long serialVersionUID = -8612022020200669122L;
    final v<? super T> downstream;
    final AtomicReference<b> upstream = new AtomicReference<>();

    public ObserverResourceWrapper(v<? super T> vVar) {
        this.downstream = vVar;
    }

    @Override // io.reactivex.v
    public void onSubscribe(b bVar) {
        if (DisposableHelper.setOnce(this.upstream, bVar)) {
            this.downstream.onSubscribe(this);
        }
    }

    @Override // io.reactivex.v
    public void onNext(T t) {
        this.downstream.onNext(t);
    }

    @Override // io.reactivex.v
    public void onError(Throwable th) {
        dispose();
        this.downstream.onError(th);
    }

    @Override // io.reactivex.v
    public void onComplete() {
        dispose();
        this.downstream.onComplete();
    }

    @Override // io.reactivex.disposables.b
    public void dispose() {
        DisposableHelper.dispose(this.upstream);
        DisposableHelper.dispose(this);
    }

    @Override // io.reactivex.disposables.b
    public boolean isDisposed() {
        return this.upstream.get() == DisposableHelper.DISPOSED;
    }

    public void setResource(b bVar) {
        DisposableHelper.set(this, bVar);
    }
}
