package io.reactivex.internal.operators.maybe;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.m;
import io.reactivex.w;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeDelay<T> extends a<T, T> {
    final long b;
    final TimeUnit c;
    final w d;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.k
    public void b(m<? super T> mVar) {
        this.a.a(new DelayMaybeObserver(mVar, this.b, this.c, this.d));
    }

    static final class DelayMaybeObserver<T> extends AtomicReference<b> implements b, m<T>, Runnable {
        private static final long serialVersionUID = 5566860102500855068L;
        final long delay;
        final m<? super T> downstream;
        Throwable error;
        final w scheduler;
        final TimeUnit unit;
        T value;

        DelayMaybeObserver(m<? super T> mVar, long j, TimeUnit timeUnit, w wVar) {
            this.downstream = mVar;
            this.delay = j;
            this.unit = timeUnit;
            this.scheduler = wVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            Throwable th = this.error;
            if (th != null) {
                this.downstream.onError(th);
                return;
            }
            T t = this.value;
            if (t != null) {
                this.downstream.onSuccess(t);
            } else {
                this.downstream.onComplete();
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

        @Override // io.reactivex.m
        public void onSubscribe(b bVar) {
            if (DisposableHelper.setOnce(this, bVar)) {
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.m
        public void onSuccess(T t) {
            this.value = t;
            schedule();
        }

        @Override // io.reactivex.m
        public void onError(Throwable th) {
            this.error = th;
            schedule();
        }

        @Override // io.reactivex.m
        public void onComplete() {
            schedule();
        }

        /* access modifiers changed from: package-private */
        public void schedule() {
            DisposableHelper.replace(this, this.scheduler.a(this, this.delay, this.unit));
        }
    }
}
