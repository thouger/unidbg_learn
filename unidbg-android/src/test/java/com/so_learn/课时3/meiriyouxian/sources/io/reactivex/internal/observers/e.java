package io.reactivex.internal.observers;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: ResumeSingleObserver */
public final class e<T> implements z<T> {
    final AtomicReference<b> a;
    final z<? super T> b;

    public e(AtomicReference<b> atomicReference, z<? super T> zVar) {
        this.a = atomicReference;
        this.b = zVar;
    }

    @Override // io.reactivex.z
    public void onSubscribe(b bVar) {
        DisposableHelper.replace(this.a, bVar);
    }

    @Override // io.reactivex.z
    public void onSuccess(T t) {
        this.b.onSuccess(t);
    }

    @Override // io.reactivex.z
    public void onError(Throwable th) {
        this.b.onError(th);
    }
}
