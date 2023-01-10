package io.reactivex.internal.operators.maybe;

import io.reactivex.disposables.c;
import io.reactivex.internal.a.f;
import io.reactivex.k;
import io.reactivex.m;

/* compiled from: MaybeJust */
public final class b<T> extends k<T> implements f<T> {
    final T a;

    public b(T t) {
        this.a = t;
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.k
    public void b(m<? super T> mVar) {
        mVar.onSubscribe(c.a());
        mVar.onSuccess(this.a);
    }

    @Override // io.reactivex.internal.a.f, java.util.concurrent.Callable
    public T call() {
        return this.a;
    }
}
