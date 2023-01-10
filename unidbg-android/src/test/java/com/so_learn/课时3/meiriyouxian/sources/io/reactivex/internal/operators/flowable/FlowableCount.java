package io.reactivex.internal.operators.flowable;

import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import org.a.c;
import org.a.d;

public final class FlowableCount<T> extends a<T, Long> {
    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super Long> cVar) {
        this.b.a((j) new CountSubscriber(cVar));
    }

    static final class CountSubscriber extends DeferredScalarSubscription<Long> implements j<Object> {
        private static final long serialVersionUID = 4973004223787171406L;
        long count;
        d upstream;

        CountSubscriber(c<? super Long> cVar) {
            super(cVar);
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.upstream, dVar)) {
                this.upstream = dVar;
                this.downstream.onSubscribe(this);
                dVar.request(Long.MAX_VALUE);
            }
        }

        public void onNext(Object obj) {
            this.count++;
        }

        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: io.reactivex.internal.operators.flowable.FlowableCount$CountSubscriber */
        /* JADX WARN: Multi-variable type inference failed */
        public void onComplete() {
            complete(Long.valueOf(this.count));
        }

        @Override // io.reactivex.internal.subscriptions.DeferredScalarSubscription
        public void cancel() {
            super.cancel();
            this.upstream.cancel();
        }
    }
}
