package io.reactivex.internal.operators.flowable;

import io.reactivex.internal.a.a;
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

public final class FlowableSkipUntil<T, U> extends a<T, T> {
    final b<U> c;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        SkipUntilMainSubscriber skipUntilMainSubscriber = new SkipUntilMainSubscriber(cVar);
        cVar.onSubscribe(skipUntilMainSubscriber);
        this.c.subscribe(skipUntilMainSubscriber.other);
        this.b.a((j) skipUntilMainSubscriber);
    }

    static final class SkipUntilMainSubscriber<T> extends AtomicInteger implements a<T>, d {
        private static final long serialVersionUID = -6270983465606289181L;
        final c<? super T> downstream;
        final AtomicThrowable error = new AtomicThrowable();
        volatile boolean gate;
        final SkipUntilMainSubscriber<T>.OtherSubscriber other = new OtherSubscriber();
        final AtomicLong requested = new AtomicLong();
        final AtomicReference<d> upstream = new AtomicReference<>();

        SkipUntilMainSubscriber(c<? super T> cVar) {
            this.downstream = cVar;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            SubscriptionHelper.deferredSetOnce(this.upstream, this.requested, dVar);
        }

        public void onNext(T t) {
            if (!tryOnNext(t)) {
                this.upstream.get().request(1);
            }
        }

        @Override // io.reactivex.internal.a.a
        public boolean tryOnNext(T t) {
            if (!this.gate) {
                return false;
            }
            e.a((c) this.downstream, (Object) t, (AtomicInteger) this, this.error);
            return true;
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
            private static final long serialVersionUID = -5592042965931999169L;

            OtherSubscriber() {
            }

            @Override // io.reactivex.j
            public void onSubscribe(d dVar) {
                SubscriptionHelper.setOnce(this, dVar, Long.MAX_VALUE);
            }

            public void onNext(Object obj) {
                SkipUntilMainSubscriber.this.gate = true;
                get().cancel();
            }

            public void onError(Throwable th) {
                SubscriptionHelper.cancel(SkipUntilMainSubscriber.this.upstream);
                c<? super T> cVar = SkipUntilMainSubscriber.this.downstream;
                SkipUntilMainSubscriber skipUntilMainSubscriber = SkipUntilMainSubscriber.this;
                e.a((c<?>) cVar, th, (AtomicInteger) skipUntilMainSubscriber, skipUntilMainSubscriber.error);
            }

            public void onComplete() {
                SkipUntilMainSubscriber.this.gate = true;
            }
        }
    }
}
