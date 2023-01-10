package io.reactivex.internal.operators.flowable;

import io.reactivex.g;
import io.reactivex.internal.a.f;
import io.reactivex.internal.subscriptions.ScalarSubscription;
import org.a.c;

/* compiled from: FlowableJust */
public final class e<T> extends g<T> implements f<T> {
    private final T b;

    public e(T t) {
        this.b = t;
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        cVar.onSubscribe(new ScalarSubscription(cVar, this.b));
    }

    @Override // io.reactivex.internal.a.f, java.util.concurrent.Callable
    public T call() {
        return this.b;
    }
}
