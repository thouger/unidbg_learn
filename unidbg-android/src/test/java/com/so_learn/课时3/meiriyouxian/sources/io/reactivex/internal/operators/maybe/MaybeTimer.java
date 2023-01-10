package io.reactivex.internal.operators.maybe;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.k;
import io.reactivex.m;
import io.reactivex.w;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeTimer extends k<Long> {
    final long a;
    final TimeUnit b;
    final w c;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.k
    public void b(m<? super Long> mVar) {
        TimerDisposable timerDisposable = new TimerDisposable(mVar);
        mVar.onSubscribe(timerDisposable);
        timerDisposable.setFuture(this.c.a(timerDisposable, this.a, this.b));
    }

    static final class TimerDisposable extends AtomicReference<b> implements b, Runnable {
        private static final long serialVersionUID = 2875964065294031672L;
        final m<? super Long> downstream;

        TimerDisposable(m<? super Long> mVar) {
            this.downstream = mVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.downstream.onSuccess(0L);
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        /* access modifiers changed from: package-private */
        public void setFuture(b bVar) {
            DisposableHelper.replace(this, bVar);
        }
    }
}
