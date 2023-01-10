package io.reactivex.internal.observers;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.v;

public abstract class DeferredScalarObserver<T, R> extends DeferredScalarDisposable<R> implements v<T> {
    private static final long serialVersionUID = -266195175408988651L;
    protected b upstream;

    public DeferredScalarObserver(v<? super R> vVar) {
        super(vVar);
    }

    @Override // io.reactivex.v
    public void onSubscribe(b bVar) {
        if (DisposableHelper.validate(this.upstream, bVar)) {
            this.upstream = bVar;
            this.downstream.onSubscribe(this);
        }
    }

    @Override // io.reactivex.v
    public void onError(Throwable th) {
        this.value = null;
        error(th);
    }

    @Override // io.reactivex.v
    public void onComplete() {
        Object obj = this.value;
        if (obj != null) {
            this.value = null;
            complete(obj);
            return;
        }
        complete();
    }

    @Override // io.reactivex.internal.observers.DeferredScalarDisposable, io.reactivex.disposables.b
    public void dispose() {
        super.dispose();
        this.upstream.dispose();
    }
}
