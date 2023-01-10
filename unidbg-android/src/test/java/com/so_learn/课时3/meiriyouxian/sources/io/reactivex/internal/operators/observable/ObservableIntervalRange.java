package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.schedulers.k;
import io.reactivex.q;
import io.reactivex.v;
import io.reactivex.w;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableIntervalRange extends q<Long> {
    final w a;
    final long b;
    final long c;
    final long d;
    final long e;
    final TimeUnit f;

    @Override // io.reactivex.q
    public void a(v<? super Long> vVar) {
        IntervalRangeObserver intervalRangeObserver = new IntervalRangeObserver(vVar, this.b, this.c);
        vVar.onSubscribe(intervalRangeObserver);
        w wVar = this.a;
        if (wVar instanceof k) {
            w.c a = wVar.a();
            intervalRangeObserver.setResource(a);
            a.a(intervalRangeObserver, this.d, this.e, this.f);
            return;
        }
        intervalRangeObserver.setResource(wVar.a(intervalRangeObserver, this.d, this.e, this.f));
    }

    static final class IntervalRangeObserver extends AtomicReference<b> implements b, Runnable {
        private static final long serialVersionUID = 1891866368734007884L;
        long count;
        final v<? super Long> downstream;
        final long end;

        IntervalRangeObserver(v<? super Long> vVar, long j, long j2) {
            this.downstream = vVar;
            this.count = j;
            this.end = j2;
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
            if (!isDisposed()) {
                long j = this.count;
                this.downstream.onNext(Long.valueOf(j));
                if (j == this.end) {
                    DisposableHelper.dispose(this);
                    this.downstream.onComplete();
                    return;
                }
                this.count = j + 1;
            }
        }

        public void setResource(b bVar) {
            DisposableHelper.setOnce(this, bVar);
        }
    }
}
