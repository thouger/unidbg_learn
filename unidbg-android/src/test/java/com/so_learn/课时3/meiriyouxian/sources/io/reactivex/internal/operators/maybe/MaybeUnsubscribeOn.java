package io.reactivex.internal.operators.maybe;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.m;
import io.reactivex.w;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeUnsubscribeOn<T> extends a<T, T> {
    final w b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.k
    public void b(m<? super T> mVar) {
        this.a.a(new UnsubscribeOnMaybeObserver(mVar, this.b));
    }

    static final class UnsubscribeOnMaybeObserver<T> extends AtomicReference<b> implements b, m<T>, Runnable {
        private static final long serialVersionUID = 3256698449646456986L;
        final m<? super T> downstream;
        b ds;
        final w scheduler;

        UnsubscribeOnMaybeObserver(m<? super T> mVar, w wVar) {
            this.downstream = mVar;
            this.scheduler = wVar;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            b andSet = getAndSet(DisposableHelper.DISPOSED);
            if (andSet != DisposableHelper.DISPOSED) {
                this.ds = andSet;
                this.scheduler.a(this);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            this.ds.dispose();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // io.reactivex.m
        public void onSubscribe(b bVar) {
            if (DisposableHelper.setOnce(this, bVar)) {
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.m
        public void onSuccess(T t) {
            this.downstream.onSuccess(t);
        }

        @Override // io.reactivex.m
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // io.reactivex.m
        public void onComplete() {
            this.downstream.onComplete();
        }
    }
}
