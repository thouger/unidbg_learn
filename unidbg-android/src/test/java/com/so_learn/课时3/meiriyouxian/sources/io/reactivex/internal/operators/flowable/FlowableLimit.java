package io.reactivex.internal.operators.flowable;

import io.reactivex.e.a;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicLong;
import org.a.c;
import org.a.d;

public final class FlowableLimit<T> extends a<T, T> {
    final long c;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        this.b.a((j) new LimitSubscriber(cVar, this.c));
    }

    static final class LimitSubscriber<T> extends AtomicLong implements j<T>, d {
        private static final long serialVersionUID = 2288246011222124525L;
        final c<? super T> downstream;
        long remaining;
        d upstream;

        LimitSubscriber(c<? super T> cVar, long j) {
            this.downstream = cVar;
            this.remaining = j;
            lazySet(j);
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (!SubscriptionHelper.validate(this.upstream, dVar)) {
                return;
            }
            if (this.remaining == 0) {
                dVar.cancel();
                EmptySubscription.complete(this.downstream);
                return;
            }
            this.upstream = dVar;
            this.downstream.onSubscribe(this);
        }

        public void onNext(T t) {
            long j = this.remaining;
            if (j > 0) {
                long j2 = j - 1;
                this.remaining = j2;
                this.downstream.onNext(t);
                if (j2 == 0) {
                    this.upstream.cancel();
                    this.downstream.onComplete();
                }
            }
        }

        public void onError(Throwable th) {
            if (this.remaining > 0) {
                this.remaining = 0;
                this.downstream.onError(th);
                return;
            }
            a.a(th);
        }

        public void onComplete() {
            if (this.remaining > 0) {
                this.remaining = 0;
                this.downstream.onComplete();
            }
        }

        public void request(long j) {
            long j2;
            long j3;
            if (SubscriptionHelper.validate(j)) {
                do {
                    j2 = get();
                    if (j2 != 0) {
                        j3 = j2 <= j ? j2 : j;
                    } else {
                        return;
                    }
                } while (!compareAndSet(j2, j2 - j3));
                this.upstream.request(j3);
            }
        }

        public void cancel() {
            this.upstream.cancel();
        }
    }
}
