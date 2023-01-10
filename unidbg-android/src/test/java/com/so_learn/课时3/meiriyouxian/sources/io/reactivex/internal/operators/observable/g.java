package io.reactivex.internal.operators.observable;

import io.reactivex.internal.a.f;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.q;
import io.reactivex.v;

/* compiled from: ObservableEmpty */
public final class g extends q<Object> implements f<Object> {
    public static final q<Object> a = new g();

    @Override // io.reactivex.internal.a.f, java.util.concurrent.Callable
    public Object call() {
        return null;
    }

    private g() {
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.q
    public void a(v<? super Object> vVar) {
        EmptyDisposable.complete(vVar);
    }
}
