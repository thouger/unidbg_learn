package io.reactivex.internal.operators.flowable;

import io.reactivex.g;
import io.reactivex.internal.a.f;
import io.reactivex.internal.subscriptions.EmptySubscription;

/* compiled from: FlowableEmpty */
public final class c extends g<Object> implements f<Object> {
    public static final g<Object> b = new c();

    @Override // io.reactivex.internal.a.f, java.util.concurrent.Callable
    public Object call() {
        return null;
    }

    private c() {
    }

    @Override // io.reactivex.g
    public void a(org.a.c<? super Object> cVar) {
        EmptySubscription.complete(cVar);
    }
}
