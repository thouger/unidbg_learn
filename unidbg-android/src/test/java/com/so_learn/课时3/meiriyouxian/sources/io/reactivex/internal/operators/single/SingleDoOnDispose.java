package io.reactivex.internal.operators.single;

import io.reactivex.ab;
import io.reactivex.c.a;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.x;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleDoOnDispose<T> extends x<T> {
    final ab<T> a;
    final a b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.x
    public void b(z<? super T> zVar) {
        this.a.a(new DoOnDisposeObserver(zVar, this.b));
    }

    static final class DoOnDisposeObserver<T> extends AtomicReference<a> implements b, z<T> {
        private static final long serialVersionUID = -8583764624474935784L;
        final z<? super T> downstream;
        b upstream;

        DoOnDisposeObserver(z<? super T> zVar, a aVar) {
            this.downstream = zVar;
            lazySet(aVar);
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            a andSet = getAndSet(null);
            if (andSet != null) {
                try {
                    andSet.a();
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    io.reactivex.e.a.a(th);
                }
                this.upstream.dispose();
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        @Override // io.reactivex.z
        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.z
        public void onSuccess(T t) {
            this.downstream.onSuccess(t);
        }

        @Override // io.reactivex.z
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }
    }
}
