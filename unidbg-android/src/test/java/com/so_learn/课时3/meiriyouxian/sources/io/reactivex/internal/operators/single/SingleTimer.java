package io.reactivex.internal.operators.single;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.w;
import io.reactivex.x;
import io.reactivex.z;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleTimer extends x<Long> {
    final long a;
    final TimeUnit b;
    final w c;

    public SingleTimer(long j, TimeUnit timeUnit, w wVar) {
        this.a = j;
        this.b = timeUnit;
        this.c = wVar;
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.x
    public void b(z<? super Long> zVar) {
        TimerDisposable timerDisposable = new TimerDisposable(zVar);
        zVar.onSubscribe(timerDisposable);
        timerDisposable.setFuture(this.c.a(timerDisposable, this.a, this.b));
    }

    static final class TimerDisposable extends AtomicReference<b> implements b, Runnable {
        private static final long serialVersionUID = 8465401857522493082L;
        final z<? super Long> downstream;

        TimerDisposable(z<? super Long> zVar) {
            this.downstream = zVar;
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
