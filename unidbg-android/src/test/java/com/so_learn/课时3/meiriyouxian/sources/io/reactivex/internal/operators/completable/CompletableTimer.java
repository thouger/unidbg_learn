package io.reactivex.internal.operators.completable;

import io.reactivex.a;
import io.reactivex.c;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.w;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public final class CompletableTimer extends a {
    final long a;
    final TimeUnit b;
    final w c;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.a
    public void b(c cVar) {
        TimerDisposable timerDisposable = new TimerDisposable(cVar);
        cVar.onSubscribe(timerDisposable);
        timerDisposable.setFuture(this.c.a(timerDisposable, this.a, this.b));
    }

    static final class TimerDisposable extends AtomicReference<b> implements b, Runnable {
        private static final long serialVersionUID = 3167244060586201109L;
        final c downstream;

        TimerDisposable(c cVar) {
            this.downstream = cVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.downstream.onComplete();
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
