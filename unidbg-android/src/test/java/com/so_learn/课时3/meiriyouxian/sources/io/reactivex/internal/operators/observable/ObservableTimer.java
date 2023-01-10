package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.q;
import io.reactivex.v;
import io.reactivex.w;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableTimer extends q<Long> {
    final w a;
    final long b;
    final TimeUnit c;

    public ObservableTimer(long j, TimeUnit timeUnit, w wVar) {
        this.b = j;
        this.c = timeUnit;
        this.a = wVar;
    }

    @Override // io.reactivex.q
    public void a(v<? super Long> vVar) {
        TimerObserver timerObserver = new TimerObserver(vVar);
        vVar.onSubscribe(timerObserver);
        timerObserver.setResource(this.a.a(timerObserver, this.b, this.c));
    }

    static final class TimerObserver extends AtomicReference<b> implements b, Runnable {
        private static final long serialVersionUID = -2809475196591179431L;
        final v<? super Long> downstream;

        TimerObserver(v<? super Long> vVar) {
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
            if (!isDisposed()) {
                this.downstream.onNext(0L);
                lazySet(EmptyDisposable.INSTANCE);
                this.downstream.onComplete();
            }
        }

        public void setResource(b bVar) {
            DisposableHelper.trySet(this, bVar);
        }
    }
}
