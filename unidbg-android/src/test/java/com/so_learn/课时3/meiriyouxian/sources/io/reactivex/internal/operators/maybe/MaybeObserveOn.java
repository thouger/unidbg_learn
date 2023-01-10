package io.reactivex.internal.operators.maybe;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.m;
import io.reactivex.w;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeObserveOn<T> extends a<T, T> {
    final w b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.k
    public void b(m<? super T> mVar) {
        this.a.a(new ObserveOnMaybeObserver(mVar, this.b));
    }

    static final class ObserveOnMaybeObserver<T> extends AtomicReference<b> implements b, m<T>, Runnable {
        private static final long serialVersionUID = 8571289934935992137L;
        final m<? super T> downstream;
        Throwable error;
        final w scheduler;
        T value;

        ObserveOnMaybeObserver(m<? super T> mVar, w wVar) {
            this.downstream = mVar;
            this.scheduler = wVar;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            DisposableHelper.dispose(this);
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
            this.value = t;
            DisposableHelper.replace(this, this.scheduler.a(this));
        }

        @Override // io.reactivex.m
        public void onError(Throwable th) {
            this.error = th;
            DisposableHelper.replace(this, this.scheduler.a(this));
        }

        @Override // io.reactivex.m
        public void onComplete() {
            DisposableHelper.replace(this, this.scheduler.a(this));
        }

        @Override // java.lang.Runnable
        public void run() {
            Throwable th = this.error;
            if (th != null) {
                this.error = null;
                this.downstream.onError(th);
                return;
            }
            T t = this.value;
            if (t != null) {
                this.value = null;
                this.downstream.onSuccess(t);
                return;
            }
            this.downstream.onComplete();
        }
    }
}
