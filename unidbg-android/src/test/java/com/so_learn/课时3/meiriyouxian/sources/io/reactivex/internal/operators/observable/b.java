package io.reactivex.internal.operators.observable;

import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.a;
import io.reactivex.q;
import io.reactivex.t;
import io.reactivex.v;
import java.util.concurrent.Callable;

/* compiled from: ObservableDefer */
public final class b<T> extends q<T> {
    final Callable<? extends t<? extends T>> a;

    public b(Callable<? extends t<? extends T>> callable) {
        this.a = callable;
    }

    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        try {
            ((t) a.a(this.a.call(), "null ObservableSource supplied")).subscribe(vVar);
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            EmptyDisposable.error(th, vVar);
        }
    }
}
