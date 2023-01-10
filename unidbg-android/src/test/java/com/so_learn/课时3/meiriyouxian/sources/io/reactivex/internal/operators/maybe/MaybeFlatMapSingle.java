package io.reactivex.internal.operators.maybe;

import io.reactivex.ab;
import io.reactivex.c.h;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.m;
import io.reactivex.o;
import io.reactivex.x;
import io.reactivex.z;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeFlatMapSingle<T, R> extends x<R> {
    final o<T> a;
    final h<? super T, ? extends ab<? extends R>> b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.x
    public void b(z<? super R> zVar) {
        this.a.a(new FlatMapMaybeObserver(zVar, this.b));
    }

    static final class FlatMapMaybeObserver<T, R> extends AtomicReference<b> implements b, m<T> {
        private static final long serialVersionUID = 4827726964688405508L;
        final z<? super R> downstream;
        final h<? super T, ? extends ab<? extends R>> mapper;

        FlatMapMaybeObserver(z<? super R> zVar, h<? super T, ? extends ab<? extends R>> hVar) {
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

        @Override // io.reactivex.m
        public void onSubscribe(b bVar) {
            if (DisposableHelper.setOnce(this, bVar)) {
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.m
        public void onSuccess(T t) {
            try {
                ab abVar = (ab) io.reactivex.internal.functions.a.a(this.mapper.apply(t), "The mapper returned a null SingleSource");
                if (!isDisposed()) {
                    abVar.a(new a(this, this.downstream));
                }
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                onError(th);
            }
        }

        @Override // io.reactivex.m
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // io.reactivex.m
        public void onComplete() {
            this.downstream.onError(new NoSuchElementException());
        }
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
