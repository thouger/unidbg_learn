package io.reactivex.internal.operators.flowable;

import io.reactivex.c.k;
import io.reactivex.exceptions.a;
import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import org.a.c;
import org.a.d;

public final class FlowableAll<T> extends a<T, Boolean> {
    final k<? super T> c;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super Boolean> cVar) {
        this.b.a((j) new AllSubscriber(cVar, this.c));
    }

    static final class AllSubscriber<T> extends DeferredScalarSubscription<Boolean> implements j<T> {
        private static final long serialVersionUID = -3521127104134758517L;
        boolean done;
        final k<? super T> predicate;
        d upstream;

        AllSubscriber(c<? super Boolean> cVar, k<? super T> kVar) {
            super(cVar);
            this.predicate = kVar;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.upstream, dVar)) {
                this.upstream = dVar;
                this.downstream.onSubscribe(this);
                dVar.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                try {
                    if (!this.predicate.test(t)) {
                        this.done = true;
                        this.upstream.cancel();
                        complete(false);
                    }
                } catch (Throwable th) {
                    a.b(th);
                    this.upstream.cancel();
                    onError(th);
                }
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                io.reactivex.e.a.a(th);
                return;
            }
            this.done = true;
            this.downstream.onError(th);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                complete(true);
            }
        }

        @Override // io.reactivex.internal.subscriptions.DeferredScalarSubscription
        public void cancel() {
            super.cancel();
            this.upstream.cancel();
        }
    }
}
