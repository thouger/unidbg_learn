package io.reactivex.internal.observers;

import io.reactivex.c;
import io.reactivex.c.a;
import io.reactivex.c.g;
import io.reactivex.disposables.b;
import io.reactivex.exceptions.OnErrorNotImplementedException;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;

public final class CallbackCompletableObserver extends AtomicReference<b> implements c, g<Throwable>, b {
    private static final long serialVersionUID = -4361286194466301354L;
    final a onComplete;
    final g<? super Throwable> onError;

    public CallbackCompletableObserver(a aVar) {
        this.onError = this;
        this.onComplete = aVar;
    }

    public CallbackCompletableObserver(g<? super Throwable> gVar, a aVar) {
        this.onError = gVar;
        this.onComplete = aVar;
    }

    public void accept(Throwable th) {
        io.reactivex.e.a.a(new OnErrorNotImplementedException(th));
    }

    @Override // io.reactivex.c
    public void onComplete() {
        try {
            this.onComplete.a();
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            io.reactivex.e.a.a(th);
        }
        lazySet(DisposableHelper.DISPOSED);
    }

    @Override // io.reactivex.c
    public void onError(Throwable th) {
        try {
            this.onError.accept(th);
        } catch (Throwable th2) {
            io.reactivex.exceptions.a.b(th2);
            io.reactivex.e.a.a(th2);
        }
        lazySet(DisposableHelper.DISPOSED);
    }

    @Override // io.reactivex.c
    public void onSubscribe(b bVar) {
        DisposableHelper.setOnce(this, bVar);
    }

    @Override // io.reactivex.disposables.b
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    @Override // io.reactivex.disposables.b
    public boolean isDisposed() {
        return get() == DisposableHelper.DISPOSED;
    }

    public boolean hasCustomOnError() {
        return this.onError != this;
    }
}
