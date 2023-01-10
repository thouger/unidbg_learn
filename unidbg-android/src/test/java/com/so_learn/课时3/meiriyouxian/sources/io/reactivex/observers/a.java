package io.reactivex.observers;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.d;
import io.reactivex.v;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: DisposableObserver */
public abstract class a<T> implements b, v<T> {
    final AtomicReference<b> d = new AtomicReference<>();

    /* access modifiers changed from: protected */
    public void a() {
    }

    @Override // io.reactivex.v
    public final void onSubscribe(b bVar) {
        if (d.a(this.d, bVar, getClass())) {
            a();
        }
    }

    @Override // io.reactivex.disposables.b
    public final boolean isDisposed() {
        return this.d.get() == DisposableHelper.DISPOSED;
    }

    @Override // io.reactivex.disposables.b
    public final void dispose() {
        DisposableHelper.dispose(this.d);
    }
}
