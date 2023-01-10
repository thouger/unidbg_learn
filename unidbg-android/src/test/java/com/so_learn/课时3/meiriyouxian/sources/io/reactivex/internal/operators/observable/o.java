package io.reactivex.internal.operators.observable;

import io.reactivex.internal.a.f;
import io.reactivex.internal.operators.observable.ObservableScalarXMap;
import io.reactivex.q;
import io.reactivex.v;

/* compiled from: ObservableJust */
public final class o<T> extends q<T> implements f<T> {
    private final T a;

    public o(T t) {
        this.a = t;
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        ObservableScalarXMap.ScalarDisposable scalarDisposable = new ObservableScalarXMap.ScalarDisposable(vVar, this.a);
        vVar.onSubscribe(scalarDisposable);
        scalarDisposable.run();
    }

    @Override // io.reactivex.internal.a.f, java.util.concurrent.Callable
    public T call() {
        return this.a;
    }
}
