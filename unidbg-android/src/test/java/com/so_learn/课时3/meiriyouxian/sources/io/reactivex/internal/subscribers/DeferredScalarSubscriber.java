package io.reactivex.internal.subscribers;

import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import org.a.c;
import org.a.d;

public abstract class DeferredScalarSubscriber<T, R> extends DeferredScalarSubscription<R> implements j<T> {
    private static final long serialVersionUID = 2984505488220891551L;
    protected boolean hasValue;
    protected d upstream;

    public DeferredScalarSubscriber(c<? super R> cVar) {
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

    public void onError(Throwable th) {
        this.value = null;
        this.downstream.onError(th);
    }

    public void onComplete() {
        if (this.hasValue) {
            complete(this.value);
        } else {
            this.downstream.onComplete();
        }
    }

    @Override // io.reactivex.internal.subscriptions.DeferredScalarSubscription
    public void cancel() {
        super.cancel();
        this.upstream.cancel();
    }
}
