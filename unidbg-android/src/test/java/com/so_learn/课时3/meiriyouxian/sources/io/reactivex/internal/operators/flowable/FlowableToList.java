package io.reactivex.internal.operators.flowable;

import io.reactivex.internal.functions.a;
import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import java.util.Collection;
import java.util.concurrent.Callable;
import org.a.c;
import org.a.d;

public final class FlowableToList<T, U extends Collection<? super T>> extends a<T, U> {
    final Callable<U> c;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super U> cVar) {
        try {
            this.b.a((j) new ToListSubscriber(cVar, (Collection) a.a(this.c.call(), "The collectionSupplier returned a null collection. Null values are generally not allowed in 2.x operators and sources.")));
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            EmptySubscription.error(th, cVar);
        }
    }

    static final class ToListSubscriber<T, U extends Collection<? super T>> extends DeferredScalarSubscription<U> implements j<T>, d {
        private static final long serialVersionUID = -8134157938864266736L;
        d upstream;

        ToListSubscriber(c<? super U> cVar, U u) {
            super(cVar);
            this.value = u;
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
            Collection collection = (Collection) this.value;
            if (collection != null) {
                collection.add(t);
            }
        }

        public void onError(Throwable th) {
            this.value = null;
            this.downstream.onError(th);
        }

        public void onComplete() {
            complete(this.value);
        }

        @Override // io.reactivex.internal.subscriptions.DeferredScalarSubscription
        public void cancel() {
            super.cancel();
            this.upstream.cancel();
        }
    }
}
