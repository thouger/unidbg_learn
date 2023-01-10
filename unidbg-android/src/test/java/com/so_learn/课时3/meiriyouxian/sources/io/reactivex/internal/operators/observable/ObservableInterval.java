package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.schedulers.k;
import io.reactivex.q;
import io.reactivex.v;
import io.reactivex.w;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableInterval extends q<Long> {
    final w a;
    final long b;
    final long c;
    final TimeUnit d;

    public ObservableInterval(long j, long j2, TimeUnit timeUnit, w wVar) {
        this.b = j;
        this.c = j2;
        this.d = timeUnit;
        this.a = wVar;
    }

    @Override // io.reactivex.q
    public void a(v<? super Long> vVar) {
        IntervalObserver intervalObserver = new IntervalObserver(vVar);
        vVar.onSubscribe(intervalObserver);
        w wVar = this.a;
        if (wVar instanceof k) {
            w.c a = wVar.a();
            intervalObserver.setResource(a);
            a.a(intervalObserver, this.b, this.c, this.d);
            return;
        }
        intervalObserver.setResource(wVar.a(intervalObserver, this.b, this.c, this.d));
    }

    static final class IntervalObserver extends AtomicReference<b> implements b, Runnable {
        private static final long serialVersionUID = 346773832286157679L;
        long count;
        final v<? super Long> downstream;

        IntervalObserver(v<? super Long> vVar) {
            this.downstream = vVar;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return get() == DisposableHelper.DISPOSED;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (get() != DisposableHelper.DISPOSED) {
                v<? super Long> vVar = this.downstream;
                long j = this.count;
                this.count = 1 + j;
                vVar.onNext(Long.valueOf(j));
            }
        }

        public void setResource(b bVar) {
            DisposableHelper.setOnce(this, bVar);
        }
    }
}
