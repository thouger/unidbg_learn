package io.reactivex.internal.observers;

import io.reactivex.c.a;
import io.reactivex.c.g;
import io.reactivex.c.k;
import io.reactivex.disposables.b;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.v;
import java.util.concurrent.atomic.AtomicReference;

public final class ForEachWhileObserver<T> extends AtomicReference<b> implements b, v<T> {
    private static final long serialVersionUID = -4403180040475402120L;
    boolean done;
    final a onComplete;
    final g<? super Throwable> onError;
    final k<? super T> onNext;

    public ForEachWhileObserver(k<? super T> kVar, g<? super Throwable> gVar, a aVar) {
        this.onNext = kVar;
        this.onError = gVar;
        this.onComplete = aVar;
    }

    @Override // io.reactivex.v
    public void onSubscribe(b bVar) {
        DisposableHelper.setOnce(this, bVar);
    }

    @Override // io.reactivex.v
    public void onNext(T t) {
        if (!this.done) {
            try {
                if (!this.onNext.test(t)) {
                    dispose();
                    onComplete();
                }
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                dispose();
                onError(th);
            }
        }
    }

    @Override // io.reactivex.v
    public void onError(Throwable th) {
        if (this.done) {
            io.reactivex.e.a.a(th);
            return;
        }
        this.done = true;
        try {
            this.onError.accept(th);
        } catch (Throwable th2) {
            io.reactivex.exceptions.a.b(th2);
            io.reactivex.e.a.a(new CompositeException(th, th2));
        }
    }

    @Override // io.reactivex.v
    public void onComplete() {
        if (!this.done) {
            this.done = true;
            try {
                this.onComplete.a();
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                io.reactivex.e.a.a(th);
            }
        }
    }

    @Override // io.reactivex.disposables.b
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    @Override // io.reactivex.disposables.b
    public boolean isDisposed() {
        return DisposableHelper.isDisposed(get());
    }
}
