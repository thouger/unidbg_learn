package io.reactivex.internal.operators.flowable;

import io.reactivex.c.b;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import java.util.concurrent.Callable;
import org.a.c;
import org.a.d;

public final class FlowableCollect<T, U> extends a<T, U> {
    final Callable<? extends U> c;
    final b<? super U, ? super T> d;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super U> cVar) {
        try {
            this.b.a((j) new CollectSubscriber(cVar, a.a(this.c.call(), "The initial value supplied is null"), this.d));
        } catch (Throwable th) {
            EmptySubscription.error(th, cVar);
        }
    }

    static final class CollectSubscriber<T, U> extends DeferredScalarSubscription<U> implements j<T> {
        private static final long serialVersionUID = -3589550218733891694L;
        final b<? super U, ? super T> collector;
        boolean done;
        final U u;
        d upstream;

        CollectSubscriber(c<? super U> cVar, U u, b<? super U, ? super T> bVar) {
            super(cVar);
            this.collector = bVar;
            this.u = u;
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
                    this.collector.a(this.u, t);
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
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
                complete(this.u);
            }
        }

        @Override // io.reactivex.internal.subscriptions.DeferredScalarSubscription
        public void cancel() {
            super.cancel();
            this.upstream.cancel();
        }
    }
}
