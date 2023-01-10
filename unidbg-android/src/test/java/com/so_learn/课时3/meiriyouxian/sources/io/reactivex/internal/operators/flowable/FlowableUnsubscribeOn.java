package io.reactivex.internal.operators.flowable;

import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import io.reactivex.w;
import java.util.concurrent.atomic.AtomicBoolean;
import org.a.c;
import org.a.d;

public final class FlowableUnsubscribeOn<T> extends a<T, T> {
    final w c;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        this.b.a((j) new UnsubscribeSubscriber(cVar, this.c));
    }

    static final class UnsubscribeSubscriber<T> extends AtomicBoolean implements j<T>, d {
        private static final long serialVersionUID = 1015244841293359600L;
        final c<? super T> downstream;
        final w scheduler;
        d upstream;

        UnsubscribeSubscriber(c<? super T> cVar, w wVar) {
            this.downstream = cVar;
            this.scheduler = wVar;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.upstream, dVar)) {
                this.upstream = dVar;
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!get()) {
                this.downstream.onNext(t);
            }
        }

        public void onError(Throwable th) {
            if (get()) {
                io.reactivex.e.a.a(th);
            } else {
                this.downstream.onError(th);
            }
        }

        public void onComplete() {
            if (!get()) {
                this.downstream.onComplete();
            }
        }

        public void request(long j) {
            this.upstream.request(j);
        }

        public void cancel() {
            if (compareAndSet(false, true)) {
                this.scheduler.a(new a());
            }
        }

        final class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                UnsubscribeSubscriber.this.upstream.cancel();
            }
        }
    }
}
