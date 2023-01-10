package io.reactivex.internal.operators.maybe;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.observers.DeferredScalarDisposable;
import io.reactivex.m;
import io.reactivex.o;
import io.reactivex.q;
import io.reactivex.v;

public final class MaybeToObservable<T> extends q<T> {
    final o<T> a;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        this.a.a(c((v) vVar));
    }

    public static <T> m<T> c(v<? super T> vVar) {
        return new MaybeToObservableObserver(vVar);
    }

    /* access modifiers changed from: package-private */
    public static final class MaybeToObservableObserver<T> extends DeferredScalarDisposable<T> implements m<T> {
        private static final long serialVersionUID = 7603343402964826922L;
        b upstream;

        MaybeToObservableObserver(v<? super T> vVar) {
            super(vVar);
        }

        @Override // io.reactivex.m
        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.m
        public void onSuccess(T t) {
            complete(t);
        }

        @Override // io.reactivex.m
        public void onError(Throwable th) {
            error(th);
        }

        @Override // io.reactivex.m
        public void onComplete() {
            complete();
        }

        @Override // io.reactivex.internal.observers.DeferredScalarDisposable, io.reactivex.disposables.b
        public void dispose() {
            super.dispose();
            this.upstream.dispose();
        }
    }
}
