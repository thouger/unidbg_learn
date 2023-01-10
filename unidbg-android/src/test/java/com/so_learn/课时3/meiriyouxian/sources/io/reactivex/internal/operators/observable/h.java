package io.reactivex.internal.operators.observable;

import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.a;
import io.reactivex.q;
import io.reactivex.v;
import java.util.concurrent.Callable;

/* compiled from: ObservableError */
public final class h<T> extends q<T> {
    final Callable<? extends Throwable> a;

    public h(Callable<? extends Throwable> callable) {
        this.a = callable;
    }

    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        Throwable th;
        try {
            th = (Throwable) a.a(this.a.call(), "Callable returned null throwable. Null values are generally not allowed in 2.x operators and sources.");
        } catch (Throwable th2) {
            th = th2;
            io.reactivex.exceptions.a.b(th);
        }
        EmptyDisposable.error(th, vVar);
    }
}
