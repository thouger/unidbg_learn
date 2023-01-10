package io.reactivex.internal.operators.flowable;

import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.e;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.b;
import org.a.c;
import org.a.d;

public final class FlowableTakeUntil<T, U> extends a<T, T> {
    final b<? extends U> c;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        TakeUntilMainSubscriber takeUntilMainSubscriber = new TakeUntilMainSubscriber(cVar);
        cVar.onSubscribe(takeUntilMainSubscriber);
        this.c.subscribe(takeUntilMainSubscriber.other);
        this.b.a((j) takeUntilMainSubscriber);
    }

    /* access modifiers changed from: package-private */
    public static final class TakeUntilMainSubscriber<T> extends AtomicInteger implements j<T>, d {
        private static final long serialVersionUID = -4945480365982832967L;
        final c<? super T> downstream;
        final AtomicThrowable error = new AtomicThrowable();
        final TakeUntilMainSubscriber<T>.OtherSubscriber other = new OtherSubscriber();
        final AtomicLong requested = new AtomicLong();
        final AtomicReference<d> upstream = new AtomicReference<>();

        TakeUntilMainSubscriber(c<? super T> cVar) {
            this.downstream = cVar;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            SubscriptionHelper.deferredSetOnce(this.upstream, this.requested, dVar);
        }

        public void onNext(T t) {
            e.a((c) this.downstream, (Object) t, (AtomicInteger) this, this.error);
        }

        public void onError(Throwable th) {
            SubscriptionHelper.cancel(this.other);
            e.a((c<?>) this.downstream, th, (AtomicInteger) this, this.error);
        }

        public void onComplete() {
            SubscriptionHelper.cancel(this.other);
            e.a(this.downstream, this, this.error);
        }

        public void request(long j) {
            SubscriptionHelper.deferredRequest(this.upstream, this.requested, j);
        }

        public void cancel() {
            SubscriptionHelper.cancel(this.upstream);
            SubscriptionHelper.cancel(this.other);
        }

        final class OtherSubscriber extends AtomicReference<d> implements j<Object> {
            private static final long serialVersionUID = -3592821756711087922L;

            OtherSubscriber() {
            }

            @Override // io.reactivex.j
            public void onSubscribe(d dVar) {
                SubscriptionHelper.setOnce(this, dVar, Long.MAX_VALUE);
            }

            public void onNext(Object obj) {
                SubscriptionHelper.cancel(this);
                onComplete();
            }

            public void onError(Throwable th) {
                SubscriptionHelper.cancel(TakeUntilMainSubscriber.this.upstream);
                c<? super T> cVar = TakeUntilMainSubscriber.this.downstream;
                TakeUntilMainSubscriber takeUntilMainSubscriber = TakeUntilMainSubscriber.this;
                e.a((c<?>) cVar, th, (AtomicInteger) takeUntilMainSubscriber, takeUntilMainSubscriber.error);
            }

            public void onComplete() {
                SubscriptionHelper.cancel(TakeUntilMainSubscriber.this.upstream);
                c<? super T> cVar = TakeUntilMainSubscriber.this.downstream;
                TakeUntilMainSubscriber takeUntilMainSubscriber = TakeUntilMainSubscriber.this;
                e.a(cVar, takeUntilMainSubscriber, takeUntilMainSubscriber.error);
            }
        }
    }
}
