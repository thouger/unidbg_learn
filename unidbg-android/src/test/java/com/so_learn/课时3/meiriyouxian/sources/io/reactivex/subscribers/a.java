package io.reactivex.subscribers;

import io.reactivex.disposables.b;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicReference;
import org.a.d;

/* compiled from: DisposableSubscriber */
public abstract class a<T> implements b, j<T> {
    final AtomicReference<d> f = new AtomicReference<>();

    @Override // io.reactivex.j
    public final void onSubscribe(d dVar) {
        if (io.reactivex.internal.util.d.a(this.f, dVar, getClass())) {
            b();
        }
    }

    /* access modifiers changed from: protected */
    public void b() {
        this.f.get().request(Long.MAX_VALUE);
    }

    /* access modifiers changed from: protected */
    public final void c() {
        dispose();
    }

    @Override // io.reactivex.disposables.b
    public final boolean isDisposed() {
        return this.f.get() == SubscriptionHelper.CANCELLED;
    }

    @Override // io.reactivex.disposables.b
    public final void dispose() {
        SubscriptionHelper.cancel(this.f);
    }
}
