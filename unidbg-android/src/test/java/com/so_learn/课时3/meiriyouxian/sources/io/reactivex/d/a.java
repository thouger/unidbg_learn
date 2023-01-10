package io.reactivex.d;

import io.reactivex.c.g;
import io.reactivex.disposables.b;
import io.reactivex.internal.operators.observable.ObservablePublishAlt;
import io.reactivex.internal.operators.observable.ObservableRefCount;
import io.reactivex.internal.operators.observable.s;
import io.reactivex.q;

/* compiled from: ConnectableObservable */
public abstract class a<T> extends q<T> {
    public abstract void f(g<? super b> gVar);

    private a<T> a() {
        return this instanceof s ? io.reactivex.e.a.a((a) new ObservablePublishAlt(((s) this).a())) : this;
    }

    public q<T> b() {
        return io.reactivex.e.a.a(new ObservableRefCount(a()));
    }
}
