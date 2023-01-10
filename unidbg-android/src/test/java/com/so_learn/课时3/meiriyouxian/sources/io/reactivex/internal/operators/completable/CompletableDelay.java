package io.reactivex.internal.operators.completable;

import io.reactivex.a;
import io.reactivex.c;
import io.reactivex.disposables.b;
import io.reactivex.e;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.w;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public final class CompletableDelay extends a {
    final e a;
    final long b;
    final TimeUnit c;
    final w d;
    final boolean e;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.a
    public void b(c cVar) {
        this.a.a(new Delay(cVar, this.b, this.c, this.d, this.e));
    }

    static final class Delay extends AtomicReference<b> implements c, b, Runnable {
        private static final long serialVersionUID = 465972761105851022L;
        final long delay;
        final boolean delayError;
        final c downstream;
        Throwable error;
        final w scheduler;
        final TimeUnit unit;

        Delay(c cVar, long j, TimeUnit timeUnit, w wVar, boolean z) {
            this.downstream = cVar;
            this.delay = j;
            this.unit = timeUnit;
            this.scheduler = wVar;
            this.delayError = z;
        }

        @Override // io.reactivex.c
        public void onSubscribe(b bVar) {
            if (DisposableHelper.setOnce(this, bVar)) {
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.c
        public void onComplete() {
            DisposableHelper.replace(this, this.scheduler.a(this, this.delay, this.unit));
        }

        @Override // io.reactivex.c
        public void onError(Throwable th) {
            this.error = th;
            DisposableHelper.replace(this, this.scheduler.a(this, this.delayError ? this.delay : 0, this.unit));
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // java.lang.Runnable
        public void run() {
            Throwable th = this.error;
            this.error = null;
            if (th != null) {
                this.downstream.onError(th);
            } else {
                this.downstream.onComplete();
            }
        }
    }
}
