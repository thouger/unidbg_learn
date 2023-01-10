package io.reactivex.internal.operators.flowable;

import io.reactivex.disposables.b;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.w;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;
import org.a.d;

public final class FlowableTimer extends g<Long> {
    final w b;
    final long c;
    final TimeUnit d;

    @Override // io.reactivex.g
    public void a(c<? super Long> cVar) {
        TimerSubscriber timerSubscriber = new TimerSubscriber(cVar);
        cVar.onSubscribe(timerSubscriber);
        timerSubscriber.setResource(this.b.a(timerSubscriber, this.c, this.d));
    }

    static final class TimerSubscriber extends AtomicReference<b> implements Runnable, d {
        private static final long serialVersionUID = -2809475196591179431L;
        final c<? super Long> downstream;
        volatile boolean requested;

        TimerSubscriber(c<? super Long> cVar) {
            this.downstream = cVar;
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                this.requested = true;
            }
        }

        public void cancel() {
            DisposableHelper.dispose(this);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (get() == DisposableHelper.DISPOSED) {
                return;
            }
            if (this.requested) {
                this.downstream.onNext(0L);
                lazySet(EmptyDisposable.INSTANCE);
                this.downstream.onComplete();
                return;
            }
            lazySet(EmptyDisposable.INSTANCE);
            this.downstream.onError(new MissingBackpressureException("Can't deliver value due to lack of requests"));
        }

        public void setResource(b bVar) {
            DisposableHelper.trySet(this, bVar);
        }
    }
}
