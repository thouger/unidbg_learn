package io.reactivex.internal.operators.flowable;

import io.reactivex.c.c;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import org.a.d;

public final class FlowableReduce<T> extends a<T, T> {
    final c<T, T, T> c;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(org.a.c<? super T> cVar) {
        this.b.a((j) new ReduceSubscriber(cVar, this.c));
    }

    static final class ReduceSubscriber<T> extends DeferredScalarSubscription<T> implements j<T> {
        private static final long serialVersionUID = -4663883003264602070L;
        final c<T, T, T> reducer;
        d upstream;

        ReduceSubscriber(org.a.c<? super T> cVar, c<T, T, T> cVar2) {
            super(cVar);
            this.reducer = cVar2;
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
            if (this.upstream != SubscriptionHelper.CANCELLED) {
                Object obj = this.value;
                if (obj == null) {
                    this.value = t;
                    return;
                }
                try {
                    this.value = a.a(this.reducer.apply(obj, t), "The reducer returned a null value");
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    this.upstream.cancel();
                    onError(th);
                }
            }
        }

        public void onError(Throwable th) {
            if (this.upstream == SubscriptionHelper.CANCELLED) {
                io.reactivex.e.a.a(th);
                return;
            }
            this.upstream = SubscriptionHelper.CANCELLED;
            this.downstream.onError(th);
        }

        public void onComplete() {
            if (this.upstream != SubscriptionHelper.CANCELLED) {
                this.upstream = SubscriptionHelper.CANCELLED;
                Object obj = this.value;
                if (obj != null) {
                    complete(obj);
                } else {
                    this.downstream.onComplete();
                }
            }
        }

        @Override // io.reactivex.internal.subscriptions.DeferredScalarSubscription
        public void cancel() {
            super.cancel();
            this.upstream.cancel();
            this.upstream = SubscriptionHelper.CANCELLED;
        }
    }
}
