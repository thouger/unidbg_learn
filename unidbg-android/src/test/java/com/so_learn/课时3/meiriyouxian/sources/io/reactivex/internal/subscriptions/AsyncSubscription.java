package io.reactivex.internal.subscriptions;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.d;

public final class AsyncSubscription extends AtomicLong implements b, d {
    private static final long serialVersionUID = 7028635084060361255L;
    final AtomicReference<d> actual;
    final AtomicReference<b> resource;

    public AsyncSubscription() {
        this.resource = new AtomicReference<>();
        this.actual = new AtomicReference<>();
    }

    public AsyncSubscription(b bVar) {
        this();
        this.resource.lazySet(bVar);
    }

    public void request(long j) {
        SubscriptionHelper.deferredRequest(this.actual, this, j);
    }

    public void cancel() {
        dispose();
    }

    @Override // io.reactivex.disposables.b
    public void dispose() {
        SubscriptionHelper.cancel(this.actual);
        DisposableHelper.dispose(this.resource);
    }

    @Override // io.reactivex.disposables.b
    public boolean isDisposed() {
        return this.actual.get() == SubscriptionHelper.CANCELLED;
    }

    public boolean setResource(b bVar) {
        return DisposableHelper.set(this.resource, bVar);
    }

    public boolean replaceResource(b bVar) {
        return DisposableHelper.replace(this.resource, bVar);
    }

    public void setSubscription(d dVar) {
        SubscriptionHelper.deferredSetOnce(this.actual, this, dVar);
    }
}
