package io.reactivex.internal.operators.flowable;

import io.reactivex.e.a;
import io.reactivex.g;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.b;
import org.a.c;
import org.a.d;

public final class FlowableDelaySubscriptionOther<T, U> extends g<T> {
    final b<? extends T> b;
    final b<U> c;

    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        MainSubscriber mainSubscriber = new MainSubscriber(cVar, this.b);
        cVar.onSubscribe(mainSubscriber);
        this.c.subscribe(mainSubscriber.other);
    }

    static final class MainSubscriber<T> extends AtomicLong implements j<T>, d {
        private static final long serialVersionUID = 2259811067697317255L;
        final c<? super T> downstream;
        final b<? extends T> main;
        final MainSubscriber<T>.OtherSubscriber other = new OtherSubscriber();
        final AtomicReference<d> upstream = new AtomicReference<>();

        MainSubscriber(c<? super T> cVar, b<? extends T> bVar) {
            this.downstream = cVar;
            this.main = bVar;
        }

        /* access modifiers changed from: package-private */
        public void next() {
            this.main.subscribe(this);
        }

        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        public void onComplete() {
            this.downstream.onComplete();
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                SubscriptionHelper.deferredRequest(this.upstream, this, j);
            }
        }

        public void cancel() {
            SubscriptionHelper.cancel(this.other);
            SubscriptionHelper.cancel(this.upstream);
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            SubscriptionHelper.deferredSetOnce(this.upstream, this, dVar);
        }

        final class OtherSubscriber extends AtomicReference<d> implements j<Object> {
            private static final long serialVersionUID = -3892798459447644106L;

            OtherSubscriber() {
            }

            @Override // io.reactivex.j
            public void onSubscribe(d dVar) {
                if (SubscriptionHelper.setOnce(this, dVar)) {
                    dVar.request(Long.MAX_VALUE);
                }
            }

            public void onNext(Object obj) {
                SubscriptionHelper subscriptionHelper = (d) get();
                if (subscriptionHelper != SubscriptionHelper.CANCELLED) {
                    lazySet(SubscriptionHelper.CANCELLED);
                    subscriptionHelper.cancel();
                    MainSubscriber.this.next();
                }
            }

            public void onError(Throwable th) {
                if (((d) get()) != SubscriptionHelper.CANCELLED) {
                    MainSubscriber.this.downstream.onError(th);
                } else {
                    a.a(th);
                }
            }

            public void onComplete() {
                if (((d) get()) != SubscriptionHelper.CANCELLED) {
                    MainSubscriber.this.next();
                }
            }
        }
    }
}
