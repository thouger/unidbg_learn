package io.reactivex.internal.observers;

import io.reactivex.c.a;
import io.reactivex.c.g;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.v;

/* compiled from: DisposableLambdaObserver */
public final class c<T> implements b, v<T> {
    final v<? super T> a;
    final g<? super b> b;
    final a c;
    b d;

    public c(v<? super T> vVar, g<? super b> gVar, a aVar) {
        this.a = vVar;
        this.b = gVar;
        this.c = aVar;
    }

    @Override // io.reactivex.v
    public void onSubscribe(b bVar) {
        try {
            this.b.accept(bVar);
            if (DisposableHelper.validate(this.d, bVar)) {
                this.d = bVar;
                this.a.onSubscribe(this);
            }
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            bVar.dispose();
            this.d = DisposableHelper.DISPOSED;
            EmptyDisposable.error(th, this.a);
        }
    }

    @Override // io.reactivex.v
    public void onNext(T t) {
        this.a.onNext(t);
    }

    @Override // io.reactivex.v
    public void onError(Throwable th) {
        if (this.d != DisposableHelper.DISPOSED) {
            this.d = DisposableHelper.DISPOSED;
            this.a.onError(th);
            return;
        }
        io.reactivex.e.a.a(th);
    }

    @Override // io.reactivex.v
    public void onComplete() {
        if (this.d != DisposableHelper.DISPOSED) {
            this.d = DisposableHelper.DISPOSED;
            this.a.onComplete();
        }
    }

    @Override // io.reactivex.disposables.b
    public void dispose() {
        b bVar = this.d;
        if (bVar != DisposableHelper.DISPOSED) {
            this.d = DisposableHelper.DISPOSED;
            try {
                this.c.a();
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                io.reactivex.e.a.a(th);
            }
            bVar.dispose();
        }
    }

    @Override // io.reactivex.disposables.b
    public boolean isDisposed() {
        return this.d.isDisposed();
    }
}
