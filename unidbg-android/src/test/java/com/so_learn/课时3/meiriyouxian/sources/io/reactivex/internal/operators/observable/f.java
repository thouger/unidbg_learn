package io.reactivex.internal.operators.observable;

import io.reactivex.c.a;
import io.reactivex.c.g;
import io.reactivex.disposables.b;
import io.reactivex.internal.observers.c;
import io.reactivex.q;
import io.reactivex.v;

/* compiled from: ObservableDoOnLifecycle */
public final class f<T> extends a<T, T> {
    private final g<? super b> b;
    private final a c;

    public f(q<T> qVar, g<? super b> gVar, a aVar) {
        super(qVar);
        this.b = gVar;
        this.c = aVar;
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        this.a.subscribe(new c(vVar, this.b, this.c));
    }
}
