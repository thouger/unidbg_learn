package io.reactivex.internal.operators.single;

import io.reactivex.ab;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.observers.DeferredScalarDisposable;
import io.reactivex.q;
import io.reactivex.v;
import io.reactivex.z;

public final class SingleToObservable<T> extends q<T> {
    final ab<? extends T> a;

    public SingleToObservable(ab<? extends T> abVar) {
        this.a = abVar;
    }

    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        this.a.a(c((v) vVar));
    }

    public static <T> z<T> c(v<? super T> vVar) {
        return new SingleToObservableObserver(vVar);
    }

    /* access modifiers changed from: package-private */
    public static final class SingleToObservableObserver<T> extends DeferredScalarDisposable<T> implements z<T> {
        private static final long serialVersionUID = 3786543492451018833L;
        b upstream;

        SingleToObservableObserver(v<? super T> vVar) {
            super(vVar);
        }

        @Override // io.reactivex.z
        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.z
        public void onSuccess(T t) {
            complete(t);
        }

        @Override // io.reactivex.z
        public void onError(Throwable th) {
            error(th);
        }

        @Override // io.reactivex.internal.observers.DeferredScalarDisposable, io.reactivex.disposables.b
        public void dispose() {
            super.dispose();
            this.upstream.dispose();
        }
    }
}
