package io.reactivex.internal.subscribers;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;
import org.a.d;

public final class SubscriberResourceWrapper<T> extends AtomicReference<b> implements b, j<T>, d {
    private static final long serialVersionUID = -8612022020200669122L;
    final c<? super T> downstream;
    final AtomicReference<d> upstream = new AtomicReference<>();

    public SubscriberResourceWrapper(c<? super T> cVar) {
        this.downstream = cVar;
    }

    @Override // io.reactivex.j
    public void onSubscribe(d dVar) {
        if (SubscriptionHelper.setOnce(this.upstream, dVar)) {
            this.downstream.onSubscribe(this);
        }
    }

    public void onNext(T t) {
        this.downstream.onNext(t);
    }

    public void onError(Throwable th) {
        DisposableHelper.dispose(this);
        this.downstream.onError(th);
    }

    public void onComplete() {
        DisposableHelper.dispose(this);
        this.downstream.onComplete();
    }

    public void request(long j) {
        if (SubscriptionHelper.validate(j)) {
            this.upstream.get().request(j);
        }
    }

    @Override // io.reactivex.disposables.b
    public void dispose() {
        SubscriptionHelper.cancel(this.upstream);
        DisposableHelper.dispose(this);
    }

    @Override // io.reactivex.disposables.b
    public boolean isDisposed() {
        return this.upstream.get() == SubscriptionHelper.CANCELLED;
    }

    public void cancel() {
        dispose();
    }

    public void setResource(b bVar) {
        DisposableHelper.set(this, bVar);
    }
}
