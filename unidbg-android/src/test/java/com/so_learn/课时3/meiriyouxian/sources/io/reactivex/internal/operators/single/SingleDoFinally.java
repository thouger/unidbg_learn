package io.reactivex.internal.operators.single;

import io.reactivex.ab;
import io.reactivex.c.a;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.x;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicInteger;

public final class SingleDoFinally<T> extends x<T> {
    final ab<T> a;
    final a b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.x
    public void b(z<? super T> zVar) {
        this.a.a(new DoFinallyObserver(zVar, this.b));
    }

    static final class DoFinallyObserver<T> extends AtomicInteger implements b, z<T> {
        private static final long serialVersionUID = 4109457741734051389L;
        final z<? super T> downstream;
        final a onFinally;
        b upstream;

        DoFinallyObserver(z<? super T> zVar, a aVar) {
            this.downstream = zVar;
            this.onFinally = aVar;
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
            runFinally();
        }

        @Override // io.reactivex.z
        public void onError(Throwable th) {
            this.downstream.onError(th);
            runFinally();
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.upstream.dispose();
            runFinally();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        /* access modifiers changed from: package-private */
        public void runFinally() {
            if (compareAndSet(0, 1)) {
                try {
                    this.onFinally.a();
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    io.reactivex.e.a.a(th);
                }
            }
        }
    }
}
