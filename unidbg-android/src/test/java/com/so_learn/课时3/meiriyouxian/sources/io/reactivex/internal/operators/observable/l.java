package io.reactivex.internal.operators.observable;

import io.reactivex.q;
import io.reactivex.t;
import io.reactivex.v;

/* compiled from: ObservableFromUnsafeSource */
public final class l<T> extends q<T> {
    final t<T> a;

    public l(t<T> tVar) {
        this.a = tVar;
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        this.a.subscribe(vVar);
    }
}
