package io.reactivex.internal.operators.single;

import io.reactivex.ab;
import io.reactivex.c.h;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.x;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleFlatMap<T, R> extends x<R> {
    final ab<? extends T> a;
    final h<? super T, ? extends ab<? extends R>> b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.x
    public void b(z<? super R> zVar) {
        this.a.a(new SingleFlatMapCallback(zVar, this.b));
    }

    static final class SingleFlatMapCallback<T, R> extends AtomicReference<b> implements b, z<T> {
        private static final long serialVersionUID = 3258103020495908596L;
        final z<? super R> downstream;
        final h<? super T, ? extends ab<? extends R>> mapper;

        SingleFlatMapCallback(z<? super R> zVar, h<? super T, ? extends ab<? extends R>> hVar) {
            this.downstream = zVar;
            this.mapper = hVar;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // io.reactivex.z
        public void onSubscribe(b bVar) {
            if (DisposableHelper.setOnce(this, bVar)) {
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.z
        public void onSuccess(T t) {
            try {
                ab abVar = (ab) io.reactivex.internal.functions.a.a(this.mapper.apply(t), "The single returned by the mapper is null");
                if (!isDisposed()) {
                    abVar.a(new a(this, this.downstream));
                }
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                this.downstream.onError(th);
            }
        }

        @Override // io.reactivex.z
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        static final class a<R> implements z<R> {
            final AtomicReference<b> a;
            final z<? super R> b;

            a(AtomicReference<b> atomicReference, z<? super R> zVar) {
                this.a = atomicReference;
                this.b = zVar;
            }

            @Override // io.reactivex.z
            public void onSubscribe(b bVar) {
                DisposableHelper.replace(this.a, bVar);
            }

            @Override // io.reactivex.z
            public void onSuccess(R r) {
                this.b.onSuccess(r);
            }

            @Override // io.reactivex.z
            public void onError(Throwable th) {
                this.b.onError(th);
            }
        }
    }
}
